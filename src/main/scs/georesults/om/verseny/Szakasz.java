package scs.georesults.om.verseny;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class Szakasz extends StorableEntityBase
{

  private long szid;

  private long vid;

  private String nev;

  private int megengedettKesesEtaponkent;

  private int kesesertBuntetoPont;

  private boolean szlalomRedukaltPontokkal;

  private boolean ertekelendo;

  private boolean eredmenyFrissitendo;

  protected Szakasz ()
  {
  }

  protected Szakasz ( long szid )
  {
    this.szid = szid;
  }

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

  public long getSzid ()
  {
    return szid;
  }

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
