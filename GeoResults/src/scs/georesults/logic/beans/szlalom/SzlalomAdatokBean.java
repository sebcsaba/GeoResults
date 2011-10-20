package scs.georesults.logic.beans.szlalom;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoException;
import scs.georesults.logic.beans.AdatbevitelBean;
import scs.georesults.om.alap.SzlalomFeladat;
import scs.georesults.om.szlalom.SzlalomBejegyzes;
import scs.georesults.om.szlalom.SzlalomFutam;
import scs.georesults.om.verseny.VersenyImpl;

/**
 * <p>A szlalom-futamok adatfelvitelét segítő osztály.</p>
 */
public class SzlalomAdatokBean extends AdatbevitelBean
{

  /**
   * Az első megjelenített adatmező azonosítója. Azért tároljuk, hogy betöltés után rögtön rá lehessen fókuszálni.
   */
  private long firstInputId;

  /**
   * Létrehoz egy új objektumot az adott versenyhez kapcsolódóan.
   */
  public SzlalomAdatokBean ( VersenyImpl verseny ) throws GeoException, RdbException
  {
    super( verseny );
    setBetoltes( SzlalomFutam.newInstance( 0, 0 ) );
    updateFirstInputId();
  }

  /**
   * Megadja, hogy a paraméterben adott feladatot a futam során a
   * versenyző hányszor 'teljesítette'. Ha nem talál megfelelő
   * bejegyzést a futamon, akkor létrehozza, nulla darabbal.
   */
  public int getDarab ( SzlalomFeladat szlalomFeladat )
  {
    SzlalomFutam szlalomFutam = ( SzlalomFutam ) getFutam();
    for ( int i = 0; i < szlalomFutam.getBejegyzesek().size(); ++i ) {
      SzlalomBejegyzes szb = ( SzlalomBejegyzes ) szlalomFutam.getBejegyzesek().get( i );
      if ( szb.getSzfid() == szlalomFeladat.getSzfid() )return szb.getDarab();
    }
    SzlalomBejegyzes szb = SzlalomBejegyzes.newInstance();
    szb.setSzlid( szlalomFutam.getSzlid() );
    szb.setRajtszam( szlalomFutam.getRajtszam() );
    szb.setSzfid( szlalomFeladat.getSzfid() );
    szb.setDarab( 0 );
    szlalomFutam.getBejegyzesek().add( szb );
    return 0;
  }

  /**
   * Frissíti a <code>updateFirstInputId</code> mező értékét.
   */
  public void updateFirstInputId () throws GeoException, RdbException
  {
    List szlalomFeladatok = getVerseny().getSzlalomFeladatok();
    if ( szlalomFeladatok.size() > 0 ) {
      SzlalomFeladat szf = ( SzlalomFeladat ) szlalomFeladatok.get( 0 );
      firstInputId = szf.getSzfid();
    }
  }

  public long getFirstInputId ()
  {
    return firstInputId;
  }

}
