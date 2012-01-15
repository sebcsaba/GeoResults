package scs.georesults.om.eredmeny;

import scs.georesults.om.verseny.Szlalom;
import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class SzlalomEredmeny extends StorableEntityBase
{

  private long szlid;

  private int rajtszam;

  private long vid;

  private int pontszam;

  private long kategoria;

  protected SzlalomEredmeny ()
  {
  }

  protected SzlalomEredmeny ( long szlid, int rajtszam )
  {
    this.szlid = szlid;
    this.rajtszam = rajtszam;
  }

  protected SzlalomEredmeny ( long szlid, int rajtszam, long vid, int pontszam, long kategoria )
  {
    this.szlid = szlid;
    this.rajtszam = rajtszam;
    this.vid = vid;
    this.pontszam = pontszam;
    this.kategoria = kategoria;
  }

  public long getSzlid ()
  {
    return szlid;
  }

  public void setSzlid ( long szlid )
  {
    this.szlid = szlid;
  }

  public int getRajtszam ()
  {
    return rajtszam;
  }

  public void setRajtszam ( int rajtszam )
  {
    this.rajtszam = rajtszam;
  }

  public long getVid ()
  {
    return vid;
  }

  public void setVid ( long vid )
  {
    this.vid = vid;
  }

  public int getPontszam ()
  {
    return pontszam;
  }

  public void setPontszam ( int pontszam )
  {
    this.pontszam = pontszam;
  }

  public long getKategoria ()
  {
    return kategoria;
  }

  public void setKategoria ( long kategoria )
  {
    this.kategoria = kategoria;
  }

  public static List loadAllForSzlalom ( RdbSession session, Szlalom szlalom ) throws RdbException
  {
    return loadAll( session, SzlalomEredmeny.class, "szlid", szlalom );
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, SzlalomEredmeny.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, SzlalomEredmeny.class );
  }

  public static SzlalomEredmeny newInstance ()
  {
    return new SzlalomEredmenyImpl();
  }

  public static SzlalomEredmeny newInstance ( long szlid, int rajtszam )
  {
    return new SzlalomEredmenyImpl( szlid, rajtszam );
  }

  public static SzlalomEredmeny newInstance ( long szlid, int rajtszam, long vid, int pontszam, long kategoria )
  {
    return new SzlalomEredmenyImpl( szlid, rajtszam, vid, pontszam, kategoria );
  }

}
