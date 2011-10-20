package scs.georesults.logic.actions.verseny.reszek;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.javax.web.request.InvalidRequestFieldException;
import scs.georesults.GeoException;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.actions.CrudActionBase;
import scs.georesults.logic.beans.ReszAdatBean;
import scs.georesults.om.alap.AutoTipus;
import scs.georesults.om.verseny.Szlalom;
import scs.georesults.logic.actions.*;

/**
 * <p>A versenyhez tartozó különböző autó-típusok adminisztrálását végző szolgáltatás.</p>
 * <p>A szülőosztálya ({@link CrudActionBase}) által biztosított műveletek segítségével kezeli
 * az adatokat.</p>
 */
public class AutoTipusokAction extends CrudActionBase
{

  protected void updateParentList () throws WebException, RdbException
  {
    getVerseny().updateAutoTipusok();
  }

  protected List getAllForParent () throws WebException, RdbException
  {
    return getVerseny().getAutoTipusok();
  }

  protected StorableEntityBase newEntityForNew () throws WebException, RdbException
  {
    if ( getVerseny().getSzlalomKategoriak().size() == 0 ) {
      throw new GeoMessageException( "ER_NEM_LETEZIK_SZLALOM_KATEGORIA", true );
    }
    AutoTipus at = AutoTipus.newInstance();
    at.setVid( getVerseny().getVid() );
    return at;
  }

  protected StorableEntityBase newEntityFromId ( Long id )
  {
    return AutoTipus.newInstance( id.longValue() );
  }

  protected void readEntityFromRequest ( StorableEntityBase entity, DynamicForm form ) throws InvalidRequestFieldException
  {
    AutoTipus at = ( AutoTipus ) entity;
    at.setNev( form.getString( "nev" ) );
    at.setKategoria( form.getLong( "kategoria" ) );
  }

  protected void checkUniqueAsNeed ( ReszAdatBean reszAdat ) throws WebException, RdbException, DIIException
  {
    AutoTipus at = ( AutoTipus ) reszAdat.getResz();
    List autoTipusok = getVerseny().getSzlalomKategoriak();
    AutoTipus oldAt = ( AutoTipus ) autoTipusok.findItem( "nev", at.getNev() );
    if ( oldAt != null && oldAt.getAtid() != at.getAtid() ) {
      throw new GeoMessageException( "ER_MAR_LETEZIK_ILYEN_NEV" );
    }
  }

  protected void doBeforeDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {
    try {
      AutoTipus at = ( AutoTipus ) entity;
      if ( getVerseny().getNevezesek().findItemIndex( "autoTipus", new Long( at.getAtid() ) ) >= 0 ) {
        throw new GeoMessageException( "ER_NEM_TOROLHETO_MERT_HIVATKOZAS_VAN_RA", true );
      }
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
  }

  protected void doBeforeDeleteAll ( List src ) throws WebException, RdbException
  {
    for ( int i = 0; i < src.size(); ++i ) doBeforeDelete( ( AutoTipus ) src.get( i ) );
  }

  protected void doAfterUpdate ( StorableEntityBase entity ) throws WebException, RdbException
  {
    for ( int i = 0; i < getVerseny().getSzlalomok().size(); ++i ) {
      Szlalom sz = ( Szlalom ) getVerseny().getSzlalomok().get( i );
      sz.read( getDb() );
      sz.setEredmenyFrissitendo( true );
      sz.update( getDb() );
    }
  }

}
