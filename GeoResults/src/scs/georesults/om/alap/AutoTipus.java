package scs.georesults.om.alap;

import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class AutoTipus extends StorableEntityBase
{

  private long atid;

  private long vid;

  private String nev;

  private long kategoria;

  protected AutoTipus ()
  {
  }

  protected AutoTipus ( long atid )
  {
    this.atid = atid;
  }

  protected AutoTipus ( long atid, long vid, String nev, long kategoria )
  {
    this.atid = atid;
    this.vid = vid;
    this.nev = nev;
    this.kategoria = kategoria;
  }

  public long getAtid ()
  {
    return atid;
  }

  public void setAtid ( long atid )
  {
    this.atid = atid;
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

  public long getKategoria ()
  {
    return kategoria;
  }

  public void setKategoria ( long kategoria )
  {
    this.kategoria = kategoria;
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, AutoTipus.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, AutoTipus.class );
  }

  public static AutoTipus newInstance ()
  {
    return new AutoTipusImpl();
  }

  public static AutoTipus newInstance ( long atid )
  {
    return new AutoTipusImpl( atid );
  }

  public static AutoTipus newInstance ( long atid, long vid, String nev, long kategoria )
  {
    return new AutoTipusImpl( atid, vid, nev, kategoria );
  }

}
