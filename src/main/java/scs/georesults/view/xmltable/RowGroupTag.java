package scs.georesults.view.xmltable;

public class RowGroupTag extends XmlTagBase
{

  private boolean header;

  protected void doPrintXmlStart () throws Exception
  {
  }

  protected void doPrintXmlEnd () throws Exception
  {
  }

  protected void doPrintXhtmlStart () throws Exception
  {
    if (header) {
      out.startTag( "thead" );
    } else {
      out.startTag( "tbody" );
    }
  }

  protected void doPrintXhtmlEnd () throws Exception
  {
    if (header) {
      out.endTag( "thead" );
    } else {
      out.endTag( "tbody" );
    }
  }

  public void setHeader ( boolean header )
  {
    this.header = header;
  }

}
