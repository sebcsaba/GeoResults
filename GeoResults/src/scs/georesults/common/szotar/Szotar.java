package scs.georesults.common.szotar;

import java.util.HashMap;
import java.util.Map;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.georesults.Config;
import scs.georesults.GeoException;
import scs.georesults.om.kozos.Nyelv;

/**
 * <p>A szótárak közös, absztrakt alaposztálya.</p>
 */
public abstract class Szotar
{

  /**
   * A szótár alapértelmezett nyelva.
   */
  protected Nyelv nyelv;

  /**
   * A szótár tartalma. A kulcsok típusa string, viszont az
   * értékek típusát a leszármazott osztály fogja meghatározni.
   */
  protected Map szavak; // Map( String[key] -> (?) )


  /**
   * Létrehoz egy új szótárt a megadott nyelvhez.
   *
   * @param nyelv A megadott nyelv
   */
  public Szotar ( Nyelv nyelv ) throws GeoException
  {
    this.nyelv = nyelv;
    this.szavak = new HashMap();
  }

  /**
   * A megadott adatbázis alapján feltölti szavakkal a szótárat.
   *
   * @param db Az adatbázis objektuma
   */
  protected abstract void fillSzavak ( RdbSession db ) throws GeoException, RdbException;

  /**
   * Megadja a szótár key kulcshoz tartozó szavát. Ha nincs ilyen kulcs,
   * akkor a kulcsot adja vissza, két kérdőjel között.
   *
   * @param key A kulcs
   * @return A talált érték
   */
  public String getValue ( String key )
  {
    Object szoRepr = szavak.get( key );
    if ( szoRepr == null ) {
      if ( Config.DEBUG_SZOTAR ) System.err.println( "Szótár kulcs nem található: " + key );
      return "?" + key + "?";
    } else {
      return getValueFromSzavakValueObject( szoRepr );
    }
  }

  /**
   * Megadja, hogy van-e a szótárban bejegyzés az adott kulcshoz.
   *
   * @param key A kulcs
   * @return Igaz, ha létezik bejegyzés a kulcshoz
   */
  public boolean hasKey ( String key )
  {
    return ( szavak.get( key ) != null );
  }

  /**
   * A paraméterben átadott objektumból (amelyet a <tt>szavak</tt> asszociatív tömbből
   * választott ki megfelelő kulcs alapján) előállítja a nyelvnek megfelelő szót.
   *
   * @param valueFromSzavak A <tt>szavak</tt>-ban talált objektum
   * @return A megfelelő szó
   */
  protected abstract String getValueFromSzavakValueObject ( Object valueFromSzavak );

}
