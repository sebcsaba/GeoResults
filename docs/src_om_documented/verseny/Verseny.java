package scs.georesults.om.verseny;

import scs.javax.collections.List;
import scs.javax.lang.Date;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

/**
 * <p>Egy verseny adatait reprezentáló osztály.</p>
 */
public abstract class Verseny extends StorableEntityBase
{

  /**
   * A verseny azonosítója
   */
  private long vid;

  /**
   * A verseny neve
   */
  private String nev;

  /**
   * A verseny kezdetének dátuma
   */
  private Date kezdeteDatum;

  /**
   * A verseny végének dátuma
   */
  private Date vegeDatum;

  /**
   * A verseny alapnyelvének kódja
   */
  private String alapNyelv;

  /**
   * A verseny alapértelmezett menetlevél-beviteli formulája. A felépítését
   * lásd a {@link scs.georesults.common.menetlevelformula} csomagban.
   */
  private String menetlevelformula;

  /**
   * Azt jelzi, hogy az etapok eredményének összesítését tartalmazó
   * eredménylista frissítése szükséges
   */
  private boolean eredmenyFrissitendoMindenEtap;

  /**
   * Azt jelzi, hogy a szlalomok eredményének összesítését tartalmazó
   * eredménylista frissítése szükséges
   */
  private boolean eredmenyFrissitendoMindenSzlalom;

  /**
   * Azt jelzi, hogy a verseny végeresményét tartalmazó eredménylista frissítése szükséges
   */
  private boolean eredmenyFrissitendoVerseny;

  /**
   * Azt jelzi, hogy a versenyhez tartozó csapatverseny eredménylistájának frissítése szükséges
   */
  private boolean eredmenyFrissitendoCsapat;

  /**
   * Azt a dátumot jelzi, amikor a verseny adatai lezárásra kerültek. Ha értéke <code>null</code>, akkor a verseny adatai még nincsenek lezárva.
   */
  private Date lezarva;


