package scs.georesults.om.eredmeny;

import scs.georesults.om.verseny.Etap;
import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class EtapEredmeny extends StorableEntityBase
{

  private long eid;

  private int rajtszam;

  private long vid;

  private int kesesPerc;

  private int kesesPont;

  private int buntetoPont;

  private int pontszam;

  private DarabFuggoFeladatEredmeny.Lista darabFuggoEredmenyek;

  private SorrendFuggoFeladatEredmeny.Lista sorrendFuggoEredmenyek;

  protected EtapEredmeny ()
  {
    darabFuggoEredmenyek = new DarabFuggoFeladatEredmeny.Lista();
    sorrendFuggoEredmenyek = new SorrendFuggoFeladatEredmeny.Lista();
  }

  protected EtapEredmeny ( long eid, int rajtszam )
  {
    darabFuggoEredmenyek = new DarabFuggoFeladatEredmeny.Lista();
    sorrendFuggoEredmenyek = new SorrendFuggoFeladatEredmeny.Lista();
    setEid( eid );
    setRajtszam( rajtszam );
  }

  protected EtapEredmeny ( long eid, int rajtszam, long vid, int kesesPerc, int kesesPont, int buntetoPont, int pontszam )
  {
    darabFuggoEredmenyek = new DarabFuggoFeladatEredmeny.Lista();
    sorrendFuggoEredmenyek = new SorrendFuggoFeladatEredmeny.Lista();
    setEid( eid );
    setRajtszam( rajtszam );
    setVid( vid );
    setKesesPerc( kesesPerc );
    setKesesPont( kesesPont );
    setBuntetoPont( buntetoPont );
    setPontszam( pontszam );
  }

  public DarabFuggoFeladatEredmeny.Lista getDarabFuggoEredmenyek ()
  {
    return darabFuggoEredmenyek;
  }

  public SorrendFuggoFeladatEredmeny.Lista getSorrendFuggoEredmenyek ()
  {
    return sorrendFuggoEredmenyek;
  }

  public long getEid ()
  {
    return eid;
  }

  public void setEid ( long eid )
  {
    this.eid = eid;
    darabFuggoEredmenyek.setEid( eid );
    sorrendFuggoEredmenyek.setEid( eid );
  }

  public int getRajtszam ()
  {
    return rajtszam;
  }

  public void setRajtszam ( int rajtszam )
  {
    this.rajtszam = rajtszam;
    darabFuggoEredmenyek.setRajtszam( rajtszam );
    sorrendFuggoEredmenyek.setRajtszam( rajtszam );
  }

  public long getVid ()
  {
    return vid;
  }

  public void setVid ( long vid )
  {
    this.vid = vid;
  }

  public int getKesesPerc ()
  {
    return kesesPerc;
  }

  public void setKesesPerc ( int kesesPerc )
  {
    this.kesesPerc = kesesPerc;
  }

  public int getKesesPont ()
  {
    return kesesPont;
  }

  public void setKesesPont ( int kesesPont )
  {
    this.kesesPont = kesesPont;
  }

  public int getBuntetoPont ()
  {
    return buntetoPont;
  }

  public void setBuntetoPont ( int buntetoPont )
  {
    this.buntetoPont = buntetoPont;
  }

  public int getPontszam ()
  {
    return pontszam;
  }

  public void setPontszam ( int pontszam )
  {
    this.pontszam = pontszam;
  }

  public static List loadAllForEtap ( RdbSession session, Etap etap ) throws RdbException
  {
    return loadAll( session, EtapEredmeny.class, "eid", etap );
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, EtapEredmeny.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, EtapEredmeny.class );
  }

  public static EtapEredmeny newInstance ()
  {
    return new EtapEredmenyImpl();
  }

  public static EtapEredmeny newInstance ( long eid, int rajtszam )
  {
    return new EtapEredmenyImpl( eid, rajtszam );
  }

  public static EtapEredmeny newInstance ( long eid, int rajtszam, long vid, int kesesPerc, int kesesPont, int buntetoPont, int pontszam )
  {
    return new EtapEredmenyImpl( eid, rajtszam, vid, kesesPerc, kesesPont, buntetoPont, pontszam );
  }

}
