package scs.georesults.logic.actions.eredmeny;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.WebException;
import scs.georesults.common.SzakaszElem;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.om.nevezes.Nevezes;

/**
 * <p>A szakaszt felépítő objektumok (etapok vagy szlalomok) eredménylistáját frissítő szolgáltatások ősosztálya.</p>
 */
public abstract class SzakaszAlapElemFrissitesActionBase extends SzakaszElemFrissitesActionBase
{

  protected void szakaszElemFrissites ( SzakaszElem szakaszElem ) throws DIIException, WebException, RdbException
  {
    List futamok = getSzakaszElemhezFutamLista( szakaszElem );
    checkAllFutamExists( futamok );
    deleteAllCurrentEredmeny( szakaszElem );
    for ( int i = 0; i < futamok.size(); ++i ) {
      StorableEntityBase futam = ( StorableEntityBase ) futamok.get( i );
      StorableEntityBase eredmeny = futamKiertekeles( szakaszElem, futam );
      eredmeny.create( getDb() );
    }
    szakaszElem.read( getDb() );
    szakaszElem.setEredmenyFrissitendo( false );
    szakaszElem.update( getDb() );
  }

  /**
   * Az adott szakaszelemhez tartozó futamok listáját adja vissza.
   */
  protected abstract List getSzakaszElemhezFutamLista ( SzakaszElem szakaszElem ) throws WebException, RdbException;

  /**
   * Kiértékeli az adott futam eredményét. Az eredményt tartalmazó,
   * megfelelő típusú objektumot adja vissza.
   */
  protected abstract StorableEntityBase futamKiertekeles ( SzakaszElem szakaszElem, StorableEntityBase futam ) throws RdbException, DIIException, WebException;

  /**
   * Ellenőrzi, hogy az összes forrásadat rendelkezésre áll-e.
   */
  protected void checkAllFutamExists ( List futamok ) throws WebException, DIIException, RdbException
  {
    List nevezesek = getVerseny().getNevezesek();
    for ( int i = 0; i < nevezesek.size(); ++i ) {
      Nevezes n = ( Nevezes ) nevezesek.get( i );
      int index = futamok.findItemIndex( "rajtszam", new Integer( n.getRajtszam() ) );
      if ( index == -1 )throw new GeoMessageException( "ER_HIANYZO_ADAT" );
    }
  }

  /**
   * Az adott szakaszelemhez tartozó, jelenleg az adatbázisban található eredményeket tartalmazó listát adja vissza.
   */
  protected abstract List loadAllCurrendEredmeny ( SzakaszElem szakaszElem ) throws WebException, RdbException;

  /**
   * Törli az adatbázisból az adott szakaszelemhez tartozó eredményeket.
   */
  protected void deleteAllCurrentEredmeny ( SzakaszElem szakaszElem ) throws WebException, RdbException
  {
    List eredmenyek = loadAllCurrendEredmeny( szakaszElem );
    for ( int i = 0; i < eredmenyek.size(); ++i ) {
      StorableEntityBase entity = ( StorableEntityBase ) eredmenyek.get( i );
      entity.delete( getDb() );
    }
  }

}
