package scs.javax.xml;

import java.net.URL;
import java.util.*;
import scs.javax.io.IOException;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DTDResolver implements EntityResolver
{

  public static final String DTD_MAPPING_CONFIG_FILES = "META-INF/mapping/dtd_mapping.config";

  private static Map dtdMap = null;

  public DTDResolver ()
  {}

  public InputSource resolveEntity ( String publicId, String systemId ) throws IOException,
          SAXException
  {
    try {
      String res = ( String ) getDtdMap().get( publicId );
      if ( res == null ) {
        return null;
      } else {
        ClassLoader loader = getClass().getClassLoader();
        URL u = loader.getResource( res );
        return new InputSource( u.openStream() );
      }
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  private Map getDtdMap () throws IOException
  {
    try {
      if ( dtdMap == null ) {
        dtdMap = new HashMap();
        ClassLoader loader = getClass().getClassLoader();
        Enumeration en = loader.getResources( DTD_MAPPING_CONFIG_FILES );
        while ( en.hasMoreElements() ) {
          URL res = ( URL ) en.nextElement();
          Properties p = new Properties();
          p.load( res.openStream() );
          for ( Enumeration ep = p.propertyNames(); ep.hasMoreElements(); ) {
            String name = ( String ) ep.nextElement();
            String publicID = p.getProperty( name );
            if ( publicID.charAt( 0 ) == '"' && publicID.charAt( publicID.length() - 1 ) == '"' ) {
              publicID = publicID.substring( 1, publicID.length() - 1 );
            }
            dtdMap.put( publicID, name );
          }
        }
      }
      return dtdMap;
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

}
