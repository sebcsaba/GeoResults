package scs.georesults.logic.beans.eredmeny;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoException;
import scs.georesults.om.eredmeny.SzakaszEredmeny;
import scs.georesults.om.verseny.Szakasz;
import scs.georesults.om.verseny.SzakaszImpl;

/**
 * <p>Egy szakasz eredményének megjelenítését segítő osztály.</p>
 */
public class SzakaszMegjelenitoBean extends EredmenyWithIdBeanBase
{

  /**
   * A megjelenítendő eredmény ehhez a szakaszhoz tartozik.
   */
  private SzakaszImpl szakasz;

  /**
   * A szakaszhoz tartozó etapok listája.
   */
  private List etapok;

  /**
   * A szakaszhoz tartozó szlalomok listája.
   */
  private List szlalomok;

  /**
   * Az objektum által megjelenített szakasz azonosítóját állítja be.
   */
  public void setId ( long id ) throws RdbException, GeoException
  {
    updateSzakasz( id );
    eredmenyek = SzakaszEredmeny.loadAllForSzakasz( getDb(), szakasz );
    szlalomok = null;
    etapok = null;
  }

  /**
   * Betölti a megfelelő szakaszt az azonosítója alapján.
   */
  private void updateSzakasz ( long szid ) throws GeoException, RdbException
  {
    szakasz = ( SzakaszImpl ) Szakasz.newInstance( szid );
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
   * Frissíti az etapok listáját.
   */
  public void updateEtapok () throws GeoException, RdbException
  {
    etapok = szakasz.getEtapok();
  }

  /**
   * Visszaadja az etapok listáját. Ha a lista értéke <code>null</code>,
   * akkor az <code>updateEtapok()</code> művelet automatikusan végrehajtódik.
   */
  public List getEtapok () throws RdbException, GeoException
  {
    if ( etapok == null ) updateEtapok();
    return etapok;
  }

  /**
   * Frissíti a szlalomok listáját.
   */
  public void updateSzlalomok () throws GeoException, RdbException
  {
    szlalomok = szakasz.getSzlalomok();
  }

  /**
   * Visszaadja a szlalomok listáját. Ha a lista értéke <code>null</code>,
   * akkor az <code>updateSzlalomok()</code> művelet automatikusan végrehajtódik.
   */
  public List getSzlalomok () throws RdbException, GeoException
  {
    if ( szlalomok == null ) updateSzlalomok();
    return szlalomok;
  }

  /**
   * Megadja, hogy a szakaszon létezik-e szlalomverseny.
   */
  public boolean isHasAnySzlalom () throws GeoException, RdbException
  {
    return ( !getSzlalomok().isEmpty() );
  }

}
