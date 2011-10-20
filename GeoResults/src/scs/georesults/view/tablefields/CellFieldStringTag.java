package scs.georesults.view.tablefields;

import scs.georesults.view.fields.SimpleFieldTag;
import scs.georesults.view.fields.SimpleStringTag;

public class CellFieldStringTag extends CellTagBase
{

  protected SimpleFieldTag createBase ()
  {
    return new SimpleStringTag();
  }

}
