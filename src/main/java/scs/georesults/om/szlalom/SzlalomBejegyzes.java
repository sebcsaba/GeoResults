package scs.georesults.om.szlalom;

import scs.georesults.om.alap.SzlalomFeladat;
import scs.georesults.om.verseny.Szlalom;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.StorableObjectList;

public abstract class SzlalomBejegyzes extends StorableEntityBase
{

  private long szlid;

  private int rajtszam;

  private long szfid;

  private int darab;

  protected SzlalomBejegyzes ()
  {
  }

  protected SzlalomBejegyzes ( long szlid, int rajtszam, long szfid )
  {
    this.szlid = szlid;
    this.rajtszam = rajtszam;
    this.szfid = szfid;
  }

  protected SzlalomBejegyzes ( long szlid, int rajtszam, long szfid, int darab )
  {
    this.szlid = szlid;
    this.rajtszam = rajtszam;
    this.szfid = szfid;
    this.darab = darab;
  }

  public long getSzlid ()
  {
    return szlid;
  }

  public void setSzlid ( long szlid )
  {
    this.szlid = szlid;
  }

  public int getRajtszam ()
  {
    return rajtszam;
  }

  public void setRajtszam ( int rajtszam )
  {
    this.rajtszam = rajtszam;
  }

  public long getSzfid ()
  {
    return szfid;
  }

  public void setSzfid ( long szfid )
  {
    this.szfid = szfid;
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

    private SzlalomBejegyzes keyEntity = new SzlalomBejegyzesImpl();

    protected Object getKeyEntity ()
    {
      return keyEntity;
    }

    public boolean add ( Object o )
    {
      SzlalomBejegyzes entity = ( SzlalomBejegyzes ) o;
      entity.setSzlid( getSzlid () );
      entity.setRajtszam( getRajtszam () );
      return super.add( entity );
    }

    public void setSzlid ( long szlid )
    {
      keyEntity.setSzlid( szlid );
      for ( int i = 0; i < size(); ++i ) {
        SzlalomBejegyzes entity = ( SzlalomBejegyzes ) get( i );
        entity.setSzlid( szlid );
      }
    }

    public long getSzlid ()
    {
      return keyEntity.getSzlid();
    }

    public void setRajtszam ( int rajtszam )
    {
      keyEntity.setRajtszam( rajtszam );
      for ( int i = 0; i < size(); ++i ) {
        SzlalomBejegyzes entity = ( SzlalomBejegyzes ) get( i );
        entity.setRajtszam( rajtszam );
      }
    }

    public int getRajtszam ()
    {
      return keyEntity.getRajtszam();
    }

  }

  public static List loadAllForSzlalomFeladat ( RdbSession session, SzlalomFeladat szlalomfeladat ) throws RdbException
  {
    return loadAll( session, SzlalomBejegyzes.class, "szfid", szlalomfeladat );
  }

  public static List loadAllForSzlalom ( RdbSession session, Szlalom szlalom ) throws RdbException
  {
    return loadAll( session, SzlalomBejegyzes.class, "szlid", szlalom );
  }

  public static SzlalomBejegyzes newInstance ()
  {
    return new SzlalomBejegyzesImpl();
  }

  public static SzlalomBejegyzes newInstance ( long szlid, int rajtszam, long szfid )
  {
    return new SzlalomBejegyzesImpl( szlid, rajtszam, szfid );
  }

  public static SzlalomBejegyzes newInstance ( long szlid, int rajtszam, long szfid, int darab )
  {
    return new SzlalomBejegyzesImpl( szlid, rajtszam, szfid, darab );
  }

}
