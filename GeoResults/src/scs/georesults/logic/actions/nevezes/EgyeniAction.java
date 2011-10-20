package scs.georesults.logic.actions.nevezes;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.actions.CrudActionBase;
import scs.georesults.logic.beans.ReszAdatBean;
import scs.georesults.om.menetlevel.MenetlevelImpl;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.szlalom.SzlalomFutamImpl;
import scs.georesults.om.verseny.Etap;
import scs.georesults.om.verseny.Szlalom;
import scs.georesults.om.nevezes.CsapatNevezes;
import scs.georesults.om.nevezes.CsapatNevezesImpl;

/**
 * <p>Egyéni nevezések adminisztrálását végző szolgáltatás osztálya.</p>
 */
public class EgyeniAction extends CrudActionBase
{

  protected List getAllForParent () throws WebException, RdbException
  {
    return getVerseny().getNevezesek();
  }

  protected StorableEntityBase newEntityForNew () throws WebException
  {
    Nevezes n = Nevezes.newInstance();
    n.setVid( getVerseny().getVid() );
    return n;
  }

  protected StorableEntityBase newEntityFromId ( Long id ) throws WebException
  {
    return Nevezes.newInstance( getVerseny().getVid(), id.intValue() );
  }

  protected void readEntityFromRequest ( StorableEntityBase entity, DynamicForm form ) throws WebException, RdbException
  {
    Nevezes n = ( Nevezes ) entity;
    ReszAdatBean rab = ( ReszAdatBean ) getFromSession( SESSION_KEY_RESZADAT );
    if ( rab.isCreate() ) {
      n.setRajtszam( form.getInteger( "rajtszam" ) );
    }
    n.setSofor( form.getString( "sofor" ) );
    n.setNavigator( form.getString( "navigator" ) );
    n.setUtas1( form.getOptionalString( "utas1" ) );
    n.setUtas2( form.getOptionalString( "utas2" ) );
    n.setUtas3( form.getOptionalString( "utas3" ) );
    n.setOrszag( form.getString( "orszag" ) );
    n.setAutoTipus( form.getInteger( "autoTipus" ) );
  }

  protected void checkUniqueAsNeed ( ReszAdatBean reszAdat ) throws DIIException, RdbException, WebException
  {
    if ( reszAdat.isCreate() ) {
      Nevezes n = ( Nevezes ) reszAdat.getResz();
      if ( getVerseny().getNevezesek().findItemIndex( "rajtszam", new Integer( n.getRajtszam() ) ) >= 0 ) {
        throw new GeoMessageException( "ER_DUPLA_RAJTSZAM" );
      }
    }
  }

  protected void updateParentList () throws WebException, RdbException
  {
    getVerseny().updateNevezesek();
  }

  protected void doBeforeDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {
    Nevezes n = ( Nevezes ) entity;
    if ( MenetlevelImpl.loadAllForNevezes( getDb(), n ).size() > 0 || SzlalomFutamImpl.loadAllForNevezes( getDb(), n ).size() > 0 ) {
      throw new GeoMessageException( "ER_NEM_TOROLHETO_MERT_HIVATKOZAS_VAN_RA", true );
    }
    List csapatok = CsapatNevezes.loadAllForVerseny( getDb(), getVerseny() );
    for ( int i = 0; i < csapatok.size(); ++i ) {
      CsapatNevezesImpl csn = ( CsapatNevezesImpl ) csapatok.get( i );
      if ( csn.isVanBenneRajtszam( n.getRajtszam() ) )throw new GeoMessageException( "ER_NEM_TOROLHETO_MERT_HIVATKOZAS_VAN_RA", true );
    }
  }

  protected void doBeforeDeleteAll ( List src ) throws WebException, RdbException
  {
    for ( int i = 0; i < src.size(); ++i ) doBeforeDelete( ( Nevezes ) src.get( i ) );
  }

  protected void doAfterUpdate ( StorableEntityBase entity ) throws WebException, RdbException
  {
    Nevezes n = ( Nevezes ) entity;
    for ( int i = 0; i < getVerseny().getEtapok().size(); ++i ) {
      Etap etap = ( Etap ) getVerseny().getEtapok().get( i );
      etap.read( getDb() );
      etap.setEredmenyFrissitendo( true );
      etap.update( getDb() );
    }
    for ( int i = 0; i < getVerseny().getSzlalomok().size(); ++i ) {
      Szlalom szlalom = ( Szlalom ) getVerseny().getSzlalomok().get( i );
      szlalom.read( getDb() );
      szlalom.setEredmenyFrissitendo( true );
      szlalom.update( getDb() );
    }
  }

}
