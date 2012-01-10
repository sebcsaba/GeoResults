package scs.georesults.common.iotools.exml;

import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;

public abstract class PageHeaderFooterBase implements XmlContent
{

  private String data;

  private Double margin;

  protected PageHeaderFooterBase ()
  {}

  protected PageHeaderFooterBase ( String data )
  {
    this.data = data;
  }

  protected PageHeaderFooterBase ( String data, double margin )
  {
    this.data = data;
    this.margin = new Double( margin );
  }

  protected abstract String getTagName ();

  public void print ( XmlWriter out ) throws IOException
  {
    out.startTag( getTagName() );
    if ( data != null ) out.writeParam( "x:Data", data );
    if ( margin != null ) out.writeParam( "x:Margin", margin.toString() );
    out.endTag( getTagName() );
  }

  public void setData ( String data )
  {
    this.data = data;
  }

  public void setMargin ( Double margin )
  {
    this.margin = margin;
  }

}
