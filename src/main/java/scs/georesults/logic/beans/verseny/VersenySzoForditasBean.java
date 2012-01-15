package scs.georesults.logic.beans.verseny;

import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.om.alap.VersenySzotarBejegyzes;
import scs.georesults.om.kozos.Nyelv;
import scs.georesults.om.kozos.NyelvImpl;
import scs.georesults.om.kozos.SzotarBejegyzes;

/**
 * <p>A verseny szótárának kezelését segítő osztály.</p>
 */
public class VersenySzoForditasBean
{

  /**
   * Az aktuálisan használt nyelv
   */
  private Nyelv aktualisNyelv;

  /**
   * A fordítást mint szótárbejegyzést tartalmazó verseny alapnyelve
   */
  private String alapNyelv;

  /**
   * A fordítást mint szótárbejegyzést tartalmazó verseny azonosítója
   */
  private long vid;

  /**
   * A fordítás mint szótárbejegyzés azonosítója
   */
  private long vszbid;

  /**
   * A fordítás nyelvének kódja
   */
  private String lang;

  /**
   * Igaz, ha a bejegyzés még nem szerepel az adatbázisban
   */
  private boolean create;

  public VersenySzoForditasBean ( Nyelv aktualisNyelv, String alapNyelv, String lang, long vid, long vszbid, boolean create )
  {
    this.aktualisNyelv = aktualisNyelv;
    this.alapNyelv = alapNyelv;
    this.vid = vid;
    this.vszbid = vszbid;
    this.lang = lang;
    this.create = create;
  }

  /**
   * Az aktuális nyelv nevét adja meg (ahogy az magát nevezi)
   */
  public String getNyelvNeve () throws GeoException, RdbException
  {
    SzotarBejegyzes szb = SzotarBejegyzes.newInstance( aktualisNyelv.getLang(), NyelvImpl.BUNDLE_PREFIX + lang );
    szb.read( GeoDbSession.getCurrentInstance() );
    return szb.getValuestr();
  }

  /**
   * Megadja a szó fordítását az aktuális nyelven
   */
  public String getSzoAzAktualisNyelven () throws GeoException, RdbException
  {
    VersenySzotarBejegyzes vszb = VersenySzotarBejegyzes.newInstance( vid, vszbid, aktualisNyelv.getLang() );
    vszb.read( GeoDbSession.getCurrentInstance() );
    return vszb.getFelirat();
  }

  /**
   * Megadja a szó fordítását a verseny alapnyelvén
   */
  public String getSzoAzAlapNyelven () throws GeoException, RdbException
  {
    VersenySzotarBejegyzes vszb = VersenySzotarBejegyzes.newInstance( vid, vszbid, alapNyelv );
    vszb.read( GeoDbSession.getCurrentInstance() );
    return vszb.getFelirat();
  }

  /**
   * Megadja a fordítás adatbázisban található értékét.
   *
   * @return A fordítás értéke az adatbázisban, vagy <code>null</code> ha nincs ilyen objektum az adatbázisban.
   */
  public String getSzo () throws GeoException, RdbException
  {
    if ( create ) {
      return null;
    } else {
      VersenySzotarBejegyzes vszb = VersenySzotarBejegyzes.newInstance( vid, vszbid, lang );
      vszb.read( GeoDbSession.getCurrentInstance() );
      return vszb.getFelirat();
    }
  }

  public long getVszbid ()
  {
    return vszbid;
  }

  public String getLang ()
  {
    return lang;
  }

  public boolean isCreate ()
  {
    return create;
  }

  public long getVid ()
  {
    return vid;
  }

}
