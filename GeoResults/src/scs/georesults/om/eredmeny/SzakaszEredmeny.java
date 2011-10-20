package scs.georesults.om.eredmeny;

import scs.georesults.om.verseny.Szakasz;
import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class SzakaszEredmeny extends StorableEntityBase
{

  private long szid;

  private int rajtszam;

  private long vid;

  private boolean keses;

  private int pontszam;

  protected SzakaszEredmeny ()
  {
  }

  protected SzakaszEredmeny ( long szid, int rajtszam )
  {
    this.szid = szid;
    this.rajtszam = rajtszam;
  }

  protected SzakaszEredmeny ( long szid, int rajtszam, long vid, boolean keses, int pontszam )
  {
    this.szid = szid;
    this.rajtszam = rajtszam;
    this.vid = vid;
    this.keses = keses;
    this.pontszam = pontszam;
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

  public boolean isKeses ()
  {
    return keses;
  }

  public void setKeses ( boolean keses )
  {
    this.keses = keses;
  }

  public int getPontszam ()
  {
    return pontszam;
  }

  public void setPontszam ( int pontszam )
  {
    this.pontszam = pontszam;
  }

  public static List loadAllForSzakasz ( RdbSession session, Szakasz szakasz ) throws RdbException
  {
    return loadAll( session, SzakaszEredmeny.class, "szid", szakasz );
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, SzakaszEredmeny.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, SzakaszEredmeny.class );
  }

  public static SzakaszEredmeny newInstance ()
  {
    return new SzakaszEredmenyImpl();
  }

  public static SzakaszEredmeny newInstance ( long szid, int rajtszam )
  {
    return new SzakaszEredmenyImpl( szid, rajtszam );
  }

  public static SzakaszEredmeny newInstance ( long szid, int rajtszam, long vid, boolean keses, int pontszam )
  {
    return new SzakaszEredmenyImpl( szid, rajtszam, vid, keses, pontszam );
  }

}
