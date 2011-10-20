package scs.georesults.logic.actions.kulso;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.om.verseny.Verseny;

/**
 * <p>Egy verseny kiválasztását végző szolgáltatás osztálya.</p>
 */
public class VersenyValasztasAction extends VersenyValasztoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    Long id = form.getOptionalLongWithoutCheckbox( "verseny" );
    if ( id == null )throw new GeoMessageException( "ER_NINCS_VERSENY_KIVALASZTVA" );
    Verseny verseny = ( Verseny ) Verseny.newInstance( id.longValue() );
    verseny.read( getDb() );
    versenySessionbe( verseny );
    return "ok";
  }

}
