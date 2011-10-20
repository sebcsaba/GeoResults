package scs.georesults.logic.actions.eredmeny;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.web.WebException;
import scs.georesults.common.ReszEredmeny;
import scs.georesults.om.eredmeny.EtapEredmeny;
import scs.georesults.om.eredmeny.MindenEtapEredmeny;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.verseny.Etap;
import scs.georesults.om.verseny.VersenyImpl;

/**
 * <p>Az etapok összesített eredményét frissítő szolgáltatás osztálya.</p>
 */
public class MindenEtapFrissitesAction extends GlobalEredmenyFrissitesActionBase
{

  protected List getSzakaszElemek () throws WebException, RdbException
  {
    return Etap.loadAllForVerseny( getDb(), getVerseny() );
  }

  protected List getGlobalEredmenyek () throws WebException, RdbException
  {
    return MindenEtapEredmeny.loadAllForVerseny( getDb(), getVerseny() );
  }

  protected ReszEredmeny getNewOsszInstance ( Nevezes nevezes )
  {
    return ( ReszEredmeny ) MindenEtapEredmeny.newInstance( nevezes.getVid(), nevezes.getRajtszam() );
  }

  protected ReszEredmeny getNewReszInstance ( long id, int rajtszam )
  {
    return ( ReszEredmeny ) EtapEredmeny.newInstance( id, rajtszam );
  }

  protected void setVersenyFrissitendoFlags ( VersenyImpl verseny )
  {
    verseny.setEredmenyFrissitendoMindenEtap( false );
  }

}
