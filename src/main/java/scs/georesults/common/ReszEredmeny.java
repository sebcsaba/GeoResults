package scs.georesults.common;

import scs.javax.rdb.StorableClass;

/**
 * <p>Olyan felület, amelyet minden olyan osztály implementál, amely egy versenyzőnek egy adott értékelhető egységen elért eredményét tartalmazza.</p>
 */
public interface ReszEredmeny extends StorableClass
{

  /**
   * A részegységen elért pontszám lekérdezése.
   *
   * @return A pontszám
   */
  public int getPontszam ();

  /**
   * A részegységen elért pontszám beállítása.
   *
   * @param pontszam Az új pontszám
   */
  public void setPontszam ( int pontszam );

}
