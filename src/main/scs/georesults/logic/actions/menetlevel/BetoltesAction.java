package scs.georesults.logic.actions.menetlevel;

import scs.javax.lang.Time;
import scs.javax.rdb.StorableEntityBase;
import scs.georesults.logic.actions.AdatBevitelBetoltesActionBase;
import scs.georesults.om.menetlevel.Menetlevel;
import scs.georesults.logic.actions.*;

/**
 * <p>A menetlevelek felvitele lapon egy adott menetlevél betöltését biztosító szolgáltatás osztálya.</p>
 */
public class BetoltesAction extends AdatBevitelBetoltesActionBase
{

  /**
   * Az adatokat tartalmazó bean osztály munkafolyamat-beli kulcsát adja vissza.
   */
  protected String getAdatokSessionKey ()
  {
    return "menetlevelAdatok";
  }

  /**
   * A futamot tartalmazó versenyrész objektum azonosító mezőjének neve.
   */
  protected String getIdFieldName ()
  {
    return "eid";
  }

  /**
   * Új menetlevél létrehozása előtt beállítja az idő-adatait <code>null</code>
   * helyett 0 időpontot tartalmazó <code>Time</code> objektumra.
   */
  protected void doBeforeCreate ( StorableEntityBase entity )
  {
    Menetlevel ml = ( Menetlevel ) entity;
    ml.setIndulasiIdo( new Time( 0, 0 ) );
    ml.setErkezesiIdo( new Time( 0, 0 ) );
  }

  /**
   * Egy új, üres menetlevél objektumot hoz létre.
   */
  protected StorableEntityBase createFutamEntity ()
  {
    return Menetlevel.newInstance();
  }

}
