package checkpoint.andela.db;

import checkpoint.andela.buffer.DataAndLogBuffer;
import checkpoint.andela.model.DatabaseConfig;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Spykins on 28/01/2016.
 */
public class DBWriter implements Runnable {
  private Connection databaseConnection;
  private QueryDatabase queryDatabase;
  private DataAndLogBuffer dataAndLogBuffer;

  public DBWriter(){
    initialize();
  }

  private void initialize(){
    dataAndLogBuffer = DataAndLogBuffer.getInstance();
    try {
      databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" +
          DatabaseConfig.DATABASE_NAME.getGetRealName(), DatabaseConfig.DATABASE_USER_NAME.getGetRealName(),
          DatabaseConfig.DATABASE_PASSWORD.getGetRealName());
      queryDatabase = new QueryDatabase(databaseConnection);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void insertIntoDatabase (HashMap<String, String> dataToInsertInDatabase ){
    if(!queryDatabase.hasTable(DatabaseConfig.TABLE_NAME.getGetRealName())) {
      queryDatabase.createTable(DatabaseConfig.TABLE_NAME.getGetRealName());
      queryDatabase.insertIntoDatabase(DatabaseConfig.TABLE_NAME.getGetRealName(), dataToInsertInDatabase);
    } else {
      queryDatabase.insertIntoDatabase(DatabaseConfig.TABLE_NAME.getGetRealName(), dataToInsertInDatabase);
    }
  }

  @Override
  public void run() {
    while (dataAndLogBuffer.hasDataToRead()){
      insertIntoDatabase(dataAndLogBuffer.removeItemFromDataBuffer());
      if(!dataAndLogBuffer.hasDataToRead()){
        if ((dataAndLogBuffer.getBufferUsedToStoreData().size() == 1)){
          insertIntoDatabase(dataAndLogBuffer.removeItemFromDataBuffer());
        }
        break;
      }
    }
  }

}
