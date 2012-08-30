package scs.georesults;

import java.util.Properties;
import scs.javax.io.Path;

/**
 * <p>Program-szintű konfigurációs beállításokat tartalmaz</p>
 */
public class Config
{

  /**
   * Biztosítja, hogy az osztály ne legyen példányosítható.
   */
  private Config ()
  {}


  /**
   * Ennyi ms-ot vár az automatikus backup két mentés között.
   */
  public static final long BACKUP_TIME = 10 * 60 * 1000;

  /**
   * Alapértelmezésben a részletes képernyők ennyi mezővel jelennek meg. A 'Megnövelés' művelet a mezők számát szintén ennyivel növeli.
   */
  public static final int ETAP_RESZ_ADAT_LISTA_HOSSZ = 20;

  /**
   * A versenyhez tartozó szótár elrendezése az ablak alakja miatt több oszlopban történik. Ez a paraméter adja meg az oszlopok számát.
   */
  public static final int VERSENYSZOTAR_OSZLOPOK_SZAMA = 3;


  /**
   * Ha igaz, a program minden SQL utasítást kiír a szabványos kimenetre.
   */
  public static final boolean DEBUG_SQLLOG = false;

  /**
   * Ha igaz, a program minden megjelenítendő szótári szó azonosítóját kiírja a kimenetre, amelyet nem talál az adatbázisban.
   */
  public static final boolean DEBUG_SZOTAR = false;

  /**
   * Ha igaz, a program minden kérés (request) előtt és után hibakeresési információt nyomtat a kimenetre a munkafolyamatról (session).
   */
  public static final boolean DEBUG_SESSION = false;


  /**
   * Az adatbázis típusa. A JDBC kapcsolódásnál használja fel.
   */
  public static final String DATABASE_TYPE = "mysql";
  
  /**
   * Az adatbázis driver-e, amelyet be kell tölteni a kapcsolódás előtt
   */
  public static final Class DATABASE_DRIVER = com.mysql.jdbc.Driver.class;

  /**
   * Az a kulcs, amely az adatbázishoz kapcsolódás adatai között az adatbázis-szerver nevét azonosítja.
   */
  public static final String PROP_KEY_DATABASEHOST = "hostname";

  /**
   * Az a kulcs, amely az adatbázishoz kapcsolódás adatai között az adatbázis nevét azonosítja.
   */
  public static final String PROP_KEY_DATABASENAME = "database";

  /**
   * Az a kulcs, amely az adatbázishoz kapcsolódás adatai között az adatbázis felhasználójának nevét azonosítja.
   */
  public static final String PROP_KEY_DATABASEUSER = "username";

  /**
   * Az a kulcs, amely az adatbázishoz kapcsolódás adatai között az adatbázis felhasználójának jelszavát azonosítja.
   */
  public static final String PROP_KEY_DATABASEPASS = "password";

  /**
   * Az a kulcs, amely a biztonsági mentések könyvtárát azonosítja.
   */
  public static final String PROP_KEY_BACKUPPATH = "backuppath";

  /**
   * Az adatbázishoz kapcsolódás adatait tartalmazza.
   */
  private static Properties configProps;


  /**
   * A kapcsolódáshoz szükséges JDBC kapcsolódási stringet álítja elő.
   *
   * @return A kapcsolódási string
   */
  public static String getJdbcConnectionString ()
  {
    StringBuffer sb = new StringBuffer();
    sb.append( "jdbc:" ).append( DATABASE_TYPE );
    sb.append( "://" ).append( configProps.getProperty( PROP_KEY_DATABASEHOST ) );
    sb.append( "/" ).append( configProps.getProperty( PROP_KEY_DATABASENAME ) );
    sb.append( "?user=" ).append( configProps.getProperty( PROP_KEY_DATABASEUSER ) );
    sb.append( "&password=" ).append( configProps.getProperty( PROP_KEY_DATABASEPASS ) );
    return sb.toString();
  }

  public static Path getBackuppath()
  {
    return new Path( configProps.getProperty( PROP_KEY_BACKUPPATH ) );
  }

  /**
   * A paraméterben megadott kapcsolódási paramétereket eltárolja a 'databaseProps' mezőben.
   *
   * @param props Az új kapcsolódási paraméterek
   */
  public static void setConfigurationProperties ( Properties props )
  {
    configProps = props;
  }

}
