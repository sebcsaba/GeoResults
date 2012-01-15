package scs.javax.dii;

import java.lang.reflect.Constructor;

public class ConstructorUtils
{

  private ConstructorUtils ()
  {}

  public static boolean hasConstructor ( Class clazz, Class paramType )
  {
    try {
      clazz.getConstructor( new Class[] {paramType} );
      return true;
    }
    catch ( NoSuchMethodException ex ) {
      return false;
    }
  }

  public static boolean hasDefaultConstructor ( Class clazz )
  {
    try {
      clazz.getConstructor( new Class[0] );
      return true;
    }
    catch ( NoSuchMethodException ex ) {
      return false;
    }
  }

  public static Object newInstanceAnyParameter ( Class clazz, Object[] params ) throws DIIException
  {
    try {
      return findConstructorWithParams( clazz, params.length ).newInstance( params );
    }
    catch ( Exception ex ) {
      throw new DIIException( ex );
    }
  }

  private static Constructor findConstructorWithParams ( Class clazz, int paramCount ) throws NoSuchMethodException
  {
    Constructor[] inits = clazz.getConstructors();
    for ( int i = 0; i < inits.length; ++i ) {
      if ( inits[i].getParameterTypes().length == paramCount ) {
        return inits[i];
      }
    }
    throw new NoSuchMethodException( "constructor for class '" + clazz.getName() + "' with " + paramCount + " parameters" );
  }

}
