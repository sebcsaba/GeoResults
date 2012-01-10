package scs.georesults.common.iotools;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.io.DataWriter;
import scs.javax.io.IOException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.mapping.ClassMapping;
import scs.javax.rdb.sql.SqlFactory;

/**
 * <p>Olyan, SQL-t használó relációs adatbázis folyamatot reprezentáló osztály, amely
 * egy adott kimeneti adatfolyamra kiírja az objektumokat létrehozó utasításokat.</p>
 * <p>Az osztály adatelérési lekérdezéseket nem hajt végre, csak adatot módosítókat.</p>
 */
public class SqlWriterSession extends RdbSession
{

  private DataWriter writer;

  public SqlWriterSession ( DataWriter writer ) throws DIIException, RdbException
  {
    super( new SqlFactory() );
    this.writer = writer;
  }

  protected long updateWork ( Object update ) throws RdbException
  {
    try {
      writer.println( update );
      return 0;
    }
    catch ( IOException ex ) {
      throw new RdbException( ex.toString() );
    }
  }

  protected Object queryFirstWork ( Object query, ClassMapping cm, Object entity ) throws RdbException
  {
    throw new UnsupportedOperationException();
  }

  protected List queryAllWork ( Object query, ClassMapping cm ) throws RdbException
  {
    throw new UnsupportedOperationException();
  }

}
