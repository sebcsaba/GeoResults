package scs.georesults.logic.actions;

import java.util.Properties;
import scs.javax.io.IOException;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.Config;
import scs.georesults.GeoException;
import scs.georesults.common.Constants;
import scs.georesults.common.szotar.GlobalSzotar;
import scs.georesults.common.szotar.NyelvUtils;
import scs.georesults.config.ConfigUtils;
import scs.georesults.om.kozos.Nyelv;
import scs.georesults.BackupThread;

/**
 * <p>A munkafolyamatot inicializáló szolgáltatás osztálya.</p>
 * <p>Betölti az adatbázis-kapcsolathoz szükséges paramétereket és felépíti
 * az adatbázis-kapcsolatot. Ezután inicializálja a munkamenetet, betölti a
 * böngésző beállításai alapján kiválaszott nyelvet és a hozzá
 * tartozó szótárt.</p>
 */
public class InitAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    try {
      createSession();
      Properties props = ConfigUtils.loadProperties( ConfigUtils.CONFIG_DATABASE );
      if ( props == null ) {
        return "install";
      }
      Config.setConfigurationProperties( props );
      Nyelv nyelv = NyelvUtils.getAlapNyelv( getRequest() );
      setToSession( Constants.SESSION_KEY_NYELV, nyelv );
      GlobalSzotar globalSzotar = new GlobalSzotar( getDb(), nyelv );
      setToSession( Constants.SESSION_KEY_GLOBAL_SZOTAR, globalSzotar );
      BackupThread.getCurrentInstance();
      return "versenyValasztas";
    }
    catch ( IOException ex ) {
      throw new GeoException( ex );
    }
  }

}
