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

  /**
   * Add Item to the DataBuffer, The FileParser Thread uses this Method to add item into the Buffer
   * @param itemToAddToBuffer of data parsed from FileParser method: initializeFileParser()
   * @return void
   */
  public void addItemToDataBuffer(HashMap<String, String> itemToAddToBuffer){
    try {
      bufferUsedToStoreData.put(itemToAddToBuffer);
      constructLog(itemToAddToBuffer,"FileParser", "Wrote", "to");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  /**
   * Removes item from DataBuffer
   * @return void
   */

  public HashMap<String, String> removeItemFromDataBuffer(){
    try {
      HashMap<String, String> dataToRemove = bufferUsedToStoreData.take();
      constructLog(dataToRemove, "DBWriter", "collected", "from");
      return dataToRemove ;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }

  private void constructLog(HashMap<String, String> dataToLog, String threadName, String appendName, String preposition){
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    String logData = threadName + " " + dateFormat.format(date) + " ---- " + appendName + " " + "UNIQUE ID " +
          dataToLog.get("UNIQUE-ID") + preposition + " buffer";
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
