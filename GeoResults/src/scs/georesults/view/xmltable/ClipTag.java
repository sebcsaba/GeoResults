package scs.georesults.view.xmltable;

public class ClipTag extends XmlTagBase
{

  private String styleClass;

  protected void doPrintXmlStart () throws Exception
  {
  }

  protected void doPrintXmlEnd () throws Exception
  {
  }

  protected void doPrintXhtmlStart () throws Exception
  {
    out.startDivWithClass( styleClass );
  }

  protected void doPrintXhtmlEnd () throws Exception
  {
    out.endDiv();
  }

  public void setStyleClass ( String styleClass )
  {
    this.styleClass = styleClass;
  }

}
