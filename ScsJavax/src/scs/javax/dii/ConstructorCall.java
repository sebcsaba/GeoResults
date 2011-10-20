package scs.javax.dii;

import java.lang.reflect.Constructor;

public class ConstructorCall
{

  private Constructor init;

  private Object[] params;

  public ConstructorCall ( Class clazz ) throws DIIException
  {
    this( clazz, new ValueWithStaticType[0] );
  }

  public ConstructorCall ( Class clazz, ValueWithStaticType param ) throws DIIException
  {
    this( clazz, new ValueWithStaticType[] {param} );
  }

  public ConstructorCall ( Class clazz, ValueWithStaticType[] params ) throws DIIException
  {
    try {
      this.init = clazz.getConstructor( ValueWithStaticType.getClassArray( params ) );
      this.params = ValueWithStaticType.getValueArray( params );
    }
    catch ( Exception ex ) {
      throw new DIIException( ex );
    }
  }

  public Object create () throws DIIException
  {
    try {
      return init.newInstance( params );
    }
    catch ( Exception ex ) {
      throw new DIIException( ex );
    }
  }

  public int getParamCount ()
  {
    return params.length;
  }

  public ValueWithStaticType getParam ( int i )
  {
    return new ValueWithStaticType( init.getParameterTypes()[i], params[i] );
  }

}
