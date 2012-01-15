package scs.javax.web.taglibs.formelements;

import scs.javax.io.IOException;
import scs.javax.web.taglibs.TagBaseWithXhtmlBaseAttributes;

public class OptionTag extends TagBaseWithXhtmlBaseAttributes
{

  protected boolean selected;

  protected boolean disabled;

  /* deprecated protected String label; */

  protected String value;

  protected void doPrintHeader () throws IOException
  {
    out.startTag( "option" );
    writeExistBaseAttributes();
    if ( selected ) out.writeParam( "selected", "selected" );
    if ( disabled ) out.writeParam( "disabled", "disabled" );
    if ( value != null ) out.writeParam( "value", value );
    out.closeOpenTag();
  }

  protected void doPrintFooter () throws IOException
  {
    out.endTag( "option" );
  }

  public void setSelected ( boolean selected )
  {
    this.selected = selected;
  }

  public void setDisabled ( boolean disabled )
  {
    this.disabled = disabled;
  }

  public void setValue ( String value )
  {
    this.value = value;
  }

  /*
          %attrs;
          selected    (selected)     #IMPLIED
          disabled    (disabled)     #IMPLIED
          label       %Text;         #IMPLIED
          value       CDATA          #IMPLIED
   */
}
