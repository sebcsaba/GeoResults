package scs.javax.io;

public class IOException extends java.io.IOException
{

  public IOException ()
  {
    super();
  }

  public IOException ( String message )
  {
    super( message );
  }

  public IOException ( Throwable cause )
  {
    super();
    initCause( cause );
  }

  public IOException ( String message, Throwable cause )
  {
    super( message );
    initCause( cause );
  }

}
