package scs.georesults.logic.servlets;

import java.util.Iterator;
import javax.servlet.http.HttpServletResponse;
import scs.javax.io.IOException;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.ServletBase;
import scs.javax.web.WebException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.iotools.*;
import scs.georesults.logic.GeoMessageException;

/**
 * <p>Adatok exportálását végző servlet.</p>
 */
public class AdatokExportServlet extends ServletBase
{

  /**
   * Az exportálási művelet. A kapott paraméterek alapján eldönti,
   * hogy milyen adatot exportál, valamint hogy milyen formátumba.
   * Az exportálandó adat lehet:
   * <ul>
   * <li>A közös, nyelvi adatok: ehhez a {@link scs.georesults.common.iotools.DatabaseKozosExportIterator} osztályt használja</li>
   * <li>Egy verseny adatai: a {@link scs.georesults.common.iotools.DatabaseVersenyExportIterator} osztály segítségével</li>
   * </ul>
   * A kimeneti formátum lehet:
   * <ul>
   * <li>GeoResults 2006 XML, a {@link scs.georesults.common.iotools.DatabaseGeoxmlWriter} osztály alapján</li>
   * <li>Ms Excel 2003 XML, a {@link scs.georesults.common.iotools.DatabaseExmlWriter} osztály alapján</li>
   * <li>SQL INSERTs, a {@link scs.georesults.common.iotools.DatabaseSqlWriter} osztály alapján</li>
   * </ul>
   * E három osztály közös őse a {@link scs.georesults.common.iotools.DatabaseWriterBase} osztály, amelynek <code>doExport</code> műveletével végzi jelen osztály a kimenet elkészítését.
   *
   * @param form A paramétereket tartalmazó objektum
   * @param response HttpServletResponse
   */
  protected void doPostService ( DynamicForm form, HttpServletResponse response ) throws WebException, IOException, RdbException
  {
    if ( !form.has( "mit" ) || !form.has( "mibe" ) )throw new GeoMessageException( "ER_ADAT_NINCS_KIJELOLVE" );
    Iterator dbit;
    if ( form.getString( "mit" ).equals( "kozos" ) ) {
      dbit = new DatabaseKozosExportIterator( GeoDbSession.getCurrentInstance() );
    } else {
      dbit = new DatabaseVersenyExportIterator( GeoDbSession.getCurrentInstance(), form.getLong( "mit" ) );
    }
    String mibe = form.getString( "mibe" );
    DatabaseWriterBase writer;
    if ( mibe.equals( "geoxml" ) ) {
      writer = new DatabaseGeoxmlWriter( dbit );
    } else if ( mibe.equals( "exml" ) ) {
      writer = new DatabaseExmlWriter( dbit );
    } else if ( mibe.equals( "sql" ) ) {
      writer = new DatabaseSqlWriter( dbit );
    } else throw new GeoException( "unknown 'mibe' parameter: '" + mibe + "'" );
    response.setContentType( writer.getContentType() );
    setOutputFileName( "database." + writer.getExtension() );
    writer.doExport( response );
  }

}
