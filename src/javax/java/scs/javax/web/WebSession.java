package scs.javax.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

public class WebSession
{

  public static final String SESSION_VALID_KEY = "scs.javax.web.WebSession.isValid";

  private static ObjectPool webSessionPool = new GenericObjectPool( new WebSessionFactory() );

  private HttpServletRequest request;

  private WebSession ()
  {}

  public void init ( HttpServletRequest request )
  {
    this.request = request;
  }

  public HttpSession createSession ()
  {
    HttpSession session = request.getSession( true );
    session.setAttribute( SESSION_VALID_KEY, SESSION_VALID_KEY );
    return session;
  }

  public HttpSession getSession () throws SessionTimeoutException
  {
    HttpSession session = request.getSession( false );
    if ( session == null || !isValidSession( session ) ) {
      throw new SessionTimeoutException();
    }
    return session;
  }

  private static boolean isValidSession ( HttpSession session )
  {
    return ( session.getAttribute( SESSION_VALID_KEY ) != null );
  }

  public static WebSession getWebSession ( HttpServletRequest request )
  {
    try {
      WebSession session = ( WebSession ) webSessionPool.borrowObject();
      session.init( request );
      return session;
    }
    catch ( Exception ex ) {
      throw new Error( ex );
    }
  }

  public static void releaseWebSession ( WebSession session )
  {
    try {
      webSessionPool.returnObject( session );
    }
    catch ( Exception ex ) {
      throw new Error( ex );
    }
  }

  public static HttpSession justGetHttpSession ( HttpServletRequest request ) throws SessionTimeoutException
  {
    WebSession webSession = getWebSession( request );
    HttpSession session = webSession.getSession();
    releaseWebSession( webSession );
    return session;
  }

  public static HttpSession justGetHttpSession ( PageContext context ) throws SessionTimeoutException
  {
    WebSession webSession = getWebSession( ( HttpServletRequest ) context.getRequest() );
    HttpSession session = webSession.getSession();
    releaseWebSession( webSession );
    return session;
  }

  private static class WebSessionFactory extends BasePoolableObjectFactory
  {

    public Object makeObject ()
    {
      return new WebSession();
    }

  }

}
