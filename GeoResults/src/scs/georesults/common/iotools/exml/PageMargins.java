package scs.georesults.common.iotools.exml;

import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;

public class PageMargins implements XmlContent
{

  private Double leftMargin;

  private Double rightMargin;

  private Double topMargin;

  private Double bottomMargin;

  public PageMargins ()
  {}

  public PageMargins ( double leftMargin, double rightMargin, double topMargin, double bottomMargin )
  {
    this.leftMargin = new Double( leftMargin );
    this.rightMargin = new Double( rightMargin );
    this.topMargin = new Double( topMargin );
    this.bottomMargin = new Double( bottomMargin );
  }

  public void print ( XmlWriter out ) throws IOException
  {
    out.startTag( "PageMargins" );
    if ( leftMargin != null ) out.writeParam( "x:Left", leftMargin.toString() );
    if ( rightMargin != null ) out.writeParam( "x:Right", rightMargin.toString() );
    if ( topMargin != null ) out.writeParam( "x:Top", topMargin.toString() );
    if ( bottomMargin != null ) out.writeParam( "x:Bottom", bottomMargin.toString() );
    out.endTag( "PageMargins" );
  }

  public void setBottomMargin ( Double bottomMargin )
  {
    this.bottomMargin = bottomMargin;
  }

  public void setTopMargin ( Double topMargin )
  {
    this.topMargin = topMargin;
  }

  public void setRightMargin ( Double rightMargin )
  {
    this.rightMargin = rightMargin;
  }

  public void setLeftMargin ( Double leftMargin )
  {
    this.leftMargin = leftMargin;
  }

}
