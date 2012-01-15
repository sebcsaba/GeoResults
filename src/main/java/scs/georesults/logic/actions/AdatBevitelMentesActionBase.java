package scs.georesults.logic.actions;

import scs.javax.dii.DIIException;
import scs.javax.dii.PropertyUtils;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.GeoException;
import scs.georesults.logic.LezarvaException;
import scs.georesults.logic.beans.AdatbevitelBean;

/**
 * <p>Az adatbeviteli funkcióknál a futam mentését végző szolgáltatások közös ősosztálya.</p>
 * <p>Betölti a HTTP kérésben érkezett paraméterekből a futam adatait, majd eltárolja azt
 * az adatbázisban.</p>
 */
public abstract class AdatBevitelMentesActionBase extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    try {
      if ( getVerseny().isLeVanZarva() )throw new LezarvaException();
      AdatbevitelBean adatokBean = ( AdatbevitelBean ) getFromSession( getAdatokSessionKey() );
      StorableEntityBase entity = adatokBean.getFutam();
      fillEntity( entity, adatokBean, form );
      if ( adatokBean.isCreate() ) {
        entity.create( getDb() );
      } else {
        entity.update( getDb() );
      }
      long id = ( ( Long ) PropertyUtils.getProperty( entity, getIdFieldName() ) ).longValue();
      invalidate( id );
      return "ok";
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
  }

  /**
   * Az adatokat tartalmazó bean osztály munkafolyamat-beli kulcsát adja vissza.
   */
  protected abstract String getAdatokSessionKey ();

  /**
   * A kijelölt versenyszámon jelzi, hogy módosítás történt az adatain, ezért a hozzá tartozó eredményt frissíteni kell.
   *
   * @param id A versenyszám azonosítója
   */
  protected abstract void invalidate ( long id ) throws WebException, RdbException;

  /**
   * Feltölti a HTTP kérésben érkezett paraméterekkel a betöltött futam-objektumot.
   *
   * @param entity A betöltött futam-objektum
   * @param adatokBean Az adatbevitelt segítő osztály
   * @param form A kérésben érkezett paramétereket tartalmazó objektum
   */
  protected abstract void fillEntity ( StorableEntityBase entity, AdatbevitelBean adatokBean, DynamicForm form ) throws WebException, RdbException;

  /**
   * A futamot tartalmazó versenyrész objektum azonosító mezőjének neve.
   */
  protected abstract String getIdFieldName ();

}
