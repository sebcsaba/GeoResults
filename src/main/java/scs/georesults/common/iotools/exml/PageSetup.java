package scs.georesults.common.iotools.exml;

import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;

public class PageSetup implements XmlContent
{

  public static final double PRINT_SCALE = 0.39370078740157483;

  private PageLayout layout;

  private PageMargins margins;

  private PageHeader header;

  private PageFooter footer;

  private Integer scale;

  public PageSetup ()
  {}

  public PageSetup ( PageLayout layout, PageMargins margins, PageHeader header, PageFooter footer )
  {
    this.layout = layout;
    this.margins = margins;
    this.header = header;
    this.footer = footer;
  }

  public void print ( XmlWriter out ) throws IOException
  {
    out.startTag( "PageSetup" );
    if ( layout != null ) layout.print( out );
    if ( margins != null ) margins.print( out );
    if ( header != null ) header.print( out );
    if ( footer != null ) footer.print( out );
    out.endTag( "PageSetup" );
    if ( scale != null ) {
      out.startTag( "Print" );
      out.startTag( "Scale" );
      out.writeString( scale.toString() );
      out.endTag( "Scale" );
      out.endTag( "Print" );
    }
  }

  public void setFooter ( PageFooter footer )
  {
    this.footer = footer;
  }

  public void setMargins ( PageMargins margins )
  {
    this.margins = margins;
  }

  public void setLayout ( PageLayout layout )
  {
    this.layout = layout;
  }

  public void setHeader ( PageHeader header )
  {
    this.header = header;
  }

  public void setScale ( Integer scale )
  {
    this.scale = scale;
  }

}
