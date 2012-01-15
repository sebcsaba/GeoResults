package scs.georesults.logic.actions.szlalom;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.GeoException;
import scs.georesults.logic.actions.AdatBevitelMentesActionBase;
import scs.georesults.logic.beans.AdatbevitelBean;
import scs.georesults.om.alap.SzlalomFeladat;
import scs.georesults.om.szlalom.SzlalomBejegyzes;
import scs.georesults.om.szlalom.SzlalomFutam;
import scs.georesults.om.verseny.Szlalom;
import scs.georesults.logic.actions.*;

/**
 * <p>A szlalom-futamok felvitele lapon egy adott futam mentését biztosító szolgáltatás osztálya.</p>
 */
public class MentesAction extends AdatBevitelMentesActionBase
{

  /**
   * A kijelölt szlalomon jelzi, hogy módosítás történt az adatain, ezért a hozzá tartozó eredményt frissíteni kell.
   *
   * @param id A szlalom azonosítója
   */
  protected void invalidate ( long id ) throws WebException, RdbException
  {
    try {
      Szlalom sz = ( Szlalom ) getVerseny().getSzlalomok().findItem( "szlid", new Long( id ) );
      if ( !sz.isEredmenyFrissitendo() ) {
        sz.read( getDb() );
        sz.setEredmenyFrissitendo( true );
        sz.update( getDb() );
      }
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
  }

  /**
   * Az adatokat tartalmazó bean osztály munkafolyamat-beli kulcsát adja vissza.
   */
  protected String getAdatokSessionKey ()
  {
    return "szlalomAdatok";
  }

  /**
   * Feltölti a HTTP kérésben érkezett paraméterekkel a betöltött szlalom-futamot.
   *
   * @param entity A betöltött szlalom-futam
   * @param adatokBean Az adatbevitelt segítő osztály
   * @param form A kérésben érkezett paramétereket tartalmazó objektum
   */
  protected void fillEntity ( StorableEntityBase entity, AdatbevitelBean adatokBean, DynamicForm form ) throws WebException, RdbException
  {
    SzlalomFutam szlalomFutam = ( SzlalomFutam ) entity;
    SzlalomBejegyzes.Lista szbl = szlalomFutam.getBejegyzesek();
    szbl.clear();
    List szlalomFeladatok = getVerseny().getSzlalomFeladatok();
    for ( int i = 0; i < szlalomFeladatok.size(); ++i ) {
      SzlalomFeladat szf = ( SzlalomFeladat ) szlalomFeladatok.get( i );
      SzlalomBejegyzes szb = SzlalomBejegyzes.newInstance();
      szb.setSzlid( szlalomFutam.getSzlid() );
      szb.setRajtszam( szlalomFutam.getRajtszam() );
      szb.setSzfid( szf.getSzfid() );
      szb.setDarab( form.getInteger( "szlalomFeladat_" + szf.getSzfid() ) );
      szbl.add( szb );
    }
  }

  /**
   * A futamot tartalmazó versenyrész objektum azonosító mezőjének neve.
   */
  protected String getIdFieldName ()
  {
    return "szlid";
  }

}
