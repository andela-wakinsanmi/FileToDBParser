package checkpoint.andela.db;

import checkpoint.andela.buffer.DataAndLogBuffer;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Spykins on 28/01/2016.
 */
public class DBWriter extends checkpoint.andela.model.DatabaseConfig {
  private Connection databaseConnection;
  private QueryDatabase queryDatabase;
  private DataAndLogBuffer dataAndLogBuffer;

  public DBWriter(){
    initialize();
  }

  private void initialize(){
    try {
      databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE_NAME, DATABASE_USER_NAME, DATABASE_PASSWORD);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void insertIntoDatabase (HashMap<String, String> dataToInsertInDatabase ){


  }

}
