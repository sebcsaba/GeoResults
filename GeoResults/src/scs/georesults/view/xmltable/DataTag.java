package scs.georesults.view.xmltable;



public class DataTag extends XmlTagBase
{

  private String type;

  protected void doPrintXmlStart () throws Exception
  {
    out.startTag( "Data" );
    out.writeParam( "ss:Type", type );
  }

  protected void doPrintXmlEnd () throws Exception
  {
    out.endTag( "Data" );
  }

  protected void doPrintXhtmlStart () throws Exception
  {
  }

  protected void doPrintXhtmlEnd () throws Exception
  {
  }

  public void setType ( String type )
  {
    this.type = type;
  }

}
