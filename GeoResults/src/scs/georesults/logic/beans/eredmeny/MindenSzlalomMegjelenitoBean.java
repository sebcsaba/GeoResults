package scs.georesults.logic.beans.eredmeny;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoException;
import scs.georesults.om.alap.SzlalomKategoria;
import scs.georesults.om.eredmeny.MindenSzlalomEredmeny;

/**
 * <p>Az etapok összesített eredményének megjelenítését segítő osztály.</p>
 */
public class MindenSzlalomMegjelenitoBean extends EredmenyBeanBase
{

  /**
   * Beállítja az eredménylista tartalmát.
   */
  public void init () throws GeoException, RdbException, DIIException
  {
    eredmenyek = new List();
    List src = MindenSzlalomEredmeny.loadAllForVerseny( getDb(), verseny );
    long actKat = 0;
    List actList = null;
    for ( int i = 0; i < src.size(); ++i ) {
      MindenSzlalomEredmeny mszle = ( MindenSzlalomEredmeny ) src.get( i );
      if ( mszle.getKategoria() != actKat ) {
        SzlalomKategoria szlalomKategoria = ( SzlalomKategoria ) verseny.getSzlalomKategoriak().findItem( "szkid", new Long( mszle.getKategoria() ) );
        SzlalomKategoriaEredmeny katEredm = new SzlalomKategoriaEredmeny( szlalomKategoria );
        actKat = katEredm.getKategoria().getSzkid();
        actList = katEredm.getEredmenyek();
        eredmenyek.add( katEredm );
      }
      actList.add( mszle );
    }
  }

}
