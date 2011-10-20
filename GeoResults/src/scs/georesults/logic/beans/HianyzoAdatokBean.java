package scs.georesults.logic.beans;

import javax.servlet.http.HttpServletRequest;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.web.*;
import scs.javax.web.request.InvalidRequestFieldException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.Constants;
import scs.georesults.om.menetlevel.Menetlevel;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.szlalom.SzlalomFutam;
import scs.georesults.om.verseny.Etap;
import scs.georesults.om.verseny.Szlalom;
import scs.georesults.om.verseny.VersenyImpl;

/**
 * <p>Az adott versenyszámhoz tartozó, hiányzó adatok listájának megjelenítését segítő osztály.</p>
 */
public class HianyzoAdatokBean extends RequestBeanBase
{

  /**
   * A programban használt adatbázis.
   */
  private GeoDbSession db;

  /**
   * A versenyhez tartozó nevezések.
   */
  private List nevezesek;

  /**
   * Az objektumot inicializálja, és a nevezések közül kiszűri azokat, amelynek az adata már fel van vive.
   */
  public void init () throws GeoException, DIIException, InvalidRequestFieldException, RdbException, SessionTimeoutException
  {
    this.db = GeoDbSession.getCurrentInstance();
    DynamicForm form = new DynamicForm( ( HttpServletRequest ) pageContext.getRequest() );
    VersenyImpl verseny = ( VersenyImpl ) WebSession.justGetHttpSession( pageContext ).getAttribute( Constants.SESSION_KEY_VERSENY );
    nevezesek = Nevezes.loadAllForVerseny( db, verseny );
    String mode = form.getString( "mode" );
    if ( mode.equals( "menetlevel" ) ) {
      long eid = form.getLong( "eid" );
      removeMenetlevelesNevezesek( eid );
    } else if ( mode.equals( "szlalomfutam" ) ) {
      long szlid = form.getLong( "szlid" );
      removeSzlalomfutamosNevezesek( szlid );
    }
  }

  /**
   * Visszaadja a hiányzó adatokhoz tartozó nevezéseket
   *
   * @return Az nevezések listája, akiknek hiányzik adatuk
   */
  public List getHianyzoNevezesek ()
  {
    return nevezesek;
  }

  /**
   * Ha az objektum menetlevelekhez tartozik, akkor a megadott etap azonosítója alapján kiszűri azon nevezéseket, akiknek már fel van vive az adatuk.
   *
   * @param eid Az etap azonosítója
   */
  private void removeMenetlevelesNevezesek ( long eid ) throws DIIException, RdbException
  {
    Etap etap = Etap.newInstance( eid );
    List exists = Menetlevel.loadAllForEtap( db, etap );
    for ( int i = 0; i < exists.size(); ++i ) {
      Menetlevel m = ( Menetlevel ) exists.get( i );
      int index = nevezesek.findItemIndex( "rajtszam", new Integer( m.getRajtszam() ) );
      nevezesek.remove( index );
    }
  }

  /**
   * Ha az objektum szlalomfutamokhoz tartozik, akkor a megadott szlalom azonosítója alapján kiszűri azon nevezéseket, akiknek már fel van vive az adatuk.
   *
   * @param szlid A szlalom azonosítója
   */
  private void removeSzlalomfutamosNevezesek ( long szlid ) throws DIIException, RdbException
  {
    Szlalom szlalom = Szlalom.newInstance( szlid );
    List exists = SzlalomFutam.loadAllForSzlalom( db, szlalom );
    for ( int i = 0; i < exists.size(); ++i ) {
      SzlalomFutam szf = ( SzlalomFutam ) exists.get( i );
      int index = nevezesek.findItemIndex( "rajtszam", new Integer( szf.getRajtszam() ) );
      nevezesek.remove( index );
    }
  }

}
