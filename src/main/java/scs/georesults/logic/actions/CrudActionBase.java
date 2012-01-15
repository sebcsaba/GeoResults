package scs.georesults.logic.actions;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.javax.web.request.InvalidRequestFieldException;
import scs.georesults.GeoException;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.LezarvaException;
import scs.georesults.logic.beans.ReszAdatBean;

/**
 * <p>A CRUD (Create, Read, Update, Delete) műveleteket megvalósító szolgáltatások közös,
 * absztrakt ősosztálya.</p>
 * <p>A HTTP kérésben kaptott <code>mode</code> és <code>id</code> paraméterek alapján a
 * következő működési módokat biztosítja:</p>
 * <ul>
 * <li><code>mode="new"</code> Új objektum létrehozásához előkészíti a környezetet. Lásd {@link modeNew()} metódus.</li>
 * <li><code>mode="edit" & id!=null</li> Az <code>id</code> paraméterben megadott azonosítójú objektum adatainak megtekintése (módosítása). Lásd a {@link modeEdit(Long)} metódust.</li>
 * <li><code>mode="delete" & id!=null</li> Az <code>id</code> paraméterben megadott azonosítójú objektum törlése. Lásd a {@link modeDelete(Long)} metódust.</li>
 * <li><code>mode="delete" & id==null</li> Az összes, adott típusú objektum törlése. Lásd a {@link modeDeleteAll()} metódust.</li>
 * <li><code>mode="save"</code> A megjelenített objektum adatainak feldolgozása, és adatbázisba eltárolása. Lásd a {@link modeSave(DynamicForm)} metódust.</li>
 * <li><code>mode="back"</code> A megjelenített lapról a listát tartalmazó lapra való visszatérés. Lásd a {@link modeBack()} metódust.</li>
 * </ul>
 */
public abstract class CrudActionBase extends GeoActionBase
{

  /**
   * A művelet segítésére létrehozott {@link ReszAdat} típusú objektum kulcsa a munkamenetben.
   */
  public static final String SESSION_KEY_RESZADAT = "reszAdat";

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    try {
      String mode = form.getString( "mode" );
      Long id = null;
      if ( form.has( "id" ) ) id = new Long( form.getLong( "id" ) );
      String result = doBeforeModeExecution( mode, id );
      if ( result != null )return result;
      if ( mode.equals( "new" ) ) {
        if ( getVerseny().isLeVanZarva() )throw new LezarvaException();
        return modeNew();
      } else if ( mode.equals( "edit" ) ) {
        return modeEdit( id );
      } else if ( mode.equals( "delete" ) && id != null ) {
        if ( getVerseny().isLeVanZarva() )throw new LezarvaException();
        return modeDelete( id );
      } else if ( mode.equals( "delete" ) && id == null ) {
        if ( getVerseny().isLeVanZarva() )throw new LezarvaException();
        return modeDeleteAll();
      } else if ( mode.equals( "save" ) ) {
        if ( getVerseny().isLeVanZarva() )throw new LezarvaException();
        return modeSave( form );
      } else if ( mode.equals( "back" ) ) {
        return modeBack();
      } else throw new GeoException( "unknown mode parameter: '" + mode + "'" );
    }
    catch ( GeoMessageException ex ) {
      if ( ex.isFallBack() ) {
        setToRequest( "error", ex );
        return "errorBack";
      } else {
        throw ex;
      }
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
  }

  /**
   * Új objektum létrehozásához készíti elő a környezetet.
   * Egy új, üres objektumot hoz létre a megfelelő osztályból, és
   * beállítja egy új {@link ReszAdatBean} objektumba.
   */
  protected String modeNew () throws WebException, RdbException
  {
    StorableEntityBase entity = newEntityForNew();
    ReszAdatBean reszAdat = createReszAdatBean( entity, true );
    setToSession( SESSION_KEY_RESZADAT, reszAdat );
    return "ok";
  }

  /**
   * A paraméterben megadott azonosítójú objektumot betölti az adatbázisból,
   * és előkészíti a megjelenítésre / szerkesztésre. A betöltött
   * objektumot beállítja egy új {@link ReszAdatBean} objektumba.
   */
  protected String modeEdit ( Long id ) throws WebException, RdbException
  {
    StorableEntityBase entity = newEntityFromId( id );
    entity.read( getDb() );
    ReszAdatBean reszAdat = createReszAdatBean( entity, false );
    setToSession( SESSION_KEY_RESZADAT, reszAdat );
    return "ok";
  }

  /**
   * A paraméterben megadott azonosítójú objektumot törli az adatbázisból.
   */
  protected String modeDelete ( Long id ) throws WebException, RdbException
  {
    StorableEntityBase entity = newEntityFromId( id );
    doBeforeDelete( entity );
    entity.delete( getDb() );
    doAfterDelete( entity );
    updateParentList();
    return "back";
  }

  /**
   * Az összes, adott típusú (és aadott listában szereplő) objektumot törli az adatbázisból.
   */
  protected String modeDeleteAll () throws WebException, RdbException
  {
    List items = getAllForParent();
    doBeforeDeleteAll( items );
    for ( int i = 0; i < items.size(); ++i ) {
      StorableEntityBase entity = ( StorableEntityBase ) items.get( i );
      entity.delete( getDb() );
    }
    doAfterDeleteAll();
    updateParentList();
    return "back";
  }

