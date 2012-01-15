package scs.georesults.view.xmltable;

import scs.javax.io.IOException;

public class TableTag extends XmlTagBase
{

  private String styleClass;

  protected void doPrintXmlStart () throws IOException
  {
    out.startTag( "Table" );
  }

  protected void doPrintXmlEnd () throws IOException
  {
    out.endTag( "Table" );
  }

  protected void doPrintXhtmlStart () throws IOException
  {
    out.startTag( "table" );
    out.writeParam( "class", styleClass );
  }

  protected void doPrintXhtmlEnd () throws IOException
  {
    out.endTag( "table" );
  }

  public void setStyleClass ( String styleClass )
  {
    this.styleClass = styleClass;
  }

}
