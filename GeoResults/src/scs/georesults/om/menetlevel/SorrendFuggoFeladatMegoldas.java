package scs.georesults.om.menetlevel;

import scs.georesults.om.alap.SorrendFuggoFeladatTipus;
import scs.georesults.om.verseny.Etap;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.StorableObjectList;

public abstract class SorrendFuggoFeladatMegoldas extends StorableEntityBase
{

  private long eid;

  private int rajtszam;

  private long sfftid;

  private Integer helyes;

  private Integer hibas;

  private SorrendFuggoMenetlevelBejegyzes.Lista bejegyzesek;

  protected SorrendFuggoFeladatMegoldas ()
  {
    bejegyzesek = new SorrendFuggoMenetlevelBejegyzes.Lista();
  }

  protected SorrendFuggoFeladatMegoldas ( long eid, int rajtszam, long sfftid )
  {
    bejegyzesek = new SorrendFuggoMenetlevelBejegyzes.Lista();
    setEid( eid );
    setRajtszam( rajtszam );
    setSfftid( sfftid );
  }

  protected SorrendFuggoFeladatMegoldas ( long eid, int rajtszam, long sfftid, Integer helyes, Integer hibas )
  {
    bejegyzesek = new SorrendFuggoMenetlevelBejegyzes.Lista();
    setEid( eid );
    setRajtszam( rajtszam );
    setSfftid( sfftid );
    setHelyes( helyes );
    setHibas( hibas );
  }

  public SorrendFuggoMenetlevelBejegyzes.Lista getBejegyzesek ()
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

  public long getSfftid ()
  {
    return sfftid;
  }

  public void setSfftid ( long sfftid )
  {
    this.sfftid = sfftid;
    bejegyzesek.setSfftid( sfftid );
  }

  public Integer getHelyes ()
  {
    return helyes;
  }

  public void setHelyes ( Integer helyes )
  {
    this.helyes = helyes;
  }

  public Integer getHibas ()
  {
    return hibas;
  }

  public void setHibas ( Integer hibas )
  {
    this.hibas = hibas;
  }

  public static class Lista extends StorableObjectList
  {

    private SorrendFuggoFeladatMegoldas keyEntity = new SorrendFuggoFeladatMegoldasImpl();

    protected Object getKeyEntity ()
    {
      return keyEntity;
    }

    public boolean add ( Object o )
    {
      SorrendFuggoFeladatMegoldas entity = ( SorrendFuggoFeladatMegoldas ) o;
      entity.setEid( getEid () );
      entity.setRajtszam( getRajtszam () );
      return super.add( entity );
    }

    public void setEid ( long eid )
    {
      keyEntity.setEid( eid );
      for ( int i = 0; i < size(); ++i ) {
        SorrendFuggoFeladatMegoldas entity = ( SorrendFuggoFeladatMegoldas ) get( i );
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
        SorrendFuggoFeladatMegoldas entity = ( SorrendFuggoFeladatMegoldas ) get( i );
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
    return loadAll( session, SorrendFuggoFeladatMegoldas.class, "sfftid", sorrendfuggofeladattipus );
  }

  public static List loadAllForEtap ( RdbSession session, Etap etap ) throws RdbException
  {
    return loadAll( session, SorrendFuggoFeladatMegoldas.class, "eid", etap );
  }

  public static SorrendFuggoFeladatMegoldas newInstance ()
  {
    return new SorrendFuggoFeladatMegoldasImpl();
  }

  public static SorrendFuggoFeladatMegoldas newInstance ( long eid, int rajtszam, long sfftid )
  {
    return new SorrendFuggoFeladatMegoldasImpl( eid, rajtszam, sfftid );
  }

  public static SorrendFuggoFeladatMegoldas newInstance ( long eid, int rajtszam, long sfftid, Integer helyes, Integer hibas )
  {
    return new SorrendFuggoFeladatMegoldasImpl( eid, rajtszam, sfftid, helyes, hibas );
  }

}
