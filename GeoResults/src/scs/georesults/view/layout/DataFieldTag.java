package scs.georesults.view.layout;

import scs.javax.web.taglibs.common.WriteTagHelper;

public class DataFieldTag extends LayoutTagBase
{

  protected String title;

  protected String label;

  protected String caption;

  protected void printHeader () throws Exception
  {
    out.startTag( "tr" );
    out.startTagWithParam( "td", "class", "formFieldName" );
    out.writeString( WriteTagHelper.getTitleOrLabelOrCaption( pageContext, title, label, caption ) );
    out.endTag( "td" );
    out.startTagWithParam( "td", "class", "formFieldWidget" );
    out.closeOpenTag();
  }

  protected void printFooter () throws Exception
  {
    out.endTag( "td" );
    out.endTag( "tr" );
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
