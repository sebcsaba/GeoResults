package scs.georesults.om.alap;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.georesults.GeoException;
import scs.georesults.om.verseny.Verseny;

public class VersenySzotarBejegyzesImpl extends VersenySzotarBejegyzes
{

  public VersenySzotarBejegyzesImpl ()
  {
    super();
  }

  public VersenySzotarBejegyzesImpl ( long vid, long vszbid, String lang )
  {
    super( vid, vszbid, lang );
  }

  public VersenySzotarBejegyzesImpl ( long vid, long vszbid, String lang, String felirat )
  {
    super( vid, vszbid, lang, felirat );
  }

  public static long getNexyKey ( RdbSession db, Verseny verseny ) throws RdbException, GeoException
  {
    List vszbk = VersenySzotarBejegyzes.loadAllForVerseny( db, verseny );
    long max = 0;
    for ( int i = 0; i < vszbk.size(); ++i ) {
      VersenySzotarBejegyzes vszb = ( VersenySzotarBejegyzes ) vszbk.get( i );
      if ( vszb.getVszbid() > max ) max = vszb.getVszbid();
    }
    return max + 1;
  }

}
