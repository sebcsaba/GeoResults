package scs.georesults.om.eredmeny;

import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class MindenSzlalomEredmeny extends StorableEntityBase
{

  private long vid;

  private int rajtszam;

  private long kategoria;

  private int pontszam;

  protected MindenSzlalomEredmeny ()
  {
  }

  protected MindenSzlalomEredmeny ( long vid, int rajtszam )
  {
    this.vid = vid;
    this.rajtszam = rajtszam;
  }

  protected MindenSzlalomEredmeny ( long vid, int rajtszam, long kategoria, int pontszam )
  {
    this.vid = vid;
    this.rajtszam = rajtszam;
    this.kategoria = kategoria;
    this.pontszam = pontszam;
  }

  public long getVid ()
  {
    return vid;
  }

  public void setVid ( long vid )
  {
    this.vid = vid;
  }

  public int getRajtszam ()
  {
    return rajtszam;
  }

  public void setRajtszam ( int rajtszam )
  {
    this.rajtszam = rajtszam;
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

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, MindenSzlalomEredmeny.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, MindenSzlalomEredmeny.class );
  }

  public static MindenSzlalomEredmeny newInstance ()
  {
    return new MindenSzlalomEredmenyImpl();
  }

  public static MindenSzlalomEredmeny newInstance ( long vid, int rajtszam )
  {
    return new MindenSzlalomEredmenyImpl( vid, rajtszam );
  }

  public static MindenSzlalomEredmeny newInstance ( long vid, int rajtszam, long kategoria, int pontszam )
  {
    return new MindenSzlalomEredmenyImpl( vid, rajtszam, kategoria, pontszam );
  }

}
