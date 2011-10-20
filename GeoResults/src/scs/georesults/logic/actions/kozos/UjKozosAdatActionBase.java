package scs.georesults.logic.actions.kozos;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.GeoException;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.actions.KozosActionBase;
import scs.georesults.om.kozos.Nyelv;
import scs.georesults.om.kozos.SzotarBejegyzes;

/**
 * <p>Új nyelv és ország létrehozását végző szolgáltatások közös ősosztálya.</p>
 */
public abstract class UjKozosAdatActionBase extends KozosActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    String kod = form.getString( "kod" );
    checkKod( kod );
    StorableEntityBase newEntity = createNewEntity( kod );
    String selfName = getSelf( form );
    Object additionalObject = createAdditionalObjects( form );
    newEntity.create( getDb() );
    useAdditionalObjects( additionalObject, kod );
    List letezoNyelvek = Nyelv.loadAll( getDb() );
    for ( int i = 0; i < letezoNyelvek.size(); ++i ) {
      Nyelv letezoNyelv = ( Nyelv ) letezoNyelvek.get( i );
      SzotarBejegyzes szb = SzotarBejegyzes.newInstance( letezoNyelv.getLang(), getBundlePrefix() + kod, selfName );
      szb.create( getDb() );
    }
    globalSzotarFrissites();
    return "ok";
  }

  /**
   * Ellenőrzi, hogy a megadott kód megfelelő-e.
   */
  protected void checkKod ( String kod ) throws RdbException, WebException
  {
    try {
      if ( kod == null || !kod.matches( "[a-z]{2}" ) ) {
        throw new GeoMessageException( "ER_HIBAS_KOD" );
      }
      List exist = getAllExistEntities();
      int index = exist.findItemIndex( getEntityKeyName(), kod );
      if ( index != -1 ) {
        throw new GeoMessageException( "ER_MAR_LETEZO_KOD" );
      }
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
  }

  /**
   * Visszaadja a HTTP kérés "self" paraméterét.
   * Ez kötelező paraméter, hiánya esetén kivételt dob.
   */
  protected String getSelf ( DynamicForm form ) throws WebException
  {
    String self = form.getString( "self" );
    if ( self == null || self.length() == 0 )throw new GeoMessageException( "ER_HIBAS_NEV" );
    return self;
  }

  /**
   * Az adott típusra jellemző string, amely a típushoz tartozó objektumok szótárban tárolt neveinek kulcsa elején szerepel.
   */
  protected abstract String getBundlePrefix ();

  /**
   * Az összes, adott típusú objektumot adja vissza.
   */
  protected abstract List getAllExistEntities () throws WebException, RdbException;

  /**
   * Az objektum kulcsmezőjének nevét adja vissza.
   */
  protected abstract String getEntityKeyName ();

  /**
   * Létrehoz eggy új objektumot az adott típusból, a megadott kóddal.
   */
  protected abstract StorableEntityBase createNewEntity ( String kod );

  /**
   * Az új objektumhoz kapcsolódó esetleges további objektumok létrehozásához szükséges metaadatot állítja elő..
   */
  protected abstract Object createAdditionalObjects ( DynamicForm form ) throws WebException, RdbException;

  /**
   * Az új objektumhoz kapcsolódó esetleges további objektumokat hozza létre.
   */
  protected abstract void useAdditionalObjects ( Object additionalObject, String kod ) throws WebException, RdbException;

}
