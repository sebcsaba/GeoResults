package scs.georesults.logic.actions.szlalom;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.logic.beans.szlalom.SzlalomAdatokBean;

/**
 * <p>A szlalom-futamok felvitele lap megnyitását biztosító szolgáltatás osztálya.</p>
 */
public class AlapBetoltesAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    SzlalomAdatokBean szab = new SzlalomAdatokBean( getVerseny() );
    setToSession( "szlalomAdatok", szab );
    return "ok";
  }

}
