package scs.georesults;

import scs.javax.dii.ClassUtils;
import scs.javax.dii.DIIException;
import scs.javax.io.IOException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.mapping.MappingPool;
import scs.javax.rdb.mapping.fields.RdbMetaStringField;
import scs.javax.rdb.sql.SqlSession;
import scs.javax.xml.XmlDomException;

/**
 * <p>A programban használt adatbázis-kapcsolat osztálya.</p>
 */
public class GeoDbSession extends SqlSession
{

  /**
   * Az egyetlen létrehozott adatbázis-kapcsolat objektum.
   */
  private static GeoDbSession instance = null;

  /**
   * Az adatbázis-kapcsolatot hozza létre a Config osztály alapján. Betölti az osztály-tábla leképezéseket.
   *
   * @throws RdbException Ha az adatbázis-kezeléskor lép fel kivétel
   * @throws XmlDomException Ha a betöltött XML fájlokban hiba van
   * @throws IOException Ha a fájlok betöltése nem sikerült
   * @throws DIIException Ha a dinamikus kezelés során lép fel hiba
   */
  private GeoDbSession () throws RdbException, XmlDomException, IOException, DIIException
  {
    super( Config.DATABASE_DRIVER, Config.getJdbcConnectionString() );
    setLog( Config.DEBUG_SQLLOG );
    loadMappings();
  }

  /**
   * Visszaadja a kapcsolat-objektumot. Ha az még nem létezik, akkor létrehozza.
   *
   * @return A kapcsolat-objektum
   * @throws Ha nem sikerült a kapcsolatot létrehozni
   */
  public static synchronized GeoDbSession getCurrentInstance () throws GeoException
  {
    try {
      if ( instance == null ) {
        instance = new GeoDbSession();
      }
      return instance;
    }
    catch ( Exception ex ) {
      throw new GeoException( ex );
    }
  }

  /**
   * Létrehozza a kapcsolatot, és ellenőrzi azt. A kapcsolat objektumát eltárolja az instance mezőben.
   *
   * @return Igaz, ha a kapcsolat felépítése sikeres.
   */
  public static boolean resetAndTestConnection ()
  {
    try {
      instance = new GeoDbSession();
      instance.queryAll( "show tables", RdbMetaStringField.getMetaCm() );
    }
    catch ( Exception ex ) {
      instance = null;
      return false;
    }
    return true;
  }

  /**
   * A programban szereplő osztály-tábla leképezéseket tartalmazó XML fájlok listája.
   */
  public static final String[] mappingXmls = new String[] {
                                             "scs/georesults/om/kozos/nyelv.xml",
                                             "scs/georesults/om/kozos/orszag.xml",
                                             "scs/georesults/om/kozos/szotarbejegyzes.xml",

                                             "scs/georesults/om/verseny/verseny.xml",
                                             "scs/georesults/om/verseny/szakasz.xml",
                                             "scs/georesults/om/verseny/szlalom.xml",
                                             "scs/georesults/om/verseny/etap.xml",

                                             "scs/georesults/om/alap/szlalomkategoria.xml",
                                             "scs/georesults/om/alap/autotipus.xml",
                                             "scs/georesults/om/alap/buntetestipus.xml",
                                             "scs/georesults/om/alap/kesesizona.xml",
                                             "scs/georesults/om/alap/szlalomfeladat.xml",
                                             "scs/georesults/om/alap/versenyszotarbejegyzes.xml",
                                             "scs/georesults/om/alap/ellenorzopont.xml",
                                             "scs/georesults/om/alap/darabfuggofeladattipus.xml",
                                             "scs/georesults/om/alap/sorrendfuggofeladattipus.xml",

                                             "scs/georesults/om/nevezes/nevezes.xml",
                                             "scs/georesults/om/nevezes/csapatnevezes.xml",

                                             "scs/georesults/om/etap/darabfuggoetapfeladatetalonbejegyzes.xml",
                                             "scs/georesults/om/etap/darabfuggoetapfeladat.xml",
                                             "scs/georesults/om/etap/sorrendfuggoetapfeladatetalonbejegyzes.xml",
                                             "scs/georesults/om/etap/sorrendfuggoetapfeladat.xml",

                                             "scs/georesults/om/szlalom/szlalombejegyzes.xml",
                                             "scs/georesults/om/szlalom/szlalomfutam.xml",

                                             "scs/georesults/om/menetlevel/buntetes.xml",
                                             "scs/georesults/om/menetlevel/darabfuggomenetlevelbejegyzes.xml",
                                             "scs/georesults/om/menetlevel/darabfuggofeladatmegoldas.xml",
                                             "scs/georesults/om/menetlevel/sorrendfuggomenetlevelbejegyzes.xml",
                                             "scs/georesults/om/menetlevel/sorrendfuggofeladatmegoldas.xml",
                                             "scs/georesults/om/menetlevel/menetlevel.xml",

                                             "scs/georesults/om/eredmeny/csapateredmeny.xml",
                                             "scs/georesults/om/eredmeny/szlalomeredmeny.xml",
                                             "scs/georesults/om/eredmeny/szlalomosszesitetteredmeny.xml",
                                             "scs/georesults/om/eredmeny/versenyeredmeny.xml",
                                             "scs/georesults/om/eredmeny/mindenetaperedmeny.xml",
                                             "scs/georesults/om/eredmeny/mindenszlalomeredmeny.xml",
                                             "scs/georesults/om/eredmeny/szakaszeredmeny.xml",
                                             "scs/georesults/om/eredmeny/sorrendfuggofeladatkiertekeles.xml",
                                             "scs/georesults/om/eredmeny/sorrendfuggofeladateredmeny.xml",
                                             "scs/georesults/om/eredmeny/darabfuggofeladateredmeny.xml",
                                             "scs/georesults/om/eredmeny/etaperedmeny.xml",
  };

  /**
   * Betölti az osztály-tábla leképezéseket.
   *
   * @throws RdbException Ha az adatbázis-kezeléskor lép fel kivétel
   * @throws XmlDomException Ha a betöltött XML fájlokban hiba van
   * @throws IOException Ha a fájlok betöltése nem sikerült
   * @throws DIIException Ha a dinamikus kezelés során lép fel hiba
   */
  private void loadMappings () throws DIIException, IOException, XmlDomException, RdbException
  {
    ClassLoader loader = getClass().getClassLoader();
    for ( int i = 0; i < mappingXmls.length; ++i ) {
      MappingPool.process( ClassUtils.getResourcePath( loader, mappingXmls[i] ) );
    }
  }

}
