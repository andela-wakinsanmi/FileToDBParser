package checkpoint.andela.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Spykins on 31/01/2016.
 */
public class FileDelimetersTest {

  @Test
  public void testGetRealName() throws Exception {

    assertTrue(FileDelimeters.COMMENT.getRealName().equals("#"));
    assertTrue(FileDelimeters.END_OF_DATA.getRealName().equals("//"));
    assertTrue(FileDelimeters.KEY_VALUE_SEPARATOR.getRealName().equals(" - "));

  }
}