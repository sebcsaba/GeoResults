package scs.georesults.common.iotools;

import java.util.Iterator;
import scs.javax.dii.DIIException;
import scs.javax.io.IOException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;

/**
 * <p>SQL formátumú kimenetet készítő osztály.</p>
 * <p>A kimenet <code>INSERT</code> utasítások formájában fog megjelenni, amelyeket
 * végrehajtva egy adatbázison az exportált objektumok létrejönnek.</p>
 * <p>A kimenet elkészítéséhez felhasznlája a {@link SqlWriterSession} osztályt.</p>
 */
public class DatabaseSqlWriter extends DatabaseWriterBase
{

  public DatabaseSqlWriter ( Iterator dbiter ) throws IOException
  {
    super( dbiter );
  }

  protected void writeHeaderCommentLine () throws IOException
  {
    out.println( "-- " + HEADER );
  }

  protected void doPrint () throws IOException
  {
    try {
      SqlWriterSession writerSession = new SqlWriterSession( out );
      while ( dbiter.hasNext() ) {
        StorableEntityBase entity = ( StorableEntityBase ) dbiter.next();
        entity.create( writerSession );
      }
      out.close();
    }
    catch ( DIIException ex ) {
      throw new IOException( ex );
    }
    catch ( RdbException ex ) {
      throw new IOException( ex );
    }
  }

  public String getContentType ()
  {
    return "application/octet-stream";
  }

  public String getExtension ()
  {
    return "sql";
  }

}
