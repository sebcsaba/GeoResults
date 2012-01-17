package scs.georesults.om.kozos;

/**
 * <p>A {@link Nyelv} osztály implementációs leszármazott osztálya.</p>
 */
public class NyelvImpl extends Nyelv
{

  /**
   * A szótárban a nyelveket azonosító bejegyzések kulcsainak közös prefixe.
   * A kulcsok a prefix után a nyelvek kétbetűs kódját tartalmazzák.
   */
  public static final String BUNDLE_PREFIX = "LG_";

  /**
   * Egy új, üres objektumot hoz létre.
   */
  public NyelvImpl ()
  {
    super();
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméter alapján.
   *
   * @param lang A nyelv kódja
   */
  public NyelvImpl ( String lang )
  {
    super( lang );
  }

}
