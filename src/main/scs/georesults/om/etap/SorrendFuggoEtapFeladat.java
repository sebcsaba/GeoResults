package scs.georesults.om.etap;

import scs.georesults.om.alap.SorrendFuggoFeladatTipus;
import scs.georesults.om.verseny.Etap;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class SorrendFuggoEtapFeladat extends StorableEntityBase
{

  private long eid;

  private long sfftid;

  private boolean reszletesBevitel;

  private Integer ellenorzesTipus;

  private Integer darab;

  private SorrendFuggoEtapFeladatEtalonBejegyzes.Lista bejegyzesek;

  protected SorrendFuggoEtapFeladat ()
  {
    bejegyzesek = new SorrendFuggoEtapFeladatEtalonBejegyzes.Lista();
  }

  protected SorrendFuggoEtapFeladat ( long eid, long sfftid )
  {
    bejegyzesek = new SorrendFuggoEtapFeladatEtalonBejegyzes.Lista();
    setEid( eid );
    setSfftid( sfftid );
  }

  protected SorrendFuggoEtapFeladat ( long eid, long sfftid, boolean reszletesBevitel, Integer ellenorzesTipus, Integer darab )
  {
    bejegyzesek = new SorrendFuggoEtapFeladatEtalonBejegyzes.Lista();
    setEid( eid );
    setSfftid( sfftid );
    setReszletesBevitel( reszletesBevitel );
    setEllenorzesTipus( ellenorzesTipus );
    setDarab( darab );
  }

  public SorrendFuggoEtapFeladatEtalonBejegyzes.Lista getBejegyzesek ()
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

  public long getSfftid ()
  {
    return sfftid;
  }

  public void setSfftid ( long sfftid )
  {
    this.sfftid = sfftid;
    bejegyzesek.setSfftid( sfftid );
  }

  public boolean isReszletesBevitel ()
  {
    return reszletesBevitel;
  }

  public void setReszletesBevitel ( boolean reszletesBevitel )
  {
    this.reszletesBevitel = reszletesBevitel;
  }

  public Integer getEllenorzesTipus ()
  {
    return ellenorzesTipus;
  }

  public void setEllenorzesTipus ( Integer ellenorzesTipus )
  {
    this.ellenorzesTipus = ellenorzesTipus;
  }

  public Integer getDarab ()
  {
    return darab;
  }

  public void setDarab ( Integer darab )
  {
    this.darab = darab;
  }

  public static List loadAllForSorrendFuggoFeladatTipus ( RdbSession session, SorrendFuggoFeladatTipus sorrendfuggofeladattipus ) throws RdbException
  {
    return loadAll( session, SorrendFuggoEtapFeladat.class, "sfftid", sorrendfuggofeladattipus );
  }

  public static List loadAllForEtap ( RdbSession session, Etap etap ) throws RdbException
  {
    return loadAll( session, SorrendFuggoEtapFeladat.class, "eid", etap );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, SorrendFuggoEtapFeladat.class );
  }

  public static SorrendFuggoEtapFeladat newInstance ()
  {
    return new SorrendFuggoEtapFeladatImpl();
  }

  public static SorrendFuggoEtapFeladat newInstance ( long eid, long sfftid )
  {
    return new SorrendFuggoEtapFeladatImpl( eid, sfftid );
  }

  public static SorrendFuggoEtapFeladat newInstance ( long eid, long sfftid, boolean reszletesBevitel, Integer ellenorzesTipus, Integer darab )
  {
    return new SorrendFuggoEtapFeladatImpl( eid, sfftid, reszletesBevitel, ellenorzesTipus, darab );
  }

}
