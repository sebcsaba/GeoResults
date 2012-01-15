package scs.georesults.view.fields;

import javax.servlet.http.HttpServletRequest;
import scs.javax.io.IOException;

public class FormTag extends scs.javax.web.taglibs.formelements.FormTag
{

  protected void doPrintHeader () throws IOException
  {
    HttpServletRequest request = ( HttpServletRequest ) pageContext.getRequest();
    action = request.getContextPath() + action;
    if ( method == null ) method = "post";
    super.doPrintHeader();
  }

}
