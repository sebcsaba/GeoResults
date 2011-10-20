package scs.georesults.common.iotools.exml;

import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;

public class PageLayout implements XmlContent
{

  public static final String ORIENTATION_LANDSCAPE = "Landscape";

  public static final String ORIENTATION_PORTRAIT = "Portrait";

  private String orientation;

  private Boolean centerHorizontal;

  private Boolean centerVertical;

  public PageLayout ()
  {}

  public PageLayout ( String orientation )
  {
    this.orientation = orientation;
  }

  public PageLayout ( String orientation, boolean centerHorizontal, boolean centerVertical )
  {
    this.orientation = orientation;
    this.centerHorizontal = Boolean.valueOf( centerHorizontal );
    this.centerVertical = Boolean.valueOf( centerVertical );
  }

  public void print ( XmlWriter out ) throws IOException
  {
    out.startTag( "Layout" );
    if ( orientation != null ) out.writeParam( "x:Orientation", orientation );
    if ( centerHorizontal != null ) out.writeParam( "x:CenterHorizontal", centerHorizontal.booleanValue() ? "1" : "0" );
    if ( centerVertical != null ) out.writeParam( "x:centerVertical", centerVertical.booleanValue() ? "1" : "0" );
    out.endTag( "Layout" );
  }

  public void setCenterHorizontal ( Boolean centerHorizontal )
  {
    this.centerHorizontal = centerHorizontal;
  }

  public void setOrientation ( String orientation )
  {
    this.orientation = orientation;
  }

  public void setCenterVertical ( Boolean centerVertical )
  {
    this.centerVertical = centerVertical;
  }
}
