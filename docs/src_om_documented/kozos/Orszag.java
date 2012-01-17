package scs.georesults.om.kozos;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

/**
 * <p>Egy, a programban definiált országot reprezentál.</p>
 */
public abstract class Orszag extends StorableEntityBase
{

  /**
   * Az ország kétbetűs kódja
   */
  private String country;

  /**
   * Egy új, üres objektumot hoz létre.
   */
  protected Orszag ()
  {
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméter alapján.
   *
   * @param country Az ország kódja
   */
  protected Orszag ( String country )
  {
    this.country = country;
  }

  /**
   * Az ország kódját adja vissza.
   *
   * @return Az ország kódja
   */
  public String getCountry ()
  {
    return country;
  }

  /**
   * Az ország kódját állítja be.
   *
   * @param country Az ország új kódja
   */
  public void setCountry ( String country )
  {
    this.country = country;
  }

  /**
   * Betölti az adatbázisból az összes létező Ország típusú objektumot.
   *
   * @param session Az adatbázis
   * @return A betöltött objektumok listája
   */
  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, Orszag.class );
  }

  /**
   * Egy új, üres objektumot hoz létre.
   *
   * @return Az új objektum
   */
  public static Orszag newInstance ()
  {
    return new OrszagImpl();
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméter alapján.
   *
   * @param lang A nyelv kódja
   * @return Az új objektum
   */
  public static Orszag newInstance ( String country )
  {
    return new OrszagImpl( country );
  }

}
