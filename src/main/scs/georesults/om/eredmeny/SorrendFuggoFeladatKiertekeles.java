package scs.georesults.om.eredmeny;

import scs.georesults.om.alap.SorrendFuggoFeladatTipus;
import scs.georesults.om.verseny.Etap;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.StorableObjectList;

public abstract class SorrendFuggoFeladatKiertekeles extends StorableEntityBase
{

  private long eid;

  private int rajtszam;

  private long sfftid;

  private int posindex;

  private String menetlevelFelirat;

  private String etalonFelirat;

  private int tipus;

  protected SorrendFuggoFeladatKiertekeles ()
  {
  }

  protected SorrendFuggoFeladatKiertekeles ( long eid, int rajtszam, long sfftid, int posindex )
  {
    this.eid = eid;
    this.rajtszam = rajtszam;
    this.sfftid = sfftid;
    this.posindex = posindex;
  }

  protected SorrendFuggoFeladatKiertekeles ( long eid, int rajtszam, long sfftid, int posindex, String menetlevelFelirat, String etalonFelirat, int tipus )
  {
    this.eid = eid;
    this.rajtszam = rajtszam;
    this.sfftid = sfftid;
    this.posindex = posindex;
    this.menetlevelFelirat = menetlevelFelirat;
    this.etalonFelirat = etalonFelirat;
    this.tipus = tipus;
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

  public String getMenetlevelFelirat ()
  {
    return menetlevelFelirat;
  }

  public void setMenetlevelFelirat ( String menetlevelFelirat )
  {
    this.menetlevelFelirat = menetlevelFelirat;
  }

  public String getEtalonFelirat ()
  {
    return etalonFelirat;
  }

  public void setEtalonFelirat ( String etalonFelirat )
  {
    this.etalonFelirat = etalonFelirat;
  }

  public int getTipus ()
  {
    return tipus;
  }

  public void setTipus ( int tipus )
  {
    this.tipus = tipus;
  }

  public static class Lista extends StorableObjectList
  {

    private SorrendFuggoFeladatKiertekeles keyEntity = new SorrendFuggoFeladatKiertekelesImpl();

    protected Object getKeyEntity ()
    {
      return keyEntity;
    }

    public boolean add ( Object o )
    {
      SorrendFuggoFeladatKiertekeles entity = ( SorrendFuggoFeladatKiertekeles ) o;
      entity.setEid( getEid () );
      entity.setRajtszam( getRajtszam () );
      entity.setSfftid( getSfftid () );
      return super.add( entity );
    }

    public void setEid ( long eid )
    {
      keyEntity.setEid( eid );
      for ( int i = 0; i < size(); ++i ) {
        SorrendFuggoFeladatKiertekeles entity = ( SorrendFuggoFeladatKiertekeles ) get( i );
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
        SorrendFuggoFeladatKiertekeles entity = ( SorrendFuggoFeladatKiertekeles ) get( i );
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
        SorrendFuggoFeladatKiertekeles entity = ( SorrendFuggoFeladatKiertekeles ) get( i );
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
    return loadAll( session, SorrendFuggoFeladatKiertekeles.class, "sfftid", sorrendfuggofeladattipus );
  }

  public static List loadAllForEtap ( RdbSession session, Etap etap ) throws RdbException
  {
    return loadAll( session, SorrendFuggoFeladatKiertekeles.class, "eid", etap );
  }

  public static SorrendFuggoFeladatKiertekeles newInstance ()
  {
    return new SorrendFuggoFeladatKiertekelesImpl();
  }

  public static SorrendFuggoFeladatKiertekeles newInstance ( long eid, int rajtszam, long sfftid, int posindex )
  {
    return new SorrendFuggoFeladatKiertekelesImpl( eid, rajtszam, sfftid, posindex );
  }

  public static SorrendFuggoFeladatKiertekeles newInstance ( long eid, int rajtszam, long sfftid, int posindex, String menetlevelFelirat, String etalonFelirat, int tipus )
  {
    return new SorrendFuggoFeladatKiertekelesImpl( eid, rajtszam, sfftid, posindex, menetlevelFelirat, etalonFelirat, tipus );
  }

}
