package checkpoint.andela.parser;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Spykins on 28/01/2016.
 */
public class ReadData {
  BufferedReader bufferedReader;
  String lineFromFile;
  FileReader fileReader;

  public ReadData(String pathToFile){
    try {
      fileReader = new FileReader(pathToFile);
      bufferedReader = new BufferedReader(fileReader);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }


  public HashMap<String,String> getData(){
    HashMap<String, String> newDataToAdd = new HashMap<String, String>();

    while(true) {
      try {
        lineFromFile = bufferedReader.readLine();
        if (lineFromFile == null || isTheEndOfData()) {
          break;
        }
        //checking for comment lin that starts with /
        if (getSplittedString(lineFromFile).length == 2) {
          if (newDataToAdd.containsKey(getSplittedString(lineFromFile)[0])) {
            String newValue = newDataToAdd.get(getSplittedString(lineFromFile)[0]) + "::" + getSplittedString(lineFromFile)[1];
            newDataToAdd.put(getSplittedString(lineFromFile)[0], newValue);

          } else {
            newDataToAdd.put(getSplittedString(lineFromFile)[0], getSplittedString(lineFromFile)[1]);
          }
        } else {
          if (!isTheEndOfData() && newDataToAdd.containsKey("COMMENT-INTERNAL")) {
            String oldValue = newDataToAdd.get("COMMENT-INTERNAL");
            newDataToAdd.put("COMMENT-INTERNAL", oldValue + lineFromFile);
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return  newDataToAdd;
  }


  private String[] getSplittedString(String presentLineRead){
    return presentLineRead.split(" - ", 2);
  }

  private boolean isTheBeginingOfData() {
    return lineFromFile.charAt(0) != '#';
  }

  private boolean isTheEndOfData(){
    return lineFromFile.equals("//");
  }


}
