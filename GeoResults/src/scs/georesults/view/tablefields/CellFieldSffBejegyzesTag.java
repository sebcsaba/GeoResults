package scs.georesults.view.tablefields;

import scs.georesults.om.etap.SorrendFuggoEtapFeladatImpl;
import scs.georesults.view.fields.SimpleFieldTag;

public class CellFieldSffBejegyzesTag extends CellTagBase
{

  protected boolean etalon;

  protected SorrendFuggoEtapFeladatImpl sfef;

  protected int ellenorzesTipus;

  protected SimpleFieldTag createBase ()
  {
    SffBejegyzesTag bejegyzesTag = new SffBejegyzesTag();
    bejegyzesTag.setEtalon( etalon );
    bejegyzesTag.setSfef( sfef );
    bejegyzesTag.setEllenorzesTipus( ellenorzesTipus );
    return bejegyzesTag;
  }

  public void setEtalon ( boolean etalon )
  {
    this.etalon = etalon;
  }

  public void setSfef ( SorrendFuggoEtapFeladatImpl sfef )
  {
    this.sfef = sfef;
  }

  public void setEllenorzesTipus ( int ellenorzesTipus )
  {
    this.ellenorzesTipus = ellenorzesTipus;
  }

}
