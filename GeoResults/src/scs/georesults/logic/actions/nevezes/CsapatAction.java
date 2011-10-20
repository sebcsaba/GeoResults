package scs.georesults.logic.actions.nevezes;

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
import scs.georesults.om.nevezes.CsapatNevezes;
import scs.georesults.om.nevezes.CsapatNevezesImpl;
import scs.georesults.logic.actions.*;

/**
 * <p>Csapatnevezések adminisztrálását végző szolgáltatás osztálya.</p>
 */
public class CsapatAction extends CrudActionBase
{

  protected List getAllForParent () throws WebException, RdbException
  {
    return getVerseny().getCsapatNevezesek();
  }

  protected StorableEntityBase newEntityForNew () throws WebException
  {
    CsapatNevezes csn = CsapatNevezes.newInstance();
    csn.setVid( getVerseny().getVid() );
    return csn;
  }

  protected StorableEntityBase newEntityFromId ( Long id )
  {
    return CsapatNevezes.newInstance( id.longValue() );
  }

  protected void updateParentList () throws WebException, RdbException
  {
    getVerseny().updateCsapatNevezesek();
  }

  protected void readEntityFromRequest ( StorableEntityBase entity, DynamicForm form ) throws InvalidRequestFieldException, GeoMessageException, WebException, RdbException
  {
    CsapatNevezesImpl csn = ( CsapatNevezesImpl ) entity;
    csn.setNev( form.getString( "nev" ) );
    csn.setRajtszam1( form.getInteger( "rajtszam1" ) );
    csn.setRajtszam2( form.getInteger( "rajtszam2" ) );
    csn.setRajtszam3( form.getInteger( "rajtszam3" ) );
    csn.setRajtszam4( form.getOptionalInteger( "rajtszam4" ) );
  }

  /**
   * Ellenőrzi, hogy a megadott csapatnevezés érvényes-e.
   */
  protected void checkUniqueAsNeed ( ReszAdatBean reszAdat ) throws DIIException, RdbException, WebException
  {
    CsapatNevezesImpl csn = ( CsapatNevezesImpl ) reszAdat.getResz();
    List csapatNevezesek = getVerseny().getCsapatNevezesek();
    CsapatNevezes oldCsn = ( CsapatNevezes ) csapatNevezesek.findItem( "nev", csn.getNev() );
    if ( oldCsn != null && oldCsn.getCsnid() != csn.getCsnid() ) {
      throw new GeoMessageException( "ER_MAR_LETEZIK_ILYEN_NEV" );
    }
    int[] rajtszamok = new int[ ( csn.getRajtszam4() == null ? 3 : 4 )];
    for ( int i = 0; i < rajtszamok.length; ++i ) rajtszamok[i] = csn.getRajtszamByIndex( i + 1 );
    checkRajtszamUnique( rajtszamok );
    checkCsapatUnique( rajtszamok, csn.getCsnid() );
  }

  /**
   * Ellenőrzi, hogy a csapatban nem szerepel-e egy rajtszám kétszer.
   */
  private void checkRajtszamUnique ( int[] rajtszamok ) throws GeoMessageException
  {
    for ( int i = 1; i < rajtszamok.length; ++i ) {
      for ( int j = 0; j < i; ++j ) {
        if ( rajtszamok[i] == rajtszamok[j] ) {
          throw new GeoMessageException( "ER_AZONOS_RAJTSZAMOK" );
        }
      }
    }
  }

  /**
   * Ellenőrzi, hogy a megadott rajtszámú versenyzők nem szerepelnek-e már egy másik csapatban.
   */
  private void checkCsapatUnique ( int[] rajtszamok, long csnid ) throws WebException, RdbException
  {
    List csapatok = CsapatNevezes.loadAllForVerseny( getDb(), getVerseny() );
    for ( int j = 0; j < rajtszamok.length; ++j ) {
      for ( int i = 0; i < csapatok.size(); ++i ) {
        CsapatNevezesImpl csn = ( CsapatNevezesImpl ) csapatok.get( i );
        if ( csn.getCsnid() != csnid ) {
          if ( isVanRajtszamCsapatban( csn, rajtszamok[j] ) ) {
            throw new GeoMessageException( "ER_AUTO_MASIK_CSAPATBAN" );
          }
        }
      }
    }
  }

  /**
   * Igaz, ha a megadott csapatban szerepel a megadott rajtszámú versenyző.
   */
  private boolean isVanRajtszamCsapatban ( CsapatNevezesImpl csn, int rajtszam )
  {
    int tag = csn.getRajtszam4() == null ? 3 : 4;
    for ( int i = 1; i <= tag; ++i ) {
      if ( csn.getRajtszamByIndex( i ) == rajtszam )return true;
    }
    return false;
  }

  protected void doAfterCreate ( StorableEntityBase entity ) throws WebException, RdbException
  {
    getVerseny().read( getDb() );
    getVerseny().setEredmenyFrissitendoCsapat( true );
    getVerseny().update( getDb() );
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
