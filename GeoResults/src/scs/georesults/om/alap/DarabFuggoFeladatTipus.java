package scs.georesults.om.alap;

import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class DarabFuggoFeladatTipus extends StorableEntityBase
{

  private long dfftid;

  private long vid;

  private String nev;

  private int hianyHibapont;

  private int tobbletHibapont;

  protected DarabFuggoFeladatTipus ()
  {
  }

  protected DarabFuggoFeladatTipus ( long dfftid )
  {
    this.dfftid = dfftid;
  }

  protected DarabFuggoFeladatTipus ( long dfftid, long vid, String nev, int hianyHibapont, int tobbletHibapont )
  {
    this.dfftid = dfftid;
    this.vid = vid;
    this.nev = nev;
    this.hianyHibapont = hianyHibapont;
    this.tobbletHibapont = tobbletHibapont;
  }

  public long getDfftid ()
  {
    return dfftid;
  }

  public void setDfftid ( long dfftid )
  {
    this.dfftid = dfftid;
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

  public int getHianyHibapont ()
  {
    return hianyHibapont;
  }

  public void setHianyHibapont ( int hianyHibapont )
  {
    this.hianyHibapont = hianyHibapont;
  }

  public int getTobbletHibapont ()
  {
    return tobbletHibapont;
  }

  public void setTobbletHibapont ( int tobbletHibapont )
  {
    this.tobbletHibapont = tobbletHibapont;
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, DarabFuggoFeladatTipus.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, DarabFuggoFeladatTipus.class );
  }

  public static DarabFuggoFeladatTipus newInstance ()
  {
    return new DarabFuggoFeladatTipusImpl();
  }

  public static DarabFuggoFeladatTipus newInstance ( long dfftid )
  {
    return new DarabFuggoFeladatTipusImpl( dfftid );
  }

  public static DarabFuggoFeladatTipus newInstance ( long dfftid, long vid, String nev, int hianyHibapont, int tobbletHibapont )
  {
    return new DarabFuggoFeladatTipusImpl( dfftid, vid, nev, hianyHibapont, tobbletHibapont );
  }

}
