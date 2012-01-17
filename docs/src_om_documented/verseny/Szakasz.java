package scs.georesults.om.verseny;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

/**
 * <p>A verseny egy szakaszát reprezentáló osztály.</p>
 */
public abstract class Szakasz extends StorableEntityBase
{

  /**
   * A szakasz azonosítója
   */
  private long szid;

  /**
   * A szakaszt tartalmazó verseny azonosítója
   */
  private long vid;

  /**
   * A szakasz neve. (Szótári szóra mutató hivatkozást is tartalmazhat.)
   */
  private String nev;

  /**
   * A szakasz megengedett maximális késés etapokra jutó egysége, percben megadva
   */
  private int megengedettKesesEtaponkent;

  /**
   * A megengedett maximálisnál nagyobb késés esetén kapott pontszám
   */
  private int kesesertBuntetoPont;

  /**
   * Azt jelzi, hogy a szakaszhoz tartozó szlalomok eredménye redukálva
   * számítson-e bele a végeredménybe
   */
  private boolean szlalomRedukaltPontokkal;

  /**
   * Azt jelzi, hogy a szakasz eredménye beleszámítson-e a végeredménybe
   */
  private boolean ertekelendo;

  /**
   * Azt jelzi, hogy a szakasz végeredményét tartalmazó
   * eredménylista frissítése szükséges
   */
  private boolean eredmenyFrissitendo;

  /**
   * Egy új, üres objektumot hoz létre.
   */
  protected Szakasz ()
  {
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméter alapján.
   *
   * @param szid A szakasz azonosítója
   */
  protected Szakasz ( long szid )
  {
    this.szid = szid;
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméterek alapján.
   *
   * @param szid A szakasz azonosítója
   * @param vid A szakaszt tartalmazó verseny azonosítója
   * @param nev A szakasz neve
   * @param megengedettKesesEtaponkent A szakaszon etaponként megengedett maximális késés
   * @param kesesertBuntetoPont A maximálison túli késésért járó büntetőpont
   * @param szlalomRedukaltPontokkal Azt jelzi, hogy a szlalomok eredményét redukált pontokkal számítsa-e
   * @param ertekelendo Azt jlzi, hogy a szakasz eredménye az összesítésbe beleszámítson-e
   * @param eredmenyFrissitendo Azt jelzi, hogy a szakasz végeredménye frissítendő
   */
  protected Szakasz ( long szid, long vid, String nev, int megengedettKesesEtaponkent, int kesesertBuntetoPont, boolean szlalomRedukaltPontokkal, boolean ertekelendo, boolean eredmenyFrissitendo )
  {
    this.szid = szid;
    this.vid = vid;
    this.nev = nev;
    this.megengedettKesesEtaponkent = megengedettKesesEtaponkent;
    this.kesesertBuntetoPont = kesesertBuntetoPont;
    this.szlalomRedukaltPontokkal = szlalomRedukaltPontokkal;
    this.ertekelendo = ertekelendo;
    this.eredmenyFrissitendo = eredmenyFrissitendo;
  }

  /**
   * A szakasz azonostóját adja vissza
   *
   * @return long
   */
  public long getSzid ()
  {
    return szid;
  }

  /**
   * A szakasz azonosítóját állítja be
   *
   * @param szid A szakasz új azonosítója
   */
  public void setSzid ( long szid )
  {
    this.szid = szid;
  }

  public long getVid ()
  {
    return vid;
  }

  public void setVid ( long vid )
  {
    this.vid = vid;
  }

  public String getNev ()
  {
    return nev;
  }

  public void setNev ( String nev )
  {
    this.nev = nev;
  }

  public int getMegengedettKesesEtaponkent ()
  {
    return megengedettKesesEtaponkent;
  }

  public void setMegengedettKesesEtaponkent ( int megengedettKesesEtaponkent )
  {
    this.megengedettKesesEtaponkent = megengedettKesesEtaponkent;
  }

  public int getKesesertBuntetoPont ()
  {
    return kesesertBuntetoPont;
  }

  public void setKesesertBuntetoPont ( int kesesertBuntetoPont )
  {
    this.kesesertBuntetoPont = kesesertBuntetoPont;
  }

  public boolean isSzlalomRedukaltPontokkal ()
  {
    return szlalomRedukaltPontokkal;
  }

  public void setSzlalomRedukaltPontokkal ( boolean szlalomRedukaltPontokkal )
  {
    this.szlalomRedukaltPontokkal = szlalomRedukaltPontokkal;
  }

  public boolean isErtekelendo ()
  {
    return ertekelendo;
  }

  public void setErtekelendo ( boolean ertekelendo )
  {
    this.ertekelendo = ertekelendo;
  }

  public boolean isEredmenyFrissitendo ()
  {
    return eredmenyFrissitendo;
  }

  public void setEredmenyFrissitendo ( boolean eredmenyFrissitendo )
  {
    this.eredmenyFrissitendo = eredmenyFrissitendo;
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, Szakasz.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, Szakasz.class );
  }

  public static Szakasz newInstance ()
  {
    return new SzakaszImpl();
  }

  public static Szakasz newInstance ( long szid )
  {
    return new SzakaszImpl( szid );
  }

  public static Szakasz newInstance ( long szid, long vid, String nev, int megengedettKesesEtaponkent, int kesesertBuntetoPont, boolean szlalomRedukaltPontokkal, boolean ertekelendo, boolean eredmenyFrissitendo )
  {
    return new SzakaszImpl( szid, vid, nev, megengedettKesesEtaponkent, kesesertBuntetoPont, szlalomRedukaltPontokkal, ertekelendo, eredmenyFrissitendo );
  }

}
