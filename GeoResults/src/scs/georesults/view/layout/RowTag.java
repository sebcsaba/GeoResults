package scs.georesults.view.layout;

public class RowTag extends LayoutTagBase
{

  protected void printHeader () throws Exception
  {
    out.startTag("tr");
    out.closeOpenTag();
  }

  protected void printFooter () throws Exception
  {
    out.endTag("tr");
  }

}
