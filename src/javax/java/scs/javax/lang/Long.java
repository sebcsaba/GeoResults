package scs.javax.lang;

import scs.javax.io.Serializable;

public class Long extends Number implements Cloneable, Comparable, Serializable
{

  public static final long MIN_VALUE = 0x8000000000000000L;

  public static final long MAX_VALUE = 0x7fffffffffffffffL;

  // package láthatóság miatt nem használható: Class.getPrimitiveClass("long");
  public static final Class TYPE = java.lang.Long.TYPE;

  private long value;

  public Long ( long value )
  {
    this.value = value;
  }

  public Long ( String s ) throws NumberFormatException
  {
    this.value = parseLong( s, 10 );
  }

  public static Long valueOf ( String s, int radix ) throws NumberFormatException
  {
    return new Long( parseLong( s, radix ) );
  }

  public static Long valueOf ( String s ) throws NumberFormatException
  {
    return new Long( parseLong( s, 10 ) );
  }

  public long getValue ()
  {
    return value;
  }

  public void setValue ( long value )
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
    return ( int ) value;
  }

  public long longValue ()
  {
    return value;
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
    return ( int ) ( value ^ ( value >>> 32 ) );
  }

  public boolean equals ( Object obj )
  {
    if ( obj instanceof Long ) {
      return value == ( ( Long ) obj ).longValue();
    }
    return false;
  }

  public int compareTo ( Long anotherLong )
  {
    long thisVal = this.value;
    long anotherVal = anotherLong.value;
    return ( thisVal < anotherVal ? -1 : ( thisVal == anotherVal ? 0 : 1 ) );
  }

  public int compareTo ( Object o )
  {
    return compareTo( ( Long ) o );
  }

  // nem implementálom az alap függvényeket
  public static String toString ( long i )
  {
    return java.lang.Long.toString( i );
  }

  public static String toString ( long i, int radix )
  {
    return java.lang.Long.toString( i, radix );
  }

  public static String toHexString ( long i )
  {
    return java.lang.Long.toHexString( i );
  }

  public static String toOctalString ( long i )
  {
    return java.lang.Long.toOctalString( i );
  }

  public static String toBinaryString ( long i )
  {
    return java.lang.Long.toBinaryString( i );
  }

  public static long parseLong ( String s, int radix ) throws NumberFormatException
  {
    return java.lang.Long.parseLong( s, radix );
  }

  public static long parseLong ( String s ) throws NumberFormatException
  {
    return java.lang.Long.parseLong( s );
  }

  public static Long decode ( String nm ) throws NumberFormatException
  {
    return new Long( java.lang.Long.decode( nm ).longValue() );
  }

}
