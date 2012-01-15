package scs.georesults.logic;

import scs.georesults.GeoException;

/**
 * <p>Ez a kivétel olyan esetet jelez, amikor a kivétel leírása nem érhető el az adatbázisból. Olyankor váltódhat ki, ha nincs megfelelő adatbázis-kapcsolat vagy -struktúra.</p>
 */
public class PrimitiveMessageException extends GeoException
{

  /**
   * Létrehoz egy új kivétel-objektumot.
   *
   * @param message A hiba leírása
   */
  public PrimitiveMessageException ( String message )
  {
    super( message );
  }

}
