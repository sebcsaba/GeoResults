package scs.georesults.logic.actions.verseny.reszek;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.javax.web.request.InvalidRequestFieldException;
import scs.georesults.Config;
import scs.georesults.GeoException;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaLista;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaResz;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.actions.CrudActionBase;
import scs.georesults.logic.beans.ReszAdatBean;
import scs.georesults.logic.utils.EllenorzesTipusUtils;
import scs.georesults.om.alap.EllenorzoPont;
import scs.georesults.om.alap.SorrendFuggoFeladatTipus;
import scs.georesults.om.verseny.EtapImpl;

/**
 * <p>A versenyhez tartozó különböző sorrend-függő feladattípusok adminisztrálását végző szolgáltatás.</p>
 * <p>A szülőosztálya ({@link CrudActionBase}) által biztosított műveletek segítségével kezeli
 * az adatokat.</p>
 */
public class SorrendFuggoFeladatTipusokAction extends CrudActionBase
{

  protected void updateParentList () throws WebException, RdbException
  {
    getVerseny().updateSorrendFuggoFeladatTipusok();
  }

  protected List getAllForParent () throws WebException, RdbException
  {
    return getVerseny().getSorrendFuggoFeladatTipusok();
  }

  protected StorableEntityBase newEntityForNew () throws WebException
  {
    SorrendFuggoFeladatTipus sfft = SorrendFuggoFeladatTipus.newInstance();
    sfft.setVid( getVerseny().getVid() );
    return sfft;
  }

  protected StorableEntityBase newEntityFromId ( Long id )
  {
    return SorrendFuggoFeladatTipus.newInstance( id.longValue() );
  }

  protected void readEntityFromRequest ( StorableEntityBase entity, DynamicForm form ) throws InvalidRequestFieldException
  {
    SorrendFuggoFeladatTipus sfft = ( SorrendFuggoFeladatTipus ) entity;
    sfft.setNev( form.getDictionaryString( "nev" ) );
    sfft.setHianyHibapont( form.getInteger( "hianyHibapont" ) );
    sfft.setTobbletHibapont( form.getInteger( "tobbletHibapont" ) );
    sfft.setReszletesBevitel( form.getBoolean( "reszletesBevitel" ) );
    sfft.setEllenorzesTipus( EllenorzesTipusUtils.getEllenorzesTipusFromForm( form, "ellenorzesTipus" ).intValue() );
    List src = form.getStringList( "ellenorzoPontok" );
    sfft.getEllenorzoPontok().clear();
    for ( int i = 0; i < src.size(); ++i ) {
      String line = ( String ) src.get( i );
      if ( line.length() > 0 ) {
        EllenorzoPont ep = EllenorzoPont.newInstance( sfft.getSfftid(), line );
        sfft.getEllenorzoPontok().add( ep );
      }
    }
  }

  protected void checkUniqueAsNeed ( ReszAdatBean reszAdat ) throws WebException, RdbException, DIIException
  {
    SorrendFuggoFeladatTipus sfft = ( SorrendFuggoFeladatTipus ) reszAdat.getResz();
    List sorrendFuggoFeladatTipusok = getVerseny().getSorrendFuggoFeladatTipusok();
    SorrendFuggoFeladatTipus oldSfft = ( SorrendFuggoFeladatTipus ) sorrendFuggoFeladatTipusok.findItem( "nev", sfft.getNev() );
    if ( oldSfft != null && oldSfft.getSfftid() != sfft.getSfftid() ) {
      throw new GeoMessageException( "ER_MAR_LETEZIK_ILYEN_NEV" );
    }
  }

  protected void doAfterCreate ( StorableEntityBase entity ) throws WebException, RdbException
  {
    SorrendFuggoFeladatTipus sfft = ( SorrendFuggoFeladatTipus ) entity;
    MenetlevelFormulaResz mfr = new MenetlevelFormulaResz( sfft.getSfftid(), false, Config.ETAP_RESZ_ADAT_LISTA_HOSSZ );
    String formula = getVerseny().getMenetlevelformula();
    getVerseny().setMenetlevelformula( formula + ";" + mfr.toString() );
    getVerseny().update( getDb() );
  }

  protected void doAfterDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {
    SorrendFuggoFeladatTipus sfft = ( SorrendFuggoFeladatTipus ) entity;
    MenetlevelFormulaLista mlfl = new MenetlevelFormulaLista( getVerseny().getMenetlevelformula() );
    int index = mlfl.findSorrendIndex( sfft.getSfftid() );
    if ( index >= 0 ) mlfl.remove( index );
    getVerseny().setMenetlevelformula( mlfl.toString() );
    getVerseny().update( getDb() );
  }

  protected void doAfterDeleteAll () throws WebException, RdbException
  {
    MenetlevelFormulaLista mlfl = new MenetlevelFormulaLista( getVerseny().getMenetlevelformula() );
    mlfl.removeAllSorrend();
    getVerseny().setMenetlevelformula( mlfl.toString() );
    getVerseny().update( getDb() );
  }

  protected void doBeforeDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {
    try {
      SorrendFuggoFeladatTipus sfft = ( SorrendFuggoFeladatTipus ) entity;
      for ( int i = 0; i < getVerseny().getEtapok().size(); ++i ) {
        EtapImpl etap = ( EtapImpl ) getVerseny().getEtapok().get( i );
        if ( etap.getSorrendFuggoEtapFeladatok().findItemIndex( "sfftid", new Long( sfft.getSfftid() ) ) >= 0 ) {
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
    for ( int i = 0; i < src.size(); ++i ) doBeforeDelete( ( SorrendFuggoFeladatTipus ) src.get( i ) );
  }

  protected void doAfterUpdate ( StorableEntityBase entity ) throws WebException, RdbException
  {
    try {
      SorrendFuggoFeladatTipus sfft = ( SorrendFuggoFeladatTipus ) entity;
      for ( int i = 0; i < getVerseny().getEtapok().size(); ++i ) {
        EtapImpl etap = ( EtapImpl ) getVerseny().getEtapok().get( i );
        if ( etap.getSorrendFuggoEtapFeladatok().findItemIndex( "sfftid", new Long( sfft.getSfftid() ) ) >= 0 ) {
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
