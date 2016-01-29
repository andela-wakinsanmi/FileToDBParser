package checkpoint.andela.parser;

import java.util.HashMap;

/**
 * Created by Spykins on 28/01/2016.
 */
public class FileParser {
  ReadData readData;

  public FileParser(String filePath){
    readData = new ReadData(filePath);
  }

  public HashMap<String,String> fetchDataFromFile(){
    return readData.getData();
  }
}
