package scs.georesults.logic.beans;

import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.georesults.GeoException;
import scs.georesults.om.verseny.VersenyImpl;

/**
 * <p>Az adatfelviteli műveletek (menetlevél és szlalom-futma felvitele illetve módosítása) kezelésére szolgáló osztály.</p>
 */
public class AdatbevitelBean
{

  /**
   * Az aktuális verseny
   */
  private VersenyImpl verseny;

  /**
   * Az adatok betöltéséhez használt segédobjektum.
   */
  private StorableEntityBase betoltes;

  /**
   * Az aktuális futam (menetlevél vagy szlalom-futam)
   */
  private StorableEntityBase futam;

  /**
   * A következő, betöltésre váró rajtszám.
   */
  private int kovetkezoRajtszam;

  /**
   * Igaz, ha a futam még nem létezik az adatbázisban.
   */
  private boolean create;


  /**
   * Egy új, üres objektumot hoz létre.
   */
  public AdatbevitelBean ()
  {}

  /**
   * A megadott versenyhez tartozó objektumot hoz létre.
   *
   * @param verseny Az aktuális verseny
   */
  public AdatbevitelBean ( VersenyImpl verseny ) throws GeoException
  {
    this.verseny = verseny;
    this.kovetkezoRajtszam = 0;
    this.create = false;
  }

  /**
   * Megadja, hogy a kapott rajtszám érvényes-e, vagyis szerepel-e adott rajtszámú elem a nevezési listán.
   *
   * @param rajtszam A vizsgált rajtszám
   * @return Igaz, ha a rajtszám érvényes
   */
  public boolean isErvenyesRajtszam ( int rajtszam ) throws DIIException, GeoException, RdbException
  {
    return verseny.getNevezesek().findItemIndex( "rajtszam", new Integer( rajtszam ) ) > -1;
  }

  /**
   * A betöltséhez használt segédobjektumot adja vissza
   *
   * @return A segédobjektum
   */
  public StorableEntityBase getBetoltes ()
  {
    return betoltes;
  }

  /**
   * A betöltséhez használt segédobjektumot állítja be
   *
   * @param betoltes Az új segédobjektum
   */
  protected void setBetoltes ( StorableEntityBase betoltes )
  {
    this.betoltes = betoltes;
  }

  /**
   * Az aktuális futamot adja vissza
   *
   * @return Az aktuális futam
   */
  public StorableEntityBase getFutam ()
  {
    return futam;
  }

  /**
   * Az aktuális futamot állítja be
   *
   * @param futam Az aktuális futam új értéke
   */
  public void setFutam ( StorableEntityBase futam )
  {
    this.futam = futam;
  }

  /**
   * A következő rajtszám értékét adja vissza
   *
   * @return A következő rajtszám
   */
  public int getKovetkezoRajtszam ()
  {
    return kovetkezoRajtszam;
  }

  /**
   * A következő rajtszám értékét állítja be
   *
   * @param kovetkezoRajtszam A következő rajtszám új értéke
   */
  public void setKovetkezoRajtszam ( int kovetkezoRajtszam )
  {
    this.kovetkezoRajtszam = kovetkezoRajtszam;
  }

  /**
   * Beállítja, hogy a futamot újként, adatbázisban még nem létezőként tekintse
   *
   * @param create Az 'újdonság' jelző új állapota
   */
  public void setCreate ( boolean create )
  {
    this.create = create;
  }

  /**
   * Az aktuális versenyt adja vissza
   *
   * @return VersenyImpl
   */
  public VersenyImpl getVerseny ()
  {
    return verseny;
  }

  /**
   * Megadja, hogy az aktuális futam új-e, vagyis az adatbázisban még nem létezőként kell-e kezelni.
   *
   * @return Igaz, ha az aktuális futam még új.
   */
  public boolean isCreate ()
  {
    return create;
  }

}
