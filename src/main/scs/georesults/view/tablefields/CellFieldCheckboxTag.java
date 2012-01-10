package scs.georesults.view.tablefields;

import scs.georesults.view.fields.SimpleCheckboxTag;
import scs.georesults.view.fields.SimpleFieldTag;

public class CellFieldCheckboxTag extends CellTagBase
{

  protected SimpleFieldTag createBase ()
  {
    return new SimpleCheckboxTag();
  }

}
