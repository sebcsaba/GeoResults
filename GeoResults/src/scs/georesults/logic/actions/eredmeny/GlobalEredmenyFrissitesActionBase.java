package scs.georesults.logic.actions.eredmeny;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableClass;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.GeoException;
import scs.georesults.common.ReszEredmeny;
import scs.georesults.common.SzakaszElem;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.LezarvaException;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.nevezes.NevezesImpl;
import scs.georesults.om.verseny.VersenyImpl;

/**
 * <p>A verseny "globális" eredménylistáit frissítő szolgáltatások közös ősosztálya.</p>
 * <p>"Globális" eredménylistának nevezzük azokat, amelyekből a versenyhez csak egy-egy lista tartozik, valamint a lista nevezésenként egy-egy elemet tartalmaz.</p>
 */
public abstract class GlobalEredmenyFrissitesActionBase extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    try {
      if ( getVerseny().isLeVanZarva() )throw new LezarvaException();
      List nevezesek = getVerseny().getNevezesek();
      List szakaszElemek = getSzakaszElemek();
      checkAllRequiredEredmenyExists( szakaszElemek );
      deleteAllCurrentEredmeny();
      szakaszElemEredmenyOsszesito( nevezesek, szakaszElemek );
      updateVersenyFrissitendoFlags();
      return "ok";
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
  }

  /**
   * Jelzi a verseny objektumán, hogy frissíteni kell bizonyos eredménylistákat.
   */
  private void updateVersenyFrissitendoFlags () throws WebException, RdbException
  {
    VersenyImpl verseny = getVerseny();
    verseny.read( getDb() );
    setVersenyFrissitendoFlags( verseny );
    verseny.update( getDb() );
  }

  /**
   * Beállítja a versenyen, hogy mely eredménylistákat kell frissíteni.
   */
  protected abstract void setVersenyFrissitendoFlags ( VersenyImpl verseny );

  /**
   * Törli az eredménylista létező elemeit.
   */
  protected void deleteAllCurrentEredmeny () throws WebException, RdbException
  {
    List eredmenyek = getGlobalEredmenyek();
    for ( int i = 0; i < eredmenyek.size(); ++i ) {
      StorableClass sc = ( StorableClass ) eredmenyek.get( i );
      sc.delete( getDb() );
    }
  }

  /**
   * Ellenőrzi, hogy az összes forrásadat rendelkezésre áll-e.
   */
  protected void checkAllRequiredEredmenyExists ( List szakaszElemek ) throws GeoMessageException
  {
    for ( int i = 0; i < szakaszElemek.size(); ++i ) {
      SzakaszElem szakaszElem = ( SzakaszElem ) szakaszElemek.get( i );
      if ( szakaszElem.isErtekelendo() ) {
        if ( szakaszElem.isEredmenyFrissitendo() )throw new GeoMessageException( "ER_ALAPADAT_FFRISSITENDO" );
      }
    }
  }

  /**
   * Azon {@link SzakaszElem} típusú objektumok listáját adja vissza, amelyek eredményét az aktuális osztály összesíti.
   */
  protected abstract List getSzakaszElemek () throws WebException, RdbException;

  /**
   * Visszaadja az eredménylista jelenleg az adatbázisban található elemeit.
   */
  protected abstract List getGlobalEredmenyek () throws WebException, RdbException;

  /**
   * Elvégzi a szakaszelemek pontszámának összesítését. Az eremdényeket az adatbázisban eltárolja.
   */
  protected void szakaszElemEredmenyOsszesito ( List nevezesek, List szakaszElemek ) throws DIIException, GeoException, RdbException
  {
    for ( int i = 0; i < nevezesek.size(); ++i ) {
      NevezesImpl nevezes = ( NevezesImpl ) nevezesek.get( i );
      ReszEredmeny osszEredmeny = getNewOsszInstance( nevezes );
      int pont = 0;
      for ( int j = 0; j < szakaszElemek.size(); ++j ) {
        SzakaszElem szakaszElem = ( SzakaszElem ) szakaszElemek.get( j );
        if ( szakaszElem.isErtekelendo() ) {
          ReszEredmeny reszEredmeny = getNewReszInstance( szakaszElem.getSzakaszElemId(), nevezes.getRajtszam() );
          reszEredmeny.read( getDb() );
          pont += reszEredmeny.getPontszam();
        }
      }
      osszEredmeny.setPontszam( pont );
      osszEredmeny.create( getDb() );
    }
  }

  /**
   * Egy olyan, a nevezéshez tartozó új objektumot hoz létre
   * és ad vissza, amely az adott eredménylista eleme lesz.
   */
  protected abstract ReszEredmeny getNewOsszInstance ( Nevezes nevezes ) throws RdbException, GeoException;

  /**
   * Létrehoz és visszaad egy olyan objektumot, amely az adott azonosítójú
   * szakaszelem részeredményét tartalmazza - majd, ha később beolvassák az adatbázisból.
   */
  protected abstract ReszEredmeny getNewReszInstance ( long id, int rajtszam );

}
