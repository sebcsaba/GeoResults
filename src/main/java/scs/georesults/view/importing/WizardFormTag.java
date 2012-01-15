package scs.georesults.view.importing;

import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.common.LinkTag;
import scs.javax.web.taglibs.html.ImgTag;
import scs.georesults.view.WebUtils;
import scs.georesults.view.layout.LayoutTagBase;

public class WizardFormTag extends LayoutTagBase
{

  private Integer percent;

  protected void printHeader () throws Exception
  {
    doPrintPanelHeader( globalSzotar.getValue( "IF_IMPORT_WIZARD" ) );
    if ( percent != null ) {
      out.startTagWithParam( "table", "class", "statusPanel" );
      out.startTag( "tr" );
      out.startTag( "td" );
      out.startDivWithClass( "statusBar" );
      out.writeParam( "style", "width:" + percent + "%;" );
      out.writeString( "&nbsp;" );
      out.endDiv();
      out.endTag( "td" );
      out.startTagWithParam( "td", "class", "statusLabel" );
      out.writeString( percent + "%" );
      out.endTag( "td" );
      out.endTag( "tr" );
      out.endTag( "table" );
    }
  }

  protected void printFooter () throws Exception
  {
    doPrintPanelFooter();
  }

  protected void printIcons () throws Exception
  {
    if ( percent != null ) {
      LinkTag link = new LinkTag();
      link.init( this );
      link.setAction( "/importing/cancel.do" );
      ImgTag img = new ImgTag();
      img.init( link );
      img.setId( "import_abort" );
      img.setSrc( WebUtils.getStylePath( pageContext ) + "abort.gif" );
      img.setTitle( globalSzotar.getValue( "IF_MEGSZAKITAS" ) );
      img.setStyleClass( "icon" );
      DynamicTag dt = new DynamicTag( link );
      dt.addChild( img );
      dt.eval();
    }
  }

  public void setPercent ( Integer percent )
  {
    this.percent = percent;
  }

}
