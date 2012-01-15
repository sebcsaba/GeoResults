package scs.javax.lang;

import scs.javax.io.Serializable;

public class Integer extends Number implements Cloneable, Comparable, Serializable
{

  public static final int MIN_VALUE = 0x80000000;

  public static final int MAX_VALUE = 0x7fffffff;

  // package láthatóság miatt nem használható: Class.getPrimitiveClass("int");
  public static final Class TYPE = java.lang.Integer.TYPE;

  private int value;

  public Integer ( int value )
  {
    this.value = value;
  }

  public Integer ( String s ) throws NumberFormatException
  {
    this.value = parseInt( s, 10 );
  }

  public static Integer valueOf ( String s, int radix ) throws NumberFormatException
  {
    return new Integer( parseInt( s, radix ) );
  }

  public static Integer valueOf ( String s ) throws NumberFormatException
  {
    return new Integer( parseInt( s, 10 ) );
  }

  public int getValue ()
  {
    return value;
  }

  public void setValue ( int value )
  {
    this.value = value;
  }

  public byte byteValue ()
  {
    return ( byte ) value;
  }

  public short shortValue ()
  {
    return ( short ) value;
  }

  public int intValue ()
  {
    return value;
  }

  public long longValue ()
  {
    return ( long ) value;
  }

  public float floatValue ()
  {
    return ( float ) value;
  }

  public double doubleValue ()
  {
    return ( double ) value;
  }

  public String toString ()
  {
    return toString( value );
  }

  public int hashCode ()
  {
    return value;
  }

  public boolean equals ( Object obj )
  {
    if ( obj instanceof Integer ) {
      return value == ( ( Integer ) obj ).intValue();
    }
    return false;
  }

  public int compareTo ( Integer anotherInteger )
  {
    int thisVal = this.value;
    int anotherVal = anotherInteger.value;
    return ( thisVal < anotherVal ? -1 : ( thisVal == anotherVal ? 0 : 1 ) );
  }

  public int compareTo ( Object o )
  {
    return compareTo( ( Integer ) o );
  }

  // nem implementálom az alap függvényeket
  public static String toString ( int i )
  {
    return java.lang.Integer.toString( i );
  }

  public static String toString ( int i, int radix )
  {
    return java.lang.Integer.toString( i, radix );
  }

  public static String toHexString ( int i )
  {
    return java.lang.Integer.toHexString( i );
  }

  public static String toOctalString ( int i )
  {
    return java.lang.Integer.toOctalString( i );
  }

  public static String toBinaryString ( int i )
  {
    return java.lang.Integer.toBinaryString( i );
  }

  public static int parseInt ( String s, int radix ) throws NumberFormatException
  {
    return java.lang.Integer.parseInt( s, radix );
  }

  public static int parseInt ( String s ) throws NumberFormatException
  {
    return java.lang.Integer.parseInt( s );
  }

  public static Integer decode ( String nm ) throws NumberFormatException
  {
    return new Integer( java.lang.Integer.decode( nm ).intValue() );
  }

}
