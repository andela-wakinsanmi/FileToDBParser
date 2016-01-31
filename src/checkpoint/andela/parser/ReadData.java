package checkpoint.andela.parser;

import checkpoint.andela.model.FileDelimeters;

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

        if (getSplittedString(lineFromFile).length == 2) {
          addLineToNewDataMap(newDataToAdd, lineFromFile);

        } else {
          checkLineReadForComment(newDataToAdd, lineFromFile);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return  newDataToAdd;
  }

  private void addLineToNewDataMap(HashMap newDataToAdd, String lineFromFile){
    if (newDataToAdd.containsKey(getSplittedString(lineFromFile)[0])) {
      String newValue = newDataToAdd.get(getSplittedString(lineFromFile)[0]) +
          "::" + getSplittedString(lineFromFile)[1];
      newDataToAdd.put(getSplittedString(lineFromFile)[0], newValue);

    } else {
      newDataToAdd.put(getSplittedString(lineFromFile)[0], getSplittedString(lineFromFile)[1]);
    }
  }

  private void checkLineReadForComment(HashMap<String, String> newDataToAdd, String lineFromFile){
    if (!isTheEndOfData() && newDataToAdd.containsKey("COMMENT-INTERNAL")) {
      String oldValue = newDataToAdd.get("COMMENT-INTERNAL");
      newDataToAdd.put("COMMENT-INTERNAL", oldValue + lineFromFile);
    }
  }

  private String[] getSplittedString(String presentLineRead){
    return presentLineRead.split(FileDelimeters.KEY_VALUE_SEPARATOR.getRealName(), 2);
  }

  private boolean isTheEndOfData(){
    return lineFromFile.equals(FileDelimeters.END_OF_DATA.getRealName());
  }


}
