package scs.georesults.logic.actions.etap;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.om.verseny.Etap;

/**
 * <p>Egy etap alapadatainak betöltését végző szolgáltatás osztálya.</p>
 */
public class AdatokBetoltesAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    Etap etap = Etap.newInstance( form.getLong( "eid" ) );
    etap.read( getDb() );
    setToSession( "etap", etap );
    return "ok";
  }

}
