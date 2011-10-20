package scs.georesults.common;

/**
 * <p>A programban használt numerikus- és string-kontansokat tartalmazza.</p>
 */
public class Constants
{

  /**
   * Biztosítja, hogy az osztály ne legyen példányosítható.
   */
  private Constants ()
  {}

  /**
   * 'Nincs ellenőrzés' opciót jelölő érték.
   */
  public static final int ETAPFELADAT_ELLENORZES_TIPUS_NINCS = 0;

  /**
   * 'Ellenőrzés típusa: háttérszín kiemelés, megadott lista alapján' opciót jelölő érték.
   */
  public static final int ETAPFELADAT_ELLENORZES_TIPUS_TEXTBG_EPLIST = 1;

  /**
   * 'Ellenőrzés típusa: lenyíló lista, megadott lista alapján' opciót jelölő érték.
   */
  public static final int ETAPFELADAT_ELLENORZES_TIPUS_COMBO_EPLIST = 2;

  /**
   * 'Ellenőrzés típusa: háttérszín kiemelés, etalon alapján' opciót jelölő érték.
   */
  public static final int ETAPFELADAT_ELLENORZES_TIPUS_TEXTBG_ETALON = 3;

  /**
   * 'Ellenőrzés típusa: lenyíló lista, etalon alapján' opciót jelölő érték.
   */
  public static final int ETAPFELADAT_ELLENORZES_TIPUS_COMBO_ETALON = 4;


  /**
   * Azt jelzi, hogy a sorrendfüggő feladat kiértékelésénél az etalonon és a menetlevélen megegyeznek az értékek.
   */
  public static final int FELADATKIERTEKELES_MEGEGYEZIK = 0;

  /**
   * Azt jelzi, hogy a sorrendfüggő feladat kiértékelésénél a menetlevélen hiányzik az etalonon szereplő érték.
   */
  public static final int FELADATKIERTEKELES_MENETLEVELEN_HIANY = 1;

  /**
   * Azt jelzi, hogy a sorrendfüggő feladat kiértékelésénél az etalonon hiányzik a menetlevélen szereplő érték.
   */
  public static final int FELADATKIERTEKELES_MENETLEVELEN_TOBBLET = 2;

  /**
   * Azt jelzi, hogy a sorrendfüggő feladat kiértékelésénél a menetlevélen olyan érték szerepel, amely az etalonról törölve lett.
   */
  public static final int FELADATKIERTEKELES_TOROLT = 3;


  /**
   * A munkafolyamatban az aktuális nyelvet azonosító kulcs.
   */
  public static final String SESSION_KEY_NYELV = "nyelv";

  /**
   * A munkafolyamatban az aktuális nyelvhez tartozó szótárt azonosító kulcs.
   */
  public static final String SESSION_KEY_GLOBAL_SZOTAR = "globalSzotar";

  /**
   * A munkafolyamatban az aktuális versenyt azonosító kulcs.
   */
  public static final String SESSION_KEY_VERSENY = "verseny";

  /**
   * A munkafolyamatban az aktuális versenyhez tartozó szótárt azonosító kulcs.
   */
  public static final String SESSION_KEY_VERSENY_SZOTAR = "versenySzotar";

  /**
   * A munkafolyamatban a karaktertáblához tartozó segédobjektumot azonosító kulcs.
   */
  public static final String SESSION_KEY_ADDON_CHARMAP = "addonCharmap";

}
