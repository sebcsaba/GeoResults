package scs.georesults.logic.actions.etap;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.Config;
import scs.georesults.GeoException;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaLista;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaResz;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.beans.ReszAdatBean;
import scs.georesults.logic.beans.etap.EtapReszAdatBean;
import scs.georesults.logic.beans.etap.SorrendFuggoExtra;
import scs.georesults.logic.utils.EllenorzesTipusUtils;
import scs.georesults.om.alap.SorrendFuggoFeladatTipus;
import scs.georesults.om.etap.SorrendFuggoEtapFeladat;
import scs.georesults.om.etap.SorrendFuggoEtapFeladatEtalonBejegyzes;

/**
 * <p>Az etaphoz tartozó sorrend-függő feladatokat adminisztráló szolgáltatások osztálya.</p>
 */
public class SorrendFuggoEtapFeladatokAction extends ReszAdatEtaphozActionBase
{

  /**
   * Felüldefiniálja a mentési műveletet. Ha létrehozás történt, akkor ő maga
   * hozza létre az objektumot a megfelelő módban, majd átáll szerkesztés
   * üzemmódra, és újra a részletes képernyőt jeleníti meg. Ezt azért így teszi,
   * mert a megjelenített űrlap adatai túl sok helyen függnek a kiválasztott
   * feladat-típustól, ezért a beállításukhoz előírjuk, hogy a feladat típusa
   * már rögzített kell legyen.
   */
  protected String modeSave ( DynamicForm form ) throws WebException, RdbException, DIIException
  {
    EtapReszAdatBean reszAdat = ( EtapReszAdatBean ) getFromSession( SESSION_KEY_RESZADAT );
    if ( reszAdat.isCreate() ) {
      StorableEntityBase entity = reszAdat.getResz();
      readEntityFromRequestToCreate( entity, form );
      checkUniqueAsNeed( reszAdat );
      doBeforeCreate( entity );
      entity.create( getDb() );
      doAfterCreate( entity );
      updateParentList();
      reszAdat.setCreate( false );
      return "ok";
    } else return super.modeSave( form );
  }

  protected int getEntityCurrentCount ( StorableEntityBase entity )
  {
    SorrendFuggoEtapFeladat sfef = ( SorrendFuggoEtapFeladat ) entity;
    return sfef.getBejegyzesek().size();
  }

  /**
   * Az adatobjektum adatait betölti a kapott HTTP kérés paramétereiből akkor,
   * ha létrehozás történik. Ekkor ugyanis a normál mezők helyett
   * csupán a feladat típusát kijelölő mező van az űrlapon.
   *
   * @param entity Az adatobjektum.
   * @param form A HTTP kérés paramétereit tartalmazó objektum
   */
  protected void readEntityFromRequestToCreate ( StorableEntityBase entity, DynamicForm form ) throws WebException, RdbException
  {
    SorrendFuggoEtapFeladat sfef = ( SorrendFuggoEtapFeladat ) entity;
    sfef.setSfftid( form.getLong( "sfftid" ) );
    SorrendFuggoFeladatTipus sfft = SorrendFuggoFeladatTipus.newInstance( sfef.getSfftid() );
    sfft.read( getDb() );
    sfef.setReszletesBevitel( sfft.isReszletesBevitel() );
    sfef.setEllenorzesTipus( null );
    sfef.setDarab( sfef.isReszletesBevitel() ? null : new Integer( 0 ) );
  }

  protected void readEntityFromRequest ( StorableEntityBase entity, DynamicForm form ) throws WebException
  {
    SorrendFuggoEtapFeladat sfef = ( SorrendFuggoEtapFeladat ) entity;
    if ( form.has( "reszletesBevitel" ) ) sfef.setReszletesBevitel( form.getBoolean( "reszletesBevitel" ) );
    if ( sfef.isReszletesBevitel() ) {
      sfef.setDarab( null );
      sfef.setEllenorzesTipus( EllenorzesTipusUtils.getEllenorzesTipusFromForm( form, "ellenorzesTipus" ) );
    } else {
      sfef.setDarab( new Integer( form.getInteger( "darab" ) ) );
      sfef.setEllenorzesTipus( null );
    }
  }

  protected void checkUniqueAsNeed ( ReszAdatBean reszAdat ) throws DIIException, RdbException, WebException
  {
    if ( reszAdat.isCreate() ) {
      SorrendFuggoEtapFeladat sfef = ( SorrendFuggoEtapFeladat ) reszAdat.getResz();
      if ( etap.getSorrendFuggoEtapFeladatok().findItemIndex( "sfftid", new Long( sfef.getSfftid() ) ) >= 0 ) {
        throw new GeoMessageException( "ER_MAR_LETEZO_FELADATTIPUS" );
      }
    }
  }

  protected List getAllForParent () throws WebException, RdbException
  {
    return etap.getSorrendFuggoEtapFeladatok();
  }

  protected StorableEntityBase newEntityFromId ( Long id ) throws WebException
  {
    return SorrendFuggoEtapFeladat.newInstance( etap.getEid(), id.longValue() );
  }

  protected StorableEntityBase newEntityForNew () throws WebException, RdbException
  {
    if ( getVerseny().getSorrendFuggoFeladatTipusok().size() == 0 ) {
      throw new GeoMessageException( "ER_NEM_LETEZIK_SORRENDFUGGO_FELADAT_TIPUS", true );
    }
    SorrendFuggoEtapFeladat sfef = SorrendFuggoEtapFeladat.newInstance();
    sfef.setEid( etap.getEid() );
    return sfef;
  }

  protected void updateParentList () throws WebException, RdbException
  {
    etap.updateSorrendFuggoEtapFeladatok();
    if ( !etap.isEredmenyFrissitendo() ) {
      etap.read( getDb() );
      etap.setEredmenyFrissitendo( true );
      etap.update( getDb() );
    }
  }

