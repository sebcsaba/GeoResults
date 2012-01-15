package scs.georesults.om.eredmeny;

import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class MindenEtapEredmeny extends StorableEntityBase
{

  private long vid;

  private int rajtszam;

  private int pontszam;

  protected MindenEtapEredmeny ()
  {
  }

  protected MindenEtapEredmeny ( long vid, int rajtszam )
  {
    this.vid = vid;
    this.rajtszam = rajtszam;
  }

  protected MindenEtapEredmeny ( long vid, int rajtszam, int pontszam )
  {
    this.vid = vid;
    this.rajtszam = rajtszam;
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
    return loadAll( session, MindenEtapEredmeny.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, MindenEtapEredmeny.class );
  }

  public static MindenEtapEredmeny newInstance ()
  {
    return new MindenEtapEredmenyImpl();
  }

  public static MindenEtapEredmeny newInstance ( long vid, int rajtszam )
  {
    return new MindenEtapEredmenyImpl( vid, rajtszam );
  }

  public static MindenEtapEredmeny newInstance ( long vid, int rajtszam, int pontszam )
  {
    return new MindenEtapEredmenyImpl( vid, rajtszam, pontszam );
  }

}
