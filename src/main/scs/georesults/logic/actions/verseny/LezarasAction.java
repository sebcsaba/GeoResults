package scs.georesults.logic.actions.verseny;

import scs.javax.lang.Date;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.om.verseny.Verseny;

/**
 * <p>Az aktuális versenyt lezáró szolgáltatás osztálya.</p>
 */
public class LezarasAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    Verseny verseny = getVerseny();
    verseny.read( getDb() );
    verseny.setLezarva( new Date() );
    verseny.update( getDb() );
    return "ok";
  }

}
