package scs.georesults.logic.actions;

import scs.javax.dii.DIIException;
import scs.javax.dii.PropertyUtils;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession.NoResultException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.GeoException;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.beans.AdatbevitelBean;

/**
 * <p>Az adatbeviteli funkcióknál a futam betöltését végző szolgáltatások közös ősosztálya.</p>
 * <p>Betölti a megadott eseményhez és megadott rajtszámhoz tartozó futamot. Ha még nincs ilyen,
 * akkor létrehoz egy újat.</p>
 */
public abstract class AdatBevitelBetoltesActionBase extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    try {
      AdatbevitelBean adatok = ( AdatbevitelBean ) getFromSession( getAdatokSessionKey() );
      StorableEntityBase betoltes = adatok.getBetoltes();
      long betoltottId = form.getInteger( getIdFieldName() );
      int betoltottRajtszam = form.getInteger( "rajtszam" );
      PropertyUtils.setProperty( betoltes, getIdFieldName(), new Long( betoltottId ) );
      PropertyUtils.setProperty( betoltes, "rajtszam", new Integer( betoltottRajtszam ) );
      try {
        if ( !adatok.isErvenyesRajtszam( betoltottRajtszam ) ) {
          adatok.setFutam( null );
          throw new GeoMessageException( "ER_NEM_LETEZO_RAJTSZAM" );
        }
      }
      catch ( DIIException ex ) {
        throw new GeoException( ex );
      }

      StorableEntityBase entity = createFutamEntity();
      PropertyUtils.setProperty( entity, getIdFieldName(), new Long( betoltottId ) );
      PropertyUtils.setProperty( entity, "rajtszam", new Integer( betoltottRajtszam ) );
      try {
        entity.read( getDb() );
        adatok.setCreate( false );
      }
      catch ( NoResultException ex ) {
        doBeforeCreate( entity );
        adatok.setCreate( true );
      }
      adatok.setFutam( entity );
      adatok.setKovetkezoRajtszam( betoltottRajtszam + 1 );
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
   * A futamot tartalmazó versenyrész objektum azonosító mezőjének neve.
   */
  protected abstract String getIdFieldName ();

  /**
   * Ha új futam-objektumot kell létrehozni, akkor ez a (jelenleg üres) metódus fog lefutni. A leszármazott osztályok felüldefiniálhatják.
   */
  protected void doBeforeCreate ( StorableEntityBase entity )
  {}

  /**
   * Egy új, megfelelő típusú futam-objektumot (menetlevél vagy szlalom-futam) hoz létre.
   */
  protected abstract StorableEntityBase createFutamEntity ();

}
