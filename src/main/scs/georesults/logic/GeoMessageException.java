package scs.georesults.logic;

import scs.georesults.GeoException;

/**
 * <p>EZ a kivétel egy olyan eseményt jelöl, amelynél a hibaüzenet
 * egy szótárban található kifejezés formájában jelenik meg.</p>
 */
public class GeoMessageException extends GeoException
{

  /**
   * A hibához tartozó szótári kulcs
   */
  private String key;

  /**
   * Azt jelzi, hogy a hiba kiváltódása esetén melyik lap jelenjen meg. Ha hamis,
   * akkor arra a lapra jutunk vissza, amelyről a hibát kiváltó folyamatot
   * indítottuk. Ha igaz, akkor egy <code>errorBack</code> címkével jelölt
   * továbbítási szabály lép életbe, és az mondja meg hogy melyik lapra jutunk.
   */
  private boolean fallBack;

  /**
   * Létrehoz egy új kivétel-obejktumot a megadott kulcs alapján.
   * A <code>fallback</code>értéke hamis lesz.
   *
   * @param key A hiba kulcsa
   */
  public GeoMessageException ( String key )
  {
    this.key = key;
    fallBack = false;
  }

  /**
   * Létrehoz egy új kivétel-obejktumot a megadott kulcs és visszalépési mód alapján.
   *
   * @param key A hiba kulcsa
   * @param fallBack A visszalépés módja
   */
  public GeoMessageException ( String key, boolean fallBack )
  {
    this.key = key;
    this.fallBack = fallBack;
  }

  /**
   * A hiba kulcsát adja vissza
   *
   * @return String
   */
  public String getKey ()
  {
    return key;
  }

  /**
   * Megadja, hogy milyen a visszalépési mód.
   *
   * @return Igaz, ha a külön megadott visszalépési mód van érvényben.
   */
  public boolean isFallBack ()
  {
    return fallBack;
  }

}
