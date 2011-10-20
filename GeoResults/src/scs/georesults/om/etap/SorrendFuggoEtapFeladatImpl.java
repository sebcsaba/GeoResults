package scs.georesults.om.etap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.om.alap.EllenorzoPont;
import scs.georesults.om.alap.SorrendFuggoFeladatTipus;

public class SorrendFuggoEtapFeladatImpl extends SorrendFuggoEtapFeladat
{

  private SorrendFuggoFeladatTipus sfft;

  private List etalonEllenorzoPontokListaja;

  public SorrendFuggoEtapFeladatImpl ()
  {
    super();
  }

  public SorrendFuggoEtapFeladatImpl ( long eid, long sfftid )
  {
    super( eid, sfftid );
  }

  public SorrendFuggoEtapFeladatImpl ( long eid, long sfftid, boolean reszletesBevitel, Integer ellenorzesTipus, Integer darab )
  {
    super( eid, sfftid, reszletesBevitel, ellenorzesTipus, darab );
  }

  public void updateSorrendFuggoFeladatTipus () throws GeoException, RdbException
  {
    sfft = SorrendFuggoFeladatTipus.newInstance( getSfftid() );
    sfft.read( GeoDbSession.getCurrentInstance() );
  }

  public SorrendFuggoFeladatTipus getSorrendFuggoFeladatTipus () throws GeoException, RdbException
  {
    if ( sfft == null ) updateSorrendFuggoFeladatTipus();
    return sfft;
  }

  public int getValodiEllenorzesTipus () throws GeoException, RdbException
  {
    if ( getEllenorzesTipus() == null ) {
      return getSorrendFuggoFeladatTipus().getEllenorzesTipus();
    } else {
      return getEllenorzesTipus().intValue();
    }
  }

  public void updateEtalonEllenorzoPontokListaja ()
  {
    Set feliratok = new HashSet();
    for ( int i = 0; i < getBejegyzesek().size(); ++i ) {
      SorrendFuggoEtapFeladatEtalonBejegyzes sfefeb = ( SorrendFuggoEtapFeladatEtalonBejegyzes ) getBejegyzesek().get( i );
      feliratok.add( sfefeb.getFelirat() );
    }
    etalonEllenorzoPontokListaja = new List();
    for ( Iterator it = feliratok.iterator(); it.hasNext(); ) {
      String felirat = ( String ) it.next();
      etalonEllenorzoPontokListaja.add( EllenorzoPont.newInstance( getSfftid(), felirat ) );
    }
  }

  public List getEtalonEllenorzoPontokListaja ()
  {
    if ( etalonEllenorzoPontokListaja == null ) updateEtalonEllenorzoPontokListaja();
    return etalonEllenorzoPontokListaja;
  }

}
