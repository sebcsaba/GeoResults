package scs.georesults.logic.actions.verseny.reszek;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.javax.web.request.InvalidRequestFieldException;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.actions.CrudActionBase;
import scs.georesults.logic.beans.ReszAdatBean;
import scs.georesults.om.alap.BuntetesTipus;
import scs.georesults.om.menetlevel.Menetlevel;
import scs.georesults.om.verseny.Etap;
import scs.georesults.logic.actions.*;

/**
 * <p>A versenyhez tartozó különböző büntetés-típusok adminisztrálását végző szolgáltatás.</p>
 * <p>A szülőosztálya ({@link CrudActionBase}) által biztosított műveletek segítségével kezeli
 * az adatokat.</p>
 */
public class BuntetesTipusokAction extends CrudActionBase
{

  protected void updateParentList () throws WebException, RdbException
  {
    getVerseny().updateBuntetesTipusok();
  }

  protected List getAllForParent () throws WebException, RdbException
  {
    return getVerseny().getBuntetesTipusok();
  }

  protected StorableEntityBase newEntityForNew () throws WebException
  {
    BuntetesTipus bt = BuntetesTipus.newInstance();
    bt.setVid( getVerseny().getVid() );
    return bt;
  }

  protected StorableEntityBase newEntityFromId ( Long id )
  {
    return BuntetesTipus.newInstance( id.longValue() );
  }

  protected void readEntityFromRequest ( StorableEntityBase entity, DynamicForm form ) throws InvalidRequestFieldException
  {
    BuntetesTipus bt = ( BuntetesTipus ) entity;
    bt.setNev( form.getDictionaryString( "nev" ) );
    bt.setPont( form.getInteger( "pont" ) );
  }

  protected void checkUniqueAsNeed ( ReszAdatBean reszAdat ) throws WebException, RdbException, DIIException
  {
    BuntetesTipus bt = ( BuntetesTipus ) reszAdat.getResz();
    List buntetesTipusok = getVerseny().getBuntetesTipusok();
    BuntetesTipus oldBt = ( BuntetesTipus ) buntetesTipusok.findItem( "nev", bt.getNev() );
    if ( oldBt != null && oldBt.getBtid() != bt.getBtid() ) {
      throw new GeoMessageException( "ER_MAR_LETEZIK_ILYEN_NEV" );
    }
  }

  protected void doBeforeDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {
    for ( int i = 0; i < getVerseny().getEtapok().size(); ++i ) {
      Etap etap = ( Etap ) getVerseny().getEtapok().get( i );
      List menetlevelek = Menetlevel.loadAllForEtap( getDb(), etap );
      if ( menetlevelek.size() > 0 ) {
        throw new GeoMessageException( "ER_NEM_TOROLHETO_MERT_HIVATKOZAS_VAN_RA", true );
      }
    }
  }

  protected void doBeforeDeleteAll ( List src ) throws WebException, RdbException
  {
    doBeforeDelete( null );
  }

  protected void doAfterUpdate ( StorableEntityBase entity ) throws WebException, RdbException
  {
    for ( int i = 0; i < getVerseny().getEtapok().size(); ++i ) {
      Etap etap = ( Etap ) getVerseny().getEtapok().get( i );
      etap.read( getDb() );
      etap.setEredmenyFrissitendo( true );
      etap.update( getDb() );
    }
  }

}
