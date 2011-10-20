package scs.georesults.om.etap;

import scs.georesults.om.alap.DarabFuggoFeladatTipus;
import scs.georesults.om.verseny.Etap;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.StorableObjectList;

public abstract class DarabFuggoEtapFeladatEtalonBejegyzes extends StorableEntityBase
{

  private long eid;

  private long dfftid;

  private int posindex;

  private String cimke;

  private int darab;

  protected DarabFuggoEtapFeladatEtalonBejegyzes ()
  {
  }

  protected DarabFuggoEtapFeladatEtalonBejegyzes ( long eid, long dfftid, int posindex )
  {
    this.eid = eid;
    this.dfftid = dfftid;
    this.posindex = posindex;
  }

  protected DarabFuggoEtapFeladatEtalonBejegyzes ( long eid, long dfftid, int posindex, String cimke, int darab )
  {
    this.eid = eid;
    this.dfftid = dfftid;
    this.posindex = posindex;
    this.cimke = cimke;
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

  public String getCimke ()
  {
    return cimke;
  }

  public void setCimke ( String cimke )
  {
    this.cimke = cimke;
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

    private DarabFuggoEtapFeladatEtalonBejegyzes keyEntity = new DarabFuggoEtapFeladatEtalonBejegyzesImpl();

    protected Object getKeyEntity ()
    {
      return keyEntity;
    }

    public boolean add ( Object o )
    {
      DarabFuggoEtapFeladatEtalonBejegyzes entity = ( DarabFuggoEtapFeladatEtalonBejegyzes ) o;
      entity.setEid( getEid () );
      entity.setDfftid( getDfftid () );
      return super.add( entity );
    }

    public void setEid ( long eid )
    {
      keyEntity.setEid( eid );
      for ( int i = 0; i < size(); ++i ) {
        DarabFuggoEtapFeladatEtalonBejegyzes entity = ( DarabFuggoEtapFeladatEtalonBejegyzes ) get( i );
        entity.setEid( eid );
      }
    }

    public long getEid ()
    {
      return keyEntity.getEid();
    }

    public void setDfftid ( long dfftid )
    {
      keyEntity.setDfftid( dfftid );
      for ( int i = 0; i < size(); ++i ) {
        DarabFuggoEtapFeladatEtalonBejegyzes entity = ( DarabFuggoEtapFeladatEtalonBejegyzes ) get( i );
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
    return loadAll( session, DarabFuggoEtapFeladatEtalonBejegyzes.class, "dfftid", darabfuggofeladattipus );
  }

  public static List loadAllForEtap ( RdbSession session, Etap etap ) throws RdbException
  {
    return loadAll( session, DarabFuggoEtapFeladatEtalonBejegyzes.class, "eid", etap );
  }

  public static DarabFuggoEtapFeladatEtalonBejegyzes newInstance ()
  {
    return new DarabFuggoEtapFeladatEtalonBejegyzesImpl();
  }

  public static DarabFuggoEtapFeladatEtalonBejegyzes newInstance ( long eid, long dfftid, int posindex )
  {
    return new DarabFuggoEtapFeladatEtalonBejegyzesImpl( eid, dfftid, posindex );
  }

  public static DarabFuggoEtapFeladatEtalonBejegyzes newInstance ( long eid, long dfftid, int posindex, String cimke, int darab )
  {
    return new DarabFuggoEtapFeladatEtalonBejegyzesImpl( eid, dfftid, posindex, cimke, darab );
  }

}
