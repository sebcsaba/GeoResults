package scs.georesults.om.eredmeny;

import scs.georesults.om.verseny.Szakasz;
import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class SzlalomOsszesitettEredmeny extends StorableEntityBase
{

  private long szid;

  private int rajtszam;

  private long vid;

  private long kategoria;

  private int pontszam;

  private int atvittPontszam;

  protected SzlalomOsszesitettEredmeny ()
  {
  }

  protected SzlalomOsszesitettEredmeny ( long szid, int rajtszam )
  {
    this.szid = szid;
    this.rajtszam = rajtszam;
  }

  protected SzlalomOsszesitettEredmeny ( long szid, int rajtszam, long vid, long kategoria, int pontszam, int atvittPontszam )
  {
    this.szid = szid;
    this.rajtszam = rajtszam;
    this.vid = vid;
    this.kategoria = kategoria;
    this.pontszam = pontszam;
    this.atvittPontszam = atvittPontszam;
  }

  public long getSzid ()
  {
    return szid;
  }

  public void setSzid ( long szid )
  {
    this.szid = szid;
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

  public long getKategoria ()
  {
    return kategoria;
  }

  public void setKategoria ( long kategoria )
  {
    this.kategoria = kategoria;
  }

  public int getPontszam ()
  {
    return pontszam;
  }

  public void setPontszam ( int pontszam )
  {
    this.pontszam = pontszam;
  }

  public int getAtvittPontszam ()
  {
    return atvittPontszam;
  }

  public void setAtvittPontszam ( int atvittPontszam )
  {
    this.atvittPontszam = atvittPontszam;
  }

  public static List loadAllForSzakasz ( RdbSession session, Szakasz szakasz ) throws RdbException
  {
    return loadAll( session, SzlalomOsszesitettEredmeny.class, "szid", szakasz );
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, SzlalomOsszesitettEredmeny.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, SzlalomOsszesitettEredmeny.class );
  }

  public static SzlalomOsszesitettEredmeny newInstance ()
  {
    return new SzlalomOsszesitettEredmenyImpl();
  }

  public static SzlalomOsszesitettEredmeny newInstance ( long szid, int rajtszam )
  {
    return new SzlalomOsszesitettEredmenyImpl( szid, rajtszam );
  }

  public static SzlalomOsszesitettEredmeny newInstance ( long szid, int rajtszam, long vid, long kategoria, int pontszam, int atvittPontszam )
  {
    return new SzlalomOsszesitettEredmenyImpl( szid, rajtszam, vid, kategoria, pontszam, atvittPontszam );
  }

}
