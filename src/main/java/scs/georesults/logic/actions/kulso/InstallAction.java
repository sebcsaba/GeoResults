package scs.georesults.logic.actions.kulso;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.io.IOException;
import scs.javax.io.InputStream;
import scs.javax.io.readers.LineReader;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.helper.RdbHelper;
import scs.javax.rdb.mapping.fields.RdbMetaStringField;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.GeoException;
import scs.georesults.config.ConfigUtils;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.logic.beans.kulso.InstallBean;
import scs.javax.io.UTF8InputStreamReader;

/**
 * <p>A program adatbázisának telepítését végző szolgáltatás osztálya.</p>
 */
public class InstallAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    try {
      clearDatabase();
      InputStream in = ConfigUtils.openInputStream( "install.sql" );
      LineReader lr = new LineReader( new UTF8InputStreamReader( in ) );
      String str = lr.readLine();
      while ( str != null ) {
        str = str.trim();
        if ( str.length() > 0 && !str.startsWith( "--" ) ) {
          getDb().update( str );
        }
        str = lr.readLine();
      }
      in.close();
      getRequest().setAttribute( "installBean", new InstallBean( true ) );
      return "ok";
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
    catch ( IOException ex ) {
      throw new GeoException( ex );
    }
  }

  /**
   * Kiüríti az adatbázist: törli az összes táblát.
   */
  private void clearDatabase () throws DIIException, WebException, RdbException
  {
    RdbHelper helper = getDb().getHelper();
    List src = getDb().queryAll( helper.createShowTablesStement(), RdbMetaStringField.getMetaCm() );
    for ( int i = 0; i < src.size(); ++i ) {
      RdbMetaStringField.MetaData md = ( RdbMetaStringField.MetaData ) src.get( i );
      getDb().update( helper.createDropTableStatement( md.getData() ) );
    }
  }

}
