package scs.javax.utils;

import java.net.URL;
import java.util.*;
import javax.servlet.jsp.PageContext;
import scs.javax.dii.ClassUtils;
import scs.javax.dii.DIIException;
import scs.javax.io.FileInputStream;
import scs.javax.io.IOException;
import scs.javax.io.InputStream;
import scs.javax.io.wrappers.NewInputStreamToOld;

public class ServicesHandler
{

  public static final String SERVICES_CONFIG_FILE_META = "META-INF/mapping/services.config";

  public static final String SERVICES_CONFIG_FILE_WEB = "WEB-INF/mapping/services.config";

  private static ServicesHandler instance = null;

  private Map services;

  private ServicesHandler ( PageContext pageContext ) throws IOException
  {
    services = new HashMap();
    processMeta();
    if ( pageContext != null ) processWeb( pageContext );
  }

  private void processWeb ( PageContext pageContext ) throws IOException
  {
    try {
      String file = pageContext.getServletContext().getRealPath( SERVICES_CONFIG_FILE_WEB );
      InputStream s = new FileInputStream( file );
      Properties p = new Properties();
      p.load( new NewInputStreamToOld( s ) );
      processProperties( p );
      s.close();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  private void processMeta () throws IOException
  {
    try {
      ClassLoader loader = getClass().getClassLoader();
      Enumeration en = loader.getResources( SERVICES_CONFIG_FILE_META );
      while ( en.hasMoreElements() ) {
        URL res = ( URL ) en.nextElement();
        Properties p = new Properties();
        p.load( res.openStream() );
        processProperties( p );
      }
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  private void processProperties ( Properties p )
  {
    for ( Enumeration ep = p.propertyNames(); ep.hasMoreElements(); ) {
      String serviceName = ( String ) ep.nextElement();
      String serviceClass = p.getProperty( serviceName );
      services.put( StringUtils.removeQuotes( serviceName ), StringUtils.removeQuotes( serviceClass ) );
    }
  }

  public Class getServiceClass ( String serviceName ) throws DIIException
  {
    String className = ( String ) services.get( serviceName );
    if ( className == null )throw new NoSuchElementException( "service: '" + serviceName + "'" );
    return ClassUtils.loadClass( className );
  }

  public static ServicesHandler getCurrentInstance ()
  {
    return getCurrentInstance( null );
  }

  public static ServicesHandler getCurrentInstance ( PageContext pageContext )
  {
    try {
      if ( instance == null ) {
        instance = new ServicesHandler( pageContext );
      }
      return instance;
    }
    catch ( Exception ex ) {
      throw new Error( ex );
    }
  }

}
