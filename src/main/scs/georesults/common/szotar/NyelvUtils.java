package scs.georesults.common.szotar;

import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.om.kozos.Nyelv;

/**
 * <p>A nyelvek kezelését segítő osztály.</p>
 */
public class NyelvUtils
{

  /**
   * Biztosítja, hogy az osztály ne legyen példányosítható.
   */
  private NyelvUtils ()
  {}

  /**
   * A paraméterben kapott, HTTP kérést reprezentáló objektumot feldolgozza, és visszaadja a kérést küldő böngészőprogram által elfogadott nyelvek listáját.
   *
   * @param request A HTTP kérés
   * @return A nyelvek listája
   */
  public static List getAcceptLanguages ( HttpServletRequest request )
  {
    // "accept-language = hu-hu,hu;q=0.8,en-us;q=0.5,en;q=0.3"
    String base = request.getHeader( "accept-language" );
    List result = new List();
    for ( StringTokenizer stok = new StringTokenizer( base, "," ); stok.hasMoreTokens(); ) {
      String tok = stok.nextToken();
      int semicolon = tok.indexOf( ";" );
      if ( semicolon > -1 ) tok = tok.substring( 0, semicolon );
      int hyphen = tok.indexOf( "-" );
      if ( hyphen > -1 ) tok = tok.substring( 0, hyphen );
      result.add( Nyelv.newInstance( tok ) );
    }
    return result;
  }

  /**
   * Visszaadja az adatbázisban telepített nyelvek listáját.
   *
   * @return A nyelvek listája
   */
  public static List getKnownNyelvek () throws GeoException, RdbException
  {
    return Nyelv.loadAll( GeoDbSession.getCurrentInstance() );
  }

  /**
   * A kapott HTTP kérés alapján kiválasztja, hogy melyik nyelv lesz az aktuális alapnyelv.
   *
   * @param request A HTTP kérés
   * @return A kiválasztott nyelv
   */
  public static Nyelv getAlapNyelv ( HttpServletRequest request ) throws GeoException, RdbException
  {
    List instNyelvek = getKnownNyelvek();
    List accNyelvek = getAcceptLanguages( request );
    for ( int accIndex = 0; accIndex < accNyelvek.size(); ++accIndex ) {
      Nyelv accNyelv = ( Nyelv ) accNyelvek.get( accIndex );
      for ( int instIndex = 0; instIndex < instNyelvek.size(); ++instIndex ) {
        Nyelv instNyelv = ( Nyelv ) instNyelvek.get( instIndex );
        if ( instNyelv.getLang().equals( accNyelv.getLang() ) )return instNyelv;
      }
    }
    return ( Nyelv ) instNyelvek.iterator().next();
  }

}
