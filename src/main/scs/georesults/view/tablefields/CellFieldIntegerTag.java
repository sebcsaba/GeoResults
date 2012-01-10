package scs.georesults.view.tablefields;

import scs.georesults.view.fields.SimpleFieldTag;
import scs.georesults.view.fields.SimpleIntegerTag;

public class CellFieldIntegerTag extends CellTagBase
{

  protected SimpleFieldTag createBase ()
  {
    return new SimpleIntegerTag();
  }

}
