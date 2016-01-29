package checkpoint.andela.parser;

import checkpoint.andela.buffer.DataAndLogBuffer;

import java.util.HashMap;

/**
 * Created by Spykins on 28/01/2016.
 */
public class FileParser implements Runnable {
  ReadData readData;
  DataAndLogBuffer dataAndLogBuffer;

  public FileParser(String filePath){
    readData = new ReadData(filePath);
    dataAndLogBuffer = dataAndLogBuffer.getInstance();
  }

  public HashMap<String,String> fetchDataFromFile(){
    return readData.getData();
  }

  @Override
  public void run() {
    initializeFileParser();
  }

  private void initializeFileParser(){
    HashMap<String,String> dataFromFile;
    dataAndLogBuffer.addItemToDataBuffer(readData.getData());
    while (true){
      dataFromFile = readData.getData();
      if(dataFromFile.isEmpty()){
        dataAndLogBuffer.setDataAvailable(false);
        break;
      }
      dataAndLogBuffer.addItemToDataBuffer(dataFromFile);
    }
  }
}
