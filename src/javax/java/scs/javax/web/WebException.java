package scs.javax.web;

import scs.javax.lang.ExceptionBase;

public class WebException extends ExceptionBase
{

  public WebException ()
  {
    super();
  }

  public WebException ( String message )
  {
    super( message );
  }

  public WebException ( Throwable cause )
  {
    super( cause );
  }

  public WebException ( String message, Throwable cause )
  {
    super( message, cause );
  }

}
