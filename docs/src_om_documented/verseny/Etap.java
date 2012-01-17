package scs.georesults.om.verseny;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class Etap extends StorableEntityBase
{

  private long eid;

  private long szid;

  private long vid;

  private String nev;

  private int idoLimit;

  private String menetlevelformula;

  private boolean ertekelendo;

  private boolean eredmenyFrissitendo;

  protected Etap ()
  {
  }

  protected Etap ( long eid )
  {
    this.eid = eid;
  }

  protected Etap ( long eid, long szid, long vid, String nev, int idoLimit, String menetlevelformula, boolean ertekelendo, boolean eredmenyFrissitendo )
  {
    this.eid = eid;
    this.szid = szid;
    this.vid = vid;
    this.nev = nev;
    this.idoLimit = idoLimit;
    this.menetlevelformula = menetlevelformula;
    this.ertekelendo = ertekelendo;
    this.eredmenyFrissitendo = eredmenyFrissitendo;
  }

  public long getEid ()
  {
    return eid;
  }

  public void setEid ( long eid )
  {
    this.eid = eid;
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

  public int getIdoLimit ()
  {
    return idoLimit;
  }

  public void setIdoLimit ( int idoLimit )
  {
    this.idoLimit = idoLimit;
  }

  public String getMenetlevelformula ()
  {
    return menetlevelformula;
  }

  public void setMenetlevelformula ( String menetlevelformula )
  {
    this.menetlevelformula = menetlevelformula;
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
    return loadAll( session, Etap.class, "szid", szakasz );
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, Etap.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, Etap.class );
  }

  public static Etap newInstance ()
  {
    return new EtapImpl();
  }

  public static Etap newInstance ( long eid )
  {
    return new EtapImpl( eid );
  }

  public static Etap newInstance ( long eid, long szid, long vid, String nev, int idoLimit, String menetlevelformula, boolean ertekelendo, boolean eredmenyFrissitendo )
  {
    return new EtapImpl( eid, szid, vid, nev, idoLimit, menetlevelformula, ertekelendo, eredmenyFrissitendo );
  }

}
