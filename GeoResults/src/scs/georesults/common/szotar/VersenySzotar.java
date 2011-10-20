package scs.georesults.common.szotar;

import java.util.*;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebSession;
import scs.georesults.GeoException;
import scs.georesults.common.Constants;
import scs.georesults.om.alap.VersenySzotarBejegyzes;
import scs.georesults.om.alap.VersenySzotarBejegyzesImpl;
import scs.georesults.om.kozos.Nyelv;
import scs.georesults.om.verseny.Verseny;

/**
 * <p>A versenyhez tartozó szótár. Ez az osztály a szavak
 * objektumban értékként a beágyazott SzoForditasokkal
 * osztály példányait tárolja.</p>
 */
public class VersenySzotar extends Szotar
{

  // szavak : Map( String[key] -> SzoForditasokkal )

  /**
   * A szótárat tartalmazó verseny.
   */
  protected Verseny verseny;


  /**
   * Létrehoz egy új szótárt a megadott a megadott versenyhez, és feltölti az szavakkal a megadott adatbázisból.
   *
   * @param db Az adatbázis
   * @param verseny A szótárt tartalmazó verseny
   * @param nyelv A szótár nyelve
   */
  public VersenySzotar ( RdbSession db, Verseny verseny, Nyelv nyelv ) throws GeoException, RdbException
  {
    super( nyelv );
    this.verseny = verseny;
    fillSzavak( db );
  }

  /**
   * A megadott adatbázis alapján feltölti szavakkal a szótárat.
   *
   * @param db Az adatbázis objektuma
   */
  public void fillSzavak ( RdbSession db ) throws GeoException, RdbException
  {
    szavak.clear();
    List src = VersenySzotarBejegyzesImpl.loadAllForVerseny( db, verseny );
    SzoForditasokkal szoForditasokkal = null;
    long lastKey = Long.MIN_VALUE;
    for ( int i = 0; i < src.size(); ++i ) {
      VersenySzotarBejegyzes vszb = ( VersenySzotarBejegyzes ) src.get( i );
      if ( szoForditasokkal == null || lastKey != vszb.getVszbid() ) {
        lastKey = vszb.getVszbid();
        szoForditasokkal = new SzoForditasokkal( lastKey );
        szavak.put( getKeyFromVszbid( lastKey ), szoForditasokkal );
      }
      szoForditasokkal.put( vszb.getLang(), vszb.getFelirat() );
    }
  }

  /**
   * A megadott objektumot SzoForditasokkal típusú objektumként kezeli.
   * Megkeresi benne az aktuális nyelvhez tartozó szót, és visszaadja.
   * Ha nincs ilyen, akkor a verseny alapnyelvéhez tartozót adja vissza.
   *
   * @param valueFromSzavak A <tt>szavak</tt>-ban talált objektum
   * @return A megfelelő szó vagy az eredeti szöveg
   */
  protected String getValueFromSzavakValueObject ( Object valueFromSzavak )
  {
    SzoForditasokkal szf = ( SzoForditasokkal ) valueFromSzavak;
    if ( szf.isVanIlyenNyelven( nyelv.getLang() ) ) {
      return szf.getFeliratAdottNyelven( nyelv.getLang() );
    } else {
      return szf.getFeliratAdottNyelven( verseny.getAlapNyelv() );
    }
  }

  /**
   * Visszaadja a megadott
   * {@link scs.georesults.om.alap.VersenySzotarBejegyzes}
   * típusú objektumot azonosító
   * értékhez tartozó kulcsot.
   *
   * @param vszbid az objektumok azonosítója
   * @return A megfelelő kulcs
   */
  public static String getKeyFromVszbid ( long vszbid )
  {
    return "@" + vszbid;
  }

  /**
   * Visszaadja a megadott kulcsból az által reprezentált
   * {@link scs.georesults.om.alap.VersenySzotarBejegyzes}
   * típusú objektum azonosítóját.
   *
   * @param key A kulcs
   * @return Az azonosító
   */
  public static long getVszbidFromKey ( String key )
  {
    return Long.parseLong( key.substring( 1 ) );
  }

