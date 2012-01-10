package scs.georesults.common.iotools;

import scs.javax.collections.SelfEnlargerQueueIterator;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.georesults.GeoException;
import scs.georesults.om.kozos.Nyelv;
import scs.georesults.om.kozos.Orszag;
import scs.georesults.om.kozos.SzotarBejegyzes;

/**
 * <p>Olyan önmagát folyamatosan kiterjesztő sort felhasználó iterátor, amely a megadott
 * adatbázis verseny-független közös adatait tartalmazza (nyelvek, országok, szótárak).</p>
 */
public class DatabaseKozosExportIterator extends SelfEnlargerQueueIterator
{

  private RdbSession db;

  public DatabaseKozosExportIterator ( RdbSession db ) throws GeoException, RdbException
  {
    this.db = db;
    addAll( Orszag.loadAll( db ) );
    addAll( Nyelv.loadAll( db ) );
  }

  protected void dequeElement ( Object item )
  {
    try {
      if ( item instanceof Nyelv ) {
        addAll( SzotarBejegyzes.loadAllForNyelv( db, ( Nyelv ) item ) );
      }
    }
    catch ( RdbException ex ) {
      throw new Error( ex );
    }
  }

}
