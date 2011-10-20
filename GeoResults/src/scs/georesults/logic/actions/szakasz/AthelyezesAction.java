package scs.georesults.logic.actions.szakasz;

import java.util.HashSet;
import java.util.Set;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebException;
import scs.javax.web.request.InvalidRequestFieldException;
import scs.georesults.GeoException;
import scs.georesults.common.SzakaszElem;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.om.verseny.Szakasz;

/**
 * <p>Etapokt és szlalomok szakaszok közötti áthelyezését biztosító szolgáltatás oszálya.</p>
 */
public class AthelyezesAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    Set modSections = new HashSet();
    updateElemList( getVerseny().getEtapok(), form, "etap", modSections );
    updateElemList( getVerseny().getSzlalomok(), form, "szlalom", modSections );
    updateSzakaszList( modSections );
    getVerseny().updateSzakaszok();
    return "ok";
  }

  /**
   * Az áthelyezni kívánt elemek adatát frissíti.
   *
   * @param elemek Adott típusú verseny-elemek listája
   * @param form A HTTP kérés objektuma
   * @param prefix A verseny-elemek nevéhez a beivteli űrlapon tartozó prefix
   * @param modSections A módosított szakaszok azonosítóinak halmaza.
   */
  private void updateElemList ( List elemek, DynamicForm form, String prefix, Set modSections ) throws InvalidRequestFieldException, GeoException, RdbException
  {
    for ( int i = 0; i < elemek.size(); ++i ) {
      SzakaszElem sze = ( SzakaszElem ) elemek.get( i );
      long newSzid = form.getLong( prefix + sze.getSzakaszElemId() );
      if ( sze.getSzid() != newSzid ) {
        modSections.add( new Long( newSzid ) );
        modSections.add( new Long( sze.getSzid() ) );
        sze.read( getDb() );
        sze.setSzid( newSzid );
        //scs sze.setEredmenyFrissitendo( true );
        sze.update( getDb() );
      }
    }
  }

  /**
   * A módosított szakaszoknál jelzi, hogy eredményüket frissíteni kell.
   *
   * @param modSections A módosított szakaszok azonosítóinak halmaza.
   */
  private void updateSzakaszList ( Set modSections ) throws SessionTimeoutException, RdbException, GeoException
  {
    for ( int i = 0; i < getVerseny().getSzakaszok().size(); ++i ) {
      Szakasz sz = ( Szakasz ) getVerseny().getSzakaszok().get( i );
      if ( modSections.contains( new Long( sz.getSzid() ) ) ) {
        sz.read( getDb() );
        sz.setEredmenyFrissitendo( true );
        sz.update( getDb() );
      }
    }
  }

}
