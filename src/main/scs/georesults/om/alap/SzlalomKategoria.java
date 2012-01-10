package scs.georesults.om.alap;

import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class SzlalomKategoria extends StorableEntityBase
{

  private long szkid;

  private long vid;

  private String nev;

  protected SzlalomKategoria ()
  {
  }

  protected SzlalomKategoria ( long szkid )
  {
    this.szkid = szkid;
  }

  protected SzlalomKategoria ( long szkid, long vid, String nev )
  {
    this.szkid = szkid;
    this.vid = vid;
    this.nev = nev;
  }

  public long getSzkid ()
  {
    return szkid;
  }

  public void setSzkid ( long szkid )
  {
    this.szkid = szkid;
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

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, SzlalomKategoria.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, SzlalomKategoria.class );
  }

  public static SzlalomKategoria newInstance ()
  {
    return new SzlalomKategoriaImpl();
  }

  public static SzlalomKategoria newInstance ( long szkid )
  {
    return new SzlalomKategoriaImpl( szkid );
  }

  public static SzlalomKategoria newInstance ( long szkid, long vid, String nev )
  {
    return new SzlalomKategoriaImpl( szkid, vid, nev );
  }

}
