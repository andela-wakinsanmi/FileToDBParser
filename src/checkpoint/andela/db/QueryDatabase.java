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
    try {
      databaseStatement = databaseConnection.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    String databaseQuery  = runCreateTableQuery(tableName);
    try {
      databaseStatement.executeUpdate(databaseQuery);
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;

  }

  private String runCreateTableQuery(String tableName){
    String databaseQuery  = "CREATE TABLE " + tableName + " ( ";

    for(InputFieldModel inputField : InputFieldModel.values()){
      databaseQuery += "`" + inputField.getRealName()+ "`" + " " + "TEXT" + ", ";
    }
    databaseQuery = removeLastCommaFromQuery(databaseQuery);
    databaseQuery += " )";

    return databaseQuery;
  }

  private String removeLastCommaFromQuery(String databaseQuery){
    return (databaseQuery.endsWith(", ")) ? databaseQuery.substring(0, databaseQuery.length()-2) : databaseQuery;
  }

  public boolean insertIntoDatabase(String tableName, HashMap<String, String> dataToInsertInDatabase){

    ArrayList<String> columnNames = getColumnNamesInTable(tableName);
    String databaseQuery = runInsertQuery(tableName, columnNames);
    try {
      PreparedStatement preparedStatement = databaseConnection.prepareStatement(databaseQuery);
      int count = 1;

      for(String columnName : columnNames){
        if(dataToInsertInDatabase.containsKey(columnName)){
          preparedStatement.setString(count, dataToInsertInDatabase.get(columnName));
        } else {
          preparedStatement.setString(count, null);
        }
        count++;
      }
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  private ArrayList<String> getColumnNamesInTable(String tableName){
    ArrayList<String> columnNames = new ArrayList<String>();
    for(InputFieldModel inputField : InputFieldModel.values()){
      columnNames.add(inputField.getRealName());
    }

    return columnNames;
  }

  private String runInsertQuery(String tableName, ArrayList<String> columnNames){
    String databaseQuery = "INSERT INTO " + tableName + " ( ";

    for(String columnName : columnNames){
      databaseQuery += "`"+columnName+"`" + ", ";
    }
    databaseQuery =  removeLastCommaFromQuery(databaseQuery);

    databaseQuery += " ) VALUES ( ";
    for(String columnName : columnNames){
      databaseQuery += "?, ";
    }
    databaseQuery = removeLastCommaFromQuery(databaseQuery);
    databaseQuery += " )";

    return databaseQuery;
  }

  public boolean deleteTableFromDatabase(String tableName){
    try {
      databaseStatement = databaseConnection.createStatement();
      String databaseQuery  = "DROP TABLE " + tableName;
      databaseStatement.executeUpdate(databaseQuery);
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean hasTable(String tableName){
    try {
      DatabaseMetaData databaseMetaData = databaseConnection.getMetaData();
      ResultSet resultSet = databaseMetaData.getTables(null,null,tableName,null);
      return resultSet.next();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

}
