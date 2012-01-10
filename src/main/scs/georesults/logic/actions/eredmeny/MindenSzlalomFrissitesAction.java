package scs.georesults.logic.actions.eredmeny;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.web.WebException;
import scs.georesults.GeoException;
import scs.georesults.common.ReszEredmeny;
import scs.georesults.om.eredmeny.MindenSzlalomEredmeny;
import scs.georesults.om.eredmeny.SzlalomEredmeny;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.nevezes.NevezesImpl;
import scs.georesults.om.verseny.Szlalom;
import scs.georesults.om.verseny.VersenyImpl;

/**
 * <p>A szlalomok összesített eredményét frissítő szolgáltatás osztálya.</p>
 */
public class MindenSzlalomFrissitesAction extends GlobalEredmenyFrissitesActionBase
{

  protected List getSzakaszElemek () throws WebException, RdbException
  {
    return Szlalom.loadAllForVerseny( getDb(), getVerseny() );
  }

  protected List getGlobalEredmenyek () throws WebException, RdbException
  {
    return MindenSzlalomEredmeny.loadAllForVerseny( getDb(), getVerseny() );
  }

  protected ReszEredmeny getNewOsszInstance ( Nevezes nevezes ) throws RdbException, GeoException
  {
    MindenSzlalomEredmeny result = MindenSzlalomEredmeny.newInstance( nevezes.getVid(), nevezes.getRajtszam() );
    result.setKategoria( ( ( NevezesImpl ) nevezes ).getAuto().getKategoria() );
    return ( ReszEredmeny ) result;
  }

  protected ReszEredmeny getNewReszInstance ( long id, int rajtszam )
  {
    return ( ReszEredmeny ) SzlalomEredmeny.newInstance( id, rajtszam );
  }

  protected void setVersenyFrissitendoFlags ( VersenyImpl verseny )
  {
    verseny.setEredmenyFrissitendoMindenSzlalom( false );
  }

}
