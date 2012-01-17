package scs.georesults.om.kozos;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;

/**
 * <p>A {@link SzotarBejegyzes} osztály implementációs leszármazott osztálya.</p>
 */
public class SzotarBejegyzesImpl extends SzotarBejegyzes
{

  /**
   * Egy új, üres objektumot hoz létre.
   */
  public SzotarBejegyzesImpl ()
  {
    super();
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméterek alapján.
   *
   * @param lang A bejegyzés nyelve
   * @param keystr A bejegyzés kulcsa
   */
  public SzotarBejegyzesImpl ( String lang, String keystr )
  {
    super( lang, keystr );
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméterek alapján.
   *
   * @param lang A bejegyzés nyelve
   * @param keystr A bejegyzés kulcsa
   * @param valuestr A bejegyzés szövege
   */
  public SzotarBejegyzesImpl ( String lang, String keystr, String valuestr )
  {
    super( lang, keystr, valuestr );
  }

  /**
   * Betölti az adatbázisból az összes létező SzotarBejegyzes típusú objektumot.
   *
   * @param session Az adatbázis
   * @return A betöltött objektumok listája
   */
  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, SzotarBejegyzes.class );
  }

}
