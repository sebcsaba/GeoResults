package scs.georesults.view.layout;

public class LeftcellTag extends LayoutTagBase
{

  protected void printHeader () throws Exception
  {
    out.startTagWithParam( "td", "class", "formFieldName" );
    out.closeOpenTag();
  }

  protected void printFooter () throws Exception
  {
    out.endTag( "td" );
  }

}
