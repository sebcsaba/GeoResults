package scs.georesults.config;

import java.util.Properties;
import scs.javax.dii.ClassUtils;
import scs.javax.io.*;
import scs.javax.io.wrappers.NewInputStreamToOld;
import scs.javax.io.wrappers.NewOutputStreamToOld;

/**
 * <p>A konfigur�ci�s f�jlokat kezel� oszt�ly.</p>
 */
public class ConfigUtils
{

  /**
   * A konfigur�ci�s f�jlok el�r�si �tja
   */
  public static final String CONFIG_PATH_NAME = "scs/georesults/config/";

  /**
   * Az adatb�zis-el�r�s konfigur�ci�s f�jlja
   */
  public static final String CONFIG_DATABASE = "configuration.properties";

  /**
   * A sablonokat defini�l� konfigur�ci�s f�jl
   */
  public static final String CONFIG_SABLONOK = "sablonok.properties";

  /**
   * A telep�t�si scriptet tartalmaz� konfigur�ci�s f�jl
   */
  public static final String CONFIG_INSTALL_SCRIPT = "install.sql";


  /**
   * Biztos�tja, hogy az oszt�ly ne legyen p�ld�nyos�that�.
   */
  private ConfigUtils ()
  {}

  /**
   * A param�terben megadott konfigur�ci�s f�jlra mutat� el�r�si utat adja meg.
   *
   * @param name A konfigur�ci�s f�jl neve
   * @return A konfigur�ci�s f�jl el�r�si �tja
   */
  public static Path getConfigFilePath ( String name )
  {
    String configPath = ClassUtils.getResourcePath( ConfigUtils.class.getClassLoader(), CONFIG_PATH_NAME + "ConfigUtils.class" ).toString().replaceAll( "\\\\", "/" );
    configPath = configPath.substring( 0, configPath.lastIndexOf( "/" ) + 1 ) + name;
    return new Path( configPath );
  }

  /**
   * Bet�lti a megadott konfigur�ci�s f�jl tartalm�t egy Properties objektumba.
   *
   * @param name A konfigur�ci�s f�jl neve
   * @return A f�jl tartalm�t tartalmaz� objektum
   */
  public static Properties loadProperties ( String name ) throws IOException
  {
    try {
      Path path = getConfigFilePath( name );
      if ( path.exists() ) {
        InputStream config = new FileInputStream( path );
        Properties props = new Properties();
        props.load( new NewInputStreamToOld( config ) );
        config.close();
        return props;
      } else return null;
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  /**
   * Elt�rolja a megadott konfigur�ci�s f�jlban a <tt>props</tt> objektum tartalm�t.
   *
   * @param name A konfigur�ci�s f�jl neve
   * @param props A t�roland� adat
   */
  public static void storeProperties ( String name, Properties props ) throws IOException
  {
    try {
      Path path = getConfigFilePath( name );
      OutputStream config = new FileOutputStream( path );
      props.store( new NewOutputStreamToOld( config ), " GeoResults 2006 database config file - (c) SebCsaba" );
      config.close();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  /**
   * A megadott konfigur�ci�s f�jlt mint bemeneti adatfolyamot nyitja meg.
   *
   * @param name A konfigur�ci�s f�jl neve
   * @return A bemeneti adatfolyam
   * @throws FileNotFoundException Ha nem l�tezik a f�jl
   */
  public static InputStream openInputStream ( String name ) throws FileNotFoundException
  {
    return new FileInputStream( getConfigFilePath( name ) );
  }

}
