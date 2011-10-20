package scs.georesults.logic.actions.szlalom;

import scs.javax.rdb.StorableEntityBase;
import scs.georesults.logic.actions.AdatBevitelBetoltesActionBase;
import scs.georesults.om.szlalom.SzlalomFutam;
import scs.georesults.logic.actions.*;

/**
 * <p>A szlalom-futamok felvitele lapon egy adott futam betöltését biztosító szolgáltatás osztálya.</p>
 */
public class BetoltesAction extends AdatBevitelBetoltesActionBase
{

  /**
   * Az adatokat tartalmazó bean osztály munkafolyamat-beli kulcsát adja vissza.
   */
  protected String getAdatokSessionKey ()
  {
    return "szlalomAdatok";
  }

  /**
   * A futamot tartalmazó versenyrész objektum azonosító mezőjének neve.
   */
  protected String getIdFieldName ()
  {
    return "szlid";
  }

  /**
   * Egy új, üres szlalom-futam objektumot hoz létre.
   */
  protected StorableEntityBase createFutamEntity ()
  {
    return SzlalomFutam.newInstance();
  }

}
