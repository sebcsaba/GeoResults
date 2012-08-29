package scs.javax.web.taglibs.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import scs.javax.web.taglibs.TagBase;

public class PathTag extends TagBase
{

  public int doEndTag () throws JspException
  {
    try {
      HttpServletRequest request = ( HttpServletRequest ) pageContext.getRequest();
      final String contextPath = request.getContextPath();
      if (!contextPath.equals("/")) {
	    out.writeString( contextPath );
      }
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

}
