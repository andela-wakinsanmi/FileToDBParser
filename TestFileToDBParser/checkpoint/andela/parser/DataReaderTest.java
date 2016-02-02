package checkpoint.andela.parser;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Spykins on 01/02/2016.
 */
public class DataReaderTest {
  DataReader readData = new DataReader("/Users/Spykins/IdeaProjects/FileToDBParser/res/reactions.dat");

  @Test
  public void testGetData() throws Exception {
    HashMap<String, String> returnedValue = readData.getData();
    assertFalse(returnedValue.isEmpty());

    HashMap<String, String> returnedValue2 = readData.getData();
    assertFalse(returnedValue2.isEmpty());

    assertFalse(returnedValue.equals(returnedValue2));

  }
}