  /**
   * Egy új, üres objektumot hoz létre.
   */
  protected Verseny ()
  {
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméter alapján.
   *
   * @param vid A verseny azonosítója
   */
  protected Verseny ( long vid )
  {
    this.vid = vid;
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméterek alapján.
   *
   * @param vid A verseny azonosítója
   * @param nev A verseny neve
   * @param kezdeteDatum A verseny kezdetének dátuma
   * @param vegeDatum A verseny végének dátuma
   * @param alapNyelv A verseny alapnyelvének kódja
   * @param menetlevelformula A versenyhez tartozó alapértelemzett menetlevél-formula
   * @param eredmenyFrissitendoMindenEtap Azt jelzi, hogy az etapok összesített eredménylistája frissítendő
   * @param eredmenyFrissitendoMindenSzlalom Azt jelzi, hogy a szlalomok összesített eredménylistája frissítendő
   * @param eredmenyFrissitendoVerseny Azt jelzi, hogy a verseny végeredménye frissítendő
   * @param eredmenyFrissitendoCsapat Azt jelzi, hogy a versenyhez tartozó csapatverseny eredménylistája frissítendő
   * @param lezarva A verseny lezárásának dátuma, vagy <code>null</code>
   */
  protected Verseny ( long vid, String nev, Date kezdeteDatum, Date vegeDatum, String alapNyelv, String menetlevelformula, boolean eredmenyFrissitendoMindenEtap, boolean eredmenyFrissitendoMindenSzlalom, boolean eredmenyFrissitendoVerseny, boolean eredmenyFrissitendoCsapat, Date lezarva )
  {
    this.vid = vid;
    this.nev = nev;
    this.kezdeteDatum = kezdeteDatum;
    this.vegeDatum = vegeDatum;
    this.alapNyelv = alapNyelv;
    this.menetlevelformula = menetlevelformula;
    this.eredmenyFrissitendoMindenEtap = eredmenyFrissitendoMindenEtap;
    this.eredmenyFrissitendoMindenSzlalom = eredmenyFrissitendoMindenSzlalom;
    this.eredmenyFrissitendoVerseny = eredmenyFrissitendoVerseny;
    this.eredmenyFrissitendoCsapat = eredmenyFrissitendoCsapat;
    this.lezarva = lezarva;
  }

  /**
   * A verseny azonosítóját adja vissza
   *
   * @return A verseny azonosítója
   */
  public long getVid ()
  {
    return vid;
  }

  /**
   * A verseny azonosítóját állítja be
   *
   * @param vid A verseny új azonosítója
   */
  public void setVid ( long vid )
  {
    this.vid = vid;
  }

  /**
   * A verseny nevét adja vissza
   *
   * @return A verseny neve
   */
  public String getNev ()
  {
    return nev;
  }

  /**
   * A verseny nevét állítja be
   *
   * @param nev A verseny új neve
   */
  public void setNev ( String nev )
  {
    this.nev = nev;
  }

  /**
   * A verseny kezdetének dátumát adja vissza
   *
   * @return A verseny kezdetének dátuma
   */
  public Date getKezdeteDatum ()
  {
    return kezdeteDatum;
  }

  /**
   * A verseny kezdetének dátumát állítja be
   *
   * @param kezdeteDatum A verseny kezdetének új dátuma
   */
  public void setKezdeteDatum ( Date kezdeteDatum )
  {
    this.kezdeteDatum = kezdeteDatum;
  }

  /**
   * A verseny végének dátumát adja vissza
   *
   * @return A verseny végének dátuma
   */
  public Date getVegeDatum ()
  {
    return vegeDatum;
  }

  /**
   * A verseny végének dátumát állítja be
   *
   * @param vegeDatum A verseny végének új dátuma
   */
  public void setVegeDatum ( Date vegeDatum )
  {
    this.vegeDatum = vegeDatum;
  }

  /**
   * A verseny alapnyelvének kódját adja vissza
   *
   * @return A verseny alapnyelvének kódja
   */
  public String getAlapNyelv ()
  {
    return alapNyelv;
  }

  /**
   * A verseny alapnyelvének kódját állítja be
   *
   * @param alapNyelv A verseny új alapnyelvének kódját
   */
  public void setAlapNyelv ( String alapNyelv )
  {
    this.alapNyelv = alapNyelv;
  }

  /**
   * A versenyhez tartozó alapértelemzett menetlevél-formulát adja vissza
   *
   * @return A versenyhez tartozó alapértelemzett menetlevél-formula
   */
  public String getMenetlevelformula ()
  {
    return menetlevelformula;
  }

  /**
   * A versenyhez tartozó alapértelemzett menetlevél-formulát állítja be
   *
   * @param menetlevelformula A verseny új menetlevél-formulája
   */
  public void setMenetlevelformula ( String menetlevelformula )
  {
    this.menetlevelformula = menetlevelformula;
  }

  /**
   * Megadja, hogy az etapok összesített eredménylistája frissítendő-e
   *
   * @return Igaz, ha az etapok összesített eredménylistája frissítendő
   */
  public boolean isEredmenyFrissitendoMindenEtap ()
  {
    return eredmenyFrissitendoMindenEtap;
  }

  /**
   * Bállítja, hogy az etapok összeített eredménylistája frissítendő-e
   *
   * @param eredmenyFrissitendoMindenEtap Igaz, ha az eredménylista frissítendő
   */
  public void setEredmenyFrissitendoMindenEtap ( boolean eredmenyFrissitendoMindenEtap )
  {
    this.eredmenyFrissitendoMindenEtap = eredmenyFrissitendoMindenEtap;
  }

  /**
   * Megadja, hogy a szlalomok összesített eredménylistája frissítendő-e
   *
   * @return Igaz, ha a szlalomok összesített eredménylistája frissítendő
   */
  public boolean isEredmenyFrissitendoMindenSzlalom ()
  {
    return eredmenyFrissitendoMindenSzlalom;
  }

  /**
   * Bállítja, hogy a szlalomok összeített eredménylistája frissítendő-e
   *
   * @param eredmenyFrissitendoMindenSzlalom Igaz, ha az eredménylista frissítendő
   */
  public void setEredmenyFrissitendoMindenSzlalom ( boolean eredmenyFrissitendoMindenSzlalom )
  {
    this.eredmenyFrissitendoMindenSzlalom = eredmenyFrissitendoMindenSzlalom;
  }

  /**
   * Megadja, hogy a verseny végeredménye frissítendő-e
   *
   * @return Igaz, ha a verseny végeredménye frissítendő
   */
  public boolean isEredmenyFrissitendoVerseny ()
  {
    return eredmenyFrissitendoVerseny;
  }

  /**
   * Bállítja, hogy a verseny végeredménye frissítendő-e
   *
   * @param eredmenyFrissitendoVerseny Igaz, ha az eredménylista frissítendő
   */
  public void setEredmenyFrissitendoVerseny ( boolean eredmenyFrissitendoVerseny )
  {
    this.eredmenyFrissitendoVerseny = eredmenyFrissitendoVerseny;
  }

  /**
   * Megadja, hogy a versenyhez tartozó csapatverseny eredménylistája frissítendő-e
   *
   * @return Igaz, ha a versenyhez tartozó csapatverseny eredménylistája frissítendő
   */
  public boolean isEredmenyFrissitendoCsapat ()
  {
    return eredmenyFrissitendoCsapat;
  }

  /**
   * Bállítja, hogy a versenyhez tartozó csapatverseny eredménylistája frissítendő-e
   *
   * @param eredmenyFrissitendoCsapat Igaz, ha az eredménylista frissítendő
   */
  public void setEredmenyFrissitendoCsapat ( boolean eredmenyFrissitendoCsapat )
  {
    this.eredmenyFrissitendoCsapat = eredmenyFrissitendoCsapat;
  }

  /**
   * A verseny lezárásának dátumát adja vissza
   *
   * @return A verseny lezárásának dátuma dátuma, vagy <code>null</code>, ha nincs lezárva.
   */
  public Date getLezarva ()
  {
    return lezarva;
  }

  /**
   * A verseny lezárási dátumát állítja be
   *
   * @param lezarva A verseny új lezárási dátuma, vagy <code>null</code>, ha nem kívánjuk lezárni a versenyt
   */
  public void setLezarva ( Date lezarva )
  {
    this.lezarva = lezarva;
  }

  /**
   * Betölti az adatbázisból az összes létező Verseny típusú objektumot.
   *
   * @param session Az adatbázis
   * @return A betöltött objektumok listája
   */
  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, Verseny.class );
  }

