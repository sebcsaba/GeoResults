package scs.georesults.om.eredmeny;

import scs.georesults.om.alap.DarabFuggoFeladatTipus;
import scs.georesults.om.verseny.Etap;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.StorableObjectList;

public abstract class DarabFuggoFeladatEredmeny extends StorableEntityBase
{

  private long eid;

  private int rajtszam;

  private long dfftid;

  private int hiany;

  private int tobblet;

  private int pontszam;

  protected DarabFuggoFeladatEredmeny ()
  {
  }

  protected DarabFuggoFeladatEredmeny ( long eid, int rajtszam, long dfftid )
  {
    this.eid = eid;
    this.rajtszam = rajtszam;
    this.dfftid = dfftid;
  }

  protected DarabFuggoFeladatEredmeny ( long eid, int rajtszam, long dfftid, int hiany, int tobblet, int pontszam )
  {
    this.eid = eid;
    this.rajtszam = rajtszam;
    this.dfftid = dfftid;
    this.hiany = hiany;
    this.tobblet = tobblet;
    this.pontszam = pontszam;
  }

  public long getEid ()
  {
    return eid;
  }

  public void setEid ( long eid )
  {
    this.eid = eid;
  }

  public int getRajtszam ()
  {
    return rajtszam;
  }

  public void setRajtszam ( int rajtszam )
  {
    this.rajtszam = rajtszam;
  }

  public long getDfftid ()
  {
    return dfftid;
  }

  public void setDfftid ( long dfftid )
  {
    this.dfftid = dfftid;
  }

  public int getHiany ()
  {
    return hiany;
  }

  public void setHiany ( int hiany )
  {
    this.hiany = hiany;
  }

  public int getTobblet ()
  {
    return tobblet;
  }

  public void setTobblet ( int tobblet )
  {
    this.tobblet = tobblet;
  }

  public int getPontszam ()
  {
    return pontszam;
  }

  public void setPontszam ( int pontszam )
  {
    this.pontszam = pontszam;
  }

  public static class Lista extends StorableObjectList
  {

    private DarabFuggoFeladatEredmeny keyEntity = new DarabFuggoFeladatEredmenyImpl();

    protected Object getKeyEntity ()
    {
      return keyEntity;
    }

    public boolean add ( Object o )
    {
      DarabFuggoFeladatEredmeny entity = ( DarabFuggoFeladatEredmeny ) o;
      entity.setEid( getEid () );
      entity.setRajtszam( getRajtszam () );
      return super.add( entity );
    }

    public void setEid ( long eid )
    {
      keyEntity.setEid( eid );
      for ( int i = 0; i < size(); ++i ) {
        DarabFuggoFeladatEredmeny entity = ( DarabFuggoFeladatEredmeny ) get( i );
        entity.setEid( eid );
      }
    }

    public long getEid ()
    {
      return keyEntity.getEid();
    }

    public void setRajtszam ( int rajtszam )
    {
      keyEntity.setRajtszam( rajtszam );
      for ( int i = 0; i < size(); ++i ) {
        DarabFuggoFeladatEredmeny entity = ( DarabFuggoFeladatEredmeny ) get( i );
        entity.setRajtszam( rajtszam );
      }
    }

    public int getRajtszam ()
    {
      return keyEntity.getRajtszam();
    }

  }

  public static List loadAllForDarabFuggoFeladatTipus ( RdbSession session, DarabFuggoFeladatTipus darabfuggofeladattipus ) throws RdbException
  {
    return loadAll( session, DarabFuggoFeladatEredmeny.class, "dfftid", darabfuggofeladattipus );
  }

  public static List loadAllForEtap ( RdbSession session, Etap etap ) throws RdbException
  {
    return loadAll( session, DarabFuggoFeladatEredmeny.class, "eid", etap );
  }

  public static DarabFuggoFeladatEredmeny newInstance ()
  {
    return new DarabFuggoFeladatEredmenyImpl();
  }

  public static DarabFuggoFeladatEredmeny newInstance ( long eid, int rajtszam, long dfftid )
  {
    return new DarabFuggoFeladatEredmenyImpl( eid, rajtszam, dfftid );
  }

  public static DarabFuggoFeladatEredmeny newInstance ( long eid, int rajtszam, long dfftid, int hiany, int tobblet, int pontszam )
  {
    return new DarabFuggoFeladatEredmenyImpl( eid, rajtszam, dfftid, hiany, tobblet, pontszam );
  }

}
