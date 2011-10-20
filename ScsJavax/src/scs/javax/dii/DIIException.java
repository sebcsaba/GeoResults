package scs.javax.dii;

/**
 *
 * <p>Title: FatRepair</p>
 *
 * <p>Ez a kivétel összefoglalja a DII csomag használatakor felmerülő kivételet.</p>
 *
 * <p>Copyright: Copyright (c) Csaba Sebastian 2006</p>
 *
 * <p>Company: Sebastian Co.</p>
 *
 * @author Csaba Sebastian
 * @version 1.0
 */
public class DIIException extends Exception
{

  public DIIException ()
  {
    super();
  }

  public DIIException ( String message )
  {
    super( message );
  }

  public DIIException ( Throwable cause )
  {
    super( cause );
  }

  public DIIException ( String message, Throwable cause )
  {
    super( message, cause );
  }

}
