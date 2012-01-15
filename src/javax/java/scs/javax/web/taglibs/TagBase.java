package scs.javax.web.taglibs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.Tag;
import scs.javax.io.writers.JspWriter;

public class TagBase implements IterationTag
{

  protected PageContext pageContext;

  protected Tag parent;

  protected JspWriter out;

  public int doStartTag () throws JspException
  {
    return EVAL_BODY_INCLUDE;
  }

  public int doAfterBody () throws JspException
  {
    return SKIP_BODY;
  }

  public int doEndTag () throws JspException
  {
    return EVAL_PAGE;
  }

  public void eval () throws JspException
  {
    int flag = this.doStartTag();
    if ( flag == Tag.EVAL_BODY_INCLUDE || flag == IterationTag.EVAL_BODY_AGAIN ) {
      do {
        flag = this.doAfterBody();
      }
      while ( flag == IterationTag.EVAL_BODY_AGAIN );
    }
    this.doEndTag();
  }

  public void init ( TagBase parent )
  {
    setPageContext( parent.getPageContext() );
    setParent( parent );
  }

  public void setPageContext ( PageContext pageContext )
  {
    this.pageContext = pageContext;
    this.out = ( JspWriter ) pageContext.getAttribute( "out", PageContext.PAGE_SCOPE );
    if ( this.out == null ) {
      this.out = new JspWriter( pageContext.getOut() );
      pageContext.setAttribute( "out", this.out, PageContext.PAGE_SCOPE );
    }
  }

  public PageContext getPageContext ()
  {
    return pageContext;
  }

  public void setParent ( Tag parent )
  {
    this.parent = parent;
  }

  public Tag getParent ()
  {
    return parent;
  }

  public void release ()
  {
    this.pageContext = null;
    this.parent = null;
  }

  protected String getContextPath ()
  {
    HttpServletRequest request = ( HttpServletRequest ) pageContext.getRequest();
    return request.getContextPath();
  }

}
