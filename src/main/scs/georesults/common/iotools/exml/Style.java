package scs.georesults.common.iotools.exml;

import scs.javax.collections.List;
import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;

public class Style implements XmlContent
{

  private String id;

  private Alignment align;

  private List borders;

  private Font font;

  private Interior interior;

  private NumberFormat numberFormat;

  public Style ()
  {
    borders = new List();
  }

  public Style ( String id )
  {
    this();
    this.id = id;
  }

  public void print ( XmlWriter out ) throws IOException
  {
    out.startTag( "Style" );
    out.writeParam( "ss:ID", id );
    if ( align != null ) align.print( out );
    out.startTag( "Borders" );
    for ( int i = 0; i < borders.size(); ++i ) getBorder( i ).print( out );
    out.endTag( "Borders" );
    if ( font != null ) font.print( out );
    if ( interior != null ) interior.print( out );
    if ( numberFormat != null ) numberFormat.print( out );
    out.endTag( "Style" );
  }

  public void setId ( String id )
  {
    this.id = id;
  }

  public void setInterior ( Interior interior )
  {
    this.interior = interior;
  }

  public void setFont ( Font font )
  {
    this.font = font;
  }

  public void setAlign ( Alignment align )
  {
    this.align = align;
  }

  public void setNumberFormat ( NumberFormat numberFormat )
  {
    this.numberFormat = numberFormat;
  }

  public void addBorder ( Border border )
  {
    borders.add( border );
  }

  public Border getBorder ( int index )
  {
    return ( Border ) borders.get( index );
  }

  public int getBorderCount ()
  {
    return borders.size();
  }

}
