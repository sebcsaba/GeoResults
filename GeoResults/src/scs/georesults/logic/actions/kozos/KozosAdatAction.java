package scs.georesults.logic.actions.kozos;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.javax.web.request.InvalidRequestFieldException;
import scs.georesults.common.Constants;
import scs.georesults.common.szotar.GlobalSzotar;
import scs.georesults.logic.actions.KozosActionBase;
import scs.georesults.logic.beans.kozos.KozosAdatBean;
import scs.georesults.om.kozos.NyelvImpl;
import scs.georesults.om.kozos.SzotarBejegyzes;

/**
 * <p>Az országok és nyelvek adminisztálát végző szolgáltatások osztálya.</p>
 * <p>A két szolgáltatás olyannyira hasonló, hogy nem kell önálló osztály a
 * kettőhöz, ezért ez az osztály látja el mindkét feladatot. A működése
 * erőteljesen hasonlít a {@link scs.georesults.logic.actions.CrudActionBase}
 * osztályéra, paraméterezése is hasonló. Nincs törlés művelet definiálva.</p>
 */
public class KozosAdatAction extends KozosActionBase
{

  /**
   * A szolgáltatást segítő bean kulcsa a munkamenetben.
   */
  public static final String SESSION_BEAN_KEY = "reszAdat";

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    String mode = form.getString( "mode" );
    if ( mode == null || mode.length() == 0 )throw new InvalidRequestFieldException( "mode" );
    if ( mode.equals( "edit" ) ) {
      String lang = form.getString( "lang" );
      String key = form.getString( "key" );
      SzotarBejegyzes szb = SzotarBejegyzes.newInstance( lang, key );
      szb.read( getDb() );
      GlobalSzotar gsz = ( GlobalSzotar ) getFromSession( Constants.SESSION_KEY_GLOBAL_SZOTAR );
      KozosAdatBean bean = new KozosAdatBean( lang, gsz.getValue( NyelvImpl.BUNDLE_PREFIX + lang ), key, gsz.getValue( key ), szb.getValuestr() );
      setToSession( SESSION_BEAN_KEY, bean );
      return "ok";
    } else if ( mode.equals( "save" ) ) {
      KozosAdatBean bean = ( KozosAdatBean ) getFromSession( SESSION_BEAN_KEY );
      SzotarBejegyzes szb = SzotarBejegyzes.newInstance( bean.getLang(), bean.getKey() );
      szb.setValuestr( form.getString( "value" ) );
      szb.update( getDb() );
      globalSzotarFrissites();
      removeFromSession( SESSION_BEAN_KEY );
      return "back";
    } else if ( mode.equals( "back" ) ) {
      removeFromSession( SESSION_BEAN_KEY );
      return "back";
    } else throw new InvalidRequestFieldException( "mode" );
  }

}
