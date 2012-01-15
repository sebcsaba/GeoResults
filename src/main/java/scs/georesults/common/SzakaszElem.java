package scs.georesults.common;

import scs.javax.rdb.StorableClass;

/**
 * <p>A szakaszok illetve a szakaszokat alkotó részek (etap, szlalom) osztályai implementálják.</p>
 */
public interface SzakaszElem extends StorableClass
{

  /**
   * Az elem saját azonosítóját adja vissza.
   *
   * @return Az elem azonosítója
   */
  public long getSzakaszElemId ();

  /**
   * Az elemet tartalmazó verseny azonosítóját adja vissza.
   *
   * @return A verseny azonosítója
   */
  public long getVid ();

  /**
   * Az elemet tartalmazó szakasz azonosítóját adja vissza. (Szakasz esetén a saját azonosítóját.)
   *
   * @return A szakasz azonosítója
   */
  public long getSzid ();

  /**
   * Az elemet tartalmazó szakasz azonosítóját állítja be. (Szakasz esetén a saját azonosítóját.)
   *
   * @param szid Az új szakasz-azonosító
   */
  public void setSzid ( long szid );

  /**
   * Megadja, hogy az adott elem eredményét bele kell-e számítani az összesítésbe.
   *
   * @return Igaz, ha az eredmény beleszámít az összesítésbe
   */
  public boolean isErtekelendo ();

  /**
   * Megadja, hogy az elem eredményének legutóbbi kiszámítása óta történt-e változás az elemhez tartozó adatokon. Ha még nem volt az eredmény kiszámítva, akkor is igaz eredményt ad.
   *
   * @return Igaz, ha az eredményt frissíteni kell
   */
  public boolean isEredmenyFrissitendo ();

  /**
   * Beállítja, hogy az elemhez tartozó kiszámított eredményt szükséges-e frissíteni.
   *
   * @param eredmenyFrissitendo A frissítés szükségességét jelzi
   */
  public void setEredmenyFrissitendo ( boolean eredmenyFrissitendo );

  /**
   * Az elem nevét adja vissza.
   *
   * @return Az elem neve
   */
  public String getNev ();

}
