package scs.georesults.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

public class WebUtils
{

  private WebUtils ()
  {}

  public static String getStylePath ( PageContext pageContext )
  {
    HttpServletRequest request = ( HttpServletRequest ) pageContext.getRequest();
    final String contextPath = request.getContextPath();
    if (!contextPath.equals("/")) {
    	return contextPath + "/style/";
    } else {
    	return "/style/";
    }
  }

}
