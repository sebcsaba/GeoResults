package scs.georesults.om.kozos;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.StorableObjectList;

/**
 * <p>A program egy szavát vagy kifejezését adott nyelven tartalmazó
 * szótári bejegyzést reprezentáló osztály.</p>
 */
public abstract class SzotarBejegyzes extends StorableEntityBase
{

  /**
   * A bejegyzés nyelvének kódja
   */
  private String lang;

  /**
   * A bejegyzés kulcsa.
   */
  private String keystr;

  /**
   * A bejegyzés szövege.
   */
  private String valuestr;

  /**
   * Egy új, üres objektumot hoz létre.
   */
  protected SzotarBejegyzes ()
  {
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméterek alapján.
   *
   * @param lang A bejegyzés nyelve
   * @param keystr A bejegyzés kulcsa
   */
  protected SzotarBejegyzes ( String lang, String keystr )
  {
    this.lang = lang;
    this.keystr = keystr;
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméterek alapján.
   *
   * @param lang A bejegyzés nyelve
   * @param keystr A bejegyzés kulcsa
   * @param valuestr A bejegyzés szövege
   */
  protected SzotarBejegyzes ( String lang, String keystr, String valuestr )
  {
    this.lang = lang;
    this.keystr = keystr;
    this.valuestr = valuestr;
  }

  /**
   * A bejegyzés nyelvi kódját adja vissza.
   *
   * @return A nyelv kódja
   */
  public String getLang ()
  {
    return lang;
  }

  /**
   * A bejegyzés nyelvi kódját állítja be.
   *
   * @param lang Az új nyelv kódja
   */
  public void setLang ( String lang )
  {
    this.lang = lang;
  }

  /**
   * A bejegyzés kulcsát adja vissza.
   *
   * @return A kulcs
   */
  public String getKeystr ()
  {
    return keystr;
  }

  /**
   * A bejegyzés kulcsát állítja be.
   *
   * @param keystr Az új kulcs
   */
  public void setKeystr ( String keystr )
  {
    this.keystr = keystr;
  }

  /**
   * A bejegyzés szövegét adja vissza.
   *
   * @return A szöveg
   */
  public String getValuestr ()
  {
    return valuestr;
  }

  /**
   * A bejegyzés szövegét állítja be.
   *
   * @param valuestr Az új szöveg
   */
  public void setValuestr ( String valuestr )
  {
    this.valuestr = valuestr;
  }

  /**
   * <p>Az egy nyelvhez tartozó szótári bejegyzések listája.</p>
   */
  public static class Lista extends StorableObjectList
  {

    /**
     * A lista elemeit azonosító kulcsobjektum
     */
    private SzotarBejegyzes keyEntity = new SzotarBejegyzesImpl();

    /**
     * A lista elemeit azonosító kulcsobjektumot adja vissza
     *
     * @return A kulcsobjektum
     */
    protected Object getKeyEntity ()
    {
      return keyEntity;
    }

    /**
     * Egy új elemet ad a listához
     *
     * @param o Az új elem
     * @return Igaz, ha sikerült a hozzáadás
     */
    public boolean add ( Object o )
    {
      SzotarBejegyzes entity = ( SzotarBejegyzes ) o;
      entity.setLang( getLang () );
      return super.add( entity );
    }

    /**
     * A lista elemeinek és kulcsobjektumának nyelvi kódját állítja be.
     *
     * @param lang Az új nyelv kódja
     */
    public void setLang ( String lang )
    {
      keyEntity.setLang( lang );
      for ( int i = 0; i < size(); ++i ) {
        SzotarBejegyzes entity = ( SzotarBejegyzes ) get( i );
        entity.setLang( lang );
      }
    }

    /**
     * A lista elemeinek és kulcsobjektumának nyelvi kódját adja vissza.
     *
     * @return String
     */
    public String getLang ()
    {
      return keyEntity.getLang();
    }

  }


  /**
   * Betölti az adatbázisból az adott {@link Nyelv} objektumhoz tartozó összes
   * SzotarBejegyzes típusú objektumot.
   *
   * @param session Az adatbázis
   * @param nyelv Az adott nyelv
   * @return A betöltött objektumok listája
   */
  public static List loadAllForNyelv ( RdbSession session, Nyelv nyelv ) throws RdbException
  {
    return loadAll( session, SzotarBejegyzes.class, "lang", nyelv );
  }

  /**
   * Egy új, üres objektumot hoz létre.
   *
   * @return Az új objektum
   */
  public static SzotarBejegyzes newInstance ()
  {
    return new SzotarBejegyzesImpl();
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméterek alapján.
   *
   * @param lang Az bejegyzés nyelvének kódja
   * @param keystr A bejegyzés kulcsa
   * @return Az új objektum
   */
  public static SzotarBejegyzes newInstance ( String lang, String keystr )
  {
    return new SzotarBejegyzesImpl( lang, keystr );
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméterek alapján.
   *
   * @param lang Az bejegyzés nyelvének kódja
   * @param keystr A bejegyzés kulcsa
   * @param valuestr A bejegyzés szövege
   * @return Az új objektum
   */
  public static SzotarBejegyzes newInstance ( String lang, String keystr, String valuestr )
  {
    return new SzotarBejegyzesImpl( lang, keystr, valuestr );
  }

}
