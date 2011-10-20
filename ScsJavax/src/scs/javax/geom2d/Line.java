package scs.javax.geom2d;

import scs.javax.lang.InvalidProgrammerException;

// Egy egyenest határoz meg a síkon.
public class Line
{

  private double a;

  private double b;

  private double c;

  // Az origón átmenő, az abszcisszával PI/4 szöget bezáró egyenest definiálja.
  public Line ()
  {
    this.a = -1;
    this.b = 1;
    this.c = 0;
  }

  // Az a*x+b*y+c egyenletű egyenest definiálja.
  public Line ( double a, double b, double c ) throws GeometricException.InvalidLineParametrisedDefinition
  {
    if ( GeometricConstants.isZero( a ) && GeometricConstants.isZero( b ) )throw new GeometricException.InvalidLineParametrisedDefinition();
    this.a = a;
    this.b = b;
    this.c = c;
  }

  // Azt az egyenest definiálja, amely a megadott két pontra illeszkedik.
  public Line ( Point p1, Point p2 ) throws GeometricException.InvalidLineTwoPointedDefinition
  {
    if ( p1.equals( p2 ) )throw new GeometricException.InvalidLineTwoPointedDefinition();
    if ( GeometricConstants.isEqual( p1.getX(), p2.getX() ) ) {
      a = 1;
      b = 0;
      c = -p1.getX();
    } else {
      a = ( p1.getY() - p2.getY() ) / ( p2.getX() - p1.getX() );
      b = 1;
      c = ( p2.getY() - p1.getY() ) / ( p2.getX() - p1.getX() ) * p1.getX() - p1.getY();
    }
  }

  // A p ponton áthaladó, az abszcisszával 'angle' szöget bezáró egyest definiálja.
  public Line ( Point p, double angle )
  {
    if ( GeometricConstants.isEqual( Math.abs( angle ), GeometricConstants.PI_PER_2 ) ) {
      a = 1;
      b = 0;
      c = -p.getX();
    } else {
      a = Math.tan( angle );
      b = -1;
      c = p.getY() - a * p.getX();
    }
  }

  // Az egyenest mint E x|->y függvényt tekintve megadja E(x)-et.
  public double getYonX ( double x ) throws GeometricException.UnknownPoint
  {
    if ( GeometricConstants.isZero( b ) )throw new GeometricException.UnknownPoint();
    else return ( -a * x - c ) / b;
  }

  // Az egyenest mint E y|->x függvényt tekintve megadja E(y)-t.
  public double getXonY ( double y ) throws GeometricException.UnknownPoint
  {
    if ( GeometricConstants.isZero( a ) )throw new GeometricException.UnknownPoint();
    else return ( -b * y - c ) / a;
  }

  // Megállapítja, hogy az egyenes illeszkedik-e a p pontra.
  public boolean isOn ( Point p )
  {
    return GeometricConstants.isZero( a * p.getX() + b * p.getY() + c );
  }

  // Visszaadja az egyenesnek az abszcisszával bezárt szögét.
  public double angle ()
  {
    if ( GeometricConstants.isZero( b ) ) {
      return GeometricConstants.PI_PER_2;
    } else {
      return Math.atan( -a / b );
    }
  }

  // Igaz, ha az adott egyenes és o egyenes azonosak.
  // (Ha EPSZILON mértékű hibával ugyanazt az egyenest definiálják.)
  public boolean equals ( Object o )
  {
    if ( this == o )return true;
    if ( o instanceof Line ) {
      Line l = ( Line ) o;
      if ( GeometricConstants.isZero( a ) ) {
        return ( GeometricConstants.isEqual( c / b, l.c / l.b ) );
      } else {
        return ( GeometricConstants.isEqual( b / a, l.b / l.a ) && GeometricConstants.isEqual( c / a, l.c / l.a ) );
      }
    } else return false;
  }

  public int hashCode ()
  {
    return new Double( a + b + c ).hashCode();
  }

  // Megállapítja, hogy az adott egyenes és az l egyenes párhuzamos-e.
  public boolean isParallel ( Line l )
  {
    if ( GeometricConstants.isZero( a ) || GeometricConstants.isZero( l.a ) ) {
      return ( GeometricConstants.isZero( a ) && GeometricConstants.isZero( l.a ) && GeometricConstants.isEqual( b, l.b ) );
    } else {
      return ( GeometricConstants.isEqual( b / a, l.b / l.a ) );
    }
  }

  // Az adott egyenes és az l egyenes meszétpontját adja vissza.
  public Point intersects ( Line l ) throws GeometricException
  {
    if ( isParallel( l ) )throw new GeometricException.ParallelLines();
    if ( GeometricConstants.isZero( b ) ) {
      double x0 = -c / a;
      return new Point( x0, l.getYonX( x0 ) );
    } else {
      double x0 = ( l.c * b - c * l.b ) / ( a * l.b - b * l.a );
      return new Point( x0, getYonX( x0 ) );
    }
  }

  // Egy olyan egyenest definiál, amely ortogonális az adott egyenesre, és illeszkedik a p pontra.
  public Line ortogonal ( Point p )
  {
    return new Line( p, angle() + GeometricConstants.PI_PER_2 );
  }

  // Megadja az egyenes azon pontját, amely a p ponthoz legközelebb esik.
  public Point nearest ( Point p )
  {
    try {
      return intersects( ortogonal( p ) );
    }
    catch ( GeometricException ex ) {
      throw new InvalidProgrammerException();
    }
  }

  // Megadja az egyenes és a p pont távolságát
  public double distance ( Point p )
  {
    return nearest( p ).distance( p );
  }

  // Az egyenes szöveges reprezentációját adja vissza.
  public String toString ()
  {
    return "line:[(" + a + ")x+(" + b + ")y+(" + c + ")==0]";
  }

}
