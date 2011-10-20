package scs.javax.geom2d;

public class GeometricException extends Exception
{

  public GeometricException ( String msg )
  {
    super( msg );
  }

  public static class UnknownAngle extends GeometricException
  {
    public UnknownAngle ()
    {
      super( "Az origónak nem értelmezhető argumentuma, vagy nulla hosszú szakasznak iránya." );
    }
  }


  public static class InvalidLineDefinition extends GeometricException
  {
    public InvalidLineDefinition ( String msg )
    {
      super( "Hibás egyenes-definíció. " + msg );
    }
  }


  public static class InvalidLineParametrisedDefinition extends InvalidLineDefinition
  {
    public InvalidLineParametrisedDefinition ()
    {
      super( "Az a és b paraméter nem lehet egyszerre nulla." );
    }
  }


  public static class InvalidLineTwoPointedDefinition extends InvalidLineDefinition
  {
    public InvalidLineTwoPointedDefinition ()
    {
      super( "A két pontnak különbözőnek kell lenni." );
    }
  }


  public static class UnknownPoint extends GeometricException
  {
    public UnknownPoint ()
    {
      super( "Nem értelemzhető ilyen pont." );
    }
  }


  public static class ParallelLines extends GeometricException
  {
    public ParallelLines ()
    {
      super( "A két egyenes párhuzamos." );
    }
  }

}