  /**
   * Megállapítja, hogy a paraméterben megadott nyelv használható-e
   * alapnyelvként, vagyis minden szónak létezik-e fordítása az adott nyelven.
   *
   * @param nyelv A nyelv kódja
   * @return Igaz, ha alapnyelvként használható
   */
  public boolean isValidAlapnyelv ( String nyelv )
  {
    for ( Iterator it = szavak.values().iterator(); it.hasNext(); ) {
      SzoForditasokkal szf = ( SzoForditasokkal ) it.next();
      if ( !szf.isVanIlyenNyelven( nyelv ) )return false;
    }
    return true;
  }

  /**
   * Visszaadja a szavak asszociatív tömb értékeit, mint egy Collection objektumot.
   *
   * @return A tömb értékei
   */
  public Collection getAllItems ()
  {
    return szavak.values();
  }

  /**
   * Visszaadja az adott kulcshoz tartozó SzoForditasokkal típusú objektumot.
   *
   * @param key A kulcs
   * @return A hozzá tartozó objektum
   */
  public SzoForditasokkal getSzoForditasokkal ( String key )
  {
    return ( SzoForditasokkal ) szavak.get( key );
  }

  /**
   * Visszaadja az adott kontextusban használt versenyszótár objektumot.
   *
   * @param pageContext A kontextus objektum
   * @return A megfelelő példány
   */
  public static VersenySzotar getCurrentInstance ( PageContext pageContext ) throws SessionTimeoutException
  {
    HttpSession session = WebSession.justGetHttpSession( pageContext );
    return ( VersenySzotar ) session.getAttribute( Constants.SESSION_KEY_VERSENY_SZOTAR );
  }

  /**
   * Megkeresi az adott kontextusban használt versenyszótár objektumot,
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


  /**
   * <p>Egy verseny egy szavának különböző nyelvű fordításait reprezentálja.</p>
   */
  public static class SzoForditasokkal
  {

    /**
     * A szóhoz tartozó
     * {@link scs.georesults.om.alap.VersenySzotarBejegyzes}
     * objektumok azonosítója.
     */
    private long vszbid;

    /**
     * Egy aszociatív tömb, amely a nyelvek kódjaihoz mint stringekhez
     * az adott nyelvű fordításokat tartalmazó stringeket rendel.
     */
    private Map forditasok; // Map( String[lang] -> String[label] )

    /**
     * Egy új objektumot hoz létre.
     *
     * @param vszbid A szóhoz tartozó {@link scs.georesults.om.alap.VersenySzotarBejegyzes}
     * objektumok azonosítója
     */
    public SzoForditasokkal ( long vszbid )
    {
      this.vszbid = vszbid;
      this.forditasok = new HashMap();
    }

    /**
     * Visszaadja a szóhoz tartozó
     * {@link scs.georesults.om.alap.VersenySzotarBejegyzes}
     * objektumok azonosítóját.
     *
     * @return long
     */
    public long getVszbid ()
    {
      return vszbid;
    }

    /**
     * Felveszi a fordítások közé lang nyelvhez a label stringet.
     *
     * @param lang A nyelv kódja
     * @param label A fordítást tartalmazó string
     */
    public void put ( String lang, String label )
    {
      forditasok.put( lang, label );
    }

    /**
     * Megadja, hogy a szónak létezik-e fordítása a megadott nyelven.
     *
     * @param lang A nyelv kódja
     * @return Igaz, ha létezik megfelelő fordítás
     */
    public boolean isVanIlyenNyelven ( String lang )
    {
      return forditasok.keySet().contains( lang );
    }

    /**
     * Visszaadja a szó adott nyelvű fordítását.
     *
     * @param lang A nyelv kódja
     * @return A szó fordítása az adott nyelven
     */
    public String getFeliratAdottNyelven ( String lang )
    {
      return ( String ) forditasok.get( lang );
    }

  }

}
