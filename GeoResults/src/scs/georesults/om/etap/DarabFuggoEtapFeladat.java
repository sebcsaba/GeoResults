package scs.georesults.om.etap;

import scs.georesults.om.alap.DarabFuggoFeladatTipus;
import scs.georesults.om.verseny.Etap;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class DarabFuggoEtapFeladat extends StorableEntityBase
{

  private long eid;

  private long dfftid;

  private DarabFuggoEtapFeladatEtalonBejegyzes.Lista bejegyzesek;

  protected DarabFuggoEtapFeladat ()
  {
    bejegyzesek = new DarabFuggoEtapFeladatEtalonBejegyzes.Lista();
  }

  protected DarabFuggoEtapFeladat ( long eid, long dfftid )
  {
    bejegyzesek = new DarabFuggoEtapFeladatEtalonBejegyzes.Lista();
    setEid( eid );
    setDfftid( dfftid );
  }

  public DarabFuggoEtapFeladatEtalonBejegyzes.Lista getBejegyzesek ()
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

  public long getDfftid ()
  {
    return dfftid;
  }

  public void setDfftid ( long dfftid )
  {
    this.dfftid = dfftid;
    bejegyzesek.setDfftid( dfftid );
  }

  public static List loadAllForDarabFuggoFeladatTipus ( RdbSession session, DarabFuggoFeladatTipus darabfuggofeladattipus ) throws RdbException
  {
    return loadAll( session, DarabFuggoEtapFeladat.class, "dfftid", darabfuggofeladattipus );
  }

  public static List loadAllForEtap ( RdbSession session, Etap etap ) throws RdbException
  {
    return loadAll( session, DarabFuggoEtapFeladat.class, "eid", etap );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, DarabFuggoEtapFeladat.class );
  }

  public static DarabFuggoEtapFeladat newInstance ()
  {
    return new DarabFuggoEtapFeladatImpl();
  }

  public static DarabFuggoEtapFeladat newInstance ( long eid, long dfftid )
  {
    return new DarabFuggoEtapFeladatImpl( eid, dfftid );
  }

}
