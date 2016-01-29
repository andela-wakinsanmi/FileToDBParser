package checkpoint.andela.log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Spykins on 29/01/2016.
 */
public class LogWriterTest {
  String filePath = "/Users/Spykins/IdeaProjects/FileToDBParser/res/reactions.dat";
  LogWriter logWriter = new LogWriter(filePath);

  @Test
  public void testWriteToLogger() throws Exception {
    logWriter.writeToLogger("My Name is Waleola");
    logWriter.writeToLogger("I Live in Abuja");

  }
}