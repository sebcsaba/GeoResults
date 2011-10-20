package scs.georesults.logic.actions.verseny;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.common.Constants;
import scs.georesults.common.szotar.VersenySzotar;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.om.alap.VersenySzotarBejegyzes;
import scs.georesults.om.alap.VersenySzotarBejegyzesImpl;

/**
 * <p>A verseny szótárára egy új szót bejegyző szolgáltatás osztálya.</p>
 * <p>Ezt a szolgáltatást a képernyő alján felnyíló szótár-ablak "Szó felvitele"
 * gomb megnyomása hívja meg. Ekkor a kijelölt mező értéke átkerül egy
 * <code>uj_szo</code> nevű rejtett mezőbe, és ez a szolgáltatás ezt a mezőt dolgozza fel.
 * A végrehajtás után a HTTP kérés objektumának felvesz két attribútumot: az
 * <code>id</code> navű a szótár-bejegyzés azonosítóját tartalmazza, a
 * <code>label</code> nevű pedig magát a felvett szót, amely megegyezik a korábbi
 * <code>uj_szo</code> mező tartalmával.</p>
 */
public class VersenySzoFelviteleAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    String felirat = form.getString( "uj_szo" );
    long id = VersenySzotarBejegyzesImpl.getNexyKey( getDb(), getVerseny() );
    VersenySzotarBejegyzes vszb = VersenySzotarBejegyzes.newInstance( getVerseny().getVid(), id, getNyelv().getLang(), felirat );
    vszb.create( getDb() );
    if ( !getNyelv().getLang().equals( getVerseny().getAlapNyelv() ) ) {
      VersenySzotarBejegyzes vszbAlap = VersenySzotarBejegyzes.newInstance( getVerseny().getVid(), id, getVerseny().getAlapNyelv(), felirat );
      vszbAlap.create( getDb() );
    }
    getRequest().setAttribute( "id", new Long( vszb.getVszbid() ) );
    getRequest().setAttribute( "label", vszb.getFelirat() );
    VersenySzotar vsz = ( VersenySzotar ) getFromSession( Constants.SESSION_KEY_VERSENY_SZOTAR );
    vsz.fillSzavak( getDb() );
    return "ok";
  }

}
