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
import scs.georesults.om.alap.KesesiZona;
import scs.georesults.om.verseny.Etap;
import scs.georesults.logic.actions.*;

/**
 * <p>A versenyhez tartozó különböző késési időzónák adminisztrálását végző szolgáltatás.</p>
 * <p>A szülőosztálya ({@link CrudActionBase}) által biztosított műveletek segítségével kezeli
 * az adatokat.</p>
 */
public class KesesiZonakAction extends CrudActionBase
{

  protected void updateParentList () throws WebException, RdbException
  {
    getVerseny().updateKesesiZonak();
  }

  protected List getAllForParent () throws WebException, RdbException
  {
    return getVerseny().getKesesiZonak();
  }

  protected StorableEntityBase newEntityForNew () throws WebException
  {
    return KesesiZona.newInstance( getVerseny().getVid(), 0 );
  }

  protected StorableEntityBase newEntityFromId ( Long id ) throws WebException
  {
    return KesesiZona.newInstance( getVerseny().getVid(), id.intValue() );
  }

  protected void readEntityFromRequest ( StorableEntityBase entity, DynamicForm form ) throws InvalidRequestFieldException, WebException, RdbException
  {
    KesesiZona kz = ( KesesiZona ) entity;
    ReszAdatBean rab = ( ReszAdatBean ) getFromSession( SESSION_KEY_RESZADAT );
    if ( rab.isCreate() ) {
      kz.setIdoLimit( form.getInteger( "idoLimit" ) );
    }
    kz.setPont( form.getInteger( "pont" ) );
  }

  protected void checkUniqueAsNeed ( ReszAdatBean reszAdat ) throws WebException, RdbException, GeoMessageException, DIIException
  {
    if ( reszAdat.isCreate() ) {
      KesesiZona kz = ( KesesiZona ) reszAdat.getResz();
      List kesesiZonak = getVerseny().getKesesiZonak();
      if ( kesesiZonak.findItemIndex( "idoLimit", new Integer( kz.getIdoLimit() ) ) >= 0 ) {
        throw new GeoMessageException( "ER_DUPLA_IDOLIMIT" );
      }
    }
  }

  protected void doAfterCreate ( StorableEntityBase entity ) throws WebException, RdbException
  {
    for ( int i = 0; i < getVerseny().getEtapok().size(); ++i ) {
      Etap etap = ( Etap ) getVerseny().getEtapok().get( i );
      etap.read( getDb() );
      etap.setEredmenyFrissitendo( true );
      etap.update( getDb() );
    }
  }

  protected void doAfterUpdate ( StorableEntityBase entity ) throws WebException, RdbException
  {
    doAfterCreate( null );
  }

  protected void doAfterDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {
    doAfterCreate( null );
  }

  protected void doAfterDeleteAll () throws WebException, RdbException
  {
    doAfterCreate( null );
  }

}
