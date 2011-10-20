package scs.georesults.logic.beans.kulso;

import java.util.Iterator;
import scs.javax.collections.List;
import scs.javax.rdb.helper.RdbHelper;
import scs.javax.rdb.mapping.ClassMapping;
import scs.javax.rdb.mapping.MappingPool;
import scs.javax.rdb.mapping.fields.RdbMetaStringField;
import scs.georesults.Config;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.config.ConfigUtils;

/**
 * <p>Az adatbázis inicializálását végző műveletet segítő osztály.</p>
 */
public class InstallBean
{

  /**
   * Megadja, hogy a telepítés siekresen befejeződött-e.
   */
  private boolean installSuccessful;

  /**
   * Megadja, hogy az adatbázis tartalmazza-e a szükséges adattáblákat.
   */
  private boolean ugynezkiTelepitveVan;

  /**
   * Megadja, hogy az adatbázis tartalmaz-e adattáblákat.
   */
  private boolean vannakTablak;

  /**
   * Az adatbzáisban található táblák nevének listája.
   */
  private List currentTablak;


  /**
   * Egy új, üres objektumot hoz létre, majd inicializálja azt.
   */
  public InstallBean () throws GeoException
  {
    this.installSuccessful = false;
    init();
  }

  /**
   * Egy új objektumot hoz létre, amely a paraméterben megadott állapotjelző szerint befejezte a telepítést avagy sem.
   *
   * @param installSuccessful Ha igat, a telepítés rendben befejeződött.
   */
  public InstallBean ( boolean installSuccessful ) throws GeoException
  {
    this.installSuccessful = installSuccessful;
    if ( !installSuccessful ) init();
  }

  /**
   * Ha még szükség van a telepítésre, akkor inicializálja az objektumot. Lekérdezi a létező
   * táblák listáját.
   */
  private void init () throws GeoException
  {
    try {
      Config.setConfigurationProperties( ConfigUtils.loadProperties( ConfigUtils.CONFIG_DATABASE ) );
      GeoDbSession db = GeoDbSession.getCurrentInstance();
      RdbHelper helper = db.getHelper();
      List src = db.queryAll( helper.createShowTablesStement(), RdbMetaStringField.getMetaCm() );
      currentTablak = new List( src.size() );
      for ( int i = 0; i < src.size(); ++i ) {
        RdbMetaStringField.MetaData md = ( RdbMetaStringField.MetaData ) src.get( i );
        currentTablak.add( md.getData() );
      }
      vannakTablak = !currentTablak.isEmpty();
      updateUgynezkiTelepitveVan();
    }
    catch ( Exception ex ) {
      throw new GeoException( ex );
    }
  }

  /**
   * Megállapítja, hogy a program által használt adattáblák rendelkezésre
   * állnak-e az adatbázisban. Az eredményt eltárolja a <code>ugynezkiTelepitveVan</code>
   * mezőben.
   */
  public void updateUgynezkiTelepitveVan ()
  {
    for ( Iterator it = MappingPool.getAllClassMappings().iterator(); it.hasNext(); ) {
      ClassMapping cm = ( ClassMapping ) it.next();
      if ( currentTablak.contains( cm.getTableName() ) ) {
        currentTablak.remove( cm.getTableName() );
      } else {
        ugynezkiTelepitveVan = false;
        return;
      }
    }
    ugynezkiTelepitveVan = true;
  }

  public boolean isInstallSuccessful ()
  {
    return installSuccessful;
  }

  public boolean isUgynezkiTelepitveVan ()
  {
    return ugynezkiTelepitveVan;
  }

  public boolean isVannakTablak ()
  {
    return vannakTablak;
  }

}
