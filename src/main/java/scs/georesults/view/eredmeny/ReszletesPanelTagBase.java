package scs.georesults.view.eredmeny;

import javax.servlet.jsp.JspException;
import scs.javax.io.IOException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.common.szotar.GlobalSzotar;

public abstract class ReszletesPanelTagBase extends TagBase
{

  protected static final String CELL_TAG_HEADER = "<td class=\"resultCell\">";

  public int doEndTag () throws JspException
  {
    try {
      doPrint();
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  protected abstract void doPrint () throws Exception;

  protected void writeRow ( String title, StringBuffer content ) throws IOException, SessionTimeoutException
  {
    out.startTag( "tr" );
    out.startTagWithParam( "th", "class", "detailHeader" );
    out.writeString( GlobalSzotar.resolve( pageContext, title ) + ":" );
    out.endTag( "th" );
    out.writeString( content.toString() );
    out.endTag( "tr" );
  }

}
