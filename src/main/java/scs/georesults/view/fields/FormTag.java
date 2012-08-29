package scs.georesults.view.fields;

import javax.servlet.http.HttpServletRequest;
import scs.javax.io.IOException;

public class FormTag extends scs.javax.web.taglibs.formelements.FormTag
{

  protected void doPrintHeader () throws IOException
  {
    HttpServletRequest request = ( HttpServletRequest ) pageContext.getRequest();
    final String contextPath = request.getContextPath();
    if (!contextPath.equals("/")) {
	  action = contextPath + action;
    }
    if ( method == null ) method = "post";
    super.doPrintHeader();
  }

}
