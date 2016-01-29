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
    try {
      bufferUsedToStoreData.put(itemToAddToBuffer);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  public void removeItemFromDataBuffer(){
    try {
      bufferUsedToStoreData.take();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void addItemToLogBuffer(String logToAdd){
    try {
      bufferUsedToStoreLogInfo.put(logToAdd);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  public void removeItemFromLogBuffer(){
    try {
      bufferUsedToStoreLogInfo.take();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  public ArrayBlockingQueue<String> getBufferUsedToStoreLogInfo(){
    return bufferUsedToStoreLogInfo;
  }

  public ArrayBlockingQueue<HashMap<String, String>> getBufferUsedToStoreData(){
    return bufferUsedToStoreData;
  }

}
