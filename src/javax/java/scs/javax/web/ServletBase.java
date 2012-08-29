package scs.javax.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import scs.javax.utils.StringUtils;
import scs.javax.web.request.InvalidRequestFieldException;

public abstract class ServletBase extends HttpServlet
{

  public static final String GLOBAL_ERROR_KEY = "error";

  public static final String REFERRER_HEADER_KEY = "Referer";

  public static final String ERROR_PAGE_URL = "/common/error.jsp";

  private HttpServletRequest request;

  private HttpServletResponse response;

  private WebSession webSession;

  protected abstract void doPostService ( DynamicForm form, HttpServletResponse response ) throws Exception;

  protected void doPost ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
  {
    String contextPath = request.getContextPath();
    if (contextPath.equals("/")) {
    	contextPath = "";
    }
	try {
      this.request = request;
      this.response = response;
      this.webSession = WebSession.getWebSession( request );
      DynamicForm form = new DynamicForm( request );
      response.setCharacterEncoding( StringUtils.DEFAULT_ENCODING );
      doPostService( form, response );
    }
    catch ( InvalidRequestFieldException ex ) {
      request.getSession().setAttribute( GLOBAL_ERROR_KEY, ex );
      response.sendRedirect( request.getHeader( REFERRER_HEADER_KEY ) );
    }
    catch ( SessionTimeoutException ex ) {
      request.getSession().setAttribute( GLOBAL_ERROR_KEY, ex );
      response.sendRedirect( contextPath + "/" );
    }
    catch ( Throwable ex ) {
      request.getSession().setAttribute( GLOBAL_ERROR_KEY, ex );
      response.sendRedirect( contextPath + ERROR_PAGE_URL );
    }
    WebSession.releaseWebSession( webSession );
  }

  protected void setOutputFileName ( String filename )
  {
    response.setHeader( "Content-Disposition", "attachment; filename=\"" + filename + "\"" );
  }

  protected HttpServletRequest getRequest ()
  {
    return request;
  }

  protected HttpServletResponse getResponse ()
  {
    return response;
  }

  protected void setToRequest ( String sessionKey, Object object )
  {
    request.setAttribute( sessionKey, object );
  }

  protected void setToSession ( String sessionKey, Object object )
  {
    request.getSession().setAttribute( sessionKey, object );
  }

  protected Object getFromSession ( String sessionKey )
  {
    return request.getSession().getAttribute( sessionKey );
  }

  protected void removeFromSession ( String sessionKey )
  {
    request.getSession().removeAttribute( sessionKey );
  }

}
