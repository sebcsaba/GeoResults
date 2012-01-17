package scs.georesults.om.verseny;

import scs.javax.collections.List;
import scs.javax.lang.Date;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.om.alap.*;
import scs.georesults.om.nevezes.CsapatNevezes;
import scs.georesults.om.nevezes.Nevezes;

/**
 * <p>A {@link Verseny} osztály implementációs leszármazott osztálya.</p>
 * <p>Az osztály adatmezői a versenhez tartozó adott típusú objektumokat tárolják.
 * Ezeket a listákat a megfelelő <code>get*</code> metódusokon keresztül tudjuk elkérni.
 * Ha az elkérés pillanatában a megfelelő adatmező <code>null</code>, akkor a megfelelő
 * <code>update*</code> metódus hívódik meg. Ez betölti az adatbázisból a lista tartalmát,
 * és beállítja az adatmezőt.</p>
 */
public class VersenyImpl extends Verseny
{

  /**
   * A versenyhez tartozó darabszám-függő feladattípusokat tartalmazó lista.
   */
  private List darabFuggoFeladatTipusok;

  /**
   * A versenyhez tartozó sorrend-függő feladattípusokat tartalmazó lista.
   */
  private List sorrendFuggoFeladatTipusok;

  /**
   * A versenyhez tartozó késési zónákat tartalmazó lista.
   */
  private List kesesiZonak;

  /**
   * A versenyhez tartozó büntetés-típusokat tartalmazó lista.
   */
  private List buntetesTipusok;

  /**
   * A versenyhez tartozó szlalom-feladattípusokat tartalmazó lista.
   */
  private List szlalomFeladatok;

  /**
   * A versenyhez tartozó szlalom-kategóriákat tartalmazó lista.
   */
  private List szlalomKategoriak;

  /**
   * A versenyhez tartozó autó-típusokat tartalmazó lista.
   */
  private List autoTipusok;

  /**
   * A versenyhez tartozó szlalomokat tartalmazó lista.
   */
  private List szlalomok;

  /**
   * A versenyhez tartozó etapokat tartalmazó lista.
   */
  private List etapok;

  /**
   * A versenyhez tartozó szakaszokat tartalmazó lista.
   */
  private List szakaszok;

  /**
   * A versenyhez tartozó nevezéseket tartalmazó lista.
   */
  private List nevezesek;

  /**
   * A versenyhez tartozó csapatnevezéseket tartalmazó lista.
   */
  private List csapatNevezesek;


