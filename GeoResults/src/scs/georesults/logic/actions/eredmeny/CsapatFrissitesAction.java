package scs.georesults.logic.actions.eredmeny;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.GeoException;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.LezarvaException;
import scs.georesults.om.eredmeny.CsapatEredmeny;
import scs.georesults.om.eredmeny.CsapatEredmenyImpl;
import scs.georesults.om.eredmeny.VersenyEredmeny;
import scs.georesults.om.nevezes.CsapatNevezesImpl;
import scs.georesults.om.nevezes.Nevezes;

/**
 * <p>A csapatverseny eredményét frissítő szolgáltatás.</p>
 */
public class CsapatFrissitesAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    try {
      if ( getVerseny().isLeVanZarva() )throw new LezarvaException();
      List nevezesek = getVerseny().getNevezesek();
      List csapatNevezesek = getVerseny().getCsapatNevezesek();
      List versenyEredmenyek = VersenyEredmeny.loadAllForVerseny( getDb(), getVerseny() );
      checkAllVersenyEredmenyExists( nevezesek, versenyEredmenyek );
      deleteAllCurrentEredmeny();
      csapatEredmenyOsszesito( csapatNevezesek, versenyEredmenyek );
      getVerseny().read( getDb() );
      getVerseny().setEredmenyFrissitendoCsapat( false );
      getVerseny().update( getDb() );
      return "ok";
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
  }

  /**
   * Az összes csapatnevezéshez kiszámítja a megfelelő {@link CsapatEredmeny} objektumot a kapott versenyeredményekből.
   */
  private void csapatEredmenyOsszesito ( List csapatNevezesek, List versenyEredmenyek ) throws DIIException, WebException, RdbException
  {
    for ( int i = 0; i < csapatNevezesek.size(); ++i ) {
      CsapatNevezesImpl csn = ( CsapatNevezesImpl ) csapatNevezesek.get( i );
      CsapatEredmenyImpl cse = ( CsapatEredmenyImpl ) CsapatEredmeny.newInstance( csn.getCsnid() );
      cse.setVid( csn.getVid() );
      cse.setNev( csn.getNev() );
      int[] pontok = new int[4];
      pontok[0] = getPontszam( versenyEredmenyek, csn.getRajtszam1() );
      pontok[1] = getPontszam( versenyEredmenyek, csn.getRajtszam2() );
      pontok[2] = getPontszam( versenyEredmenyek, csn.getRajtszam3() );
      pontok[3] = Integer.MAX_VALUE;
      if ( csn.getRajtszam4() != null ) pontok[3] = getPontszam( versenyEredmenyek, csn.getRajtszam4().intValue() );
      int[] rajtszamok = findMinIndex( 3, pontok );
      int pont = 0;
      for ( int j = 0; j < rajtszamok.length; ++j ) {
        cse.setRajtszamByIndex( j + 1, csn.getRajtszamByIndex( rajtszamok[j] + 1 ) );
        pont += pontok[rajtszamok[j]];
      }
      cse.setPontszam( pont );
      cse.create( getDb() );
    }
  }

  /**
   * A kapott tömb legkisebb értékű elemeinek indexeit gyűjti össze.
   *
   * @param count A tömbből ennyi darab legkisebb elemet kell megkeresni.
   * @param data Az értékeket tartalmazó tömb
   * @return Egy <code>count</code> elemet tartalmazó tömb, amely a <code>data</code>
   *   tömb legkisebb elemeinek indexeit tartalmazza.
   */
  private int[] findMinIndex ( int count, int[] data )
  {
    int[] result = new int[count];
    int[] src = data.clone();
    for ( int i = 0; i < count; ++i ) {
      int minIndex = 0;
      for ( int j = 1; j < src.length; ++j ) {
        if ( src[j] < src[minIndex] ) minIndex = j;
      }
      result[i] = minIndex;
      src[minIndex] = Integer.MAX_VALUE;
    }
    return result;
  }

  /**
   * A versenyeredmények kapott listáján megkeresi a megfelelő rajtszámú versenyző eredményét, és az ő pontszámát adja vissza.
   */
  private int getPontszam ( List versenyEredmenyek, int rajtszam ) throws DIIException
  {
    VersenyEredmeny ve = ( VersenyEredmeny ) versenyEredmenyek.findItem( "rajtszam", new Integer( rajtszam ) );
    return ve.getPontszam();
  }

  /**
   * Ellenőrzi, hogy az összes forrásadat rendelkezésre áll-e.
   */
  private void checkAllVersenyEredmenyExists ( List nevezesek, List versenyEredmenyek ) throws WebException, DIIException
  {
    for ( int i = 0; i < nevezesek.size(); ++i ) {
      Nevezes n = ( Nevezes ) nevezesek.get( i );
      if ( versenyEredmenyek.findItemIndex( "rajtszam", new Integer( n.getRajtszam() ) ) == -1 ) {
        throw new GeoMessageException( "ER_VERSENY_EREDMENY_FRISSITENDO" );
      }
    }
  }

  /**
   * Törli az adatbázisból a csapateredményeket.
   */
  private void deleteAllCurrentEredmeny () throws WebException, RdbException
  {
    List eredmenyek = CsapatEredmeny.loadAllForVerseny( getDb(), getVerseny() );
    for ( int i = 0; i < eredmenyek.size(); ++i ) {
      CsapatEredmeny cse = ( CsapatEredmeny ) eredmenyek.get( i );
      cse.delete( getDb() );
    }
  }

}
