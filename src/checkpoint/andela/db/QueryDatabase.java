package checkpoint.andela.db;

import checkpoint.andela.model.InputFieldModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Spykins on 28/01/2016.
 */
public class QueryDatabase {
  private Connection databaseConnection;
  private Statement databaseStatement;


  public QueryDatabase(Connection databaseConnection){
    this.databaseConnection = databaseConnection;
  }

  public boolean createTable(String tableName){

    return false;

  }

  public boolean insertIntoDatabase(String tableName, HashMap<String, String> dataToInsertInDatabase){

    return false;
  }



  public void deleteTableFromDatabase(String tableName){

  }

  public boolean hasTable(String tableName){

    return false;
  }

}
