package scs.georesults.view.xmltable;

public class RowTag extends XmlTagBase
{

  private Double height;

  protected void doPrintXmlStart () throws Exception
  {
    out.startTag( "Row" );
    if ( height != null ) out.writeParam( "ss:Height", Double.toString( height.doubleValue() * EXML_SIZE_SCALE ) );
  }

  protected void doPrintXmlEnd () throws Exception
  {
    out.endTag( "Row" );
  }

  protected void doPrintXhtmlStart () throws Exception
  {
    out.startTag( "tr" );
    out.writeParam( "class", "row" );
    if ( height != null ) out.writeParam( "style", "height:" + height + "mm;" );
  }

  protected void doPrintXhtmlEnd () throws Exception
  {
    out.endTag( "tr" );
  }

  public void setHeight ( Double height )
  {
    this.height = height;
  }

}
