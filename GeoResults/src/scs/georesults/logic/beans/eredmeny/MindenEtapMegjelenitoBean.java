package scs.georesults.logic.beans.eredmeny;

import scs.javax.rdb.RdbException;
import scs.georesults.GeoException;
import scs.georesults.om.eredmeny.MindenEtapEredmeny;

/**
 * <p>Az etapok összesített eredményének megjelenítését segítő osztály.</p>
 */
public class MindenEtapMegjelenitoBean extends EredmenyBeanBase
{

  /**
   * Beállítja az eredménylista tartalmát.
   */
  public void init () throws GeoException, RdbException
  {
    eredmenyek = MindenEtapEredmeny.loadAllForVerseny( getDb(), verseny );
  }

}
