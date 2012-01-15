package scs.javax.web.taglibs.html;

import javax.servlet.jsp.PageContext;
import scs.javax.web.taglibs.TagBaseWithXhtmlBaseAttributes;

public class DivTag extends TagBaseWithXhtmlBaseAttributes
{

  public DivTag ()
  {}

  public DivTag ( PageContext pageContext, String styleClass )
  {
    setPageContext( pageContext );
    setStyleClass( styleClass );
  }

  protected void doPrintHeader () throws Exception
  {
    out.startTag( "div" );
    writeExistBaseAttributes();
    out.closeOpenTag();
  }

  protected void doPrintFooter () throws Exception
  {
    out.endDiv();
  }

}
