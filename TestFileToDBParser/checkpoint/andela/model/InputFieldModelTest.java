package checkpoint.andela.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Spykins on 29/01/2016.
 */
public class InputFieldModelTest {

  @Test
  public void testGetRealName() throws Exception {
    ArrayList<String> allValues = new ArrayList<>();
    allValues.add("UNIQUE-ID");
    allValues.add("COMMON-NAME");
    allValues.add("ATOM-MAPPINGS");
    allValues.add("CREDITS");
    allValues.add("EC-NUMBER");
    allValues.add("ENZYMATIC-REACTION");
    allValues.add("IN-PATHWAY");
    allValues.add("ORPHAN?");
    allValues.add("PHYSIOLOGICALLY-RELEVANT?");
    allValues.add("REACTION-DIRECTION");

    ArrayList<String> allValues2 = new ArrayList<>();

    for(InputFieldModel inputField : InputFieldModel.values()){
      allValues2.add(inputField.getRealName());
    }

    assertTrue(allValues.equals(allValues2));


  }
}