package checkpoint.andela.main;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Spykins on 29/01/2016.
 */
public class MainAppTest {
  MainApp mainApp = new MainApp("/Users/Spykins/IdeaProjects/FileToDBParser/res/reactions.dat");

  @Test
  public void testRunApplication() throws Exception {
    mainApp.runApplication();
  }
}