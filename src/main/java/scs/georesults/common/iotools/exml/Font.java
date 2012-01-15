package scs.georesults.common.iotools.exml;

import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;

public class Font implements XmlContent
{

  private Boolean bold;

  private Boolean italic;

  private Double size;

  private String color;

  public Font ()
  {}

  public Font ( boolean bold )
  {
    this.bold = Boolean.valueOf( bold );
  }

  public void print ( XmlWriter out ) throws IOException
  {
    out.startTag( "Font" );
    if ( bold != null ) out.writeParam( "ss:Bold", bold.booleanValue() ? "1" : "0" );
    if ( italic != null ) out.writeParam( "ss:Italic", italic.booleanValue() ? "1" : "0" );
    if ( size != null ) out.writeParam( "ss:Size", size.toString() );
    if ( color != null ) out.writeParam( "ss:Color", color.toString() );
    out.endTag( "Font" );
  }

  public void setBold ( Boolean bold )
  {
    this.bold = bold;
  }

  public void setSize ( Double size )
  {
    this.size = size;
  }

  public void setItalic ( Boolean italic )
  {
    this.italic = italic;
  }

  public void setColor ( String color )
  {
    this.color = color;
  }

}
