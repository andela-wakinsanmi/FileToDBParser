package checkpoint.andela.model;

/**
 * Created by Spykins on 28/01/2016.
 */
public enum DatabaseConfig {
  DATABASE_USER_NAME ("root"),
  DATABASE_PASSWORD("oluronti20"),
  DATABASE_NAME ("reactiondb"),
  TABLE_NAME("reactions");

  private DatabaseConfig(String getRealName){
    this.getRealName = getRealName;
  }

  private String getRealName;

  public String getGetRealName(){
    return getRealName;
  }
}
