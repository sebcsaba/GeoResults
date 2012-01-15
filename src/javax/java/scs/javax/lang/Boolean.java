package scs.javax.lang;

import scs.javax.io.Serializable;

/**
 * <p>A java.lang.Boolean osztályhoz hasonlóan, egy primitív logikai típusú értéket tartalmazó osztály.
 */
public class Boolean implements Cloneable, Comparable, Serializable
{

  public static final Boolean TRUE = new Boolean( true );

  public static final Boolean FALSE = new Boolean( false );

  // package láthatóság miatt nem használható: Class.getPrimitiveClass("boolean");
  public static final Class TYPE = java.lang.Boolean.TYPE;

  private boolean value;

  public Boolean ( boolean value )
  {
    this.value = value;
  }

  public Boolean ( String s )
  {
    this( toBoolean( s ) );
  }

  public boolean booleanValue ()
  {
    return value;
  }

  public boolean getValue ()
  {
    return value;
  }

  public void setValue ( boolean value )
  {
    this.value = value;
  }

  public static Boolean valueOf ( boolean b )
  {
    return ( b ? TRUE : FALSE );
  }

  public static Boolean valueOf ( String s )
  {
    return toBoolean( s ) ? TRUE : FALSE;
  }

  public static String toString ( boolean b )
  {
    return b ? "true" : "false";
  }

  public String toString ()
  {
    return toString( value );
  }

  public int hashCode ()
  {
    return value ? 1231 : 1237;
  }

  public boolean equals ( Object obj )
  {
    if ( obj instanceof Boolean ) {
      return value == ( ( Boolean ) obj ).booleanValue();
    }
    return false;
  }

  public int compareTo ( Object o )
  {
    return compareTo( ( Boolean ) o );
  }

  public int compareTo ( Boolean anotherInteger )
  {
    int thisVal = getIntegerValue();
    int anotherVal = anotherInteger.getIntegerValue();
    return ( thisVal < anotherVal ? -1 : ( thisVal == anotherVal ? 0 : 1 ) );
  }

  private int getIntegerValue ()
  {
    return ( value ? 1 : 0 );
  }

  private static boolean toBoolean ( String name )
  {
    return ( ( name != null ) && name.equalsIgnoreCase( "true" ) );
  }

}
