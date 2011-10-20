package scs.georesults.logic.actions.menetlevel;

import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.logic.beans.menetlevel.MenetlevelAdatokBean;

/**
 * <p>A menetlevelek felvitele lap megnyitását biztosító szolgáltatás osztálya.</p>
 */
public class AlapBetoltesAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException
  {
    MenetlevelAdatokBean mlab = new MenetlevelAdatokBean( getVerseny() );
    setToSession( "menetlevelAdatok", mlab );
    return "ok";
  }

}
