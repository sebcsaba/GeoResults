package scs.georesults.logic.beans;

import scs.javax.web.RequestBeanBase;
import scs.javax.web.WebSession;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.Constants;
import scs.georesults.om.menetlevel.MenetlevelImpl;
import scs.georesults.om.szlalom.SzlalomFutamImpl;
import scs.georesults.om.verseny.Etap;
import scs.georesults.om.verseny.Szlalom;
import scs.georesults.om.verseny.VersenyImpl;

/**
 * <p>A verseny nyitólapján megjelenő állapotjelzők működését segítő osztály.</p>
 */
public class StatusBean extends RequestBeanBase
{

  /**
   * Azt adja meg, hogy a paraméterben megadott versenyszám adatainak hány százaléka van feldolgozva.
   *
   * @param item A versenyszám (etap, szlalom)
   * @return 0..100 közöttti szám, a feldolgozottság százaléka
   */
  public int getPercentage ( Object item ) throws GeoException
  {
    try {
      VersenyImpl verseny = ( VersenyImpl ) WebSession.justGetHttpSession( pageContext ).getAttribute( Constants.SESSION_KEY_VERSENY );
      int neveztek = verseny.getNevezesek().size();
      if ( neveztek == 0 )return 0;
      int darab = 0;
      if ( item instanceof Etap ) {
        darab = MenetlevelImpl.countAllForEtap( GeoDbSession.getCurrentInstance(), ( Etap ) item );
      } else if ( item instanceof Szlalom ) {
        darab = SzlalomFutamImpl.countAllForSzlalom( GeoDbSession.getCurrentInstance(), ( Szlalom ) item );
      }
      return 100 * darab / neveztek;
    }
    catch ( Exception ex ) {
      throw new GeoException( ex );
    }
  }

}
