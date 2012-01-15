package scs.georesults.view.layout;

import javax.servlet.jsp.JspException;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.common.szotar.GlobalSzotar;

public abstract class LayoutTagBase extends TagBase
{

  protected GlobalSzotar globalSzotar;

  public int doStartTag () throws JspException
  {
    try {
      globalSzotar = GlobalSzotar.getCurrentInstance( pageContext );
      printHeader();
      return EVAL_BODY_INCLUDE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  public int doEndTag () throws JspException
  {
    try {
      printFooter();
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  protected abstract void printHeader () throws Exception;

  protected abstract void printFooter () throws Exception;

  protected void doPrintPanelHeader ( String str ) throws Exception
  {
    out.startDivWithClass( "panel" );
    if ( str != null ) {
      out.startDivWithClass( "panelHeader" );
      out.writeDivWithClassAndText( "panelHeaderTitle", str );
      out.startDivWithClass( "panelHeaderIcons" );
      out.closeOpenTag();
      printIcons();
      out.endDiv();
      out.endDiv();
    }
    out.startDivWithClass( "panelContent" );
    out.closeOpenTag();
  }

  protected void printIcons () throws Exception
  {}

  protected void doPrintPanelFooter () throws Exception
  {
    out.endDiv();
    out.endDiv();

  }

}
