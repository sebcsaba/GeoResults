package scs.georesults.logic.beans.eredmeny;

import scs.javax.rdb.RdbException;
import scs.georesults.GeoException;
import scs.georesults.om.eredmeny.VersenyEredmeny;

/**
 * <p>A verseny végeredményének megjelenítését segítő osztály.</p>
 */
public class VersenyMegjelenitoBean extends EredmenyBeanBase
{

  /**
   * Beállítja az eredménylista tartalmát.
   */
  public void init () throws GeoException, RdbException
  {
    eredmenyek = VersenyEredmeny.loadAllForVerseny( getDb(), verseny );
  }

}
