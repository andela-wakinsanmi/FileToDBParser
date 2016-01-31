package checkpoint.andela.model;

/**
 * Created by Spykins on 31/01/2016.
 */
public enum FileDelimeters {
  KEY_VALUE_SEPARATOR(" - "),
  COMMENT("#"),
  END_OF_DATA("//");

  private FileDelimeters(String realName){
    this.realName = realName;
  }

  public String getRealName(){
    return realName;
  }
  private final String realName;

}
