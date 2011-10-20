package scs.georesults.logic.actions.szakasz;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.om.verseny.Szakasz;

/**
 * <p>Egy szakasz alapadatainak betöltését végző szolgáltatás osztálya.</p>
 */
public class AdatokBetoltesAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    Szakasz szakasz = Szakasz.newInstance( form.getLong( "szid" ) );
    szakasz.read( getDb() );
    setToSession( "szakasz", szakasz );
    return "ok";
  }

}
