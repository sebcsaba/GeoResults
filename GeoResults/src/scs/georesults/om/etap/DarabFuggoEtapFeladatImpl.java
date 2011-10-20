package scs.georesults.om.etap;

import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.om.alap.DarabFuggoFeladatTipus;

public class DarabFuggoEtapFeladatImpl extends DarabFuggoEtapFeladat
{

  private DarabFuggoFeladatTipus dfft;

  public DarabFuggoEtapFeladatImpl ()
  {
    super();
  }

  public DarabFuggoEtapFeladatImpl ( long eid, long dfftid )
  {
    super( eid, dfftid );
  }

  public void updateDarabFuggoFeladatTipus () throws GeoException, RdbException
  {
    dfft = DarabFuggoFeladatTipus.newInstance( getDfftid() );
    dfft.read( GeoDbSession.getCurrentInstance() );
  }

  public DarabFuggoFeladatTipus getDarabFuggoFeladatTipus () throws GeoException, RdbException
  {
    if ( dfft == null ) updateDarabFuggoFeladatTipus();
    return dfft;
  }

}
