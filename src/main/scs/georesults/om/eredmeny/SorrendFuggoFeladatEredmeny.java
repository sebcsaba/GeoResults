package scs.georesults.om.eredmeny;

import scs.georesults.om.alap.SorrendFuggoFeladatTipus;
import scs.georesults.om.verseny.Etap;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.StorableObjectList;

public abstract class SorrendFuggoFeladatEredmeny extends StorableEntityBase
{

  private long eid;

  private int rajtszam;

  private long sfftid;

  private int hiany;

  private int tobblet;

  private int pontszam;

  private SorrendFuggoFeladatKiertekeles.Lista kiertekeles;

  protected SorrendFuggoFeladatEredmeny ()
  {
    kiertekeles = new SorrendFuggoFeladatKiertekeles.Lista();
  }

  protected SorrendFuggoFeladatEredmeny ( long eid, int rajtszam, long sfftid )
  {
    kiertekeles = new SorrendFuggoFeladatKiertekeles.Lista();
    setEid( eid );
    setRajtszam( rajtszam );
    setSfftid( sfftid );
  }

  protected SorrendFuggoFeladatEredmeny ( long eid, int rajtszam, long sfftid, int hiany, int tobblet, int pontszam )
  {
    kiertekeles = new SorrendFuggoFeladatKiertekeles.Lista();
    setEid( eid );
    setRajtszam( rajtszam );
    setSfftid( sfftid );
    setHiany( hiany );
    setTobblet( tobblet );
    setPontszam( pontszam );
  }

  public SorrendFuggoFeladatKiertekeles.Lista getKiertekeles ()
  {
    return kiertekeles;
  }

  public long getEid ()
  {
    return eid;
  }

  public void setEid ( long eid )
  {
    this.eid = eid;
    kiertekeles.setEid( eid );
  }

  public int getRajtszam ()
  {
    return rajtszam;
  }

  public void setRajtszam ( int rajtszam )
  {
    this.rajtszam = rajtszam;
    kiertekeles.setRajtszam( rajtszam );
  }

  public long getSfftid ()
  {
    return sfftid;
  }

  public void setSfftid ( long sfftid )
  {
    this.sfftid = sfftid;
    kiertekeles.setSfftid( sfftid );
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

    private SorrendFuggoFeladatEredmeny keyEntity = new SorrendFuggoFeladatEredmenyImpl();

    protected Object getKeyEntity ()
    {
      return keyEntity;
    }

    public boolean add ( Object o )
    {
      SorrendFuggoFeladatEredmeny entity = ( SorrendFuggoFeladatEredmeny ) o;
      entity.setEid( getEid () );
      entity.setRajtszam( getRajtszam () );
      return super.add( entity );
    }

    public void setEid ( long eid )
    {
      keyEntity.setEid( eid );
      for ( int i = 0; i < size(); ++i ) {
        SorrendFuggoFeladatEredmeny entity = ( SorrendFuggoFeladatEredmeny ) get( i );
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
        SorrendFuggoFeladatEredmeny entity = ( SorrendFuggoFeladatEredmeny ) get( i );
        entity.setRajtszam( rajtszam );
      }
    }

    public int getRajtszam ()
    {
      return keyEntity.getRajtszam();
    }

  }

  public static List loadAllForSorrendFuggoFeladatTipus ( RdbSession session, SorrendFuggoFeladatTipus sorrendfuggofeladattipus ) throws RdbException
  {
    return loadAll( session, SorrendFuggoFeladatEredmeny.class, "sfftid", sorrendfuggofeladattipus );
  }

  public static List loadAllForEtap ( RdbSession session, Etap etap ) throws RdbException
  {
    return loadAll( session, SorrendFuggoFeladatEredmeny.class, "eid", etap );
  }

  public static SorrendFuggoFeladatEredmeny newInstance ()
  {
    return new SorrendFuggoFeladatEredmenyImpl();
  }

  public static SorrendFuggoFeladatEredmeny newInstance ( long eid, int rajtszam, long sfftid )
  {
    return new SorrendFuggoFeladatEredmenyImpl( eid, rajtszam, sfftid );
  }

  public static SorrendFuggoFeladatEredmeny newInstance ( long eid, int rajtszam, long sfftid, int hiany, int tobblet, int pontszam )
  {
    return new SorrendFuggoFeladatEredmenyImpl( eid, rajtszam, sfftid, hiany, tobblet, pontszam );
  }

}
