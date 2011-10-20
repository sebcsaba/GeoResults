package scs.georesults.logic.actions.etap;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaLista;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.beans.ReszAdatBean;
import scs.georesults.logic.beans.etap.EtapReszAdatBean;
import scs.georesults.om.etap.DarabFuggoEtapFeladat;
import scs.georesults.om.etap.DarabFuggoEtapFeladatEtalonBejegyzes;

/**
 * <p>Az etaphoz tartozó darabszám-függő feladatokat adminisztráló szolgáltatások osztálya.</p>
 */
public class DarabFuggoEtapFeladatokAction extends ReszAdatEtaphozActionBase
{

  /**
   * Visszaadja a feladat bejegyzéseinek számát.
   */
  protected int getEntityCurrentCount ( StorableEntityBase entity )
  {
    DarabFuggoEtapFeladat dfef = ( DarabFuggoEtapFeladat ) entity;
    return dfef.getBejegyzesek().size();
  }

  protected void readEntityFromRequest ( StorableEntityBase entity, DynamicForm form ) throws WebException, RdbException
  {
    DarabFuggoEtapFeladat dfef = ( DarabFuggoEtapFeladat ) entity;
    EtapReszAdatBean reszAdat = ( EtapReszAdatBean ) getFromSession( SESSION_KEY_RESZADAT );
    if ( reszAdat.isCreate() ) {
      dfef.setDfftid( form.getLong( "dfftid" ) );
    }
    dfef.getBejegyzesek().clear();
    for ( int i = 0; i < reszAdat.getDarab(); ++i ) {
      String cimke = form.getOptionalString( "cimke_" + Integer.toString( i ) );
      if ( cimke == null )break;
      int darab = form.getInteger( "darab_" + Integer.toString( i ) );
      dfef.getBejegyzesek().add( DarabFuggoEtapFeladatEtalonBejegyzes.newInstance( dfef.getEid(), dfef.getDfftid(), i, cimke, darab ) );
    }
  }

  protected void checkUniqueAsNeed ( ReszAdatBean reszAdat ) throws DIIException, RdbException, WebException
  {
    if ( reszAdat.isCreate() ) {
      DarabFuggoEtapFeladat dfef = ( DarabFuggoEtapFeladat ) reszAdat.getResz();
      if ( etap.getDarabFuggoEtapFeladatok().findItemIndex( "dfftid", new Long( dfef.getDfftid() ) ) >= 0 ) {
        throw new GeoMessageException( "ER_MAR_LETEZO_FELADATTIPUS" );
      }
    }
  }

  protected List getAllForParent () throws WebException, RdbException
  {
    return etap.getDarabFuggoEtapFeladatok();
  }

  protected StorableEntityBase newEntityFromId ( Long id ) throws WebException
  {
    return DarabFuggoEtapFeladat.newInstance( etap.getEid(), id.longValue() );
  }

  protected StorableEntityBase newEntityForNew () throws WebException, RdbException
  {
    if ( getVerseny().getDarabFuggoFeladatTipusok().size() == 0 ) {
      throw new GeoMessageException( "ER_NEM_LETEZIK_DARABFUGGO_FELADAT_TIPUS", true );
    }
    DarabFuggoEtapFeladat dfef = DarabFuggoEtapFeladat.newInstance();
    dfef.setEid( etap.getEid() );
    return dfef;
  }

  protected void updateParentList () throws WebException, RdbException
  {
    etap.updateDarabFuggoEtapFeladatok();
    if ( !etap.isEredmenyFrissitendo() ) {
      etap.read( getDb() );
      etap.setEredmenyFrissitendo( true );
      etap.update( getDb() );
    }
  }

  protected Object getDefaultBejegyzes ( StorableEntityBase entity )
  {
    DarabFuggoEtapFeladat dfef = ( DarabFuggoEtapFeladat ) entity;
    return DarabFuggoEtapFeladatEtalonBejegyzes.newInstance( dfef.getEid(), dfef.getDfftid(), 0, "", 0 );
  }

  protected Object getBeanExtra ( boolean isNew, StorableEntityBase entity )
  {
    return null;
  }

  protected void doAfterCreate ( StorableEntityBase seb ) throws WebException, RdbException
  {
    DarabFuggoEtapFeladat dfef = ( DarabFuggoEtapFeladat ) seb;
    etap.read( getDb() );
    updateMenetlevelformulaAfterCreate( dfef );
    etap.setEredmenyFrissitendo( true );
    etap.update( getDb() );
  }

  /**
   * Feladat létrehozása után frissíti az etap menetlevél-formuláját, ha szükséges.
   */
  private void updateMenetlevelformulaAfterCreate ( DarabFuggoEtapFeladat dfef ) throws WebException, RdbException
  {
    if ( etap.getMenetlevelformula() != null ) {
      MenetlevelFormulaLista vlist = new MenetlevelFormulaLista( getVerseny().getMenetlevelformula() );
      MenetlevelFormulaLista elist = new MenetlevelFormulaLista( etap.getMenetlevelformula() );
      int index = vlist.findDarabIndex( dfef.getDfftid() );
      if ( index >= 0 ) {
        elist.add( vlist.get( index ) );
        etap.setMenetlevelformula( elist.toString() );
        etap.update( getDb() );
      }
    }
  }

  protected void doAfterUpdate ( StorableEntityBase entity ) throws WebException, RdbException
  {
    etap.read( getDb() );
    etap.setEredmenyFrissitendo( true );
    etap.update( getDb() );
  }

  protected void doAfterDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {
    DarabFuggoEtapFeladat dfef = ( DarabFuggoEtapFeladat ) entity;
    etap.read( getDb() );
    updateMenetlevelformulaAfterDelete( dfef );
    etap.setEredmenyFrissitendo( true );
    etap.update( getDb() );
  }

  /**
   * Feladat törlése után frissíti az etap menetlevél-formuláját, ha szükséges.
   */
  private void updateMenetlevelformulaAfterDelete ( DarabFuggoEtapFeladat dfef ) throws WebException
  {
    if ( etap.getMenetlevelformula() != null ) {
      MenetlevelFormulaLista elist = new MenetlevelFormulaLista( etap.getMenetlevelformula() );
      int index = elist.findDarabIndex( dfef.getDfftid() );
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
      elist.removeAllDarab();
      etap.setMenetlevelformula( elist.toString() );
    }
  }

}
