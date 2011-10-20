package scs.georesults.common.iotools;

import java.util.Iterator;
import scs.javax.dii.DIIException;
import scs.javax.io.IOException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.rdbxml.DatabaseTag;
import scs.javax.rdb.rdbxml.RdbXmlMappingNamespace;
import scs.javax.xml.XmlDomException;

/**
 * <p>GeoResults 2006 XML formátumú kimenetet készítő osztály.</p>
 * <p>A kimenet elkészítéséhez felhasználja a {@link scs.javax.rdb.rdbxml}
 * csomag osztályait.</p>
 */
public class DatabaseGeoxmlWriter extends XmlWriterBase
{

  public DatabaseGeoxmlWriter ( Iterator dbiter ) throws IOException
  {
    super( dbiter );
  }

  protected void doPrint () throws IOException
  {
    try {
      DatabaseTag databaseTag = new DatabaseTag();
      while ( dbiter.hasNext() ) {
        StorableEntityBase entity = ( StorableEntityBase ) dbiter.next();
        databaseTag.addChild( entity.createRdbXmlEntityTag() );
      }
      RdbXmlMappingNamespace.writeDatabase( out, databaseTag, HEADER );
    }
    catch ( XmlDomException ex ) {
      throw new IOException( ex );
    }
    catch ( RdbException ex ) {
      throw new IOException( ex );
    }
    catch ( DIIException ex ) {
      throw new IOException( ex );
    }
  }

}