  /**
   * Egy új, üres objektumot hoz létre.
   */
  public VersenyImpl ()
  {
    super();
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméter alapján.
   *
   * @param vid A verseny azonosítója
   */
  public VersenyImpl ( long vid )
  {
    super( vid );
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
  public VersenyImpl ( long vid, String nev, Date kezdeteDatum, Date vegeDatum, String alapNyelv, String menetlevelformula, boolean eredmenyFrissitendoMindenEtap, boolean eredmenyFrissitendoMindenSzlalom, boolean eredmenyFrissitendoVerseny, boolean eredmenyFrissitendoCsapat, Date lezarva )
  {
    super( vid, nev, kezdeteDatum, vegeDatum, alapNyelv, menetlevelformula, eredmenyFrissitendoMindenEtap, eredmenyFrissitendoMindenSzlalom, eredmenyFrissitendoVerseny, eredmenyFrissitendoCsapat, lezarva );
  }

  /**
   * A verseny kezdetét és végét jelző dátumokat egy kényelmesen olvasható
   * stringgé alakítja. Ha a két dátum bizonyos szintig (év, hónap, nap) megegyezik,
   * akkor az nem fokg kétszer szerepelni az eredményben.
   *
   * @return A dátumokat tartalmazó string
   */
  public String getDatumok ()
  {
    Date kezd = getKezdeteDatum();
    Date vege = getVegeDatum();
    String kezdStr = kezd.getSimpleFace();
    String vegeStr = vege.getSimpleFace();
    if ( kezd.getYear() != vege.getYear() )
      return kezdStr + "-" + vegeStr;
    else if ( kezd.getMonth() != vege.getMonth() )
      return kezdStr + "-" + vegeStr.substring( 5 );
    else if ( kezd.getDay() != vege.getDay() )
      return kezdStr + "-" + vegeStr.substring( 8 );
    else return kezdStr;
  }

  /**
   * A program által használt adatbázist adja vissza
   *
   * @return A program adatbázisa
   */
  private GeoDbSession getDb () throws GeoException
  {
    return GeoDbSession.getCurrentInstance();
  }

  /**
   * Frissíti a darabszám-függő feladattípusok listáját
   */
  public void updateDarabFuggoFeladatTipusok () throws GeoException, RdbException
  {
    darabFuggoFeladatTipusok = DarabFuggoFeladatTipus.loadAllForVerseny( getDb(), this );
  }

  /**
   * Visszaadja a darabszám-függő feladattípusok listáját
   *
   * @return A versenyhez tartozó megfelelő típusú elemek listája
   */
  public List getDarabFuggoFeladatTipusok () throws GeoException, RdbException
  {
    if ( darabFuggoFeladatTipusok == null ) updateDarabFuggoFeladatTipusok();
    return darabFuggoFeladatTipusok;
  }

  /**
   * Frissíti a sorrend-függő feladattípusok listáját
   */
  public void updateSorrendFuggoFeladatTipusok () throws GeoException, RdbException
  {
    sorrendFuggoFeladatTipusok = SorrendFuggoFeladatTipus.loadAllForVerseny( getDb(), this );
  }

  /**
   * Visszaadja a sorrend-függő feladattípusok listáját
   *
   * @return A versenyhez tartozó megfelelő típusú elemek listája
   */
  public List getSorrendFuggoFeladatTipusok () throws GeoException, RdbException
  {
    if ( sorrendFuggoFeladatTipusok == null ) updateSorrendFuggoFeladatTipusok();
    return sorrendFuggoFeladatTipusok;
  }

  /**
   * Frissíti a késési zónák listáját
   */
  public void updateKesesiZonak () throws GeoException, RdbException
  {
    kesesiZonak = KesesiZona.loadAllForVerseny( getDb(), this );
  }

  /**
   * Visszaadja a késési zónák listáját
   *
   * @return A versenyhez tartozó megfelelő típusú elemek listája
   */
  public List getKesesiZonak () throws GeoException, RdbException
  {
    if ( kesesiZonak == null ) updateKesesiZonak();
    return kesesiZonak;
  }

  /**
   * Frissíti a büntetés-típusok listáját
   */
  public void updateBuntetesTipusok () throws GeoException, RdbException
  {
    buntetesTipusok = BuntetesTipus.loadAllForVerseny( getDb(), this );
  }

  /**
   * Visszaadja a büntetés-típusok listáját
   *
   * @return A versenyhez tartozó megfelelő típusú elemek listája
   */
  public List getBuntetesTipusok () throws GeoException, RdbException
  {
    if ( buntetesTipusok == null ) updateBuntetesTipusok();
    return buntetesTipusok;
  }

  /**
   * Frissíti a szlalom-feladattípusok listáját
   */
  public void updateSzlalomFeladatok () throws GeoException, RdbException
  {
    szlalomFeladatok = SzlalomFeladat.loadAllForVerseny( getDb(), this );
  }

  /**
   * Visszaadja a szlalom-feladattípusok listáját
   *
   * @return A versenyhez tartozó megfelelő típusú elemek listája
   */
  public List getSzlalomFeladatok () throws GeoException, RdbException
  {
    if ( szlalomFeladatok == null ) updateSzlalomFeladatok();
    return szlalomFeladatok;
  }

  /**
   * Frissíti a szlalom-kategóriák listáját
   */
  public void updateSzlalomKategoriak () throws GeoException, RdbException
  {
    szlalomKategoriak = SzlalomKategoria.loadAllForVerseny( getDb(), this );
  }

  /**
   * Visszaadja a szlalom-kategóriák listáját
   *
   * @return A versenyhez tartozó megfelelő típusú elemek listája
   */
  public List getSzlalomKategoriak () throws GeoException, RdbException
  {
    if ( szlalomKategoriak == null ) updateSzlalomKategoriak();
    return szlalomKategoriak;
  }

  /**
   * Frissíti az autó-típusok listáját
   */
  public void updateAutoTipusok () throws GeoException, RdbException
  {
    autoTipusok = AutoTipus.loadAllForVerseny( getDb(), this );
  }

  /**
   * Visszaadja az autó-típusok listáját
   *
   * @return A versenyhez tartozó megfelelő típusú elemek listája
   */
  public List getAutoTipusok () throws GeoException, RdbException
  {
    if ( autoTipusok == null ) updateAutoTipusok();
    return autoTipusok;
  }

  /**
   * Frissíti a szlalomok listáját
   */
  public void updateSzlalomok () throws GeoException, RdbException
  {
    szlalomok = Szlalom.loadAllForVerseny( getDb(), this );
  }

  /**
   * Visszaadja a szlalomok listáját
   *
   * @return A versenyhez tartozó megfelelő típusú elemek listája
   */
  public List getSzlalomok () throws GeoException, RdbException
  {
    if ( szlalomok == null ) updateSzlalomok();
    return szlalomok;
  }

  /**
   * Frissíti az etapok listáját
   */
  public void updateEtapok () throws GeoException, RdbException
  {
    etapok = Etap.loadAllForVerseny( getDb(), this );
  }

  /**
   * Visszaadja az etapok listáját
   *
   * @return A versenyhez tartozó megfelelő típusú elemek listája
   */
  public List getEtapok () throws GeoException, RdbException
  {
    if ( etapok == null ) updateEtapok();
    return etapok;
  }

  /**
   * Frissíti a szakaszok listáját
   */
  public void updateSzakaszok () throws GeoException, RdbException
  {
    szakaszok = Szakasz.loadAllForVerseny( getDb(), this );
  }

  /**
   * Visszaadja a szakaszok listáját
   *
   * @return A versenyhez tartozó megfelelő típusú elemek listája
   */
  public List getSzakaszok () throws GeoException, RdbException
  {
    if ( szakaszok == null ) updateSzakaszok();
    return szakaszok;
  }

  /**
   * Frissíti a nevezések listáját
   */
  public void updateNevezesek () throws GeoException, RdbException
  {
    nevezesek = Nevezes.loadAllForVerseny( getDb(), this );
  }

  /**
   * Visszaadja a nevezések listáját
   *
   * @return A versenyhez tartozó megfelelő típusú elemek listája
   */
  public List getNevezesek () throws GeoException, RdbException
  {
    if ( nevezesek == null ) updateNevezesek();
    return nevezesek;
  }

  /**
   * Frissíti a csapatnevezések listáját
   */
  public void updateCsapatNevezesek () throws GeoException, RdbException
  {
    csapatNevezesek = CsapatNevezes.loadAllForVerseny( getDb(), this );
  }

  /**
   * Visszaadja a csapatnevezések listáját
   *
   * @return A versenyhez tartozó megfelelő típusú elemek listája
   */
  public List getCsapatNevezesek () throws GeoException, RdbException
  {
    if ( csapatNevezesek == null ) updateCsapatNevezesek();
    return csapatNevezesek;
  }

  /**
   * Megadja, hgoy a verseny adatai le vannak-e zárva
   *
   * @return Igaz, ha a verseny le van zárva
   */
  public boolean isLeVanZarva ()
  {
    return getLezarva() != null;
  }

  /**
   * Megadja, hogy a versenyhez tartozó négy eredménylista közül bármelyik frissítendő-e
   *
   * @return Igaz, ha valamelyik eredménylista frissítendő
   */
  public boolean isBarmiAmiNemFrissitendo ()
  {
    return ( !isEredmenyFrissitendoMindenEtap() || !isEredmenyFrissitendoMindenSzlalom() ||
             !isEredmenyFrissitendoVerseny() || !isEredmenyFrissitendoCsapat() );
  }

  /**
   * Beállítja mind a négy eredménylistát frissítendőre.
   */
  public void setMindenFrissitendo ()
  {
    setEredmenyFrissitendoMindenEtap( true );
    setEredmenyFrissitendoMindenSzlalom( true );
    setEredmenyFrissitendoVerseny( true );
    setEredmenyFrissitendoCsapat( true );
  }

  /**
   * Frissíti az objektumot a kapott adatbázisból. Az eltárolt listákat <code>null</code>-ra
   * állítja, hogy a következő hivatkozásnál azokat is automatikusan újra betöltse.
   *
   * @param session Az adatbázis objektum
   */
  public void refresh ( RdbSession session ) throws RdbException
  {
    read( session );
    darabFuggoFeladatTipusok = null;
    sorrendFuggoFeladatTipusok = null;
    kesesiZonak = null;
    buntetesTipusok = null;
    szlalomFeladatok = null;
    szlalomKategoriak = null;
    autoTipusok = null;
    szlalomok = null;
    etapok = null;
    szakaszok = null;
    nevezesek = null;
    csapatNevezesek = null;
  }

}
