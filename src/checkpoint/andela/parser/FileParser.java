package checkpoint.andela.parser;

import checkpoint.andela.buffer.DataAndLogBuffer;

import java.util.HashMap;

/**
 * Created by Spykins on 28/01/2016.
 */
public class FileParser implements Runnable {
  private DataReader readData;
  private DataAndLogBuffer dataAndLogBuffer;

  public FileParser(String filePath){
    readData = new DataReader(filePath);
    dataAndLogBuffer = dataAndLogBuffer.getInstance();
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
