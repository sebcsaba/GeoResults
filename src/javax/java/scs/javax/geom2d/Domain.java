package scs.javax.geom2d;

// A síkon egy pontból kiinduló két félegyenes által közrefogott tartományt definiál
public class Domain
{

  private Point corner;

  private double angle1;

  private double angle2;


  // Az abszcissza és az ordináta pozitív tartományai által alkotott két
  // félegyenes által közrefogott tartományt definiálja
  public Domain ()
  {
    this.corner = GeometricConstants.ORIGIN;
    this.angle1 = 0.0;
    this.angle2 = GeometricConstants.PI_PER_2;
  }

  // A megadott pontból kiinduló két, az abszcisszával rendre angle1 és angle2
  // szöget bezáró két félegyenes által közrefogott tartományt definiálja
  public Domain ( Point corner, double angle1, double angle2 )
  {
    this.corner = corner;
    this.angle1 = angle1 % GeometricConstants.PI_2_TIMES;
    this.angle2 = angle2 % GeometricConstants.PI_2_TIMES;
  }

  // Igaz, ha az adott tartomány és o tartomány azonosak.
  // (Ha EPSZILON mértékű hibával ugyanaz a két félegyenes definiálja őket.)
  public boolean equals ( Object o )
  {
    if ( this == o )return true;
    if ( o instanceof Domain ) {
      Domain d = ( Domain ) o;
      return ( d.getCorner().equals( corner ) && GeometricConstants.isEqualAngle( d.getAngle1(), angle1 ) && GeometricConstants.isEqualAngle( d.getAngle2(), angle2 ) );
    } else return false;
  }

  public int hashCode ()
  {
    return new Double( corner.hashCode() + angle1 + angle2 ).hashCode();
  }

  // A tartomány csúcspontját adja vissza.
  public Point getCorner ()
  {
    return corner;
  }

  // A tartomány kezdetét meghatározó félegyenesnek az abszcisszával bezárt szögét adja vissza
  public double getAngle1 ()
  {
    return angle1;
  }

  // A tartomány végét meghatározó félegyenesnek az abszcisszával bezárt szögét adja vissza
  public double getAngle2 ()
  {
    return angle2;
  }

}
