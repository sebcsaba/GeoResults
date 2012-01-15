package scs.georesults.common.iotools.exml;

import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;

public class Column implements XmlContent
{

  private Double width;

  public Column ()
  {}

  public Column ( double width )
  {
    this.width = new Double( width );
  }

  public void print ( XmlWriter out ) throws IOException
  {
    out.startTag( "Column" );
    if ( width != null ) out.writeParam( "ss:Width", width.toString() );
    out.endTag( "Column" );
  }

  public void setWidth ( Double width )
  {
    this.width = width;
  }

}
