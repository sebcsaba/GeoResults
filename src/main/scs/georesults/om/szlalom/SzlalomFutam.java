package scs.georesults.om.szlalom;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;
import scs.georesults.om.verseny.Szlalom;
import scs.georesults.om.verseny.Verseny;

public abstract class SzlalomFutam extends StorableEntityBase
{

  private long szlid;

  private int rajtszam;

  private long vid;

  private SzlalomBejegyzes.Lista bejegyzesek;

  protected SzlalomFutam ()
  {
    bejegyzesek = new SzlalomBejegyzes.Lista();
  }

  protected SzlalomFutam ( long szlid, int rajtszam )
  {
    bejegyzesek = new SzlalomBejegyzes.Lista();
    setSzlid( szlid );
    setRajtszam( rajtszam );
  }

  protected SzlalomFutam ( long szlid, int rajtszam, long vid )
  {
    bejegyzesek = new SzlalomBejegyzes.Lista();
    setSzlid( szlid );
    setRajtszam( rajtszam );
    setVid( vid );
  }

  public SzlalomBejegyzes.Lista getBejegyzesek ()
  {
    return bejegyzesek;
  }

  public long getSzlid ()
  {
    return szlid;
  }

  public void setSzlid ( long szlid )
  {
    this.szlid = szlid;
    bejegyzesek.setSzlid( szlid );
  }

  public int getRajtszam ()
  {
    return rajtszam;
  }

  public void setRajtszam ( int rajtszam )
  {
    this.rajtszam = rajtszam;
    bejegyzesek.setRajtszam( rajtszam );
  }

  public long getVid ()
  {
    return vid;
  }

  public void setVid ( long vid )
  {
    this.vid = vid;
  }

  public static List loadAllForSzlalom ( RdbSession session, Szlalom szlalom ) throws RdbException
  {
    return loadAll( session, SzlalomFutam.class, "szlid", szlalom );
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, SzlalomFutam.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, SzlalomFutam.class );
  }

  public static SzlalomFutam newInstance ()
  {
    return new SzlalomFutamImpl();
  }

  public static SzlalomFutam newInstance ( long szlid, int rajtszam )
  {
    return new SzlalomFutamImpl( szlid, rajtszam );
  }

  public static SzlalomFutam newInstance ( long szlid, int rajtszam, long vid )
  {
    return new SzlalomFutamImpl( szlid, rajtszam, vid );
  }

}
