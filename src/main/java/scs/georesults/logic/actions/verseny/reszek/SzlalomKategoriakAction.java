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
import scs.georesults.om.alap.SzlalomKategoria;
import scs.georesults.om.verseny.Szlalom;
import scs.georesults.logic.actions.*;

/**
 * <p>A versenyhez tartozó különböző szlalom-kategóriák adminisztrálását végző szolgáltatás.</p>
 * <p>A szülőosztálya ({@link CrudActionBase}) által biztosított műveletek segítségével kezeli
 * az adatokat.</p>
 */
public class SzlalomKategoriakAction extends CrudActionBase
{

  protected void updateParentList () throws WebException, RdbException
  {
    getVerseny().updateSzlalomKategoriak();
  }

  protected List getAllForParent () throws WebException, RdbException
  {
    return getVerseny().getSzlalomKategoriak();
  }

  protected StorableEntityBase newEntityForNew () throws WebException
  {
    SzlalomKategoria szk = SzlalomKategoria.newInstance();
    szk.setVid( getVerseny().getVid() );
    return szk;
  }

  protected StorableEntityBase newEntityFromId ( Long id )
  {
    return SzlalomKategoria.newInstance( id.longValue() );
  }

  protected void readEntityFromRequest ( StorableEntityBase entity, DynamicForm form ) throws InvalidRequestFieldException
  {
    SzlalomKategoria szk = ( SzlalomKategoria ) entity;
    szk.setNev( form.getString( "nev" ) );
  }

  protected void checkUniqueAsNeed ( ReszAdatBean reszAdat ) throws WebException, RdbException, DIIException
  {
    SzlalomKategoria szk = ( SzlalomKategoria ) reszAdat.getResz();
    List szlalomKategoriak = getVerseny().getSzlalomKategoriak();
    SzlalomKategoria oldSzk = ( SzlalomKategoria ) szlalomKategoriak.findItem( "nev", szk.getNev() );
    if ( oldSzk != null && oldSzk.getSzkid() != szk.getSzkid() ) {
      throw new GeoMessageException( "ER_MAR_LETEZIK_ILYEN_NEV" );
    }
  }

  protected void doBeforeDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {
    try {
      SzlalomKategoria szk = ( SzlalomKategoria ) entity;
      if ( getVerseny().getAutoTipusok().findItemIndex( "kategoria", new Long( szk.getSzkid() ) ) >= 0 ) {
        throw new GeoMessageException( "ER_NEM_TOROLHETO_MERT_HIVATKOZAS_VAN_RA", true );
      }
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
  }

  protected void doBeforeDeleteAll ( List src ) throws WebException, RdbException
  {
    for ( int i = 0; i < src.size(); ++i ) doBeforeDelete( ( SzlalomKategoria ) src.get( i ) );
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
