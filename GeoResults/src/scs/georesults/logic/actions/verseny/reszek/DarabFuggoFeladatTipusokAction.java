package scs.georesults.logic.actions.verseny.reszek;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.javax.web.request.InvalidRequestFieldException;
import scs.georesults.GeoException;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaLista;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaResz;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.actions.CrudActionBase;
import scs.georesults.logic.beans.ReszAdatBean;
import scs.georesults.om.alap.DarabFuggoFeladatTipus;
import scs.georesults.om.verseny.EtapImpl;
import scs.georesults.logic.actions.*;

/**
 * <p>A versenyhez tartozó különböző darabszám-függő feladattípusok adminisztrálását végző szolgáltatás.</p>
 * <p>A szülőosztálya ({@link CrudActionBase}) által biztosított műveletek segítségével kezeli
 * az adatokat.</p>
 */
public class DarabFuggoFeladatTipusokAction extends CrudActionBase
{

  protected void updateParentList () throws WebException, RdbException
  {
    getVerseny().updateDarabFuggoFeladatTipusok();
  }

  protected List getAllForParent () throws WebException, RdbException
  {
    return getVerseny().getDarabFuggoFeladatTipusok();
  }

  protected StorableEntityBase newEntityForNew () throws WebException
  {
    DarabFuggoFeladatTipus dfft = DarabFuggoFeladatTipus.newInstance();
    dfft.setVid( getVerseny().getVid() );
    return dfft;
  }

  protected StorableEntityBase newEntityFromId ( Long id )
  {
    return DarabFuggoFeladatTipus.newInstance( id.longValue() );
  }

  protected void readEntityFromRequest ( StorableEntityBase entity, DynamicForm form ) throws InvalidRequestFieldException
  {
    DarabFuggoFeladatTipus dfft = ( DarabFuggoFeladatTipus ) entity;
    dfft.setNev( form.getDictionaryString( "nev" ) );
    dfft.setHianyHibapont( form.getInteger( "hianyHibapont" ) );
    dfft.setTobbletHibapont( form.getInteger( "tobbletHibapont" ) );
  }

  protected void checkUniqueAsNeed ( ReszAdatBean reszAdat ) throws WebException, RdbException, DIIException
  {
    DarabFuggoFeladatTipus dfft = ( DarabFuggoFeladatTipus ) reszAdat.getResz();
    List darabFuggoFeladatTipusok = getVerseny().getDarabFuggoFeladatTipusok();
    DarabFuggoFeladatTipus oldDfft = ( DarabFuggoFeladatTipus ) darabFuggoFeladatTipusok.findItem( "nev", dfft.getNev() );
    if ( oldDfft != null && oldDfft.getDfftid() != dfft.getDfftid() ) {
      throw new GeoMessageException( "ER_MAR_LETEZIK_ILYEN_NEV" );
    }
  }

  protected void doAfterCreate ( StorableEntityBase entity ) throws WebException, RdbException
  {
    DarabFuggoFeladatTipus dfft = ( DarabFuggoFeladatTipus ) entity;
    MenetlevelFormulaResz mfr = new MenetlevelFormulaResz( dfft.getDfftid(), false );
    String formula = getVerseny().getMenetlevelformula();
    getVerseny().setMenetlevelformula( formula + ";" + mfr.toString() );
    getVerseny().update( getDb() );
  }

  protected void doAfterDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {
    DarabFuggoFeladatTipus dfft = ( DarabFuggoFeladatTipus ) entity;
    MenetlevelFormulaLista mlfl = new MenetlevelFormulaLista( getVerseny().getMenetlevelformula() );
    int index = mlfl.findDarabIndex( dfft.getDfftid() );
    if ( index >= 0 ) mlfl.remove( index );
    getVerseny().setMenetlevelformula( mlfl.toString() );
    getVerseny().update( getDb() );
  }

  protected void doAfterDeleteAll () throws WebException, RdbException
  {
    MenetlevelFormulaLista mlfl = new MenetlevelFormulaLista( getVerseny().getMenetlevelformula() );
    mlfl.removeAllDarab();
    getVerseny().setMenetlevelformula( mlfl.toString() );
    getVerseny().update( getDb() );
  }

  protected void doBeforeDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {
    try {
      DarabFuggoFeladatTipus dfft = ( DarabFuggoFeladatTipus ) entity;
      for ( int i = 0; i < getVerseny().getEtapok().size(); ++i ) {
        EtapImpl etap = ( EtapImpl ) getVerseny().getEtapok().get( i );
        if ( etap.getDarabFuggoEtapFeladatok().findItemIndex( "dfftid", new Long( dfft.getDfftid() ) ) >= 0 ) {
          throw new GeoMessageException( "ER_NEM_TOROLHETO_MERT_HIVATKOZAS_VAN_RA", true );
        }
      }
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
  }

  protected void doBeforeDeleteAll ( List src ) throws WebException, RdbException
  {
    for ( int i = 0; i < src.size(); ++i ) doBeforeDelete( ( DarabFuggoFeladatTipus ) src.get( i ) );
  }

  protected void doAfterUpdate ( StorableEntityBase entity ) throws WebException, RdbException
  {
    try {
      DarabFuggoFeladatTipus dfft = ( DarabFuggoFeladatTipus ) entity;
      for ( int i = 0; i < getVerseny().getEtapok().size(); ++i ) {
        EtapImpl etap = ( EtapImpl ) getVerseny().getEtapok().get( i );
        if ( etap.getDarabFuggoEtapFeladatok().findItemIndex( "dfftid", new Long( dfft.getDfftid() ) ) >= 0 ) {
          etap.read( getDb() );
          etap.setEredmenyFrissitendo( true );
          etap.update( getDb() );
        }
      }
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
  }

}
