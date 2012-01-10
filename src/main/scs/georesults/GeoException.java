package scs.georesults;

import scs.javax.web.WebException;

/**
 * <p>A GeoResults specifikus kivételek közös ősosztálya</p>
 */
public class GeoException extends WebException
{

  public GeoException ()
  {
    super();
  }

  public GeoException ( String message )
  {
    super( message );
  }

  public GeoException ( Throwable cause )
  {
    super( cause );
  }

  public GeoException ( String message, Throwable cause )
  {
    super( message, cause );
  }

}
