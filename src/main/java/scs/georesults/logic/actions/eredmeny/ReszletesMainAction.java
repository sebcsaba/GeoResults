package scs.georesults.logic.actions.eredmeny;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.logic.beans.eredmeny.ReszletesMainBean;
import scs.georesults.om.etap.DarabFuggoEtapFeladat;
import scs.georesults.om.etap.SorrendFuggoEtapFeladat;
import scs.georesults.om.verseny.EtapImpl;

/**
 * <p>A részletes eredménylista megnyitását előkészítő szolgáltatás.</p>
 * <p>Megvizsgálja a kapott HTTP kérés paramétereit, hogy mely feladatok
 * eredményét kell részletesen megjeleníteni. A feldolgozott adatokat
 * a munkafolyamatban található {@link ReszletesMainBean} osztályban tárolja.</p>
 */
public class ReszletesMainAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    ReszletesMainBean bean = ( ReszletesMainBean ) getFromSession( "reszletesMain" );
    for ( int i = 0; i < bean.getEtapok().size(); ++i ) {
      EtapImpl etap = ( EtapImpl ) bean.getEtapok().get( i );
      if ( etap.isErtekelendo() ) {
        ReszletesMainBean.EtapFlags flags = ( ReszletesMainBean.EtapFlags ) bean.getEtapFlags().get( new Long( etap.getEid() ) );
        for ( int j = 0; j < etap.getDarabFuggoEtapFeladatok().size(); ++j ) {
          DarabFuggoEtapFeladat dfef = ( DarabFuggoEtapFeladat ) etap.getDarabFuggoEtapFeladatok().get( j );
          boolean detail = form.getBoolean( "detail_" + dfef.getEid() + "_d" + dfef.getDfftid() );
          flags.getDfef().put( new Long( dfef.getDfftid() ), new Boolean( detail ) );
        }
        for ( int j = 0; j < etap.getSorrendFuggoEtapFeladatok().size(); ++j ) {
          SorrendFuggoEtapFeladat sfef = ( SorrendFuggoEtapFeladat ) etap.getSorrendFuggoEtapFeladatok().get( j );
          boolean detail = form.getBoolean( "detail_" + sfef.getEid() + "_s" + sfef.getSfftid() );
          flags.getSfef().put( new Long( sfef.getSfftid() ), new Boolean( detail ) );
        }
      }
    }
    getRequest().setAttribute( "openJavaScript", Boolean.TRUE );
    return "ok";
  }

}
