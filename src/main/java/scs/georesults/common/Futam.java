package scs.georesults.common;

import scs.javax.rdb.StorableClass;

/**
 * <p>Olyan közös felület, amelyet a különböző típusú futamok (Menetlevél illetve SzlalomFutam) implementációs osztályai biztosítanak.</p>
 */
public interface Futam extends StorableClass
{

  /**
   * Visszaadja az adott futamhoz tartozó versenyző rajtszámát.
   *
   * @return A versenyző rajtszáma
   */
  public int getRajtszam ();

}
