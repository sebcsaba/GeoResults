package scs.georesults.logic.utils;

import java.util.HashMap;
import java.util.Map;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.web.RequestBeanBase;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebSession;
import scs.georesults.GeoException;
import scs.georesults.common.Constants;
import scs.georesults.common.szotar.GlobalSzotar;
import scs.georesults.om.alap.KesesiZona;
import scs.georesults.om.alap.KesesiZonaImpl;
import scs.georesults.om.kozos.OrszagImpl;
import scs.georesults.om.verseny.VersenyImpl;

/**
 * <p>A megjelenítést segítő osztály.</p>
 * <p>Általában az őt használó JSP lapok egy-egy példányt létrehoznak belőle.</p>
 */
public class DisplayUtils extends RequestBeanBase
{

  /**
   * Az aktuális verseny objektuma.
   */
  private VersenyImpl verseny;

  /**
   * Az aktuális nyelvhez tartozó globális szótár
   */
  private GlobalSzotar globalSzotar;

  /**
   * A késési zónák adatait tartalamzó aszociatív tömb. A tömb kulcsai a késési
   * zónák időlimitjei. Egy adott kulcshoz tartozó érték pedig a megfelelő késési
   * zóna másik időlimitje, vagyis pozitív időlimitű zóna esetén a nála kisebb
   * limitű zónák közül a legnagyobb limit + 1. Tehát a tömb lényegében a késési zónák
   * alsó és felső korlátjából álló intervallumokat tartalmazzák, rendezett párok formájában.
   */
  private Map kesesiZonaIntervallumok; // Map( Integer[kz.idoLimit] -> ? Integer[masikIdoLimit] )

  /**
   * Az aktuális környezet alapján inicializálja az objektumot.
   */
  public void init () throws GeoException, SessionTimeoutException
  {
    this.verseny = ( VersenyImpl ) WebSession.justGetHttpSession( pageContext ).getAttribute( Constants.SESSION_KEY_VERSENY );
    this.globalSzotar = GlobalSzotar.getCurrentInstance( pageContext );
    this.kesesiZonaIntervallumok = null;
  }

  /**
   * A megadott kódhoz tartozó ország nevét adja meg, az aktuális nyelven.
   */
  public String getOrszagNev ( String orszagKod )
  {
    return globalSzotar.getValue( OrszagImpl.BUNDLE_PREFIX + orszagKod );
  }

  /**
   * Frissíti a 'kesesiZonaIntervallumok' mező értékét az adatbázis alapján.
   */
  public void updateKesesiZonaIntervallumok () throws RdbException, GeoException
  {
    kesesiZonaIntervallumok = new HashMap();
    List kesesiZonak = verseny.getKesesiZonak();
    int firstPos = KesesiZonaImpl.findFirstPositiveIndex( kesesiZonak );
    int prev = 1;
    for ( int i = firstPos; i < kesesiZonak.size(); ++i ) {
      KesesiZona kz = ( KesesiZona ) kesesiZonak.get( i );
      kesesiZonaIntervallumok.put( new Integer( kz.getIdoLimit() ), new Integer( prev ) );
      prev = kz.getIdoLimit() + 1;
    }
    prev = -1;
    for ( int i = firstPos - 1; i >= 0; --i ) {
      KesesiZona kz = ( KesesiZona ) kesesiZonak.get( i );
      kesesiZonaIntervallumok.put( new Integer( kz.getIdoLimit() ), new Integer( prev ) );
      prev = kz.getIdoLimit() - 1;
    }
  }

  /**
   * A paraméterben kapott késési zónának a tömbben tárolt intervallumát adja vissza,
   * szöveges formában.
   */
  public String getKesesiZonaIntervallum ( KesesiZona kz ) throws GeoException, RdbException
  {
    if ( kesesiZonaIntervallumok == null ) updateKesesiZonaIntervallumok();
    Integer masik = ( Integer ) kesesiZonaIntervallumok.get( new Integer( kz.getIdoLimit() ) );
    if ( masik.intValue() < kz.getIdoLimit() ) {
      return masik.toString() + ".." + Integer.toString( kz.getIdoLimit() );
    } else {
      return Integer.toString( kz.getIdoLimit() ) + ".." + masik.toString();
    }
  }

}
