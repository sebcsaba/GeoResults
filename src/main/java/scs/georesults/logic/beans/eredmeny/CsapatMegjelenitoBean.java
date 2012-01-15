package scs.georesults.logic.beans.eredmeny;

import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoException;
import scs.georesults.om.eredmeny.CsapatEredmeny;
import scs.georesults.om.eredmeny.CsapatEredmenyImpl;
import scs.georesults.om.eredmeny.VersenyEredmeny;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.nevezes.NevezesImpl;

/**
 * <p>A csapatverseny végeredményének megjelenítését segítő osztály.</p>
 */
public class CsapatMegjelenitoBean extends EredmenyBeanBase
{

  /**
   * Az aktuális sorhoz tartozó nevezés objektum.
   */
  protected NevezesImpl nevezes;

  /**
   * Beállítja az eredménylista tartalmát.
   */
  public void init () throws GeoException, RdbException
  {
    eredmenyek = CsapatEredmeny.loadAllForVerseny( getDb(), verseny );
  }

  /**
   * Az aktuális nevezéshez tartozó végeredményt reprezentáló objektumot adja meg.
   */
  public VersenyEredmeny getNevezeshezTartozoVegeredmeny () throws GeoException, RdbException
  {
    VersenyEredmeny ve = VersenyEredmeny.newInstance( nevezes.getVid(), nevezes.getRajtszam() );
    ve.read( getDb() );
    return ve;
  }

  /**
   * A megadott csapat-eredményt reprezentáló objektum adott
   * indexű rajtszámához tarotzó nevezését adja vissza.
   */
  public Nevezes findNevezesByIndex ( CsapatEredmenyImpl cse, long index ) throws GeoException, DIIException, RdbException
  {
    int rajtszam = cse.getRajtszamByIndex( ( int ) index );
    nevezes = ( NevezesImpl ) verseny.getNevezesek().findItem( "rajtszam", new Integer( rajtszam ) );
    return nevezes;
  }

  /**
   * Beállítja az aktuális nevezés objektumot.
   */
  public void setNevezes ( NevezesImpl nevezes )
  {
    this.nevezes = nevezes;
  }

}
