package scs.javax.lang;

import javax.servlet.jsp.JspException;

public class ExceptionBase extends Exception
{

  public ExceptionBase ()
  {
    super();
  }

  public ExceptionBase ( String message )
  {
    super( message );
  }

  public ExceptionBase ( Throwable cause )
  {
    super( cause );
  }

  public ExceptionBase ( String message, Throwable cause )
  {
    super( message, cause );
  }

  public static boolean hasRootExceptionOf ( Throwable t, Class c )
  {
    return ( getRootExceptionOf( t, c ) != null );
  }

  public boolean hasRootExceptionOf ( Class c )
  {
    return hasRootExceptionOf( this, c );
  }

  public static Throwable getRootExceptionOf ( Throwable t, Class c )
  {
    if ( t instanceof JspException ) {
      JspException jex = ( JspException ) t;
      if ( c.isInstance( jex ) )return t;
      return getRootExceptionOf( jex.getRootCause(), c );
    } else {
      while ( t != null ) {
        if ( c.isInstance( t ) )return t;
        if ( t != t.getCause() ) {
          t = t.getCause();
        } else {
          t = null;
        }
      }
    }
    return null;
  }

  public Throwable getRootExceptionOf ( Class c )
  {
    return getRootExceptionOf( this, c );
  }

}
