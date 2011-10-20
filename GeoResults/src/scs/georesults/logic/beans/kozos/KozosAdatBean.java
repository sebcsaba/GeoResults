package scs.georesults.logic.beans.kozos;

/**
 * <p>A versenyektől független, úgynevezett közös adatok kezelésére szolgáló művelet, a {@link scs.georesults.logic.actions.kozos.KozosAdatAction} segédosztálya. Ez az osztály fogja kiszolgálni a
 * nyelvek és az országok adminisztrálását is.</p>
 */
public class KozosAdatBean
{

  /**
   * A kiválaszott fordítás nyelvének kódja
   */
  private String lang;

  /**
   * A kiválaszott fordítás nyelve
   */
  private String langValue;

  /**
   * Az aktuális objektum kulcsa
   */
  private String key;

  /**
   * Az aktuális objektum neve az aktuális nyelven
   */
  private String keyValue;

  /**
   * A fordítás szövege
   */
  private String value;

  /**
   * Egy új objektumot hoz létre a megadott paraméterek alapján.
   *
   * @param lang A fordítás nyelvének kódja
   * @param langValue A fordítás nyelve
   * @param key Az aktuális objektum kulcsa
   * @param keyValue Az aktuális objektum neve az aktuális nyelven
   * @param value A fordítás szövege
   */
  public KozosAdatBean ( String lang, String langValue, String key, String keyValue, String value )
  {
    this.lang = lang;
    this.langValue = langValue;
    this.key = key;
    this.keyValue = keyValue;
    this.value = value;
  }

  public String getKey ()
  {
    return key;
  }

  public String getLang ()
  {
    return lang;
  }

  public String getValue ()
  {
    return value;
  }

  public String getLangValue ()
  {
    return langValue;
  }

  public String getKeyValue ()
  {
    return keyValue;
  }

}
