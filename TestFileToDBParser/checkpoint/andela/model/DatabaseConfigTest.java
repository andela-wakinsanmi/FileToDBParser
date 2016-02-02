package checkpoint.andela.model;

import checkpoint.andela.db.QueryDatabase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Spykins on 31/01/2016.
 */
public class DatabaseConfigTest {

  @Test
  public void testGetGetRealName() throws Exception {
    assertTrue(DatabaseConfig.DATABASE_NAME.getRealName().equals("reactiondb"));
  }
}