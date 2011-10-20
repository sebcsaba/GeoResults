package scs.javax.geom2d;

// Egy kört definiál a síkon
public class Circle
{

  private Point center;

  private double radius;

  // Egy origó középpontú egységnyi sugarú kört definiál
  public Circle ()
  {
    this.center = GeometricConstants.ORIGIN;
    this.radius = 1.0;
  }

  // Egy kör definiál radius sugárral a p pont körül
  public Circle ( Point center, double radius )
  {
    this.center = center;
    this.radius = radius;
  }

  // Visszaadja a kör középpontját
  public double getRadius ()
  {
    return radius;
  }

  // Visszaadja a kör sugarát
  public Point getCenter ()
  {
    return center;
  }

  // Igaz, ha az adott kör és o kör azonosak.
  // (Ha EPSZILON mértékű hibával ugyanazt a kört definiálják.)
  public boolean equals ( Object o )
  {
    if ( this == o )return true;
    if ( o instanceof Circle ) {
      Circle c = ( Circle ) o;
      return ( center.equals( c.center ) && GeometricConstants.isEqual( radius, c.radius ) );
    } else return false;
  }

  public int hashCode ()
  {
    return new Double( center.hashCode() + radius ).hashCode();
  }

  public String toString ()
  {
    return "circle:[((x-(" + center.getX() + "))^2*(y-(" + center.getY() + "))^2==(" + radius + ")^2]";
  }

  /*public boolean isOn ( Point p )
     {
    double dx = x - p.getX();
    double dy = y - p.getY();
    return ( Constants.isEqual( dx * dx + dy * dy, r * r ) );
     }*/

}
