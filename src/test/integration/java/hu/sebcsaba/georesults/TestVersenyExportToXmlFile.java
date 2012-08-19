package hu.sebcsaba.georesults;

import scs.javax.dii.ClassUtils;
import scs.javax.io.FileOutputStream;
import scs.javax.io.OutputStreamWriter;
import scs.javax.io.writers.XmlWriter;
import scs.georesults.Config;
import scs.georesults.GeoDbSession;
import scs.georesults.common.iotools.DatabaseWriterBase;
import scs.georesults.common.iotools.DatabaseGeoxmlWriter;
import scs.georesults.common.iotools.DatabaseVersenyExportIterator;
import scs.javax.io.Path;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.Test;

import scs.georesults.config.ConfigUtils;

public class TestVersenyExportToXmlFile
{

  @Test
  public void testVersenyExportToXmlFile() throws Exception
  {
    Path file = new Path("test.xml");
    if (file.exists()) {
      file.delete();
    }
    
    Path path = ClassUtils.getResourcePath( ConfigUtils.class.getClassLoader(), "configuration.properties" );
    Assert.assertNotNull(path);
    Properties props = ConfigUtils.loadProperties( path );
    Assert.assertNotNull(props);
    Config.setConfigurationProperties( props );
    
    GeoDbSession db = GeoDbSession.getCurrentInstance();
    long vid = 1;
    DatabaseVersenyExportIterator dbitXml = new DatabaseVersenyExportIterator( db, vid );
    DatabaseWriterBase writer = new DatabaseGeoxmlWriter( dbitXml );
    OutputStreamWriter oswriter = new OutputStreamWriter( new FileOutputStream( file ) ) ;
    writer.doExport( new XmlWriter( oswriter) );
    oswriter.close();
    
    file.delete();
  }

}
