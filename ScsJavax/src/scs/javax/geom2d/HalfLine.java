package scs.javax.geom2d;

// Egy félegyenest határoz meg a síkon
public class HalfLine
{

  private Point endPoint;

  private double angle;

  // Az origóból induló, az abszcissza pozitív felére illeszkedő félegyenes
  public HalfLine ()
  {
    this.endPoint = GeometricConstants.ORIGIN;
    this.angle = 0.0;
  }

  // A megadott p pontból induló, az abszcisszával angle szöget bezáró
  public HalfLine ( Point endPoint, double angle )
  {
    this.endPoint = endPoint;
    this.angle = angle % GeometricConstants.PI_2_TIMES;
  }

  // Az aktuális félegyenes végpontját adja vissza
  public Point getEndPoint ()
  {
    return endPoint;
  }

  // Az aktuális félegyenesnek az abszcisszával bezárt szögét adja vissza
  public double getAngle ()
  {
    return angle;
  }

  // Igaz, ha az adott egyenes és o egyenes azonosak.
  // (Ha EPSZILON mértékű hibával ugyanazt a félegyenest definiálják.)
  public boolean equals ( Object o )
  {
    if ( this == o )return true;
    if ( o instanceof HalfLine ) {
      HalfLine hl = ( HalfLine ) o;
      return ( hl.getEndPoint().equals( endPoint ) && GeometricConstants.isEqualAngle( hl.getAngle(), angle ) );
    } else return false;
  }

  public int hashCode ()
  {
    return new Double( endPoint.hashCode() + angle ).hashCode();
  }

  // Visszaada azt az egyenest, amelyre az adott félegyenes illeszkedik
  public Line line ()
  {
    return new Line( endPoint, angle );
  }

  // Az egyenes szöveges reprezentációját adja vissza.
  public String toString ()
  {
    return "halfline:[" + endPoint + "->" + angle + "]";
  }

}
