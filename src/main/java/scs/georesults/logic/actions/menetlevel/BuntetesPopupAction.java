package scs.georesults.logic.actions.menetlevel;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.logic.beans.menetlevel.MenetlevelAdatokBean;

/**
 * <p>A felugró ablakban megnyíló 'büntetések' adatblokk adatainak
 * eltárolását végző szolgáltatás osztálya.</p>
 */
public class BuntetesPopupAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    MenetlevelAdatokBean mlab = ( MenetlevelAdatokBean ) getFromSession( "menetlevelAdatok" );
    mlab.fillBuntetesek( form );
    return "ok";
  }

}
