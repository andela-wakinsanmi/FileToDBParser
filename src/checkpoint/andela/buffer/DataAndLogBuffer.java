package checkpoint.andela.buffer;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Spykins on 28/01/2016.
 */
public class DataAndLogBuffer {
  private static ArrayBlockingQueue<String> bufferUsedToStoreLogInfo;
  private static ArrayBlockingQueue<HashMap<String, String>> bufferUsedToStoreData;
  private static DataAndLogBuffer dataAndLogBuffer = null;

  private DataAndLogBuffer(){
  }

  public static DataAndLogBuffer getInstance(){
    if(dataAndLogBuffer == null){
      dataAndLogBuffer = new DataAndLogBuffer();
      bufferUsedToStoreData = new ArrayBlockingQueue<HashMap<String, String>>(1);
      bufferUsedToStoreLogInfo = new ArrayBlockingQueue<String>(1);
    }
    return dataAndLogBuffer;
  }

  public void addItemToDataBuffer(HashMap<String, String> itemToAddToBuffer){

  }

  public void removeItemFromDataBuffer(){

  }

  public void addItemToLogBuffer(String logToAdd){

  }

  public void removeItemFromLogBuffer(){

  }

  public ArrayBlockingQueue<String> getBufferUsedToStoreLogInfo(){
    return null;
  }

  public ArrayBlockingQueue<HashMap<String, String>> getBufferUsedToStoreData(){
    return null;
  }

}
