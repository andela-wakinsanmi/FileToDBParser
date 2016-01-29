package checkpoint.andela.buffer;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Spykins on 28/01/2016.
 */
public class DataAndLogBufferTest {
  DataAndLogBuffer dataAndLogBuffer = DataAndLogBuffer.getInstance();

  @Test
  public void testGetInstance() throws Exception {

    DataAndLogBuffer dataAndLogBuffer1 = DataAndLogBuffer.getInstance();
    assertTrue(dataAndLogBuffer == dataAndLogBuffer1);
    DataAndLogBuffer dataAndLogBuffer2 = DataAndLogBuffer.getInstance();
    assertTrue(dataAndLogBuffer == dataAndLogBuffer1 && dataAndLogBuffer1 == dataAndLogBuffer2);

  }

  @Test
  public void testAddItemToDataBuffer() throws Exception {
    HashMap<String, String> testMap = new HashMap<String, String>();
    testMap.put("name", "Waleola");
    testMap.put("address", "Moleye 55");
    testMap.put("State", "Ondo");

    assertTrue(dataAndLogBuffer.getBufferUsedToStoreData().isEmpty());
    dataAndLogBuffer.addItemToDataBuffer(testMap);
    assertFalse(dataAndLogBuffer.getBufferUsedToStoreData().isEmpty());
  }

  @Test
  public void testRemoveItemFromDataBuffer() throws Exception {
    HashMap<String, String> testMap = new HashMap<String, String>();

    testMap.put("name", "Waleola");
    testMap.put("address", "Moleye 55");
    testMap.put("State", "Ondo");

    assertTrue(dataAndLogBuffer.getBufferUsedToStoreData().isEmpty());
    dataAndLogBuffer.addItemToDataBuffer(testMap);
    assertFalse(dataAndLogBuffer.getBufferUsedToStoreData().isEmpty());

    dataAndLogBuffer.removeItemFromDataBuffer();
    assertTrue(dataAndLogBuffer.getBufferUsedToStoreData().isEmpty());

  }

  @Test
  public void testAddItemToLogBuffer() throws Exception {
    String log1 = "FileParser Thread (2015-09-01 10:20:32)---- wrote UNIQUE ID RXN-8739 to buffer\n";

    assertTrue(dataAndLogBuffer.getBufferUsedToStoreLogInfo().isEmpty());
    dataAndLogBuffer.addItemToLogBuffer(log1);
    assertFalse(dataAndLogBuffer.getBufferUsedToStoreLogInfo().isEmpty());

  }

  @Test
  public void testRemoveItemFromLogBuffer() throws Exception {
    String log1 = "FileParser Thread (2015-09-01 10:20:32)---- wrote UNIQUE ID RXN-8739 to buffer\n";

    assertTrue(dataAndLogBuffer.getBufferUsedToStoreLogInfo().isEmpty());
    dataAndLogBuffer.addItemToLogBuffer(log1);
    assertFalse(dataAndLogBuffer.getBufferUsedToStoreLogInfo().isEmpty());

    dataAndLogBuffer.removeItemFromLogBuffer();
    assertTrue(dataAndLogBuffer.getBufferUsedToStoreLogInfo().isEmpty());

  }
}