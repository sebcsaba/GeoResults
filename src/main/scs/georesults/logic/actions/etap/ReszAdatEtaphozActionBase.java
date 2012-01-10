package scs.georesults.logic.actions.etap;

import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.Config;
import scs.georesults.logic.actions.CrudActionBase;
import scs.georesults.logic.beans.ReszAdatBean;
import scs.georesults.logic.beans.etap.EtapReszAdatBean;
import scs.georesults.om.verseny.EtapImpl;
import scs.georesults.logic.actions.*;

/**
 * <p>Az etapot alkotó részadatokat adminisztráló szolgáltatások osztálya.</p>
 */
public abstract class ReszAdatEtaphozActionBase extends CrudActionBase
{

  /**
   * A kijelölt etap
   */
  protected EtapImpl etap;

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását. Kiterjeszti a
   * szülőosztály működési módjait egy "megnövelés" móddal: ha a HTTP
   * kérésben <code>mode="enlarge"</code>, akkor a megjelenített
   * mezők számát megnöveli a {@link Config} osztályban megadott értékkel.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    if ( form.has( "enlarge" ) ) {
      EtapReszAdatBean reszAdat = ( EtapReszAdatBean ) getFromSession( SESSION_KEY_RESZADAT );
      readEntityFromRequest( reszAdat.getResz(), form );
      reszAdat.setDarab( reszAdat.getDarab() + Config.ETAP_RESZ_ADAT_LISTA_HOSSZ );
      return "ok";
    }
    return super.serve( form );
  }

  /**
   * Ez a művelet hozza létre a szükséges {@link EtapReszAdatBean} objektumot,
   * az alapértelmezett {@link ReszAdatBean} típusú objektum helyett.
   */
  protected ReszAdatBean createReszAdatBean ( StorableEntityBase entity, boolean create ) throws WebException, RdbException
  {
    EtapReszAdatBean reszAdat = new EtapReszAdatBean( entity, create, getDefaultBejegyzes( entity ) );
    reszAdat.setExtra( getBeanExtra( create, entity ) );
    if ( !create ) reszAdat.setDarab( getInitialCount( entity ) );
    return reszAdat;
  }

  /**
   * Azt adja meg, hogy az ablak megnyitásakor mennyi beviteli mező szerepeljen bejegyzések számára.
   */
  private int getInitialCount ( StorableEntityBase entity )
  {
    int base = getEntityCurrentCount( entity );
    return ( ( base / Config.ETAP_RESZ_ADAT_LISTA_HOSSZ ) + 1 ) * Config.ETAP_RESZ_ADAT_LISTA_HOSSZ;
  }

  /**
   * A szolgáltatás végrehajtása előtt betölti a megfelelő etap objektumot.
   */
  protected String doBeforeModeExecution ( String mode, Long id ) throws WebException
  {
    etap = ( EtapImpl ) getFromSession( "etap" );
    return null;
  }

  /**
   * Az adott feladathoz tartozó aktuális bejegyzések számát adja vissza.
   */
  protected abstract int getEntityCurrentCount ( StorableEntityBase entity );

  /**
   * Az adott feladattípushoz tartozó alapértelmezett bejegyzést adja vissza.
   */
  protected abstract Object getDefaultBejegyzes ( StorableEntityBase entity );

  /**
   * A műveletet segítő bean objektum kiegészítő (extra) részobjektumát hozza létre.
   */
  protected abstract Object getBeanExtra ( boolean isNew, StorableEntityBase entity ) throws WebException, RdbException;

}
