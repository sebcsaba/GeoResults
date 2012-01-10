package scs.georesults;

import scs.javax.io.FileOutputStream;
import scs.javax.io.OutputStreamWriter;
import scs.javax.io.writers.XmlWriter;
import scs.georesults.common.iotools.DatabaseWriterBase;
import scs.georesults.common.iotools.DatabaseGeoxmlWriter;
import scs.georesults.common.iotools.DatabaseVersenyExportIterator;
import scs.javax.io.Path;
import java.util.Properties;
import scs.georesults.config.ConfigUtils;

public class Test
{

  public static void main ( String[] args ) throws Exception
  {
    Properties props = ConfigUtils.loadProperties( ConfigUtils.CONFIG_DATABASE );
    Config.setConfigurationProperties( props );
    GeoDbSession db = GeoDbSession.getCurrentInstance();
    long vid = 1;
    Path file = new Path("test.xml");
    DatabaseVersenyExportIterator dbitXml = new DatabaseVersenyExportIterator( db, vid );
    DatabaseWriterBase writer = new DatabaseGeoxmlWriter( dbitXml );
    OutputStreamWriter oswriter = new OutputStreamWriter( new FileOutputStream( file ) ) ;
    writer.doExport( new XmlWriter( oswriter) );
  }

}
