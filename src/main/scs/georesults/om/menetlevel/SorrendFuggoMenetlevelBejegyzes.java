package scs.georesults.om.menetlevel;

import scs.georesults.om.alap.SorrendFuggoFeladatTipus;
import scs.georesults.om.verseny.Etap;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.StorableObjectList;

public abstract class SorrendFuggoMenetlevelBejegyzes extends StorableEntityBase
{

  private long eid;

  private int rajtszam;

  private long sfftid;

  private int posindex;

  private String felirat;

  protected SorrendFuggoMenetlevelBejegyzes ()
  {
  }

  protected SorrendFuggoMenetlevelBejegyzes ( long eid, int rajtszam, long sfftid, int posindex )
  {
    this.eid = eid;
    this.rajtszam = rajtszam;
    this.sfftid = sfftid;
    this.posindex = posindex;
  }

  protected SorrendFuggoMenetlevelBejegyzes ( long eid, int rajtszam, long sfftid, int posindex, String felirat )
  {
    this.eid = eid;
    this.rajtszam = rajtszam;
    this.sfftid = sfftid;
    this.posindex = posindex;
    this.felirat = felirat;
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

  public static class Lista extends StorableObjectList
  {

    private SorrendFuggoMenetlevelBejegyzes keyEntity = new SorrendFuggoMenetlevelBejegyzesImpl();

    protected Object getKeyEntity ()
    {
      return keyEntity;
    }

    public boolean add ( Object o )
    {
      SorrendFuggoMenetlevelBejegyzes entity = ( SorrendFuggoMenetlevelBejegyzes ) o;
      entity.setEid( getEid () );
      entity.setRajtszam( getRajtszam () );
      entity.setSfftid( getSfftid () );
      return super.add( entity );
    }

    public void setEid ( long eid )
    {
      keyEntity.setEid( eid );
      for ( int i = 0; i < size(); ++i ) {
        SorrendFuggoMenetlevelBejegyzes entity = ( SorrendFuggoMenetlevelBejegyzes ) get( i );
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
        SorrendFuggoMenetlevelBejegyzes entity = ( SorrendFuggoMenetlevelBejegyzes ) get( i );
        entity.setRajtszam( rajtszam );
      }
    }

    public int getRajtszam ()
    {
      return keyEntity.getRajtszam();
    }

    public void setSfftid ( long sfftid )
    {
      keyEntity.setSfftid( sfftid );
      for ( int i = 0; i < size(); ++i ) {
        SorrendFuggoMenetlevelBejegyzes entity = ( SorrendFuggoMenetlevelBejegyzes ) get( i );
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
    return loadAll( session, SorrendFuggoMenetlevelBejegyzes.class, "sfftid", sorrendfuggofeladattipus );
  }

  public static List loadAllForEtap ( RdbSession session, Etap etap ) throws RdbException
  {
    return loadAll( session, SorrendFuggoMenetlevelBejegyzes.class, "eid", etap );
  }

  public static SorrendFuggoMenetlevelBejegyzes newInstance ()
  {
    return new SorrendFuggoMenetlevelBejegyzesImpl();
  }

  public static SorrendFuggoMenetlevelBejegyzes newInstance ( long eid, int rajtszam, long sfftid, int posindex )
  {
    return new SorrendFuggoMenetlevelBejegyzesImpl( eid, rajtszam, sfftid, posindex );
  }

  public static SorrendFuggoMenetlevelBejegyzes newInstance ( long eid, int rajtszam, long sfftid, int posindex, String felirat )
  {
    return new SorrendFuggoMenetlevelBejegyzesImpl( eid, rajtszam, sfftid, posindex, felirat );
  }

}
