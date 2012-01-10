package scs.georesults.logic.actions;

import scs.javax.rdb.RdbException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebException;
import scs.georesults.common.Constants;
import scs.georesults.common.szotar.GlobalSzotar;
import scs.georesults.common.szotar.VersenySzotar;
import scs.georesults.om.kozos.Nyelv;

/**
 * <p>A program külső, versenytől független adatainak kezelésére szolgáló
 * szolgáltatások közös, absztrakt osztálya. Csupán néhány, az adatok
 * kezelésének megkönnyítését szolgáló metódust tartalmaz.</p>
 */
public abstract class KozosActionBase extends GeoActionBase
{

  /**
   * Frissíti a program aktuális szótárát az aktuális nyelv alapján.
   */
  protected void globalSzotarFrissites () throws WebException, RdbException
  {
    Nyelv nyelv = ( Nyelv ) getFromSession( Constants.SESSION_KEY_NYELV );
    globalSzotarFrissites( nyelv );
  }

  /**
   * Frissíti a program aktuális szótárát az adatbázisból,
   * a paraméterben megadott nyelv alapján.
   */
  protected void globalSzotarFrissites ( Nyelv nyelv ) throws WebException, RdbException
  {
    GlobalSzotar globalSzotar = new GlobalSzotar( getDb(), nyelv );
    setToSession( Constants.SESSION_KEY_GLOBAL_SZOTAR, globalSzotar );
  }

  /**
   * Frissíti a verseny szótárát az aktuális nyelv alapján.
   */
  protected void versenySzotarFrissites () throws WebException, RdbException
  {
    Nyelv nyelv = ( Nyelv ) getFromSession( Constants.SESSION_KEY_NYELV );
    versenySzotarFrissites( nyelv );
  }

  /**
   * Frissíti a verseny szótárát az adatbázisból,
   * a paraméterben megadott nyelv alapján.
   */
  protected void versenySzotarFrissites ( Nyelv nyelv ) throws WebException, RdbException
  {
    VersenySzotar versenySzotar = new VersenySzotar( getDb(), getVerseny(), nyelv );
    setToSession( Constants.SESSION_KEY_VERSENY_SZOTAR, versenySzotar );
  }

  /**
   * A paraméterben megadott nyelvet állítja be a program alapértelmezett nyelvének.
   */
  protected void nyelvFrissites ( Nyelv nyelv ) throws SessionTimeoutException
  {
    setToSession( Constants.SESSION_KEY_NYELV, nyelv );
  }

}