  /**
   * Egy új, üres objektumot hoz létre.
   *
   * @return Az új objektum
   */
  public static Verseny newInstance ()
  {
    return new VersenyImpl();
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméter alapján.
   *
   * @param vid A verseny azonosítója
   * @return Az új objektum
   */
  public static Verseny newInstance ( long vid )
  {
    return new VersenyImpl( vid );
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméterek alapján.
   *
   * @param vid A verseny azonosítója
   * @param nev A verseny neve
   * @param kezdeteDatum A verseny kezdetének dátuma
   * @param vegeDatum A verseny végének dátuma
   * @param alapNyelv A verseny alapnyelvének kódja
   * @param menetlevelformula A versenyhez tartozó alapértelemzett menetlevél-formula
   * @param eredmenyFrissitendoMindenEtap Azt jelzi, hogy az etapok összesített eredménylistája frissítendő
   * @param eredmenyFrissitendoMindenSzlalom Azt jelzi, hogy a szlalomok összesített eredménylistája frissítendő
   * @param eredmenyFrissitendoVerseny Azt jelzi, hogy a verseny végeredménye frissítendő
   * @param eredmenyFrissitendoCsapat Azt jelzi, hogy a versenyhez tartozó csapatverseny eredménylistája frissítendő
   * @param lezarva A verseny lezárásának dátuma, vagy <code>null</code>
   * @return Az új objektum
   */
  public static Verseny newInstance ( long vid, String nev, Date kezdeteDatum, Date vegeDatum, String alapNyelv, String menetlevelformula, boolean eredmenyFrissitendoMindenEtap, boolean eredmenyFrissitendoMindenSzlalom, boolean eredmenyFrissitendoVerseny, boolean eredmenyFrissitendoCsapat, Date lezarva )
  {
    return new VersenyImpl( vid, nev, kezdeteDatum, vegeDatum, alapNyelv, menetlevelformula, eredmenyFrissitendoMindenEtap, eredmenyFrissitendoMindenSzlalom, eredmenyFrissitendoVerseny, eredmenyFrissitendoCsapat, lezarva );
  }

}
