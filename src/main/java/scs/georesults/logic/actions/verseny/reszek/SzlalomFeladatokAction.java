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
import scs.georesults.om.alap.SzlalomFeladat;
import scs.georesults.om.szlalom.SzlalomFutam;
import scs.georesults.om.verseny.Szlalom;
import scs.georesults.logic.actions.*;

/**
 * <p>A versenyhez tartozó különböző szlalom-feladatok adminisztrálását végző szolgáltatás.</p>
 * <p>A szülőosztálya ({@link CrudActionBase}) által biztosított műveletek segítségével kezeli
 * az adatokat.</p>
 */
public class SzlalomFeladatokAction extends CrudActionBase
{

  protected void updateParentList () throws WebException, RdbException
  {
    getVerseny().updateSzlalomFeladatok();
  }

  protected List getAllForParent () throws WebException, RdbException
  {
    return getVerseny().getSzlalomFeladatok();
  }

  protected StorableEntityBase newEntityForNew () throws WebException
  {
    SzlalomFeladat szf = SzlalomFeladat.newInstance();
    szf.setVid( getVerseny().getVid() );
    return szf;
  }

  protected StorableEntityBase newEntityFromId ( Long id )
  {
    return SzlalomFeladat.newInstance( id.longValue() );
  }

  protected void readEntityFromRequest ( StorableEntityBase entity, DynamicForm form ) throws InvalidRequestFieldException
  {
    SzlalomFeladat szf = ( SzlalomFeladat ) entity;
    szf.setNev( form.getDictionaryString( "nev" ) );
    szf.setPont( form.getInteger( "pont" ) );
  }

  protected void checkUniqueAsNeed ( ReszAdatBean reszAdat ) throws WebException, RdbException, DIIException
  {
    SzlalomFeladat szf = ( SzlalomFeladat ) reszAdat.getResz();
    List szlalomFeladatok = getVerseny().getSzlalomFeladatok();
    SzlalomFeladat oldSzf = ( SzlalomFeladat ) szlalomFeladatok.findItem( "nev", szf.getNev() );
    if ( oldSzf != null && oldSzf.getSzfid() != szf.getSzfid() ) {
      throw new GeoMessageException( "ER_MAR_LETEZIK_ILYEN_NEV" );
    }
  }

  protected void doBeforeDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {
    for ( int i = 0; i < getVerseny().getSzlalomok().size(); ++i ) {
      Szlalom sz = ( Szlalom ) getVerseny().getSzlalomok().get( i );
      List szfk = SzlalomFutam.loadAllForSzlalom( getDb(), sz );
      if ( szfk.size() > 0 ) {
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
    for ( int i = 0; i < getVerseny().getSzlalomok().size(); ++i ) {
      Szlalom sz = ( Szlalom ) getVerseny().getSzlalomok().get( i );
      sz.read( getDb() );
      sz.setEredmenyFrissitendo( true );
      sz.update( getDb() );
    }
  }

}
