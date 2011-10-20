package scs.javax.web.taglibs.common;

import javax.servlet.jsp.JspException;
import scs.javax.web.taglibs.TagBase;

public class IfTag extends TagBase
{

  private boolean test;

  public int doStartTag () throws JspException
  {
    if ( test ) {
      return EVAL_BODY_INCLUDE;
    } else {
      return SKIP_BODY;
    }
  }

  public void setTest ( boolean test )
  {
    this.test = test;
  }

}
