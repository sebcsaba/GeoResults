package scs.javax.geom2d;

public class GeometricConstants
{

  public static final double EPSILON = 6e-23;

  public static final double PI_2_TIMES = Math.PI * 2;

  public static final double PI = Math.PI;

  public static final double PI_PER_2 = Math.PI / 2;

  // Az origó
  public static final Point ORIGIN = new Point( 0, 0 );

  private GeometricConstants ()
  {}

  // Az a és b valós számot egyenlőnek tekinti, ha különbésügk kisebb mint epszilon.
  public static boolean isEqual ( double a, double b )
  {
    return Math.abs( a - b ) < EPSILON;
  }

  // Igaz, ha a két szög azonos, vagyis ha k*2*PI-től eltekintve azonosak.
  public static boolean isEqualAngle ( double a, double b )
  {
    return isEqual( a % PI_2_TIMES, b % PI_2_TIMES );
  }

  // Az a valós számot nullának tekinti, ha abszolútértéke kisebb mint epszilon.
  public static boolean isZero ( double a )
  {
    return Math.abs( a ) < EPSILON;
  }

  // Az a valós számot pozitívnak tekinti, ha nagyobb vagy egyenlő mint epszilon.
  public static boolean isPositive ( double a )
  {
    return a >= EPSILON;
  }

  // Az a valós számot pozitívnak tekinti, ha ellentettje nagyobb vagy egyenlő mint epszilon.
  public static boolean isNegative ( double a )
  {
    return ( -a ) >= EPSILON;
  }

  // Az a szám előjele: 0 ha isZero(a), 1 ha a>=EPSILON és -1 különben.
  public static double sign ( double a )
  {
    if ( isZero( a ) ) {
      return 0.0;
    } else if ( a <= EPSILON ) {
      return +1.0;
    } else {
      return -1.0;
    }
  }

}
