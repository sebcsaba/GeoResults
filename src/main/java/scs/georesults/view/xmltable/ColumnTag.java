package scs.georesults.view.xmltable;

public class ColumnTag extends XmlTagBase
{

  private Double width;

  private Integer span;

  protected void doPrintXmlStart () throws Exception
  {
    out.startTag( "Column" );
    if ( width != null ) out.writeParam( "ss:Width", Double.toString( width.doubleValue() * EXML_SIZE_SCALE ) );
    if ( span != null ) out.writeParam( "ss:Span", Integer.toString( span.intValue() - 1 ) );
  }

  protected void doPrintXmlEnd () throws Exception
  {
    out.endTag( "Column" );
  }

  protected void doPrintXhtmlStart () throws Exception
  {
    out.startTag( "col" );
    if ( width != null ) out.writeParam( "style", "width:" + width + "mm;" );
    if ( span != null ) out.writeParam( "span", span.toString() );
  }

  protected void doPrintXhtmlEnd () throws Exception
  {
    out.endTag( "col" );
  }

  public void setWidth ( Double width )
  {
    this.width = width;
  }

  public void setSpan ( Integer span )
  {
    this.span = span;
  }

}
