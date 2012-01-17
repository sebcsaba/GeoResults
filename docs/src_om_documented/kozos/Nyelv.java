package scs.georesults.om.kozos;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

/**
 * <p>Egy, a programban definiált nyelvet reprezentál.</p>
 */
public abstract class Nyelv extends StorableEntityBase
{

  /**
   * A nyelv kétbetűs kódja
   */
  private String lang;

  /**
   * Egy új, üres objektumot hoz létre.
   */
  protected Nyelv ()
  {
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméter alapján.
   *
   * @param lang A nyelv kódja
   */
  protected Nyelv ( String lang )
  {
    this.lang = lang;
  }

  /**
   * A nyelv kódját adja vissza.
   *
   * @return A nyelv kódja
   */
  public String getLang ()
  {
    return lang;
  }

  /**
   * A nyelv kódját állítja be.
   *
   * @param lang A nyelv új kódja
   */
  public void setLang ( String lang )
  {
    this.lang = lang;
  }

  /**
   * Betölti az adatbázisból az összes létező Nyelv típusú objektumot.
   *
   * @param session Az adatbázis
   * @return A betöltött objektumok listája
   */
  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, Nyelv.class );
  }

  /**
   * Egy új, üres objektumot hoz létre.
   *
   * @return Az új objektum
   */
  public static Nyelv newInstance ()
  {
    return new NyelvImpl();
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméter alapján.
   *
   * @param lang A nyelv kódja
   * @return Az új objektum
   */
  public static Nyelv newInstance ( String lang )
  {
    return new NyelvImpl( lang );
  }

}
