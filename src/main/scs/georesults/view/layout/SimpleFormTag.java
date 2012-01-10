package scs.georesults.view.layout;

import scs.javax.web.taglibs.common.WriteTagHelper;

public class SimpleFormTag extends LayoutTagBase
{

  protected String title;

  protected String label;

  protected String caption;

  protected void printHeader () throws Exception
  {
    String str = WriteTagHelper.getTitleOrLabelOrCaption( pageContext, title, label, caption );
    if ( str != null && str.length() == 0 ) str = null;
    doPrintPanelHeader( str );
    out.startTagWithParam( "table", "class", "formTable" );
    out.closeOpenTag();
  }

  protected void printFooter () throws Exception
  {
    out.endTag( "table" );
    doPrintPanelFooter();
  }

  public void setLabel ( String label )
  {
    this.label = label;
  }

  public void setTitle ( String title )
  {
    this.title = title;
  }

  public void setCaption ( String caption )
  {
    this.caption = caption;
  }

}
