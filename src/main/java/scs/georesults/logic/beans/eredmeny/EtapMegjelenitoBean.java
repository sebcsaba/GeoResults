package scs.georesults.logic.beans.eredmeny;

import scs.javax.rdb.RdbException;
import scs.georesults.GeoException;
import scs.georesults.om.eredmeny.EtapEredmeny;
import scs.georesults.om.verseny.Etap;

/**
 * <p>Egy etap eredményének megjelenítését segítő osztály.</p>
 */
public class EtapMegjelenitoBean extends EredmenyWithIdBeanBase
{

  /**
   * A megjelenítendő eredmény ehhez az etaphoz tartozik.
   */
  private Etap etap;

  /**
   * Az objektum által megjelenített etap azonosítóját állítja be.
   */
  public void setId ( long id ) throws RdbException, GeoException
  {
    updateEtap( id );
    eredmenyek = EtapEredmeny.loadAllForEtap( getDb(), etap );
  }

  /**
   * Betölti a megfelelő etapot az azonosítója alapján.
   */
  private void updateEtap ( long eid ) throws GeoException, RdbException
  {
    etap = Etap.newInstance( eid );
    etap.read( getDb() );
  }

  public Etap getEtap ()
  {
    return etap;
  }

}
