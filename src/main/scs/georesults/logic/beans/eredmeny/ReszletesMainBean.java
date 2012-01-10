package scs.georesults.logic.beans.eredmeny;

import java.util.Map;
import scs.javax.collections.AutoInsertHashMap;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.web.RequestBeanBase;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebSession;
import scs.georesults.GeoException;
import scs.georesults.common.Constants;
import scs.georesults.om.verseny.Etap;
import scs.georesults.om.verseny.VersenyImpl;

/**
 * <p>A részletes eredménylista megjelenését konfiguráló lap megjelenítését segítő osztály.</p>
 */
public class ReszletesMainBean extends RequestBeanBase
{

  /**
   * A verseny etapjainak listája.
   */
  private List etapok;

  /**
   * Egy olyan aszociatív tömb, amely az etapok azonosítójához {@link EtapFlags}
   * objektumokat rendel. Ez alapján fogja a megjelenítő lap összeállítani az eredménylistát.
   */
  private Map etapFlags; // Integer[eid] -> EtapFlags

  /**
   * Jelzi, ha bármely etap eredményét frissíteni kell. Ekkor a lap nem jelenik meg.
   */
  private boolean barmelyEtapFrissitendo;

  /**
   * Egy új objektumot hoz létre.
   */
  public ReszletesMainBean ()
  {
    etapFlags = new AutoInsertHashMap( EtapFlags.class );
  }

  /**
   * Inicializálja az objektumot
   */
  public void init () throws GeoException, RdbException, SessionTimeoutException
  {
    updateEtapok();
    updateBarmelyEtapFrissitendo();
  }

  /**
   * Igaz, ha a a HTTP hívás paraméterei között szerepel a <code>openJavaScript</code> érték.
   * Ha ez igaz, akkor a megjelenítő lap automatikusan megnyitja felugró albakként az
   * eredménylistát.
   */
  public boolean isOpenJavascript ()
  {
    return pageContext.getRequest().getAttribute( "openJavaScript" ) != null;
  }

  public boolean isBarmelyEtapFrissitendo ()
  {
    return barmelyEtapFrissitendo;
  }

  public List getEtapok ()
  {
    return etapok;
  }

  public Map getEtapFlags ()
  {
    return etapFlags;
  }

  /**
   * Frissíti az etapok listáját.
   */
  private void updateEtapok () throws GeoException, RdbException, SessionTimeoutException
  {
    VersenyImpl verseny = ( VersenyImpl ) WebSession.justGetHttpSession( pageContext ).getAttribute( Constants.SESSION_KEY_VERSENY );
    etapok = verseny.getEtapok();
  }

  /**
   * Frissíti a <code>barmelyEtapFrissitendo</code> mező értékét.
   */
  private void updateBarmelyEtapFrissitendo () throws GeoException
  {
    for ( int i = 0; i < etapok.size(); ++i ) {
      Etap e = ( Etap ) etapok.get( i );
      if ( e.isErtekelendo() ) {
        if ( e.isEredmenyFrissitendo() ) {
          barmelyEtapFrissitendo = true;
          return;
        }
      }
    }
    barmelyEtapFrissitendo = false;
  }

  /**
   * <p>Az egy etap feladataihoz igaz vagy hamis értékeket rendelő osztály.
   * Ezt hasnzálja a {@link ReszletesMainBean} osztály arra, hogy jelezze,
   * mely feladatoknál kell részletes megjelenítést alkalmaznia.</p>
   */
  public static class EtapFlags
  {

    /**
     * A sorrendfüggő feladatok tömbje
     */
    private Map sfef;

    /**
     * A darabszám-függő feladatok tömbje
     */
    private Map dfef;

    public EtapFlags ()
    {
      dfef = new AutoInsertHashMap( Boolean.FALSE );
      sfef = new AutoInsertHashMap( Boolean.TRUE );
    }

    public Map getSfef ()
    {
      return sfef;
    }

    public Map getDfef ()
    {
      return dfef;
    }

  }

}
