package scs.georesults.view.tablefields;

import javax.servlet.jsp.JspException;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.common.szotar.GlobalSzotar;

public class InputColumnTag extends TagBase
{

  protected String title;

  protected InputTableTag itt;

  protected String styleClass;

  public int doStartTag () throws JspException
  {
    try {
      itt = ( InputTableTag ) getParent();
      if ( itt.isHeaderRow() ) {
        out.startTag( "th" );
        if ( styleClass != null ) out.writeParam( "class", styleClass );
        out.writeString( GlobalSzotar.resolve( pageContext, title ) );
        out.endTag( "th" );
        return SKIP_BODY;
      } else {
        out.startTag( "td" );
        if ( styleClass != null ) out.writeParam( "class", styleClass );
        out.closeOpenTag();
        return EVAL_BODY_INCLUDE;
      }
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  public int doEndTag () throws JspException
  {
    try {
      if ( !itt.isHeaderRow() ) {
        out.endTag( "td" );
      }
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  public void setTitle ( String title )
  {
    this.title = title;
  }

  public void setStyleClass ( String styleClass )
  {
    this.styleClass = styleClass;
  }

}
