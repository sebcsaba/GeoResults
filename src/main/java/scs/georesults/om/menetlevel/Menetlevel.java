package scs.georesults.om.menetlevel;

import scs.georesults.om.verseny.Etap;
import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.lang.Time;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class Menetlevel extends StorableEntityBase
{

  private long eid;

  private int rajtszam;

  private long vid;

  private Time indulasiIdo;

  private Time erkezesiIdo;

  private DarabFuggoFeladatMegoldas.Lista darabFuggoMegoldasok;

  private Buntetes.Lista buntetesek;

  private SorrendFuggoFeladatMegoldas.Lista sorrendFuggoMegoldasok;

  protected Menetlevel ()
  {
    darabFuggoMegoldasok = new DarabFuggoFeladatMegoldas.Lista();
    buntetesek = new Buntetes.Lista();
    sorrendFuggoMegoldasok = new SorrendFuggoFeladatMegoldas.Lista();
  }

  protected Menetlevel ( long eid, int rajtszam )
  {
    darabFuggoMegoldasok = new DarabFuggoFeladatMegoldas.Lista();
    buntetesek = new Buntetes.Lista();
    sorrendFuggoMegoldasok = new SorrendFuggoFeladatMegoldas.Lista();
    setEid( eid );
    setRajtszam( rajtszam );
  }

  protected Menetlevel ( long eid, int rajtszam, long vid, Time indulasiIdo, Time erkezesiIdo )
  {
    darabFuggoMegoldasok = new DarabFuggoFeladatMegoldas.Lista();
    buntetesek = new Buntetes.Lista();
    sorrendFuggoMegoldasok = new SorrendFuggoFeladatMegoldas.Lista();
    setEid( eid );
    setRajtszam( rajtszam );
    setVid( vid );
    setIndulasiIdo( indulasiIdo );
    setErkezesiIdo( erkezesiIdo );
  }

  public DarabFuggoFeladatMegoldas.Lista getDarabFuggoMegoldasok ()
  {
    return darabFuggoMegoldasok;
  }

  public Buntetes.Lista getBuntetesek ()
  {
    return buntetesek;
  }

  public SorrendFuggoFeladatMegoldas.Lista getSorrendFuggoMegoldasok ()
  {
    return sorrendFuggoMegoldasok;
  }

  public long getEid ()
  {
    return eid;
  }

  public void setEid ( long eid )
  {
    this.eid = eid;
    darabFuggoMegoldasok.setEid( eid );
    buntetesek.setEid( eid );
    sorrendFuggoMegoldasok.setEid( eid );
  }

  public int getRajtszam ()
  {
    return rajtszam;
  }

  public void setRajtszam ( int rajtszam )
  {
    this.rajtszam = rajtszam;
    darabFuggoMegoldasok.setRajtszam( rajtszam );
    buntetesek.setRajtszam( rajtszam );
    sorrendFuggoMegoldasok.setRajtszam( rajtszam );
  }

  public long getVid ()
  {
    return vid;
  }

  public void setVid ( long vid )
  {
    this.vid = vid;
  }

  public Time getIndulasiIdo ()
  {
    return indulasiIdo;
  }

  public void setIndulasiIdo ( Time indulasiIdo )
  {
    this.indulasiIdo = indulasiIdo;
  }

  public Time getErkezesiIdo ()
  {
    return erkezesiIdo;
  }

  public void setErkezesiIdo ( Time erkezesiIdo )
  {
    this.erkezesiIdo = erkezesiIdo;
  }

  public static List loadAllForEtap ( RdbSession session, Etap etap ) throws RdbException
  {
    return loadAll( session, Menetlevel.class, "eid", etap );
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, Menetlevel.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, Menetlevel.class );
  }

  public static Menetlevel newInstance ()
  {
    return new MenetlevelImpl();
  }

  public static Menetlevel newInstance ( long eid, int rajtszam )
  {
    return new MenetlevelImpl( eid, rajtszam );
  }

  public static Menetlevel newInstance ( long eid, int rajtszam, long vid, Time indulasiIdo, Time erkezesiIdo )
  {
    return new MenetlevelImpl( eid, rajtszam, vid, indulasiIdo, erkezesiIdo );
  }

}
