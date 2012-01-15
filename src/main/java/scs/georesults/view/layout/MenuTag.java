package scs.georesults.view.layout;

import scs.georesults.common.szotar.GlobalSzotar;

public class MenuTag extends LayoutTagBase
{

  private static final String REQUEST_KEY = "menuTagIndex";

  private String title;

  protected void printHeader () throws Exception
  {
    int index = getIndex();
    out.startDivWithClass( "menuBox" );
    out.writeParam( "id", "menuBox_" + index );
    out.startDivWithClass( "menuTitle" );
    out.writeParam( "onclick", "openMenu(this);" );
    out.writeString( GlobalSzotar.resolve( pageContext, title ) );
    out.endDiv();
    out.startDivWithClass( "menuContent" );
    out.writeParam( "style", "display:none;" );
    out.closeOpenTag();
  }

  protected void printFooter () throws Exception
  {
    out.endDiv();
    out.endDiv();
  }

  private int getIndex ()
  {
    Integer index = ( Integer ) pageContext.getAttribute( REQUEST_KEY );
    if ( index == null ) index = new Integer( 0 );
    pageContext.setAttribute( REQUEST_KEY, new Integer( index.intValue() + 1 ) );
    return index.intValue();
  }

  public void setTitle ( String title )
  {
    this.title = title;
  }

}
