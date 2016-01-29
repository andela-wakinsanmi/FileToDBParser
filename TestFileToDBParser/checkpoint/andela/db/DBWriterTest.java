package checkpoint.andela.db;

import checkpoint.andela.parser.ReadData;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Spykins on 28/01/2016.
 */
public class DBWriterTest {
  DBWriter dbWriter = new DBWriter();
  ReadData readData = new ReadData("/Users/Spykins/IdeaProjects/FileToDBParser/res/reactions.dat");


  @Test
  public void testInsertIntoDatabase() throws Exception {
    HashMap<String, String> returnedValue = readData.getData();
    assertFalse(returnedValue.isEmpty());
    dbWriter.insertIntoDatabase(returnedValue);

    HashMap<String, String> returnedValue2 = readData.getData();
    dbWriter.insertIntoDatabase(returnedValue2);
  }
}