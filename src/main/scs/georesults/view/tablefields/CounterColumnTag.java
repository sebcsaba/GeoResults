package scs.georesults.view.tablefields;

import javax.servlet.jsp.JspException;
import scs.javax.web.taglibs.TagBase;

public class CounterColumnTag extends TagBase
{

  protected int start;

  protected String styleClass;

  public int doEndTag () throws JspException
  {
    try {
      InputTableTag itt = ( InputTableTag ) getParent();
      if ( itt.isHeaderRow() ) {
        out.startTag( "th" );
        if ( styleClass != null ) out.writeParam( "class", styleClass );
        out.closeOpenTag();
        out.endTag( "th" );
      } else {
        out.startTag( "td" );
        if ( styleClass != null ) out.writeParam( "class", styleClass );
        out.writeString( Integer.toString( start + itt.getRowIndex() ) + "." );
        out.endTag( "td" );
      }
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  public void setStart ( int start )
  {
    this.start = start;
  }

  public void setStyleClass ( String styleClass )
  {
    this.styleClass = styleClass;
  }

}
