package scs.georesults.logic.actions;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;

/**
 * <p>Az aktuális verseny adatait frissítő szolgáltatás osztálya.</p>
 * <p>Betölti az adatbázisból az aktuális verseny adatait.</p>
 */
public class VersenyFrissitesAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    getVerseny().refresh( getDb() );
    return form.getString( "go2" );
  }

}
