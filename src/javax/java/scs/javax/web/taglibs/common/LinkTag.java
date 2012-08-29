package scs.javax.web.taglibs.common;

import javax.servlet.http.HttpServletRequest;
import scs.javax.web.taglibs.html.ATag;

public class LinkTag extends ATag
{

  protected String action;

  protected void doPrintHeader () throws Exception
  {
    if ( action == null || action.equals( "#" ) ) {
      href = "#";
    } else {
      HttpServletRequest request = ( HttpServletRequest ) pageContext.getRequest();
      final String contextPath = request.getContextPath();
      if (!contextPath.equals("/")) {
    	href = contextPath + action;
      } else {
	    href = action;
      }
    }
    hreflang = null;
    charset = null;
    type = null;
    name = null;
    rel = null;
    rev = null;
    shape = null;
    coords = null;
    super.doPrintHeader();
  }

  public void setAction ( String action )
  {
    this.action = action;
  }

}
