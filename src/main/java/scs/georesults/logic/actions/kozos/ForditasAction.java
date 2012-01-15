package scs.georesults.logic.actions.kozos;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.common.Constants;
import scs.georesults.logic.actions.KozosActionBase;
import scs.georesults.logic.beans.kozos.ForditasBean;
import scs.georesults.om.kozos.Nyelv;
import scs.georesults.om.kozos.SzotarBejegyzes;

/**
 * <p>Fordítások kezelését végző szolgáltatás osztálya.</p>
 * <p>Három működési módja van, amir a HTTP késésben átadott paraméterek
 * határoznak meg:</p>
 * <ul>
 * <li>Ha a kérés tartalmaz <code>group</code> paramétert, akkor az általa megadott
 *   csoportnak megfelelő szavakat és kifejezéseket tölti be szerkesztésre.</li>
 * <li>Ha a kérés tartalmaz <code>alapnyelv</code> paramétert, akkor az általa megadott
 *   nyelv lesz a fordítás alapnyelve, vagyis azon a nyelven jelennek meg a lefordítandó
 *   szavak és kifejezések.</li>
 * <li>Ha a kérés tartalmaz <code>mode</code> paramétert, amelynek értéke <code>"save"</code>,
 *   akkor a HTTP kérés paramétereiből betölti a kifejezéseket, és eltárolja azokat
 *   a szótárban, és az adatbázisban.</li>
 * </ul>
 */
public class ForditasAction extends KozosActionBase
{

  /**
   * A fordítás adatait tartlamzó bean kulcsa a munkamenetben.
   */
  public static final String FRODITAS_BEAN_SESSION_KEY = "forditasBean";

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    Nyelv nyelv = ( Nyelv ) getFromSession( Constants.SESSION_KEY_NYELV );
    if ( form.has( "group" ) ) {
      ForditasBean bean = new ForditasBean( form.getString( "group" ), nyelv.getLang(), nyelv );
      setToSession( FRODITAS_BEAN_SESSION_KEY, bean );
    } else if ( form.has( "alapnyelv" ) ) {
      ForditasBean bean = ( ForditasBean ) getFromSession( FRODITAS_BEAN_SESSION_KEY );
      bean.setAlapnyelv( form.getString( "alapnyelv" ) );
    } else if ( form.has( "mode" ) && form.getString( "mode" ).equals( "save" ) ) {
      ForditasBean bean = ( ForditasBean ) getFromSession( FRODITAS_BEAN_SESSION_KEY );
      List bejegyzesek = bean.getBejegyzesek();
      for ( int i = 0; i < bejegyzesek.size(); ++i ) {
        SzotarBejegyzes szb = ( SzotarBejegyzes ) bejegyzesek.get( i );
        szb.setValuestr( form.getString( szb.getKeystr() ) );
        szb.update( getDb() );
      }
      globalSzotarFrissites( nyelv );
    }
    return "ok";
  }

}
