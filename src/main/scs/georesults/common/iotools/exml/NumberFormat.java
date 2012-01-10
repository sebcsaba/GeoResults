package scs.georesults.common.iotools.exml;

import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;

public class NumberFormat implements XmlContent
{

  public static final String FORMAT_SHORT_DATE = "Short Date";

  public static final String FORMAT_SHORT_TIME = "Short Time";

  private String format;

  public NumberFormat ()
  {}

  public NumberFormat ( String format )
  {
    this.format = format;
  }

  public void print ( XmlWriter out ) throws IOException
  {
    out.startTag( "NumberFormat" );
    if ( format != null ) out.writeParam( "ss:Format", format );
    out.endTag( "NumberFormat" );
  }

  public void setFormat ( String format )
  {
    this.format = format;
  }

}
