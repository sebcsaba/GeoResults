package scs.georesults.om.menetlevel;

import scs.georesults.om.alap.BuntetesTipus;
import scs.georesults.om.verseny.Etap;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.StorableObjectList;

public abstract class Buntetes extends StorableEntityBase
{

  private long eid;

  private int rajtszam;

  private long btid;

  private int darab;

  protected Buntetes ()
  {
  }

  protected Buntetes ( long eid, int rajtszam, long btid )
  {
    this.eid = eid;
    this.rajtszam = rajtszam;
    this.btid = btid;
  }

  protected Buntetes ( long eid, int rajtszam, long btid, int darab )
  {
    this.eid = eid;
    this.rajtszam = rajtszam;
    this.btid = btid;
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

  public long getBtid ()
  {
    return btid;
  }

  public void setBtid ( long btid )
  {
    this.btid = btid;
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

    private Buntetes keyEntity = new BuntetesImpl();

    protected Object getKeyEntity ()
    {
      return keyEntity;
    }

    public boolean add ( Object o )
    {
      Buntetes entity = ( Buntetes ) o;
      entity.setEid( getEid () );
      entity.setRajtszam( getRajtszam () );
      return super.add( entity );
    }

    public void setEid ( long eid )
    {
      keyEntity.setEid( eid );
      for ( int i = 0; i < size(); ++i ) {
        Buntetes entity = ( Buntetes ) get( i );
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
        Buntetes entity = ( Buntetes ) get( i );
        entity.setRajtszam( rajtszam );
      }
    }

    public int getRajtszam ()
    {
      return keyEntity.getRajtszam();
    }

  }

  public static List loadAllForBuntetesTipus ( RdbSession session, BuntetesTipus buntetestipus ) throws RdbException
  {
    return loadAll( session, Buntetes.class, "btid", buntetestipus );
  }

  public static List loadAllForEtap ( RdbSession session, Etap etap ) throws RdbException
  {
    return loadAll( session, Buntetes.class, "eid", etap );
  }

  public static Buntetes newInstance ()
  {
    return new BuntetesImpl();
  }

  public static Buntetes newInstance ( long eid, int rajtszam, long btid )
  {
    return new BuntetesImpl( eid, rajtszam, btid );
  }

  public static Buntetes newInstance ( long eid, int rajtszam, long btid, int darab )
  {
    return new BuntetesImpl( eid, rajtszam, btid, darab );
  }

}
