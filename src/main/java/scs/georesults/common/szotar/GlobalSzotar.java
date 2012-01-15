package scs.georesults.common.szotar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebSession;
import scs.georesults.GeoException;
import scs.georesults.common.Constants;
import scs.georesults.om.kozos.Nyelv;
import scs.georesults.om.kozos.SzotarBejegyzes;

/**
 * <p>A globális (a program szavait és kifejezéseit tartalmazó) szótár
 * egy adott nyelven. Ez az osztály a <tt>szavak</tt> objektumban értékekként
 * stringeket tartalmaz: az adott kulcshoz a megadott nyelven
 * tartozó fordítást.</p>
 */
public class GlobalSzotar extends Szotar
{

  // szavak : Map( String[key] -> String[label] )

  /**
   * Létrehoz egy új szótárt a megadott nyelven, és feltölti az szavakkal a megadott adatbázisból.
   *
   * @param db Az adatbázis
   * @param nyelv A szótár nyelve
   */
  public GlobalSzotar ( RdbSession db, Nyelv nyelv ) throws GeoException, RdbException
  {
    super( nyelv );
    fillSzavak( db );
  }

  /**
   * A megadott adatbázis alapján feltölti szavakkal a szótárat.
   *
   * @param db Az adatbázis objektuma
   */
  protected void fillSzavak ( RdbSession db ) throws GeoException, RdbException
  {
    List szbk = SzotarBejegyzes.loadAllForNyelv( db, nyelv );
    for ( int i = 0; i < szbk.size(); ++i ) {
      SzotarBejegyzes szb = ( SzotarBejegyzes ) szbk.get( i );
      szavak.put( szb.getKeystr(), szb.getValuestr() );
    }
  }

  /**
   * A paraméterben megadott objektumot stringgé alakítva visszaadja,
   * hiszen a <tt>szavak</tt> tárában értékként stringeket tárolunk.
   *
   * @param valueFromSzavak A <tt>szavak</tt>-ban talált objektum
   * @return A megfelelő szó
   */
  protected String getValueFromSzavakValueObject ( Object valueFromSzavak )
  {
    return ( String ) valueFromSzavak;
  }

  /**
   * Visszaadja az adott kontextusban használt globális szótár objektumot.
   *
   * @param pageContext A kontextus objektum
   * @return A megfelelő példány
   */
  public static GlobalSzotar getCurrentInstance ( PageContext pageContext ) throws SessionTimeoutException
  {
    HttpSession session = WebSession.justGetHttpSession( pageContext );
    return ( GlobalSzotar ) session.getAttribute( Constants.SESSION_KEY_GLOBAL_SZOTAR );
  }

  /**
   * Visszaadja az adott kéréshez használt globális szótár objektumot.
   *
   * @param request A HTTP kérés objektum
   * @return A megfelelő példány
   */
  public static GlobalSzotar getCurrentInstance ( HttpServletRequest request ) throws SessionTimeoutException
  {
    HttpSession session = WebSession.justGetHttpSession( request );
    return ( GlobalSzotar ) session.getAttribute( Constants.SESSION_KEY_GLOBAL_SZOTAR );
  }

  /**
   * Megkeresi az adott kontextusban használt globális szótár objektumot,
   * és megkeresi benne az adott kulcshoz tartozó szöveget.
   *
   * @param context A kontextus objektum
   * @param key A keresett kulcs
   * @return A talált szöveg
   */
  public static String resolve ( PageContext context, String key ) throws SessionTimeoutException
  {
    return getCurrentInstance( context ).getValue( key );
  }

}
