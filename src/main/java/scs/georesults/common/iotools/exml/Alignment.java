package scs.georesults.common.iotools.exml;

import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;

public class Alignment implements XmlContent
{

  public static final String ALIGN_H_FILL = "Fill";

  public static final String ALIGN_H_LEFT = "Left";

  public static final String ALIGN_H_RIGHT = "Right";

  public static final String ALIGN_H_JUSTIFY = "Justify";

  public static final String ALIGN_H_DISTRIBUTED = "Distributed";

  public static final String ALIGN_H_CENTER = "Center";

  public static final String ALIGN_H_AUTOMATIC = "Automatic";

  public static final String ALIGN_H_JUSTIFYDISTRIBUTED = "JustifyDistributed";

  public static final String ALIGN_V_AUTOMATIC = "Automatic";

  public static final String ALIGN_V_TOP = "Top";

  public static final String ALIGN_V_BOTTOM = "Bottom";

  public static final String ALIGN_V_CENTER = "Center";

  public static final String ALIGN_V_JUSTIFY = "Justify";

  public static final String ALIGN_V_DISTRIBUTED = "Distributed";

  public static final String ALIGN_V_JUSTIFYDISTRIBUTED = "JustifyDistributed";

  private String horizontal;

  private String vertical;

  public Alignment ()
  {}

  public Alignment ( String horizontal, String vertical )
  {
    this.horizontal = horizontal;
    this.vertical = vertical;
  }

  public void print ( XmlWriter out ) throws IOException
  {
    out.startTag( "Alignment" );
    if ( horizontal != null ) out.writeParam( "ss:Horizontal", horizontal );
    if ( vertical != null ) out.writeParam( "ss:Vertical", vertical );
    out.endTag( "Alignment" );
  }

  public void setHorizontal ( String horizontal )
  {
    this.horizontal = horizontal;
  }

  public void setVertical ( String vertical )
  {
    this.vertical = vertical;
  }

}
