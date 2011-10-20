package scs.georesults.om.alap;

import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class BuntetesTipus extends StorableEntityBase
{

  private long btid;

  private long vid;

  private String nev;

  private int pont;

  protected BuntetesTipus ()
  {
  }

  protected BuntetesTipus ( long btid )
  {
    this.btid = btid;
  }

  protected BuntetesTipus ( long btid, long vid, String nev, int pont )
  {
    this.btid = btid;
    this.vid = vid;
    this.nev = nev;
    this.pont = pont;
  }

  public long getBtid ()
  {
    return btid;
  }

  public void setBtid ( long btid )
  {
    this.btid = btid;
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

  public int getPont ()
  {
    return pont;
  }

  public void setPont ( int pont )
  {
    this.pont = pont;
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, BuntetesTipus.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, BuntetesTipus.class );
  }

  public static BuntetesTipus newInstance ()
  {
    return new BuntetesTipusImpl();
  }

  public static BuntetesTipus newInstance ( long btid )
  {
    return new BuntetesTipusImpl( btid );
  }

  public static BuntetesTipus newInstance ( long btid, long vid, String nev, int pont )
  {
    return new BuntetesTipusImpl( btid, vid, nev, pont );
  }

}
