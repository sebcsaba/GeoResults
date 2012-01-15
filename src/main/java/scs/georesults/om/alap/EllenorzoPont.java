package scs.georesults.om.alap;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.StorableObjectList;

public abstract class EllenorzoPont extends StorableEntityBase
{

  private long sfftid;

  private String felirat;

  protected EllenorzoPont ()
  {
  }

  protected EllenorzoPont ( long sfftid, String felirat )
  {
    this.sfftid = sfftid;
    this.felirat = felirat;
  }

  public long getSfftid ()
  {
    return sfftid;
  }

  public void setSfftid ( long sfftid )
  {
    this.sfftid = sfftid;
  }

  public String getFelirat ()
  {
    return felirat;
  }

  public void setFelirat ( String felirat )
  {
    this.felirat = felirat;
  }

  public static class Lista extends StorableObjectList
  {

    private EllenorzoPont keyEntity = new EllenorzoPontImpl();

    protected Object getKeyEntity ()
    {
      return keyEntity;
    }

    public boolean add ( Object o )
    {
      EllenorzoPont entity = ( EllenorzoPont ) o;
      entity.setSfftid( getSfftid () );
      return super.add( entity );
    }

    public void setSfftid ( long sfftid )
    {
      keyEntity.setSfftid( sfftid );
      for ( int i = 0; i < size(); ++i ) {
        EllenorzoPont entity = ( EllenorzoPont ) get( i );
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
    return loadAll( session, EllenorzoPont.class, "sfftid", sorrendfuggofeladattipus );
  }

  public static EllenorzoPont newInstance ()
  {
    return new EllenorzoPontImpl();
  }

  public static EllenorzoPont newInstance ( long sfftid, String felirat )
  {
    return new EllenorzoPontImpl( sfftid, felirat );
  }

}
