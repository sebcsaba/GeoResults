package scs.javax.web.taglibs.common;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

public interface TextResolver
{

  public String resolve ( PageContext context, String string ) throws JspException;

}
