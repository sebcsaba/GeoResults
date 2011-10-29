package scs.javax.web.taglibs.common;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

public interface TextResolver
{

  public String resolve ( ServletContext servletContext, HttpServletRequest request, String string ) throws JspException;

}
