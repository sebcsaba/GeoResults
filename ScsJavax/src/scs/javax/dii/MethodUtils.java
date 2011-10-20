package scs.javax.dii;

import java.lang.reflect.Method;

public class MethodUtils
{

  private MethodUtils ()
  {}

  public static Object invokeMethod ( Object base, String methodName ) throws DIIException
  {
    try {
      Method m = base.getClass().getMethod( methodName, new Class[0] );
      return m.invoke( base, new Object[0] );
    }
    catch ( Exception ex ) {
      throw new DIIException( ex );
    }
  }

  public static Object invokeMethod ( Object base, String methodName, ValueWithStaticType[] params ) throws DIIException
  {
    try {
      Method m = base.getClass().getMethod( methodName, ValueWithStaticType.getClassArray( params ) );
      return m.invoke( base, ValueWithStaticType.getValueArray( params ) );
    }
    catch ( Exception ex ) {
      throw new DIIException( ex );
    }
  }

  public static Object invokeMethod ( Object base, String methodName, ValueWithStaticType param ) throws DIIException
  {
    return invokeMethod( base, methodName, new ValueWithStaticType[] {param} );
  }

  public static Object invokeMethodAnyType ( Object base, String methodName, Object[] params ) throws DIIException
  {
    Method m = findMethodWithNameAndParamCount( base.getClass(), methodName, params.length );
    try {
      return m.invoke( base, params );
    }
    catch ( Exception ex ) {
      throw new DIIException( ex );
    }
  }

  public static Object invokeMethodAnyType ( Class clazz, String methodName, Object[] params ) throws DIIException
  {
    Method m = findMethodWithNameAndParamCount( clazz, methodName, params.length );
    try {
      return m.invoke( null, params );
    }
    catch ( Exception ex ) {
      throw new DIIException( ex );
    }
  }

  /*private static Method findMethodWithName ( Class clazz, String methodName )
  {
    Method[] methods = clazz.getMethods();
    for ( int i = 0; i < methods.length; ++i ) {
      if ( methods[i].getName().equals( methodName ) )return methods[i];
    }
    throw new NoSuchMethodError( "no method '" + methodName + "' in class '" + clazz.getName() + "'" );
  }*/

  private static Method findMethodWithNameAndParamCount ( Class clazz, String methodName, int paramCount )
  {
    Method[] methods = clazz.getMethods();
    for ( int i = 0; i < methods.length; ++i ) {
      if ( methods[i].getName().equals( methodName ) && methods[i].getParameterTypes().length == paramCount )return methods[i];
    }
    throw new NoSuchMethodError( "no method '" + methodName + "' in class '" + clazz.getName() + "'" );
  }

}
