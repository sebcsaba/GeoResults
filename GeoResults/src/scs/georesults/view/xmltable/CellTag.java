package scs.georesults.view.xmltable;

import scs.javax.utils.StringUtils;

public class CellTag extends XmlTagBase
{

  private boolean header;

  private Integer colspan;

  private Integer rowspan;

  private String borderStyle;

  private String contentStyle;

  private Integer colIndex;

  protected void doPrintXmlStart () throws Exception
  {
    out.startTag( "Cell" );
    if ( colspan != null ) out.writeParam( "ss:MergeAcross", Integer.toString( colspan.intValue() - 1 ) );
    if ( rowspan != null ) out.writeParam( "ss:MergeDown", Integer.toString( rowspan.intValue() - 1 ) );
    String[] contentStyles = StringUtils.tokenizeToArray( contentStyle, " ", false );
    out.writeParam( "ss:StyleID", "s_" + borderStyle + "_" + StringUtils.joinStrings( contentStyles, "_" ) );
    if ( colIndex != null ) out.writeParam( "ss:Index", colIndex.toString() );
  }

  protected void doPrintXmlEnd () throws Exception
  {
    out.endTag( "Cell" );
  }

  protected void doPrintXhtmlStart () throws Exception
  {
    if ( header ) {
      out.startTag( "th" );
    } else {
      out.startTag( "td" );
    }
    if ( colspan != null ) out.writeParam( "colspan", colspan.toString() );
    if ( rowspan != null ) out.writeParam( "rowspan", rowspan.toString() );
    out.writeParam( "class", borderStyle + " " + contentStyle );
  }

  protected void doPrintXhtmlEnd () throws Exception
  {
    if ( header ) {
      out.endTag( "th" );
    } else {
      out.endTag( "td" );
    }
  }

  public void setHeader ( boolean header )
  {
    this.header = header;
  }

  public void setRowspan ( Integer rowspan )
  {
    this.rowspan = rowspan;
  }

  public void setColspan ( Integer colspan )
  {
    this.colspan = colspan;
  }

  public void setContentStyle ( String contentStyle )
  {
    this.contentStyle = contentStyle;
  }

  public void setBorderStyle ( String borderStyle )
  {
    this.borderStyle = borderStyle;
  }

  public void setColIndex ( Integer colIndex )
  {
    this.colIndex = colIndex;
  }

}
