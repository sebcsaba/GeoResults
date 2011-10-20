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
    return request.getContextPath() + "/style/";
  }

}
