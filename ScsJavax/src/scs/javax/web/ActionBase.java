package scs.javax.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.*;

public abstract class ActionBase extends Action
{

  public static final String GLOBAL_ERROR_KEY = "error";

  public static final String GLOBAL_ERROR_FORWARD = "globalError";

  public static final String GLOBAL_SESSION_TIMEOUT_FORWARD = "sessionTimeout";

  private HttpServletRequest request;

  private WebSession webSession;

  public abstract String service ( DynamicForm form ) throws Exception;

  public ActionForward execute ( ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest servletRequest, HttpServletResponse servletResponse )
  {
    this.request = servletRequest;
    this.webSession = WebSession.getWebSession( servletRequest );
    ActionForward fw;
    try {
      DynamicForm form = new DynamicForm( request );
      fw = actionMapping.findForward( service( form ) );
    }
    catch ( SessionTimeoutException ex ) {
      fw = actionMapping.findForward( GLOBAL_SESSION_TIMEOUT_FORWARD );
      servletRequest.setAttribute( GLOBAL_ERROR_KEY, ex );
      createSession().setAttribute( GLOBAL_ERROR_KEY, ex );
    }
    catch ( Throwable ex ) {
      fw = actionMapping.findForward( GLOBAL_ERROR_FORWARD );
      servletRequest.setAttribute( GLOBAL_ERROR_KEY, ex );
    }
    WebSession.releaseWebSession( webSession );
    return fw;
  }

  protected HttpServletRequest getRequest ()
  {
    return request;
  }

  protected void setToRequest ( String sessionKey, Object object )
  {
    request.setAttribute( sessionKey, object );
  }

  protected HttpSession getSession () throws SessionTimeoutException
  {
    return webSession.getSession();
  }

  protected HttpSession createSession ()
  {
    return webSession.createSession();
  }

  protected void setToSession ( String sessionKey, Object object ) throws SessionTimeoutException
  {
    getSession().setAttribute( sessionKey, object );
  }

  protected Object getFromSession ( String sessionKey ) throws SessionTimeoutException
  {
    return getSession().getAttribute( sessionKey );
  }

  protected void removeFromSession ( String sessionKey ) throws SessionTimeoutException
  {
    getSession().removeAttribute( sessionKey );
  }

}