  protected Object getDefaultBejegyzes ( StorableEntityBase entity )
  {
    SorrendFuggoEtapFeladat sfef = ( SorrendFuggoEtapFeladat ) entity;
    return SorrendFuggoEtapFeladatEtalonBejegyzes.newInstance( sfef.getEid(), sfef.getSfftid(), 0, "", true );
  }

  protected Object getBeanExtra ( boolean isNew, StorableEntityBase entity ) throws WebException, RdbException
  {
    try {
      if ( isNew ) {
        return null;
      } else {
        SorrendFuggoEtapFeladat sfef = ( SorrendFuggoEtapFeladat ) entity;
        List sffl = getVerseny().getSorrendFuggoFeladatTipusok();
        SorrendFuggoFeladatTipus sfft = ( SorrendFuggoFeladatTipus ) sffl.get( sffl.findItemIndex( "sfftid", new Long( sfef.getSfftid() ) ) );
        return new SorrendFuggoExtra( etap, sfft, sfef );
      }
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
  }

  protected void doAfterCreate ( StorableEntityBase entity ) throws WebException, RdbException
  {
    SorrendFuggoEtapFeladat sfef = ( SorrendFuggoEtapFeladat ) entity;
    etap.read( getDb() );
    updateMenetlevelformulaAfterCreate( sfef );
    etap.setEredmenyFrissitendo( true );
    etap.update( getDb() );
  }

  /**
   * Feladat létrehozása után frissíti az etap menetlevél-formuláját, ha szükséges.
   */
  private void updateMenetlevelformulaAfterCreate ( SorrendFuggoEtapFeladat sfef ) throws WebException, RdbException
  {
    if ( etap.getMenetlevelformula() != null ) {
      MenetlevelFormulaLista vlist = new MenetlevelFormulaLista( getVerseny().getMenetlevelformula() );
      MenetlevelFormulaLista elist = new MenetlevelFormulaLista( etap.getMenetlevelformula() );
      int index = vlist.findSorrendIndex( sfef.getSfftid() );
      if ( index >= 0 ) {
        elist.add( vlist.get( index ) );
        etap.setMenetlevelformula( elist.toString() );
      }
    }
    SorrendFuggoFeladatTipus sfft = SorrendFuggoFeladatTipus.newInstance( sfef.getSfftid() );
    sfft.read( getDb() );
    EtapReszAdatBean reszAdat = ( EtapReszAdatBean ) getFromSession( SESSION_KEY_RESZADAT );
    reszAdat.setExtra( new SorrendFuggoExtra( etap, sfft, sfef ) );
  }

  protected void doAfterUpdate ( StorableEntityBase entity ) throws WebException, RdbException
  {
    SorrendFuggoEtapFeladat sfef = ( SorrendFuggoEtapFeladat ) entity;
    etap.read( getDb() );
    updateMenetlevelformulaAfterUpdate( sfef );
    etap.setEredmenyFrissitendo( true );
    etap.update( getDb() );
  }

  /**
   * Feladat törlése után frissíti az etap menetlevél-formuláját, ha szükséges.
   */
  private void updateMenetlevelformulaAfterUpdate ( SorrendFuggoEtapFeladat sfef ) throws WebException
  {
    if ( etap.getMenetlevelformula() != null ) {
      MenetlevelFormulaLista elist = new MenetlevelFormulaLista( etap.getMenetlevelformula() );
      int index = elist.findSorrendIndex( sfef.getSfftid() );
      if ( index >= 0 ) {
        MenetlevelFormulaResz resz = elist.get( index );
        if ( resz.getCount() < sfef.getBejegyzesek().size() ) {
          int base = sfef.getBejegyzesek().size();
          resz.setCount( ( base / Config.ETAP_RESZ_ADAT_LISTA_HOSSZ + 1 ) * Config.ETAP_RESZ_ADAT_LISTA_HOSSZ );
          etap.setMenetlevelformula( elist.toString() );
        }
      }
    }
  }

  protected void doAfterDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {
    SorrendFuggoEtapFeladat sfef = ( SorrendFuggoEtapFeladat ) entity;
    etap.read( getDb() );
    updateMenetlevelformulaAfterDelete( sfef );
    etap.setEredmenyFrissitendo( true );
    etap.update( getDb() );
  }

  /**
   * Feladat törlése után frissíti az etap menetlevél-formuláját, ha szükséges.
   */
  private void updateMenetlevelformulaAfterDelete ( SorrendFuggoEtapFeladat sfef ) throws WebException
  {
    if ( etap.getMenetlevelformula() != null ) {
      MenetlevelFormulaLista elist = new MenetlevelFormulaLista( etap.getMenetlevelformula() );
      int index = elist.findSorrendIndex( sfef.getSfftid() );
      if ( index >= 0 ) {
        elist.remove( index );
        etap.setMenetlevelformula( elist.toString() );
      }
    }
  }

  protected void doAfterDeleteAll () throws WebException, RdbException
  {
    etap.read( getDb() );
    updateMenetlevelformulaAfterDeleteAll();
    etap.setEredmenyFrissitendo( true );
    etap.update( getDb() );
  }

  /**
   * Az összes feladat törlése után frissíti az etap menetlevél-formuláját, ha szükséges.
   */
  private void updateMenetlevelformulaAfterDeleteAll () throws WebException
  {
    if ( etap.getMenetlevelformula() != null ) {
      MenetlevelFormulaLista elist = new MenetlevelFormulaLista( etap.getMenetlevelformula() );
      elist.removeAllSorrend();
      etap.setMenetlevelformula( elist.toString() );
    }
  }

}
