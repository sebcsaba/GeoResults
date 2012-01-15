package scs.javax.web.taglibs;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import scs.javax.collections.List;

public class DynamicTag extends TagBase
{

  private TagBase baseTag;

  private List children;

  public DynamicTag ( TagBase baseTag )
  {
    this.pageContext = baseTag.getPageContext();
    this.baseTag = baseTag;
    this.children = new List();
  }

  public DynamicTag ( PageContext pageContext, TagBase baseTag )
  {
    this.pageContext = pageContext;
    this.baseTag = baseTag;
    this.baseTag.setPageContext( pageContext );
    this.children = new List();
  }

  public void addChild ( Tag childTag )
  {
    children.add( childTag );
  }

  public int getChildrenCount ()
  {
    return children.size();
  }

  public TagBase getChild ( int index )
  {
    return ( TagBase ) children.get( index );
  }

  public int doStartTag () throws JspException
  {
    return baseTag.doStartTag();
  }

  public int doAfterBody () throws JspException
  {
    this.doChildrenEval();
    return baseTag.doAfterBody();
  }

  public int doEndTag () throws JspException
  {
    return baseTag.doEndTag();
  }

  private void doChildrenEval () throws JspException
  {
    for ( int i = 0; i < getChildrenCount(); ++i ) {
      getChild( i ).eval();
    }
  }

  public TagBase getBase ()
  {
    return baseTag;
  }

  public void release ()
  {
    baseTag = null;
    children = null;
    super.release();
  }

}
