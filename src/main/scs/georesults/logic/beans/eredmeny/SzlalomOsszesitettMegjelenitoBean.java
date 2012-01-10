package scs.georesults.logic.beans.eredmeny;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoException;
import scs.georesults.om.alap.SzlalomKategoria;
import scs.georesults.om.eredmeny.SzlalomOsszesitettEredmeny;
import scs.georesults.om.verseny.Szakasz;
import scs.georesults.om.verseny.SzakaszImpl;
import scs.georesults.om.verseny.Szlalom;

/**
 * <p>Az egy szakaszhoz tartozó szlalomok összesített
 * eredményének megjelenítését segítő osztály.</p>
 */
public class SzlalomOsszesitettMegjelenitoBean extends EredmenyWithIdBeanBase
{

  /**
   * A megjelenítendő eredmény ehhez a szakaszhoz tartozik.
   */
  private SzakaszImpl szakasz;

  /**
   * A szakaszhoz tartozó szlalomok listája.
   */
  private List szlalomok;

  /**
   * Az objektum által megjelenített szakasz azonosítóját állítja be.
   */
  public void setId ( long id ) throws RdbException, GeoException, DIIException
  {
    updateSzakasz( id );
    szlalomok = Szlalom.loadAllForSzakasz( getDb(), szakasz );
    processEredmenyek();
  }

  /**
   * Betölti a megfelelő szakaszt az azonosítója alapján.
   */
  private void updateSzakasz ( long eid ) throws GeoException, RdbException
  {
    szakasz = ( SzakaszImpl ) Szakasz.newInstance( eid );
    szakasz.read( getDb() );
  }

  /**
   * Visszaadja a kijelölt szakaszt.
   */
  public Szakasz getSzakasz ()
  {
    return szakasz;
  }

  /**
   * Visszaadja a szlalomok listáját.
   */
  public List getSzlalomok ()
  {
    return szlalomok;
  }

  /**
   * Betölti az adatbázisból a szakaszhoz tartozó szlalom-összesítő eredmények listáját,
   * majd kategóriánként csoportosítja. Az eredmények listája {@link SzlalomKategoriaEredmeny}
   * objektumokat fog tartalmazni.
   */
  private void processEredmenyek () throws GeoException, RdbException, DIIException
  {
    eredmenyek = new List();
    List src = SzlalomOsszesitettEredmeny.loadAllForSzakasz( getDb(), szakasz );
    long actKat = 0;
    List actList = null;
    for ( int i = 0; i < src.size(); ++i ) {
      SzlalomOsszesitettEredmeny szloe = ( SzlalomOsszesitettEredmeny ) src.get( i );
      if ( szloe.getKategoria() != actKat ) {
        SzlalomKategoria szlalomKategoria = ( SzlalomKategoria ) verseny.getSzlalomKategoriak().findItem( "szkid", new Long( szloe.getKategoria() ) );
        SzlalomKategoriaEredmeny katEredm = new SzlalomKategoriaEredmeny( szlalomKategoria );
        actKat = katEredm.getKategoria().getSzkid();
        actList = katEredm.getEredmenyek();
        eredmenyek.add( katEredm );
      }
      actList.add( szloe );
    }
  }

}
