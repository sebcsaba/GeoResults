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
import scs.georesults.om.verseny.Etap;
import scs.georesults.om.verseny.Szakasz;
import scs.georesults.om.verseny.Szlalom;
import scs.georesults.logic.actions.*;

/**
 * <p>A versenyhez tartozó különböző szakaszok alapvető adminisztrálását (CRUD) végző szolgáltatás.</p>
 * <p>A szülőosztálya ({@link CrudActionBase}) által biztosított műveletek segítségével kezeli
 * az adatokat.</p>
 */
public class SzakaszokAction extends CrudActionBase
{

  protected void updateParentList () throws WebException, RdbException
  {
    getVerseny().updateSzakaszok();
  }

  protected List getAllForParent () throws WebException, RdbException
  {
    return getVerseny().getSzakaszok();
  }

  protected StorableEntityBase newEntityForNew () throws WebException
  {
    Szakasz sz = Szakasz.newInstance();
    sz.setVid( getVerseny().getVid() );
    sz.setErtekelendo( true );
    sz.setEredmenyFrissitendo( true );
    return sz;
  }

  protected StorableEntityBase newEntityFromId ( Long id )
  {
    return Szakasz.newInstance( id.longValue() );
  }

  protected void readEntityFromRequest ( StorableEntityBase entity, DynamicForm form ) throws InvalidRequestFieldException
  {
    Szakasz sz = ( Szakasz ) entity;
    sz.setNev( form.getDictionaryString( "nev" ) );
  }

  protected void checkUniqueAsNeed ( ReszAdatBean reszAdat ) throws DIIException, RdbException, WebException
  {
    Szakasz sz = ( Szakasz ) reszAdat.getResz();
    List szakaszok = getVerseny().getSzakaszok();
    Szakasz oldSz = ( Szakasz ) szakaszok.findItem( "nev", sz.getNev() );
    if ( oldSz != null && oldSz.getSzid() != sz.getSzid() ) {
      throw new GeoMessageException( "ER_MAR_LETEZIK_ILYEN_NEV" );
    }
  }

  private void checkDeletable ( Szakasz szakasz ) throws WebException, RdbException
  {
    if ( Etap.loadAllForSzakasz( getDb(), szakasz ).size() > 0 || Szlalom.loadAllForSzakasz( getDb(), szakasz ).size() > 0 ) {
      throw new GeoMessageException( "ER_NEM_TOROLHETO_MERT_HIVATKOZAS_VAN_RA" );
    }
  }

  protected void doBeforeDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {
    Szakasz szakasz = ( Szakasz ) entity;
    checkDeletable( szakasz );
  }

  protected void doBeforeDeleteAll ( List src ) throws WebException, RdbException
  {
    for ( int i = 0; i < src.size(); ++i ) checkDeletable( ( Szakasz ) src.get( i ) );
  }

  protected void doBeforeUpdate ( StorableEntityBase entity ) throws WebException
  {
    Szakasz szakasz = ( Szakasz ) entity;
    szakasz.setEredmenyFrissitendo( true );
  }

}
