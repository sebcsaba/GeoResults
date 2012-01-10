package scs.georesults.logic.actions.kozos;

import scs.javax.collections.List;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.GeoException;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.actions.KozosActionBase;
import scs.georesults.om.kozos.Nyelv;
import scs.georesults.logic.actions.*;

/**
 * <p>Az aktuális nyelv váltását végző szolgáltatás osztálya.</p>
 * <p>Abban különbözik a {@link scs.georesults.logic.actions.kulso.NyelvValtasAction}
 * osztálytól, hogy ez akkor került mehgívásra, ha már van kiválasztva aktuális verseny.</p>
 */
public class BelsoNyelvValtasAction extends KozosActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException
  {
    try {
      String lang = form.getString( "lang" );
      List nyelvek = Nyelv.loadAll( getDb() );
      int index = nyelvek.findItemIndex( "lang", lang );
      if ( index == -1 )throw new GeoMessageException( "ER_NINCS_ILYEN_NYELV" );
      Nyelv nyelv = ( Nyelv ) nyelvek.get( index );
      nyelvFrissites( nyelv );
      globalSzotarFrissites( nyelv );
      versenySzotarFrissites( nyelv );
      return form.getString( "from" );
    }
    catch ( Exception ex ) {
      throw new GeoException( ex );
    }
  }

}
