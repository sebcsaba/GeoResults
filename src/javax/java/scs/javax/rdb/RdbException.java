package scs.javax.rdb;

import scs.javax.lang.ExceptionBase;

public class RdbException extends ExceptionBase
{

  public RdbException ( String message )
  {
    super( message );
  }

  public RdbException ( Throwable cause )
  {
    super( cause );
  }

}
