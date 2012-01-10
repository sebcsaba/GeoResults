package scs.georesults.view.eredmeny;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.common.LinkTag;
import scs.javax.web.taglibs.common.WriteTag;
import scs.georesults.common.szotar.GlobalSzotar;
import scs.georesults.view.layout.LeftcellTag;
import scs.georesults.view.layout.RightcellTag;
import scs.georesults.view.layout.RowTag;

public class OsszesitoTablaSorTag extends TagBase
{

  protected String title;

  protected boolean frissitendo;

  protected String frissitesAction;

  protected String megjelenitesAction;

  public int doEndTag () throws JspException
  {
    try {
      HttpServletRequest request = ( HttpServletRequest ) pageContext.getRequest();
      printForm();
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  private TagBase createLeftLinkTag ( TagBase parent )
  {
    WriteTag writeTag = new WriteTag();
    writeTag.setKey( title );
    if ( frissitendo ) {
      writeTag.init( parent );
      return writeTag;
    } else {
      LinkTag linkTag = new LinkTag();
      linkTag.init( parent );
      linkTag.setOnclick( "popupResults('" + getContextPath() + megjelenitesAction + "')" );
      writeTag.init( linkTag );
      DynamicTag linkDt = new DynamicTag( linkTag );
      linkDt.addChild( writeTag );
      return linkDt;
    }
  }

  private TagBase createLeftCell ( TagBase parent )
  {
    LeftcellTag leftcellTag = new LeftcellTag();
    leftcellTag.init( parent );
    DynamicTag leftcellDt = new DynamicTag( leftcellTag );
    leftcellDt.addChild( createLeftLinkTag( leftcellTag ) );
    return leftcellDt;
  }

  private TagBase createFrissitesLink ( TagBase parent ) throws SessionTimeoutException
  {
    String styleClass = "frissites";
    if ( frissitendo ) styleClass = styleClass + " most";
    LinkTag linkTag = new LinkTag();
    linkTag.init( parent );
    linkTag.setAction( frissitesAction );
    linkTag.setStyleClass( styleClass );
    WriteTag writeTag = new WriteTag();
    writeTag.init( linkTag );
    writeTag.setLabel( GlobalSzotar.resolve( pageContext, "BS_FRISSITES" ) );
    DynamicTag linkDt = new DynamicTag( linkTag );
    linkDt.addChild( writeTag );
    return linkDt;
  }

  private TagBase createRightCell ( TagBase parent ) throws SessionTimeoutException
  {
    RightcellTag rightcellTag = new RightcellTag();
    rightcellTag.init( parent );
    DynamicTag righttcellDt = new DynamicTag( rightcellTag );
    righttcellDt.addChild( createFrissitesLink( rightcellTag ) );
    return righttcellDt;
  }

  private void printForm () throws JspException, SessionTimeoutException
  {
    RowTag rowTag = new RowTag();
    rowTag.init( ( TagBase ) getParent() );
    DynamicTag rowDt = new DynamicTag( rowTag );
    rowDt.addChild( createLeftCell( rowTag ) );
    rowDt.addChild( createRightCell( rowTag ) );
    rowDt.eval();
  }

  public void setFrissitendo ( boolean frissitendo )
  {
    this.frissitendo = frissitendo;
  }

  public void setTitle ( String title )
  {
    this.title = title;
  }

  public void setMegjelenitesAction ( String megjelenitesAction )
  {
    this.megjelenitesAction = megjelenitesAction;
  }

  public void setFrissitesAction ( String frissitesAction )
  {
    this.frissitesAction = frissitesAction;
  }

}
