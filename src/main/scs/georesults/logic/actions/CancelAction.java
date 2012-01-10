package scs.georesults.logic.actions;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpSession;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.javax.web.WebSession;
import scs.georesults.common.Constants;

/**
 * <p>A munkafolyamatot tisztító szolgáltatás osztálya.</p>
 * <p>Kitöröl a munkafolyamatból minden olyan objektumot, amely
 * csupán az aktuális művelet végrehajtásához volt szükséges.</p>
 */
public class CancelAction extends GeoActionBase
{

  /**
   * Azon objektumok kulcsai, amelyek a munkamenetben folyamatosan jelen vannak, tehát a tisztításnál nem kerülnek törlésre.
   */
  private static Set persistents;

  static
  {
    persistents = new HashSet();
    persistents.add( WebSession.SESSION_VALID_KEY );
    persistents.add( Constants.SESSION_KEY_NYELV );
    persistents.add( Constants.SESSION_KEY_GLOBAL_SZOTAR );
    persistents.add( Constants.SESSION_KEY_VERSENY );
    persistents.add( Constants.SESSION_KEY_VERSENY_SZOTAR );
    persistents.add( Constants.SESSION_KEY_ADDON_CHARMAP );
    persistents.add( "org.apache.struts.action.LOCALE" );
  }

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException
  {
    clearSession( getSession() );
    return form.getString( "go" );
  }

  /**
   * Ez a művelet hajtja végre a tényleges tisztíást a kapott munkafolyamaton.
   *
   * @param session A tisztítandó munkafolyamat
   */
  public static void clearSession ( HttpSession session )
  {
    for ( Enumeration en = session.getAttributeNames(); en.hasMoreElements(); ) {
      String attrName = ( String ) en.nextElement();
      if ( !persistents.contains( attrName ) ) session.removeAttribute( attrName );
    }
  }

}
