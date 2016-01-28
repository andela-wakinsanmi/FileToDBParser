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


    return  newDataToAdd;
  }


}
