package scs.georesults.om.kozos;

/**
 * <p>Az {@link Orszag} osztály implementációs leszármazott osztálya.</p>
 */
public class OrszagImpl extends Orszag
{

  /**
   * A szótárban az országokat azonosító bejegyzések kulcsainak közös prefixe.
   * A kulcsok a prefix után az országok kétbetűs kódját tartalmazzák.
   */
  public static final String BUNDLE_PREFIX = "CY_";

  /**
   * Egy új, üres objektumot hoz létre.
   */
  public OrszagImpl ()
  {
    super();
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméter alapján.
   *
   * @param country Az ország kódja
   */
  public OrszagImpl ( String country )
  {
    super( country );
  }

}
