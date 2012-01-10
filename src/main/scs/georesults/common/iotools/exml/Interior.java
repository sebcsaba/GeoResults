package scs.georesults.common.iotools.exml;

import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;

public class Interior implements XmlContent
{

  private String color;

  public Interior ()
  {}

  public Interior ( String color )
  {
    this.color = color;
  }

  public void print ( XmlWriter out ) throws IOException
  {
    out.startTag( "Interior" );
    if ( color != null ) out.writeParam( "ss:Color", color );
    out.writeParam( "ss:Pattern", "Solid" );
    out.endTag( "Interior" );
  }

  public void setColor ( String color )
  {
    this.color = color;
  }

}
