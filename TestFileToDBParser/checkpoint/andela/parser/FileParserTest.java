package checkpoint.andela.parser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Spykins on 29/01/2016.
 */
public class FileParserTest {
  FileParser fileParser = new FileParser("/Users/Spykins/IdeaProjects/FileToDBParser/res/reactions.dat");

  @Test
  public void testFetchDataFromFile() throws Exception {
    assertFalse(fileParser.fetchDataFromFile() == null);
  }
}