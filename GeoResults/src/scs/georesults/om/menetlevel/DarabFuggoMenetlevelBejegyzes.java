package scs.georesults.om.menetlevel;

import scs.georesults.om.alap.DarabFuggoFeladatTipus;
import scs.georesults.om.verseny.Etap;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.StorableObjectList;

public abstract class DarabFuggoMenetlevelBejegyzes extends StorableEntityBase
{

  private long eid;

  private int rajtszam;

  private long dfftid;

  private int posindex;

  private int darab;

  protected DarabFuggoMenetlevelBejegyzes ()
  {
  }

  protected DarabFuggoMenetlevelBejegyzes ( long eid, int rajtszam, long dfftid, int posindex )
  {
    this.eid = eid;
    this.rajtszam = rajtszam;
    this.dfftid = dfftid;
    this.posindex = posindex;
  }

  protected DarabFuggoMenetlevelBejegyzes ( long eid, int rajtszam, long dfftid, int posindex, int darab )
  {
    this.eid = eid;
    this.rajtszam = rajtszam;
    this.dfftid = dfftid;
    this.posindex = posindex;
    this.darab = darab;
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

  public int getPosindex ()
  {
    return posindex;
  }

  public void setPosindex ( int posindex )
  {
    this.posindex = posindex;
  }

  public int getDarab ()
  {
    return darab;
  }

  public void setDarab ( int darab )
  {
    this.darab = darab;
  }

  public static class Lista extends StorableObjectList
  {

    private DarabFuggoMenetlevelBejegyzes keyEntity = new DarabFuggoMenetlevelBejegyzesImpl();

    protected Object getKeyEntity ()
    {
      return keyEntity;
    }

    public boolean add ( Object o )
    {
      DarabFuggoMenetlevelBejegyzes entity = ( DarabFuggoMenetlevelBejegyzes ) o;
      entity.setEid( getEid () );
      entity.setRajtszam( getRajtszam () );
      entity.setDfftid( getDfftid () );
      return super.add( entity );
    }

    public void setEid ( long eid )
    {
      keyEntity.setEid( eid );
      for ( int i = 0; i < size(); ++i ) {
        DarabFuggoMenetlevelBejegyzes entity = ( DarabFuggoMenetlevelBejegyzes ) get( i );
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
        DarabFuggoMenetlevelBejegyzes entity = ( DarabFuggoMenetlevelBejegyzes ) get( i );
        entity.setRajtszam( rajtszam );
      }
    }

    public int getRajtszam ()
    {
      return keyEntity.getRajtszam();
    }

    public void setDfftid ( long dfftid )
    {
      keyEntity.setDfftid( dfftid );
      for ( int i = 0; i < size(); ++i ) {
        DarabFuggoMenetlevelBejegyzes entity = ( DarabFuggoMenetlevelBejegyzes ) get( i );
        entity.setDfftid( dfftid );
      }
    }

    public long getDfftid ()
    {
      return keyEntity.getDfftid();
    }

  }

  public static List loadAllForDarabFuggoFeladatTipus ( RdbSession session, DarabFuggoFeladatTipus darabfuggofeladattipus ) throws RdbException
  {
    return loadAll( session, DarabFuggoMenetlevelBejegyzes.class, "dfftid", darabfuggofeladattipus );
  }

  public static List loadAllForEtap ( RdbSession session, Etap etap ) throws RdbException
  {
    return loadAll( session, DarabFuggoMenetlevelBejegyzes.class, "eid", etap );
  }

  public static DarabFuggoMenetlevelBejegyzes newInstance ()
  {
    return new DarabFuggoMenetlevelBejegyzesImpl();
  }

  public static DarabFuggoMenetlevelBejegyzes newInstance ( long eid, int rajtszam, long dfftid, int posindex )
  {
    return new DarabFuggoMenetlevelBejegyzesImpl( eid, rajtszam, dfftid, posindex );
  }

  public static DarabFuggoMenetlevelBejegyzes newInstance ( long eid, int rajtszam, long dfftid, int posindex, int darab )
  {
    return new DarabFuggoMenetlevelBejegyzesImpl( eid, rajtszam, dfftid, posindex, darab );
  }

}
