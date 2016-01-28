package checkpoint.andela.parser;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Spykins on 28/01/2016.
 */
public class ReadDataTest {
  ReadData readData = new ReadData("/Users/Spykins/IdeaProjects/FileToDBParser/res/reactions.dat");

  @org.junit.Test
  public void testGetData() throws Exception {
    HashMap<String, String> returnedValue = readData.getData();
    assertFalse(returnedValue.isEmpty());

    HashMap<String, String> returnedValue2 = readData.getData();
    assertFalse(returnedValue2.isEmpty());

    assertFalse(returnedValue.equals(returnedValue2));

  }
}