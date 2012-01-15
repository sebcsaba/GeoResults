package scs.javax.dii;

import java.lang.reflect.Method;

public class BeanProperty extends Property
{

  private Method getter;

  private Method setter;

  public BeanProperty ( Class clazz, String name ) throws DIIException
  {
    super( name );
    findGetter( clazz, name );
    findSetter( clazz, name, getter.getReturnType() );
  }

  public Class getType () throws DIIException
  {
    return getter.getReturnType();
  }

  public Object getValue ( Object base ) throws DIIException
  {
    try {
      return getter.invoke( base, new Object[0] );
    }
    catch ( Exception ex ) {
      throw new DIIException( ex );
    }
  }

  public void setValue ( Object base, Object value ) throws DIIException
  {
    try {
      if ( setter == null )throw new DIIException( "cannot set a read-only property" );
      setter.invoke( base, new Object[] {value} );
    }
    catch ( Exception ex ) {
      throw new DIIException( ex );
    }
  }

  private void findGetter ( Class clazz, String name ) throws DIIException
  {
    try {
      getter = clazz.getMethod( prefixName( name, "get" ), new Class[0] );
    }
    catch ( NoSuchMethodException ex ) {
      try {
        getter = clazz.getMethod( prefixName( name, "is" ), new Class[0] );
      }
      catch ( NoSuchMethodException ex1 ) {
        try {
          getter = clazz.getMethod( name, new Class[0] );
        }
        catch ( Exception ex2 ) {
          throw new DIIException( ex2 );
        }
      }
    }
  }

  private void findSetter ( Class clazz, String name, Class type ) throws DIIException
  {
    try {
      setter = clazz.getMethod( prefixName( name, "set" ), new Class[] {type} );
    }
    catch ( Exception ex ) {
      setter = null;
    }
  }

  private static String prefixName ( String name, String prefix )
  {
    StringBuffer sb = new StringBuffer( name.length() + prefix.length() );
    sb.append( prefix ).append( name );
    sb.setCharAt( prefix.length(), Character.toUpperCase( name.charAt( 0 ) ) );
    return sb.toString();
  }

  public static String prefixGetterNameForType ( String name, String type )
  {
    if ( "boolean".equals( type ) )return prefixName( name, "is" );
    return prefixName( name, "get" );
  }

  public static String prefixSetterName ( String name )
  {
    return prefixName( name, "set" );
  }

}
