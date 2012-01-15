package scs.georesults.common.iotools.exml;

import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;

public class Border implements XmlContent
{

  public static final String POSITION_LEFT = "Left";

  public static final String POSITION_TOP = "Top";

  public static final String POSITION_RIGHT = "Right";

  public static final String POSITION_BOTTOM = "Bottom";

  public static final String POSITION_DIAGONALLEFT = "DiagonalLeft";

  public static final String POSITION_DIAGONALRIGHT = "DiagonalRight";

  public static final String LINESTYLE_NONE = "None";

  public static final String LINESTYLE_CONTINUOUS = "Continuous";

  public static final String LINESTYLE_DASH = "Dash";

  public static final String LINESTYLE_DOT = "Dot";

  public static final String LINESTYLE_DASHDOT = "DashDot";

  public static final String LINESTYLE_DASHDOTDOT = "DashDotDot";

  public static final String LINESTYLE_SLANTDASHDOT = "SlantDashDot";

  public static final String LINESTYLE_DOUBLE = "Double";

  private String position;

  private String color;

  private String linestyle;

  private Double weight;

  public Border ()
  {}

  public Border ( String position, double width )
  {
    this.position = position;
    this.linestyle = Border.LINESTYLE_CONTINUOUS;
    this.weight = new Double( width );
  }

  public void print ( XmlWriter out ) throws IOException
  {
    out.startTag( "Border" );
    out.writeParam( "ss:Position", position );
    if ( color != null ) out.writeParam( "ss:Color", color );
    if ( linestyle != null ) out.writeParam( "ss:LineStyle", linestyle );
    if ( weight != null ) out.writeParam( "ss:Weight", weight.toString() );
    out.endTag( "Border" );
  }

  public void setColor ( String color )
  {
    this.color = color;
  }

  public void setWeight ( Double weight )
  {
    this.weight = weight;
  }

  public void setWeight ( double weight )
  {
    this.weight = new Double( weight );
  }

  public void setPosition ( String position )
  {
    this.position = position;
  }

  public void setLinestyle ( String linestyle )
  {
    this.linestyle = linestyle;
  }

}
