package scs.georesults.logic.actions.menetlevel;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.logic.beans.menetlevel.MenetlevelAdatokBean;

/**
 * <p>A felugró ablakban megnyíló, sorrend-függő feladathoz tartozó adatblokk adatainak
 * eltárolását végző szolgáltatás osztálya.</p>
 */
public class SorrendPopupAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    MenetlevelAdatokBean mlab = ( MenetlevelAdatokBean ) getFromSession( "menetlevelAdatok" );
    long sfftid = form.getLong( "sfftid" );
    mlab.fillSorrendFuggoBejegyzesek( form, sfftid );
    return "ok";
  }

}
