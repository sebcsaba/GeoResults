package scs.javax.dii;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import scs.javax.io.Path;
import scs.javax.utils.StringUtils;

public class ClassUtils
{

  private static final Map primitives;

  static
  {
    primitives = new HashMap();
    primitives.put( "boolean", new Class[] {java.lang.Boolean.TYPE, java.lang.Boolean.class} );
    primitives.put( "byte", new Class[] {java.lang.Byte.TYPE, java.lang.Byte.class} );
    primitives.put( "short", new Class[] {java.lang.Short.TYPE, java.lang.Short.class} );
    primitives.put( "int", new Class[] {java.lang.Integer.TYPE, java.lang.Integer.class} );
    primitives.put( "long", new Class[] {java.lang.Long.TYPE, java.lang.Long.class} );
    primitives.put( "char", new Class[] {java.lang.Character.TYPE, java.lang.Character.class} );
    primitives.put( "float", new Class[] {java.lang.Float.TYPE, java.lang.Float.class} );
    primitives.put( "double", new Class[] {java.lang.Double.TYPE, java.lang.Double.class} );
  }

  private ClassUtils ()
  {}

  public static Class loadClass ( String className ) throws DIIException
  {
    try {
      Class[] c = ( Class[] ) primitives.get( className );
      if ( c == null ) {
        return Class.forName( className );
      } else {
        return c[0];
      }
    }
    catch ( ClassNotFoundException ex ) {
      throw new DIIException( ex );
    }
  }

  public static String getSimpleClassName ( String className )
  {
    int dot = className.lastIndexOf( '.' );
    return className.substring( dot + 1 );
  }

  public static String getPackageName ( String className )
  {
    int dot = className.lastIndexOf( '.' );
    return ( dot == -1 ? "" : className.substring( 0, dot ) );
  }

  public static Object newInstance ( Class clazz ) throws DIIException
  {
    try {
      return clazz.newInstance();
    }
    catch ( Exception ex ) {
      throw new DIIException( ex );
    }
  }

  public static Path getSystemResourcePath ( String resource )
  {
    URL u = ClassLoader.getSystemResource( resource );
    return new Path( u.getFile() );
  }

  public static Path getResourcePath ( ClassLoader loader, String resource )
  {
    if ( loader == null ) loader = ClassLoader.getSystemClassLoader();
    URL u = loader.getResource( resource );
    return new Path( StringUtils.urlDecode( u.getFile() ) );
  }

}
