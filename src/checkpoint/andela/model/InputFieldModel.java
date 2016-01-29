package checkpoint.andela.model;

/**
 * Created by Spykins on 28/01/2016.
 */
public enum  InputFieldModel {
  //InputFieldModel


  unique_id("UNIQUE-ID"),
  common_name("COMMON-NAME"),
  atom_mappings("ATOM-MAPPINGS"),
  credits("CREDITS"),
  ec_number("EC-NUMBER"),
  enzymatic_reaction("ENZYMATIC-REACTION"),
  in_pathway("IN-PATHWAY"),
  orphan("ORPHAN?"),
  physiologically_relevant("PHYSIOLOGICALLY-RELEVANT?"),
  reation_direction("REACTION-DIRECTION");

  private InputFieldModel(String realName){
    this.realName = realName;
  }

  public String getRealName(){
    return realName;
  }
  private final String realName;

}
