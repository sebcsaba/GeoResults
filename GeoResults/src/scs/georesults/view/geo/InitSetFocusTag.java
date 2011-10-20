package scs.georesults.view.geo;

import javax.servlet.jsp.JspException;
import scs.javax.io.IOException;
import scs.javax.web.taglibs.TagBase;

public class InitSetFocusTag extends TagBase
{

  private String field;

  public int doEndTag () throws JspException
  {
    try {
      if ( field != null ) {
        out.startTagWithParam( "script", "type", "text/javascript" );
        out.closeOpenTag();
        out.println( "var initSetFocusElement = document.getElementById('" + field + "');" );
        out.println( "if (initSetFocusElement!=null) initSetFocusElement.focus();" );
        out.endTag( "script" );
      }
      return EVAL_PAGE;
    }
    catch ( IOException ex ) {
      throw new JspException( ex );
    }
  }

  public void setField ( String field )
  {
    this.field = field;
  }

}
