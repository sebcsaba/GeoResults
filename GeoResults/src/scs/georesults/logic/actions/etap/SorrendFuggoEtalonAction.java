package scs.georesults.logic.actions.etap;

import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.Config;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.logic.beans.etap.EtapReszAdatBean;
import scs.georesults.om.etap.SorrendFuggoEtapFeladat;
import scs.georesults.om.etap.SorrendFuggoEtapFeladatEtalonBejegyzes;

/**
 * <p>Sorrend-függő feladat etalonját rögzítő szolgáltatás osztálya.</p>
 */
public class SorrendFuggoEtalonAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException
  {
    EtapReszAdatBean reszAdat = ( EtapReszAdatBean ) getFromSession( ReszAdatEtaphozActionBase.SESSION_KEY_RESZADAT );
    readEntityFromRequest( reszAdat, form );
    if ( form.has( "enlarge" ) ) {
      reszAdat.setDarab( reszAdat.getDarab() + Config.ETAP_RESZ_ADAT_LISTA_HOSSZ );
      return "again";
    } else {
      return "ok";
    }
  }

  /**
   * A kapott HTTP kérés paraméterei alapján feltölti a feladat-objektumot a megadott bejegyzésekkel.
   */
  protected void readEntityFromRequest ( EtapReszAdatBean reszAdat, DynamicForm form ) throws WebException
  {
    SorrendFuggoEtapFeladat sfef = ( SorrendFuggoEtapFeladat ) reszAdat.getResz();
    sfef.getBejegyzesek().clear();
    for ( int i = 0; i < reszAdat.getDarab(); ++i ) {
      String felirat = form.getOptionalString( "felirat_" + i );
      if ( felirat == null )break;
      boolean ervenyes = form.getBoolean( "ervenyes_" + Integer.toString( i ) );
      sfef.getBejegyzesek().add( SorrendFuggoEtapFeladatEtalonBejegyzes.newInstance( sfef.getEid(), sfef.getSfftid(), i, felirat, ervenyes ) );
    }
  }

}
