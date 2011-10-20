package scs.georesults.logic.beans.eredmeny;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.om.alap.SzlalomKategoria;
import scs.georesults.om.eredmeny.SzlalomEredmeny;
import scs.georesults.om.verseny.Szlalom;

/**
 * <p>Egy szlalom eredményének megjelenítését segítő osztály.</p>
 */
public class SzlalomMegjelenitoBean extends EredmenyWithIdBeanBase
{

  /**
   * A megjelenítendő eredmény ehhez a szlalomhoz tartozik.
   */
  private Szlalom szlalom;

  /**
   * A versenyen értelemzett szlalom-feladatok listája
   */
  private List szlalomFeladatok;

  /**
   * Az objektum által megjelenített szlalom azonosítóját állítja be.
   */
  public void setId ( long id ) throws RdbException, GeoException, DIIException
  {
    szlalomFeladatok = verseny.getSzlalomFeladatok();
    initSzlalom( id );
    processEredmenyek( getDb() );
  }

  /**
   * Betölti a megfelelő szlalomoz az azonosítója alapján.
   */
  private void initSzlalom ( long szid ) throws GeoException, RdbException
  {
    szlalom = Szlalom.newInstance( szid );
    szlalom.read( getDb() );
  }

  /**
   * Betölti az adatbázisból a szlalomhoz tartozó eredmények listáját, majd kategóriánként
   * csoportosítja. Az eredmények listája {@link SzlalomKategoriaEredmeny} objektumokat
   * fog tartalmazni.
   */
  private void processEredmenyek ( GeoDbSession db ) throws RdbException, GeoException, DIIException
  {
    eredmenyek = new List();
    List src = SzlalomEredmeny.loadAllForSzlalom( db, szlalom );
    long actKat = 0;
    List actList = null;
    for ( int i = 0; i < src.size(); ++i ) {
      SzlalomEredmeny szle = ( SzlalomEredmeny ) src.get( i );
      if ( szle.getKategoria() != actKat ) {
        SzlalomKategoria szlalomKategoria = ( SzlalomKategoria ) verseny.getSzlalomKategoriak().findItem( "szkid", new Long( szle.getKategoria() ) );
        SzlalomKategoriaEredmeny katEredm = new SzlalomKategoriaEredmeny( szlalomKategoria );
        actKat = katEredm.getKategoria().getSzkid();
        actList = katEredm.getEredmenyek();
        eredmenyek.add( katEredm );
      }
      actList.add( szle );
    }
  }

  public Szlalom getSzlalom ()
  {
    return szlalom;
  }

  /**
   * A szlalom-feladatok listáját adja vissza
   */
  public List getSzlalomFeladatok ()
  {
    return szlalomFeladatok;
  }

}
