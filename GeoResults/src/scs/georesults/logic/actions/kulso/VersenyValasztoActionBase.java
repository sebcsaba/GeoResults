package scs.georesults.logic.actions.kulso;

import scs.javax.rdb.RdbException;
import scs.javax.web.WebException;
import scs.georesults.common.Constants;
import scs.georesults.logic.actions.KozosActionBase;
import scs.georesults.om.kozos.Nyelv;
import scs.georesults.om.verseny.Verseny;
import scs.georesults.logic.actions.*;

/**
 * <p>Azon osztályok közös ősosztálya, amelyek egy adott versenyt kiválasztanak aktuálisnak.</p>
 */
public abstract class VersenyValasztoActionBase extends KozosActionBase
{

  /**
   * A paraméterben megadott versenyt a munkafolyamatban eltárolja, és betölti
   * a hozzá tartozó szótárat.
   */
  protected void versenySessionbe ( Verseny verseny ) throws WebException, RdbException
  {
    setToSession( Constants.SESSION_KEY_VERSENY, verseny );
    Nyelv nyelv = ( Nyelv ) getFromSession( Constants.SESSION_KEY_NYELV );
    versenySzotarFrissites( nyelv );
  }

}
