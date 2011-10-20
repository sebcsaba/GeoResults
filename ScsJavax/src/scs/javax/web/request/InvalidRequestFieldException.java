package scs.javax.web.request;

import scs.javax.web.WebException;

public class InvalidRequestFieldException extends WebException
{

  public InvalidRequestFieldException ( String name )
  {
    super( name );
  }

  public InvalidRequestFieldException ( Throwable ex )
  {
    super( ex );
  }

}
