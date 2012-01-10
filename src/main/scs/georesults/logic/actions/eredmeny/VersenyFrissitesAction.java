package scs.georesults.logic.actions.eredmeny;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.web.WebException;
import scs.georesults.common.ReszEredmeny;
import scs.georesults.om.eredmeny.SzakaszEredmeny;
import scs.georesults.om.eredmeny.VersenyEredmeny;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.verseny.Szakasz;
import scs.georesults.om.verseny.VersenyImpl;

/**
 * <p>A verseny összesített végeredményét frissítő szolgáltatás osztálya.</p>
 */
public class VersenyFrissitesAction extends GlobalEredmenyFrissitesActionBase
{

  protected List getSzakaszElemek () throws WebException, RdbException
  {
    return Szakasz.loadAllForVerseny( getDb(), getVerseny() );
  }

  protected List getGlobalEredmenyek () throws WebException, RdbException
  {
    return VersenyEredmeny.loadAllForVerseny( getDb(), getVerseny() );
  }

  protected ReszEredmeny getNewOsszInstance ( Nevezes nevezes )
  {
    return ( ReszEredmeny ) VersenyEredmeny.newInstance( nevezes.getVid(), nevezes.getRajtszam() );
  }

  protected ReszEredmeny getNewReszInstance ( long id, int rajtszam )
  {
    return ( ReszEredmeny ) SzakaszEredmeny.newInstance( id, rajtszam );
  }

  protected void setVersenyFrissitendoFlags ( VersenyImpl verseny )
  {
    verseny.setEredmenyFrissitendoVerseny( false );
    verseny.setEredmenyFrissitendoCsapat( true );
  }

}
