package scs.georesults.logic.beans.kulso;

import java.util.Properties;
import scs.javax.io.IOException;
import scs.georesults.Config;
import scs.georesults.config.ConfigUtils;

/**
 * <p>Az adatbázis konfigurációját segítő osztály.</p>
 */
public class ConfigBean
{

  /**
   * A betöltött konfigurációt tartalmazó objektum
   */
  private Properties props;


  /**
   * Egy új, üres objektumot hoz létre.
   */
  public ConfigBean () throws IOException
  {
    props = ConfigUtils.loadProperties( ConfigUtils.CONFIG_DATABASE );
  }

  /**
   * Egy új objektumot hoz létre a megadott paraméter alapján.
   *
   * @param props Konfigurációs paraméterek objektuma
   */
  public ConfigBean ( Properties props )
  {
    this.props = props;
  }

  /**
   * Visszaadja a tárolt konfigurációból a megadott kulcsú értéket.
   *
   * @param key A konfigurációs mező kulcsa
   * @return A kulcshoz tartozó érték
   */
  private String getProp ( String key )
  {
    String result = null;
    if ( props != null ) {
      result = props.getProperty( key );
    }
    return ( result == null ? "" : result );
  }

  /**
   * Megadja az konfigurációban szereplő adatbázis-szerver címét
   *
   * @return Az adatbázis-szerver címe
   */
  public String getHostname ()
  {
    return getProp( Config.PROP_KEY_DATABASEHOST );
  }

  /**
   * Megadja az konfigurációban szereplő adatbázis nevét
   *
   * @return Az adatbázis neve
   */
  public String getDatabase ()
  {
    return getProp( Config.PROP_KEY_DATABASENAME );
  }

  /**
   * Megadja az konfigurációban szereplő adatbázis felhasználójának nevét
   *
   * @return A felhasználó neve
   */
  public String getUsername ()
  {
    return getProp( Config.PROP_KEY_DATABASEUSER );
  }

  /**
   * Megadja az konfigurációban szereplő adatbázis felhasználójának jelszavát
   *
   * @return A felhasználó jelszava
   */
  public String getPassword ()
  {
    return getProp( Config.PROP_KEY_DATABASEPASS );
  }

  public String getBackuppath ()
  {
    return getProp( Config.PROP_KEY_BACKUPPATH );
  }

}