  /**
   * Az űrlap adatai alapján módosítja a {@link ReszAdatBean} objektumban szereplő
   * adatobjektumot, és eltárolja az adatbázisban. Az eltárolás lehet létrehozás
   * vagy frissítés, a {@link ReszAdatBean.isCreate()} eredményétől függően.
   */
  protected String modeSave ( DynamicForm form ) throws WebException, RdbException, DIIException
  {
    ReszAdatBean reszAdat = ( ReszAdatBean ) getFromSession( SESSION_KEY_RESZADAT );
    StorableEntityBase entity = reszAdat.getResz();
    readEntityFromRequest( entity, form );
    checkUniqueAsNeed( reszAdat );
    if ( reszAdat.isCreate() ) {
      doBeforeCreate( entity );
      entity.create( getDb() );
      doAfterCreate( entity );
    } else {
      doBeforeUpdate( entity );
      entity.update( getDb() );
      doAfterUpdate( entity );
    }
    removeFromSession( SESSION_KEY_RESZADAT );
    updateParentList();
    return "back";
  }

  /**
   * Törli a {@link ReszAdatBean} objektumot a munkamenetből, és visszalép az elemek listáját tartalamzó oldalra.
   */
  protected String modeBack () throws WebException
  {
    removeFromSession( SESSION_KEY_RESZADAT );
    return "back";
  }

  /**
   * Ez a művelet hozza létre a szükséges {@link ReszAdatBean} objektumot.
   */
  protected ReszAdatBean createReszAdatBean ( StorableEntityBase entity, boolean create ) throws WebException, RdbException
  {
    return new ReszAdatBean( entity, create );
  }

  /**
   * Ez a művelet hoz létre egy új, üres adatobjektumot.
   */
  protected abstract StorableEntityBase newEntityForNew () throws WebException, RdbException;

  /**
   * Ez a művelet hoz létre olyan adatobjektumot, amely a paraméterben megadott azonosítóval
   * rendelkezik, és be is tölti az objektum adatait az adatbázisból.
   */
  protected abstract StorableEntityBase newEntityFromId ( Long id ) throws WebException, RdbException;

  /**
   * Az adatobjektum adatait betölti a kapott HTTP kérés paramétereiből.
   *
   * @param entity Az adatobjektum.
   * @param form A HTTP kérés paramétereit tartalmazó objektum
   */
  protected abstract void readEntityFromRequest ( StorableEntityBase entity, DynamicForm form ) throws InvalidRequestFieldException, WebException, RdbException;

  /**
   * Elvégzi az objektum létrehozás illetve módosítása előtti szükséges ellenőrzést, hogy elkerülje a névütközést.
   */
  protected abstract void checkUniqueAsNeed ( ReszAdatBean reszAdat ) throws WebException, RdbException, DIIException;

  /**
   * Frissíti a 'szülő listát', vagyis az adott táblázatban megjelenő adott típusú objektumok
   * listáját.
   */
  protected abstract void updateParentList () throws WebException, RdbException;

  /**
   * Visszaadja a 'szülő listát', vagyis az adott táblázatban megjelenő adott típusú objektumok
   * listáját.
   */
  protected abstract List getAllForParent () throws WebException, RdbException;

  /**
   * A megfelelő működési mód kiválasztása és végrehajtása előtt a szolgáltatás meghívja ezt a metódust. Ebben az osztályban üres, de a leszármazottak felüldefiniálhatják.
   *
   * @param mode A működési módot leíró string
   * @param id A paraméterként kapott azonosító
   * @return <code>null</code>, ha a normál működési módot kell folytatni.
   * Ha más értékkel tér vissza, akkor a {@link serve(DynamicForm)} metódus
   * befejezi futását, és a kapott stringgel visszatér.
   */
  protected String doBeforeModeExecution ( String mode, Long id ) throws WebException, RdbException
  {
    return null;
  }

  /**
   * Ezt a - jelenleg üres, de felüldefiniálható - műveletet hívja meg az új elemek
   * adatbázisba felvitele előtt.
   */
  protected void doBeforeCreate ( StorableEntityBase entity ) throws WebException, RdbException
  {}

  /**
   * Ezt a - jelenleg üres, de felüldefiniálható - műveletet hívja meg az objektumok
   * adatbázisban történő frissítése előtt.
   */
  protected void doBeforeUpdate ( StorableEntityBase entity ) throws WebException, RdbException
  {}

  /**
   * Ezt a - jelenleg üres, de felüldefiniálható - műveletet hívja meg az objektumok
   * adatbázisból történő törlése előtt.
   */
  protected void doBeforeDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {}

  /**
   * Ezt a - jelenleg üres, de felüldefiniálható - műveletet hívja meg az objektumok
   * teljes listájának az adatbázisból történő törlése előtt.
   */
  protected void doBeforeDeleteAll ( List src ) throws WebException, RdbException
  {}

  /**
   * Ezt a - jelenleg üres, de felüldefiniálható - műveletet hívja meg az új elemek
   * adatbázisba felvitele után.
   */
  protected void doAfterCreate ( StorableEntityBase entity ) throws WebException, RdbException
  {}

  /**
   * Ezt a - jelenleg üres, de felüldefiniálható - műveletet hívja meg az objektumok
   * adatbázisban történő frissítése után.
   */
  protected void doAfterUpdate ( StorableEntityBase entity ) throws WebException, RdbException
  {}

  /**
   * Ezt a - jelenleg üres, de felüldefiniálható - műveletet hívja meg az objektumok
   * adatbázisból történő törlése után.
   */
  protected void doAfterDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {}

  /**
   * Ezt a - jelenleg üres, de felüldefiniálható - műveletet hívja meg az objektumok
   * teljes listájának az adatbázisból történő törlése után.
   */
  protected void doAfterDeleteAll () throws WebException, RdbException
  {}

}
