package checkpoint.andela.buffer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Spykins on 28/01/2016.
 */
public class DataAndLogBuffer {
  private static ArrayBlockingQueue<String> bufferUsedToStoreLogInfo;
  private static ArrayBlockingQueue<HashMap<String, String>> bufferUsedToStoreData;
  private static DataAndLogBuffer dataAndLogBuffer = null;
  private final int ADD_ITEM_TO_BUFFER_CODE = 1;
  private final int REMOVE_ITEM_FROM_BUFFER_CODE = 0;
  private boolean isDataAvailable = true;
  private static boolean firstThread = true;


  private DataAndLogBuffer(){
  }

  public static synchronized DataAndLogBuffer getInstance(){
    if(dataAndLogBuffer == null){
      if(firstThread){
        firstThread = false;
        Thread.currentThread();
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      dataAndLogBuffer = new DataAndLogBuffer();
      bufferUsedToStoreData = new ArrayBlockingQueue<HashMap<String, String>>(1);
      bufferUsedToStoreLogInfo = new ArrayBlockingQueue<String>(1);
    }
    return dataAndLogBuffer;
  }

  public void addItemToDataBuffer(HashMap<String, String> itemToAddToBuffer){
    //fileParser Thread Domain
    try {
      bufferUsedToStoreData.put(itemToAddToBuffer);
      constructLog(ADD_ITEM_TO_BUFFER_CODE, itemToAddToBuffer);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  public HashMap<String, String> removeItemFromDataBuffer(){
    //DBWriter Thread Domain
    try {
      HashMap<String, String> dataToRemove = bufferUsedToStoreData.take();
      constructLog(REMOVE_ITEM_FROM_BUFFER_CODE, dataToRemove);
      return dataToRemove ;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }

  private void constructLog(int value, HashMap<String, String> dataToLog){
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    String logData;

    if(value == REMOVE_ITEM_FROM_BUFFER_CODE){
      logData = "DBWriter Thread " + dateFormat.format(date) + " ---- collected UNIQUE ID " +
          dataToLog.get("UNIQUE-ID") + " from buffer";
    } else {
      logData = "FileParser Thread " + dateFormat.format(date) + " ---- wrote UNIQUE ID " +
          dataToLog.get("UNIQUE-ID") + " from buffer";
    }
    addItemToLogBuffer(logData);
  }

  public void addItemToLogBuffer(String logToAdd){
    try {
      bufferUsedToStoreLogInfo.put(logToAdd);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public String removeItemFromLogBuffer(){
    try {
      return bufferUsedToStoreLogInfo.take();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }

  public ArrayBlockingQueue<String> getBufferUsedToStoreLogInfo(){
    return bufferUsedToStoreLogInfo;
  }

  public ArrayBlockingQueue<HashMap<String, String>> getBufferUsedToStoreData(){
    return bufferUsedToStoreData;
  }

  public boolean hasDataToRead(){
    return isDataAvailable;
  }

  public void setDataAvailable(boolean flag){
    isDataAvailable = flag;
  }
}
