package scs.georesults.om.etap;

import scs.georesults.om.alap.SorrendFuggoFeladatTipus;
import scs.georesults.om.verseny.Etap;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.StorableObjectList;

public abstract class SorrendFuggoEtapFeladatEtalonBejegyzes extends StorableEntityBase
{

  private long eid;

  private long sfftid;

  private int posindex;

  private String felirat;

  private boolean ervenyes;

  protected SorrendFuggoEtapFeladatEtalonBejegyzes ()
  {
  }

  protected SorrendFuggoEtapFeladatEtalonBejegyzes ( long eid, long sfftid, int posindex )
  {
    this.eid = eid;
    this.sfftid = sfftid;
    this.posindex = posindex;
  }

  protected SorrendFuggoEtapFeladatEtalonBejegyzes ( long eid, long sfftid, int posindex, String felirat, boolean ervenyes )
  {
    this.eid = eid;
    this.sfftid = sfftid;
    this.posindex = posindex;
    this.felirat = felirat;
    this.ervenyes = ervenyes;
  }

  public long getEid ()
  {
    return eid;
  }

  public void setEid ( long eid )
  {
    this.eid = eid;
  }

  public long getSfftid ()
  {
    return sfftid;
  }

  public void setSfftid ( long sfftid )
  {
    this.sfftid = sfftid;
  }

  public int getPosindex ()
  {
    return posindex;
  }

  public void setPosindex ( int posindex )
  {
    this.posindex = posindex;
  }

  public String getFelirat ()
  {
    return felirat;
  }

  public void setFelirat ( String felirat )
  {
    this.felirat = felirat;
  }

  public boolean isErvenyes ()
  {
    return ervenyes;
  }

  public void setErvenyes ( boolean ervenyes )
  {
    this.ervenyes = ervenyes;
  }

  public static class Lista extends StorableObjectList
  {

    private SorrendFuggoEtapFeladatEtalonBejegyzes keyEntity = new SorrendFuggoEtapFeladatEtalonBejegyzesImpl();

    protected Object getKeyEntity ()
    {
      return keyEntity;
    }

    public boolean add ( Object o )
    {
      SorrendFuggoEtapFeladatEtalonBejegyzes entity = ( SorrendFuggoEtapFeladatEtalonBejegyzes ) o;
      entity.setEid( getEid () );
      entity.setSfftid( getSfftid () );
      return super.add( entity );
    }

    public void setEid ( long eid )
    {
      keyEntity.setEid( eid );
      for ( int i = 0; i < size(); ++i ) {
        SorrendFuggoEtapFeladatEtalonBejegyzes entity = ( SorrendFuggoEtapFeladatEtalonBejegyzes ) get( i );
        entity.setEid( eid );
      }
    }

    public long getEid ()
    {
      return keyEntity.getEid();
    }

    public void setSfftid ( long sfftid )
    {
      keyEntity.setSfftid( sfftid );
      for ( int i = 0; i < size(); ++i ) {
        SorrendFuggoEtapFeladatEtalonBejegyzes entity = ( SorrendFuggoEtapFeladatEtalonBejegyzes ) get( i );
        entity.setSfftid( sfftid );
      }
    }

    public long getSfftid ()
    {
      return keyEntity.getSfftid();
    }

  }

  public static List loadAllForSorrendFuggoFeladatTipus ( RdbSession session, SorrendFuggoFeladatTipus sorrendfuggofeladattipus ) throws RdbException
  {
    return loadAll( session, SorrendFuggoEtapFeladatEtalonBejegyzes.class, "sfftid", sorrendfuggofeladattipus );
  }

  public static List loadAllForEtap ( RdbSession session, Etap etap ) throws RdbException
  {
    return loadAll( session, SorrendFuggoEtapFeladatEtalonBejegyzes.class, "eid", etap );
  }

  public static SorrendFuggoEtapFeladatEtalonBejegyzes newInstance ()
  {
    return new SorrendFuggoEtapFeladatEtalonBejegyzesImpl();
  }

  public static SorrendFuggoEtapFeladatEtalonBejegyzes newInstance ( long eid, long sfftid, int posindex )
  {
    return new SorrendFuggoEtapFeladatEtalonBejegyzesImpl( eid, sfftid, posindex );
  }

  public static SorrendFuggoEtapFeladatEtalonBejegyzes newInstance ( long eid, long sfftid, int posindex, String felirat, boolean ervenyes )
  {
    return new SorrendFuggoEtapFeladatEtalonBejegyzesImpl( eid, sfftid, posindex, felirat, ervenyes );
  }

}
