package scs.georesults.view.geo;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import scs.javax.io.IOException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebSession;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.common.szotar.GlobalSzotar;
import scs.georesults.logic.GeoMessageException;

public class ErrorTag extends TagBase
{

  public int doEndTag () throws JspException
  {
    try {
      Throwable ex = ( Throwable ) pageContext.getRequest().getAttribute( "error" );
      if ( ex == null ) {
        HttpSession session = WebSession.justGetHttpSession( pageContext );
        ex = ( Throwable ) session.getAttribute( "error" );
        if ( ex != null ) session.removeAttribute( "error" );
      }
      if ( ex != null ) printError( ex );
      return super.doEndTag();
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  private void printError ( Throwable ex ) throws IOException, SessionTimeoutException
  {
    out.startDivWithClass( "error" );
    out.writeString( getErrorString( ex ) );
    out.endDiv();
  }

  private String getErrorString ( Throwable ex ) throws SessionTimeoutException
  {
    if ( ex instanceof GeoMessageException ) {
      String key = ( ( GeoMessageException ) ex ).getKey();
      return GlobalSzotar.resolve( pageContext, key );
    } else if ( ex instanceof SessionTimeoutException ) {
      return GlobalSzotar.resolve( pageContext, "ER_SESSION_TIMEOUT" );
    } else {
      return ex.getMessage();
    }
  }

}
