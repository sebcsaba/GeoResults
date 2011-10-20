package scs.georesults.logic.actions.szakasz.reszek;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.actions.szakasz.SzakaszReszekActionBase;
import scs.georesults.logic.beans.ReszAdatBean;
import scs.georesults.om.etap.DarabFuggoEtapFeladat;
import scs.georesults.om.etap.SorrendFuggoEtapFeladat;
import scs.georesults.om.menetlevel.Menetlevel;
import scs.georesults.om.verseny.Etap;
import scs.georesults.om.verseny.EtapImpl;

/**
 * <p>Egy szakaszhoz tartozó etapok adminisztrálását végző szolgáltatás osztálya.</p>
 */
public class EtapokAction extends SzakaszReszekActionBase
{

  /**
   * Frissíti a 'szülő listát', vagyis a szakaszhoz és a versenyhez tartozó etapok
   * listáját.
   */
  protected void updateParentList () throws WebException, RdbException
  {
    getSzakasz().updateEtapok();
    getVerseny().updateEtapok();
  }

  /**
   * Visszaadja a 'szülő listát', vagyis a szakaszhoz tartozó etapok
   * listáját.
   */
  protected List getAllForParent () throws WebException, RdbException
  {
    return getSzakasz().getEtapok();
  }

  /**
   * Létrehoz egy új, üres etapot.
   */
  protected StorableEntityBase newEntityForNew () throws WebException
  {
    Etap e = Etap.newInstance();
    e.setSzid( getSzakasz().getSzid() );
    e.setVid( getVerseny().getVid() );
    e.setErtekelendo( true );
    e.setEredmenyFrissitendo( true );
    return e;
  }

  /**
   * Betölti a megadott azonosítójú etapot az adatbázisból.
   */
  protected StorableEntityBase newEntityFromId ( Long id )
  {
    return Etap.newInstance( id.longValue() );
  }

  /**
   * Az etap adatait betölti a kapott HTTP kérés paramétereiből.
   */
  protected void readEntityFromRequest ( StorableEntityBase entity, DynamicForm form ) throws WebException
  {
    Etap e = ( Etap ) entity;
    e.setNev( form.getDictionaryString( "nev" ) );
  }

  /**
   * Elvégzi az objektum létrehozás illetve módosítása előtti szükséges ellenőrzést, hogy elkerülje a névütközést.
   */
  protected void checkUniqueAsNeed ( ReszAdatBean reszAdat ) throws DIIException, RdbException, WebException
  {
    Etap etap = ( Etap ) reszAdat.getResz();
    List etapok = getVerseny().getEtapok();
    Etap oldEtap = ( Etap ) etapok.findItem( "nev", etap.getNev() );
    if ( oldEtap != null && oldEtap.getEid() != etap.getEid() ) {
      throw new GeoMessageException( "ER_MAR_LETEZIK_ILYEN_NEV" );
    }
  }

  /**
   * Ellenőrzi, hogy az adott etap törölhető-e. Ha nem, kivételt dob.
   */
  private void checkDeletable ( EtapImpl etap ) throws WebException, RdbException
  {
    if ( Menetlevel.loadAllForEtap( getDb(), etap ).size() > 0 ) {
      throw new GeoMessageException( "ER_NEM_TOROLHETO_MERT_HIVATKOZAS_VAN_RA" );
    }
  }

  /**
   * Az adott etap törlése előtt törli annak rész-objektumait (kaszkádolás).
   */
  private void cascadedDelete ( EtapImpl etap ) throws WebException, RdbException
  {
    List dfefk = etap.getDarabFuggoEtapFeladatok();
    for ( int i = 0; i < dfefk.size(); ++i ) {
      DarabFuggoEtapFeladat dfef = ( DarabFuggoEtapFeladat ) dfefk.get( i );
      dfef.delete( getDb() );
    }
    List sfefk = etap.getSorrendFuggoEtapFeladatok();
    for ( int i = 0; i < sfefk.size(); ++i ) {
      SorrendFuggoEtapFeladat dfef = ( SorrendFuggoEtapFeladat ) sfefk.get( i );
      dfef.delete( getDb() );
    }
  }

  /**
   * Etap törlése előtt ellenőrzi, hogy törölhető-e, és ha igen, kaszkádolva kitörli az etap feladatait.
   */
  protected void doBeforeDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {
    EtapImpl etap = ( EtapImpl ) entity;
    checkDeletable( etap );
    cascadedDelete( etap );
  }

  /**
   * Az összes etap törlése előtt ellenőrzi, hogy törölhetőek-e.
   * Ha mindegyik törölhető, akkor kaszkádolva kitörli az etap feladatait.
   */
  protected void doBeforeDeleteAll ( List src ) throws WebException, RdbException
  {
    for ( int i = 0; i < src.size(); ++i ) checkDeletable( ( EtapImpl ) src.get( i ) );
    for ( int i = 0; i < src.size(); ++i ) cascadedDelete( ( EtapImpl ) src.get( i ) );
  }

  /**
   * Etap adatainak frissítése előtt jelezzük, hogy az eredménylistát is frissíteni kell.
   */
  protected void doBeforeUpdate ( StorableEntityBase entity ) throws WebException
  {
    EtapImpl etap = ( EtapImpl ) entity;
    etap.setEredmenyFrissitendo( true );
  }

}
