package scs.georesults.logic.beans;

import scs.javax.rdb.StorableEntityBase;

/**
 * <p>A CRUD műveletek során használt adatok csomagolóosztálya. A csomagolás azért szükséges,
 * mert tárolni kell azt is, hogy új objektumról van-e szó, amit a tárolás előtt kezelés
 * céljából létrehozunk.</p>
 */
public class ReszAdatBean
{

  /**
   * A becsomagolt adat mezője
   */
  private StorableEntityBase resz;

  /**
   * Igaz, ha a létrehozás művelet segítésére jött létre az objektum, különben hamis.
   */
  private boolean create;

  /**
   * Létrehozza az osztály egy új példányát a megadott paraméterek alapján.
   *
   * @param resz A becsomagolt objektum
   * @param create Igaz, ha létrehozás művelet céljából jön létre
   */
  public ReszAdatBean ( StorableEntityBase resz, boolean create )
  {
    this.resz = resz;
    this.create = create;
  }

  /**
   * Megadja, hogy létrehozás művelet segítésére jött-e létre az objektum
   *
   * @return Igaz, ha létrehozás művelet hozta létre
   */
  public boolean isCreate ()
  {
    return create;
  }

  /**
   * Beállítja, hogy a létrehozás művelet hozta-e létre az objektumot
   *
   * @param create A tulajdonság új értéke
   */
  public void setCreate ( boolean create )
  {
    this.create = create;
  }

  /**
   * A becsomagolt objektumot adja vissza.
   *
   * @return A becsomagolt objektum
   */
  public StorableEntityBase getResz ()
  {
    return resz;
  }

  /**
   * A becsomagolt objektumot állítja be.
   *
   * @param resz Az új objektum
   */
  public void setResz ( StorableEntityBase resz )
  {
    this.resz = resz;
  }

}
