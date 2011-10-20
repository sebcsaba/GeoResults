package scs.georesults.view.geo;

import javax.servlet.jsp.JspException;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.common.LinkTag;

public class PopupLinkTag extends TagBase
{

  private String action;

  private LinkTag linkTag;

  public int doStartTag () throws JspException
  {
    linkTag = new LinkTag();
    linkTag.init( this );
    linkTag.setOnclick( "popupResults('" + getContextPath() + action + "')" );
    linkTag.doStartTag();
    return EVAL_BODY_INCLUDE;
  }

  public int doEndTag () throws JspException
  {
    linkTag.doEndTag();
    return EVAL_PAGE;
  }

  public void setAction ( String action )
  {
    this.action = action;
  }

}
