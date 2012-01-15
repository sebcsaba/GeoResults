package scs.javax.lang;

public class InvalidProgrammerException extends RuntimeException
{

  public InvalidProgrammerException ()
  {
    super();
  }

  public InvalidProgrammerException ( String message )
  {
    super( message );
  }

  public InvalidProgrammerException ( Throwable cause )
  {
    super( cause );
  }

  public InvalidProgrammerException ( String message, Throwable cause )
  {
    super( message, cause );
  }

}
