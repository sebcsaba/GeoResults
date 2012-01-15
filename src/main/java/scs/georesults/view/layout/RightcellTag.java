package scs.georesults.view.layout;

public class RightcellTag extends LayoutTagBase
{

  protected void printHeader () throws Exception
  {
    out.startTagWithParam( "td", "class", "formFieldWidget" );
    out.closeOpenTag();
  }

  protected void printFooter () throws Exception
  {
    out.endTag( "td" );
  }

}
