package scs.javax.geom2d;

// Egy szakaszt határoz meg a síkon.
public class Section
{

  private Point startPoint;

  private Point endPoint;

  // Az origóból induló, nulla hosszúságú szakaszt definiálja
  public Section ()
  {
    startPoint = GeometricConstants.ORIGIN;
    endPoint = GeometricConstants.ORIGIN;
  }

  // Két végpontja által definiál egy szakaszt.
  public Section ( Point startPoint, Point endPoint )
  {
    this.startPoint = startPoint;
    this.endPoint = endPoint;
  }

  // Egy végpontja, hossza és az abszcisszával bezárt szöge alapján definiál egy szakaszt.
  public Section ( Point startPoint, double length, double angle )
  {
    this.startPoint = startPoint;
    this.endPoint = startPoint.addVector( new Point( length, angle, 0 ) );
  }

  // Igaz, ha az adott szakasz és o szakasz azonosak
  // (Minkét végpontja azonos.)
  public boolean equals ( Object o )
  {
    if ( this == o )return true;
    if ( o instanceof Section ) {
      Section s = ( Section ) o;
      return ( startPoint.equals( s.getStartPoint() ) && endPoint.equals( s.getEndPoint() ) );
    } else return false;
  }

  public int hashCode ()
  {
    return new Double( startPoint.hashCode() + endPoint.hashCode() ).hashCode();
  }

  // A szakasz egyik végpontját ('kezdő pontját') adja vissza
  public Point getStartPoint ()
  {
    return startPoint;
  }

  // A szakasz egyik végpontját ('végső pontját') adja vissza
  public Point getEndPoint ()
  {
    return endPoint;
  }

  // A szakasz felezőpontját adja vissza.
  public Point halfPoint ()
  {
    double dx = ( startPoint.getX() + endPoint.getX() ) / 2;
    double dy = ( startPoint.getY() + endPoint.getY() ) / 2;
    return new Point( dx, dy );
  }

  // A szakasz hosszát adja vissza.
  public double length ()
  {
    double dx = startPoint.getX() - endPoint.getX();
    double dy = startPoint.getY() - endPoint.getY();
    return Math.sqrt( dx * dx + dy * dy );
  }

  // A szakasz irányvektorának az abszcisszával bezárt szögét adja vissza.
  public double angle () throws GeometricException
  {
    return endPoint.subVector( startPoint ).getArgument();
  }

  // Azt az egyenest adja vissza, amelyre az adott szakasz illeszkedik.
  public Line line () throws GeometricException.InvalidLineTwoPointedDefinition
  {
    return new Line( startPoint, endPoint );
  }

  // A szakasztól dist távolsága lévő, vele párhuzamos egyenest adja vissza.
  public Line parallel ( double dist, boolean left ) throws GeometricException
  {
    return new Line( pointFromHalf( dist, left ), angle() );
  }

  // A szakasz felezőpontján átmenő, a szakaszra merőleges egyenesnek a
  // felezőponttól dist távolságra levő pontot adja vissza.
  public Point pointFromHalf ( double dist, boolean left ) throws GeometricException
  {
    Point ortog = new Point( dist, angle() + ( left ? 1 : -1 ) * GeometricConstants.PI_PER_2, 0 );
    return halfPoint().addVector( ortog );
  }

  // A szakasz szöveges reprezentációját adja vissza.
  public String toString ()
  {
    return "section:[" + startPoint + "->" + endPoint + "]";
  }

}
