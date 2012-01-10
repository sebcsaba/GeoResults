package scs.georesults.common;

/**
 * <p>Két stringből álló, felirat-érték párt reprezentáló osztály.
 * Általában a megjelenítésnél használjuk őket: amikor a felhasználói
 * felületen egy beviteli űrlapon megjelenő felirathoz egy értéket
 * kell párosítani.</p>
 */
public class ValueLabelPair
{

  /**
   * Az értéket tartalmazó string.
   */
  private String value;

  /**
   * A megjelenítendő feliratot tartalmazó string.
   */
  private String label;

  /**
   * Egy új objektumot hoz létre a megadott paraméterekből.
   *
   * @param value Az objektum értéke
   * @param label Az objektum felirata
   */
  public ValueLabelPair ( String value, String label )
  {
    this.value = value;
    this.label = label;
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméterekből.
   * A tárolt érték a paraméterben megadott szám szöveges ábrázolása lesz.
   *
   * @param value Az objektum értéke
   * @param label Az objektum felirata
   */
  public ValueLabelPair ( long value, String label )
  {
    this.value = Long.toString( value );
    this.label = label;
  }

  /**
   * Visszaadja a tárolt értéket.
   *
   * @return Az objektum értéke
   */
  public String getValue ()
  {
    return value;
  }

  /**
   * Visszaadja a tárolt feliratot.
   *
   * @return Az objektum felirata
   */
  public String getLabel ()
  {
    return label;
  }

  /**
   * Beállítja a tárolt feliratot.
   *
   * @param label Az új felirat
   */
  public void setLabel ( String label )
  {
    this.label = label;
  }

  /**
   * Beállítja a tárolt értéket.
   *
   * @param value Az új érték
   */
  public void setValue ( String value )
  {
    this.value = value;
  }

}
