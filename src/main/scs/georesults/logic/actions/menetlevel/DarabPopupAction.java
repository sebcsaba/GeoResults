package scs.georesults.logic.actions.menetlevel;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.logic.beans.menetlevel.MenetlevelAdatokBean;

/**
 * <p>A felugró ablakban megnyíló, darabszám-függő feladathoz tartozó adatblokk adatainak
 * eltárolását végző szolgáltatás osztálya.</p>
 */
public class DarabPopupAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    MenetlevelAdatokBean mlab = ( MenetlevelAdatokBean ) getFromSession( "menetlevelAdatok" );
    long dfftid = form.getLong( "dfftid" );
    mlab.fillDarabFuggoBejegyzesek( form, dfftid );
    return "ok";
  }

}
