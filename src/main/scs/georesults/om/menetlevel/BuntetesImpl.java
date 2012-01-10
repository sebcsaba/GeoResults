package scs.georesults.om.menetlevel;

import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.om.alap.BuntetesTipus;

public class BuntetesImpl extends Buntetes
{

  public BuntetesImpl ()
  {
    super();
  }

  public BuntetesImpl ( long eid, int rajtszam, long btid )
  {
    super( eid, rajtszam, btid );
  }

  public BuntetesImpl ( long eid, int rajtszam, long btid, int darab )
  {
    super( eid, rajtszam, btid, darab );
  }

  public BuntetesTipus getBuntetesTipus () throws GeoException, RdbException
  {
    BuntetesTipus bt = BuntetesTipus.newInstance( getBtid() );
    bt.read( GeoDbSession.getCurrentInstance() );
    return bt;
  }

}
