package scs.javax.geom2d;

// A sík egy pontját jelöli ki.
public class Point
{

  private double x;

  private double y;

  // A (0,0) koordinátájú pont definiálása
  public Point ()
  {
    this.x = 0;
    this.y = 0;
  }

  // Az (x,y) koordinátájú pont definiálása
  public Point ( double x, double y )
  {
    this.x = x;
    this.y = y;
  }

  // Az origóból induló, 'radius' hosszúságú, az abszcisszával 'arg' szöget
  // bezáró vektor végpontjának definiálása
  public Point ( double radius, double arg, int dummy )
  {
    this.x = Math.cos( arg ) * radius;
    this.y = Math.sin( arg ) * radius;
  }

  // Igaz, ha az adott pont és o pont azonosak.
  // (Minkét koordinátájában legfeljebb epszilon mértékű az eltérés.)
  public boolean equals ( Object o )
  {
    if ( this == o )return true;
    if ( o instanceof Point ) {
      Point p = ( Point ) o;
      return ( GeometricConstants.isEqual( x, p.x ) && GeometricConstants.isEqual( y, p.y ) );
    } else return false;
  }

  public int hashCode ()
  {
    return new Double( x + y ).hashCode();
  }

  // Az aktuális pont és a p pont távolsága
  public double distance ( Point p )
  {
    double dx = x - p.x;
    double dy = y - p.y;
    return Math.sqrt( dx * dx + dy * dy );
  }

  // Az aktuális pont x koordinátája
  public double getX ()
  {
    return x;
  }

  // Az aktuális pont y koordinátája
  public double getY ()
  {
    return y;
  }

  // Az aktuális pont origótól vett távolsága
  public double getRadius ()
  {
    return distance( GeometricConstants.ORIGIN );
  }

  // Az aktuális ponthzoz tartozó helyvektornak az abszcisszával bezárt szöge.
  public double getArgument () throws GeometricException
  {
    if ( GeometricConstants.isZero( x ) ) {
      if ( GeometricConstants.isZero( y ) )throw new GeometricException.UnknownAngle();
      return GeometricConstants.sign( y ) * GeometricConstants.PI_PER_2;
    } else {
      double arg = Math.atan( y / x );
      if ( GeometricConstants.isNegative( x ) ) arg += GeometricConstants.PI;
      return arg;
    }
  }

  // Az adott pont által reprezentált vektorhoz hozzáadja a p pont által
  // reprezentált vektort, és az eredményvektor által definiált pontot adja vissza.
  public Point addVector ( Point p )
  {
    return new Point( x + p.x, y + p.y );
  }

  // Az adott pont által reprezentált vektorból kivonja a p pont által
  // reprezentált vektort, és az eredményvektor által definiált pontot adja vissza.
  public Point subVector ( Point p )
  {
    return new Point( x - p.x, y - p.y );
  }

  // Az adott pont által reprezentált vektort megszorozza a 'lambda' konstanssal,
  // és az eredményvektor által definiált pontot adja vissza.
  public Point mulVector ( double lambda )
  {
    return new Point( x * lambda, y * lambda );
  }

  // Az adott pont által reprezentált vektort elforgatja 'angle' szöggel,
  // és az eredményvektor által definiált pontot adja vissza.
  public Point turn ( double angle )
  {
    double nx = x * Math.cos( angle ) - y * Math.sin( angle );
    double ny = x * Math.sin( angle ) + y * Math.cos( angle );
    return new Point( nx, ny );
  }

  // A pont szöveges reprezentációját adja vissza.
  public String toString ()
  {
    return "point:[" + x + "," + y + "]";
  }

}
