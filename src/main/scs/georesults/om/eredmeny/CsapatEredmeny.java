package scs.georesults.om.eredmeny;

import scs.georesults.om.nevezes.CsapatNevezes;
import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class CsapatEredmeny extends StorableEntityBase
{

  private long csnid;

  private long vid;

  private String nev;

  private int rajtszam1;

  private int rajtszam2;

  private int rajtszam3;

  private int pontszam;

  protected CsapatEredmeny ()
  {
  }

  protected CsapatEredmeny ( long csnid )
  {
    this.csnid = csnid;
  }

  protected CsapatEredmeny ( long csnid, long vid, String nev, int rajtszam1, int rajtszam2, int rajtszam3, int pontszam )
  {
    this.csnid = csnid;
    this.vid = vid;
    this.nev = nev;
    this.rajtszam1 = rajtszam1;
    this.rajtszam2 = rajtszam2;
    this.rajtszam3 = rajtszam3;
    this.pontszam = pontszam;
  }

  public long getCsnid ()
  {
    return csnid;
  }

  public void setCsnid ( long csnid )
  {
    this.csnid = csnid;
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

  public int getRajtszam1 ()
  {
    return rajtszam1;
  }

  public void setRajtszam1 ( int rajtszam1 )
  {
    this.rajtszam1 = rajtszam1;
  }

  public int getRajtszam2 ()
  {
    return rajtszam2;
  }

  public void setRajtszam2 ( int rajtszam2 )
  {
    this.rajtszam2 = rajtszam2;
  }

  public int getRajtszam3 ()
  {
    return rajtszam3;
  }

  public void setRajtszam3 ( int rajtszam3 )
  {
    this.rajtszam3 = rajtszam3;
  }

  public int getPontszam ()
  {
    return pontszam;
  }

  public void setPontszam ( int pontszam )
  {
    this.pontszam = pontszam;
  }

  public static List loadAllForCsapatNevezes ( RdbSession session, CsapatNevezes csapatnevezes ) throws RdbException
  {
    return loadAll( session, CsapatEredmeny.class, "csnid", csapatnevezes );
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, CsapatEredmeny.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, CsapatEredmeny.class );
  }

  public static CsapatEredmeny newInstance ()
  {
    return new CsapatEredmenyImpl();
  }

  public static CsapatEredmeny newInstance ( long csnid )
  {
    return new CsapatEredmenyImpl( csnid );
  }

  public static CsapatEredmeny newInstance ( long csnid, long vid, String nev, int rajtszam1, int rajtszam2, int rajtszam3, int pontszam )
  {
    return new CsapatEredmenyImpl( csnid, vid, nev, rajtszam1, rajtszam2, rajtszam3, pontszam );
  }

}
