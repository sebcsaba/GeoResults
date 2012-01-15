package scs.georesults.om.alap;

import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class SzlalomFeladat extends StorableEntityBase
{

  private long szfid;

  private long vid;

  private String nev;

  private int pont;

  protected SzlalomFeladat ()
  {
  }

  protected SzlalomFeladat ( long szfid )
  {
    this.szfid = szfid;
  }

  protected SzlalomFeladat ( long szfid, long vid, String nev, int pont )
  {
    this.szfid = szfid;
    this.vid = vid;
    this.nev = nev;
    this.pont = pont;
  }

  public long getSzfid ()
  {
    return szfid;
  }

  public void setSzfid ( long szfid )
  {
    this.szfid = szfid;
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
    return loadAll( session, SzlalomFeladat.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, SzlalomFeladat.class );
  }

  public static SzlalomFeladat newInstance ()
  {
    return new SzlalomFeladatImpl();
  }

  public static SzlalomFeladat newInstance ( long szfid )
  {
    return new SzlalomFeladatImpl( szfid );
  }

  public static SzlalomFeladat newInstance ( long szfid, long vid, String nev, int pont )
  {
    return new SzlalomFeladatImpl( szfid, vid, nev, pont );
  }

}
