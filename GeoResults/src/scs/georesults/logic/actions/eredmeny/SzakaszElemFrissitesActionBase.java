package scs.georesults.logic.actions.eredmeny;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.javax.web.request.InvalidRequestFieldException;
import scs.georesults.GeoException;
import scs.georesults.common.SzakaszElem;
import scs.georesults.logic.LezarvaException;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.om.verseny.Szakasz;

/**
 * <p>A {@link SzakaszElem} típusú objektumok eredménylistáját frissítő szolgáltatások ősosztálya.</p>
 */
public abstract class SzakaszElemFrissitesActionBase extends GeoActionBase
{

  /**
   * Igaz, ha az adatok frissítése után szükséges a szakasz eredménylistájának frissítése. Lényegében csak akkor hamis, ha az adott szolgáltatás a szakasz eredményét frissíti.
   */
  protected abstract boolean kellSzakaszFrissites ();

  /**
   * Az összes, megfelelő típusú {@link SzakaszElem} objektum listája.
   */
  protected abstract List getElemList () throws RdbException, WebException;

  /**
   * A megfelelő típusú {@link SzakaszElem} objektum azonosító mezőjének neve.
   */
  protected abstract String getIdField ();

  /**
   * Az adott {@link SzakaszElem} eredményének frissítése, az eredmény-objektumok létrehozása, adatbázisba tároláda.
   */
  protected abstract void szakaszElemFrissites ( SzakaszElem szakaszElem ) throws DIIException, WebException, RdbException;

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, InvalidRequestFieldException, RdbException
  {
    try {
      if ( getVerseny().isLeVanZarva() )throw new LezarvaException();
      if ( form.has( "id" ) ) {
        long id = form.getLong( "id" );
        SzakaszElem szakaszElem = ( SzakaszElem ) getElemList().findItem( getIdField(), new Long( id ) );
        szakaszElemFrissites( szakaszElem );
        if ( kellSzakaszFrissites() ) szakaszFrissites( szakaszElem.getSzid() );
      } else {
        for ( int i = 0; i < getElemList().size(); ++i ) {
          SzakaszElem szakaszElem = ( SzakaszElem ) getElemList().get( i );
          if ( szakaszElem.isErtekelendo() && szakaszElem.isEredmenyFrissitendo() ) szakaszElemFrissites( szakaszElem );
        }
        if ( kellSzakaszFrissites() ) szakaszokFrissites();
      }
      getVerseny().read( getDb() );
      if ( getVerseny().isBarmiAmiNemFrissitendo() ) {
        getVerseny().setMindenFrissitendo();
        getVerseny().update( getDb() );
      }
      return "ok";
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
  }

  /**
   * A paraméterben megadott azonosítójú szakasznak jelzi,
   * hogy szükséges az eredménylistájának frissítése.
   */
  protected void szakaszFrissites ( long szid ) throws WebException, RdbException
  {
    Szakasz szakasz = Szakasz.newInstance( szid );
    szakasz.read( getDb() );
    szakasz.setEredmenyFrissitendo( true );
    szakasz.update( getDb() );
  }

  /**
   * Az összes szakasznak jelzi, hogy szükséges az eredménylistájuk frissítése.
   */
  protected void szakaszokFrissites () throws WebException, RdbException
  {
    List szakaszok = getVerseny().getSzakaszok();
    for ( int i = 0; i < szakaszok.size(); ++i ) {
      Szakasz szakasz = ( Szakasz ) szakaszok.get( i );
      szakasz.setEredmenyFrissitendo( true );
      szakasz.update( getDb() );
    }
  }

}
