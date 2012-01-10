package scs.georesults.om.menetlevel;

import scs.georesults.om.alap.DarabFuggoFeladatTipus;
import scs.georesults.om.verseny.Etap;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.StorableObjectList;

public abstract class DarabFuggoFeladatMegoldas extends StorableEntityBase
{

  private long eid;

  private int rajtszam;

  private long dfftid;

  private DarabFuggoMenetlevelBejegyzes.Lista bejegyzesek;

  protected DarabFuggoFeladatMegoldas ()
  {
    bejegyzesek = new DarabFuggoMenetlevelBejegyzes.Lista();
  }

  protected DarabFuggoFeladatMegoldas ( long eid, int rajtszam, long dfftid )
  {
    bejegyzesek = new DarabFuggoMenetlevelBejegyzes.Lista();
    setEid( eid );
    setRajtszam( rajtszam );
    setDfftid( dfftid );
  }

  public DarabFuggoMenetlevelBejegyzes.Lista getBejegyzesek ()
  {
    return bejegyzesek;
  }

  public long getEid ()
  {
    return eid;
  }

  public void setEid ( long eid )
  {
    this.eid = eid;
    bejegyzesek.setEid( eid );
  }

  public int getRajtszam ()
  {
    return rajtszam;
  }

  public void setRajtszam ( int rajtszam )
  {
    this.rajtszam = rajtszam;
    bejegyzesek.setRajtszam( rajtszam );
  }

  public long getDfftid ()
  {
    return dfftid;
  }

  public void setDfftid ( long dfftid )
  {
    this.dfftid = dfftid;
    bejegyzesek.setDfftid( dfftid );
  }

  public static class Lista extends StorableObjectList
  {

    private DarabFuggoFeladatMegoldas keyEntity = new DarabFuggoFeladatMegoldasImpl();

    protected Object getKeyEntity ()
    {
      return keyEntity;
    }

    public boolean add ( Object o )
    {
      DarabFuggoFeladatMegoldas entity = ( DarabFuggoFeladatMegoldas ) o;
      entity.setEid( getEid () );
      entity.setRajtszam( getRajtszam () );
      return super.add( entity );
    }

    public void setEid ( long eid )
    {
      keyEntity.setEid( eid );
      for ( int i = 0; i < size(); ++i ) {
        DarabFuggoFeladatMegoldas entity = ( DarabFuggoFeladatMegoldas ) get( i );
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
        DarabFuggoFeladatMegoldas entity = ( DarabFuggoFeladatMegoldas ) get( i );
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
    return loadAll( session, DarabFuggoFeladatMegoldas.class, "dfftid", darabfuggofeladattipus );
  }

  public static List loadAllForEtap ( RdbSession session, Etap etap ) throws RdbException
  {
    return loadAll( session, DarabFuggoFeladatMegoldas.class, "eid", etap );
  }

  public static DarabFuggoFeladatMegoldas newInstance ()
  {
    return new DarabFuggoFeladatMegoldasImpl();
  }

  public static DarabFuggoFeladatMegoldas newInstance ( long eid, int rajtszam, long dfftid )
  {
    return new DarabFuggoFeladatMegoldasImpl( eid, rajtszam, dfftid );
  }

}
