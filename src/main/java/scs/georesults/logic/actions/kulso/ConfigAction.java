package scs.georesults.logic.actions.kulso;

import java.util.Properties;
import scs.javax.io.IOException;
import scs.javax.io.Path;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.Config;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.config.ConfigUtils;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.logic.PrimitiveMessageException;
import scs.georesults.logic.beans.kulso.ConfigBean;

/**
 * <p>A program konfigurációját beállító szolgáltatás osztálya.</p>
 */
public class ConfigAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    try {
      Properties props = new Properties();
      props.setProperty( Config.PROP_KEY_DATABASEHOST, form.getString( Config.PROP_KEY_DATABASEHOST ) );
      props.setProperty( Config.PROP_KEY_DATABASENAME, form.getString( Config.PROP_KEY_DATABASENAME ) );
      props.setProperty( Config.PROP_KEY_DATABASEUSER, form.getString( Config.PROP_KEY_DATABASEUSER ) );
      props.setProperty( Config.PROP_KEY_DATABASEPASS, form.getString( Config.PROP_KEY_DATABASEPASS ) );
      props.setProperty( Config.PROP_KEY_BACKUPPATH, form.getString( Config.PROP_KEY_BACKUPPATH ) );
      Config.setConfigurationProperties( props );
      if ( !GeoDbSession.resetAndTestConnection() ) {
        getRequest().setAttribute( "configBean", new ConfigBean( props ) );
        throw new PrimitiveMessageException( "Invalid parameter: cannot connect to database." );
      }
      final Path backupPath = Config.getBackuppath();
      if (!backupPath.isDirectory()) {
		  if ( !backupPath.mkdirs() ) {
	        getRequest().setAttribute( "configBean", new ConfigBean( props ) );
	        throw new PrimitiveMessageException( "Invalid parameter: cannot create backup path." );
	      }
      }
      ConfigUtils.storeProperties( ConfigUtils.CONFIG_DATABASE, props );
      Config.setConfigurationProperties( props );
      return "ok";
    }
    catch ( IOException ex ) {
      throw new GeoException( ex );
    }
  }

}
