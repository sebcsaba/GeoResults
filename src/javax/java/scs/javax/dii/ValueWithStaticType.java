package scs.javax.dii;

public class ValueWithStaticType
{

  private Class c;

  private Object v;

  public ValueWithStaticType ( byte value )
  {
    this.c = Byte.TYPE;
    this.v = new Byte( value );
  }

  public ValueWithStaticType ( short value )
  {
    this.c = Short.TYPE;
    this.v = new Short( value );
  }

  public ValueWithStaticType ( int value )
  {
    this.c = Integer.TYPE;
    this.v = new Integer( value );
  }

  public ValueWithStaticType ( long value )
  {
    this.c = Long.TYPE;
    this.v = new Long( value );
  }

  public ValueWithStaticType ( float value )
  {
    this.c = Float.TYPE;
    this.v = new Float( value );
  }

  public ValueWithStaticType ( double value )
  {
    this.c = Double.TYPE;
    this.v = new Double( value );
  }

  public ValueWithStaticType ( boolean value )
  {
    this.c = Boolean.TYPE;
    this.v = Boolean.valueOf( value );
  }

  public ValueWithStaticType ( char value )
  {
    this.c = Character.TYPE;
    this.v = new Character( value );
  }

  public ValueWithStaticType ( Object v )
  {
    this.c = v.getClass();
    this.v = v;
  }

  public ValueWithStaticType ( Class c, Object v )
  {
    if ( v != null && !c.isPrimitive() && !c.isInstance( v ) ) {
      throw new IllegalArgumentException( v + " not instance of " + c );
    }
    this.c = c;
    this.v = v;
  }

  public Class getTheClass ()
  {
    return c;
  }

  public Object getTheValue ()
  {
    return v;
  }

  public boolean isPrimitive ()
  {
    return c.isPrimitive();
  }

  public static Class[] getClassArray ( ValueWithStaticType[] params )
  {
    if ( params == null )return null;
    Class[] result = new Class[params.length];
    for ( int i = 0; i < params.length; ++i ) result[i] = params[i].getTheClass();
    return result;
  }

  public static Object[] getValueArray ( ValueWithStaticType[] params )
  {
    if ( params == null )return null;
    Object[] result = new Object[params.length];
    for ( int i = 0; i < params.length; ++i ) result[i] = params[i].getTheValue();
    return result;
  }

}
