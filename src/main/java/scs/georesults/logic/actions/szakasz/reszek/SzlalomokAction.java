package scs.georesults.logic.actions.szakasz.reszek;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.GeoException;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.actions.szakasz.SzakaszReszekActionBase;
import scs.georesults.logic.beans.ReszAdatBean;
import scs.georesults.om.szlalom.SzlalomFutam;
import scs.georesults.om.verseny.Szlalom;

/**
 * <p>Egy szakaszhoz tartozó szlalomok adminisztrálását végző szolgáltatás osztálya.</p>
 */
public class SzlalomokAction extends SzakaszReszekActionBase
{

  /**
   * Frissíti a 'szülő listát', vagyis a szakaszhoz és a versenyhez tartozó szlalomok
   * listáját.
   */
  protected void updateParentList () throws WebException, RdbException
  {
    getSzakasz().updateSzlalomok();
    getVerseny().updateSzlalomok();
  }

  /**
   * Visszaadja a 'szülő listát', vagyis a szakaszhoz tartozó szlalomok
   * listáját.
   */
  protected List getAllForParent () throws WebException, RdbException
  {
    return getSzakasz().getSzlalomok();
  }

  /**
   * Létrehoz egy új, üres szlalomot.
   */
  protected StorableEntityBase newEntityForNew () throws WebException
  {
    Szlalom sz = Szlalom.newInstance();
    sz.setSzid( getSzakasz().getSzid() );
    sz.setVid( getVerseny().getVid() );
    sz.setErtekelendo( true );
    sz.setEredmenyFrissitendo( true );
    return sz;
  }

  /**
   * Betölti a megadott azonosítójú szlalomot az adatbázisból.
   */
  protected StorableEntityBase newEntityFromId ( Long id )
  {
    return Szlalom.newInstance( id.longValue() );
  }

  /**
   * A szlalom adatait betölti a kapott HTTP kérés paramétereiből.
   */
  protected void readEntityFromRequest ( StorableEntityBase entity, DynamicForm form ) throws WebException
  {
    Szlalom sz = ( Szlalom ) entity;
    sz.setNev( form.getDictionaryString( "nev" ) );
    sz.setErtekelendo( form.getBoolean( "ertekelendo" ) );
  }

  /**
   * Elvégzi az objektum létrehozás illetve módosítása előtti szükséges ellenőrzést, hogy elkerülje a névütközést.
   */
  protected void checkUniqueAsNeed ( ReszAdatBean reszAdat ) throws DIIException, RdbException, WebException
  {
    Szlalom szlalom = ( Szlalom ) reszAdat.getResz();
    List szlalomok = getVerseny().getSzlalomok();
    Szlalom oldSzlalom = ( Szlalom ) szlalomok.findItem( "nev", szlalom.getNev() );
    if ( oldSzlalom != null && oldSzlalom.getSzlid() != szlalom.getSzlid() ) {
      throw new GeoMessageException( "ER_MAR_LETEZIK_ILYEN_NEV" );
    }
  }

  /**
   * Szlalom törlése előtt ellenőrzi, hogy törölhető-e. Ha nem, kivételt dob.
   */
  protected void doBeforeDelete ( StorableEntityBase entity ) throws GeoException, RdbException
  {
    Szlalom sz = ( Szlalom ) entity;
    if ( SzlalomFutam.loadAllForSzlalom( getDb(), sz ).size() > 0 ) {
      throw new GeoMessageException( "ER_NEM_TOROLHETO_MERT_HIVATKOZAS_VAN_RA" );
    }
  }

  /**
   * Az összes szlalom törlése előtt ellenőrzi, hogy mindegyik törölhető-e.
   * Ha nem, kivételt dob.
   */
  protected void doBeforeDeleteAll ( List src ) throws WebException, RdbException
  {
    for ( int i = 0; i < src.size(); ++i ) doBeforeDelete( ( Szlalom ) src.get( i ) );
  }

}
