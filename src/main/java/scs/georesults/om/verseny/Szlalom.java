package scs.georesults.om.verseny;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class Szlalom extends StorableEntityBase
{

  private long szlid;

  private long szid;

  private long vid;

  private String nev;

  private boolean ertekelendo;

  private boolean eredmenyFrissitendo;

  protected Szlalom ()
  {
  }

  protected Szlalom ( long szlid )
  {
    this.szlid = szlid;
  }

  protected Szlalom ( long szlid, long szid, long vid, String nev, boolean ertekelendo, boolean eredmenyFrissitendo )
  {
    this.szlid = szlid;
    this.szid = szid;
    this.vid = vid;
    this.nev = nev;
    this.ertekelendo = ertekelendo;
    this.eredmenyFrissitendo = eredmenyFrissitendo;
  }

  public long getSzlid ()
  {
    return szlid;
  }

  public void setSzlid ( long szlid )
  {
    this.szlid = szlid;
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

  public static List loadAllForSzakasz ( RdbSession session, Szakasz szakasz ) throws RdbException
  {
    return loadAll( session, Szlalom.class, "szid", szakasz );
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, Szlalom.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, Szlalom.class );
  }

  public static Szlalom newInstance ()
  {
    return new SzlalomImpl();
  }

  public static Szlalom newInstance ( long szlid )
  {
    return new SzlalomImpl( szlid );
  }

  public static Szlalom newInstance ( long szlid, long szid, long vid, String nev, boolean ertekelendo, boolean eredmenyFrissitendo )
  {
    return new SzlalomImpl( szlid, szid, vid, nev, ertekelendo, eredmenyFrissitendo );
  }

}
