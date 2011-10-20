package scs.javax.web.taglibs.formelements;

import scs.javax.io.IOException;
import scs.javax.web.taglibs.TagBaseWithXhtmlFocusAttributes;

public class SelectTag extends TagBaseWithXhtmlFocusAttributes
{

  protected String name;

  protected Integer size;

  protected boolean multiple;

  protected boolean disabled;

  protected String onchange;

  protected void doPrintHeader () throws IOException
  {
    accesskey = null;
    out.startTag( "select" );
    writeExistFocusAttributes();
    if ( name != null ) out.writeParam( "name", name );
    if ( size != null ) out.writeParam( "size", size.toString() );
    if ( multiple ) out.writeParam( "multiple", "multiple" );
    if ( disabled ) out.writeParam( "disabled", "disabled" );
    if ( onchange != null ) out.writeParam( "onchange", onchange );
    out.closeOpenTag();
  }

  protected void doPrintFooter () throws IOException
  {
    out.endTag( "select" );
  }

  public void setName ( String name )
  {
    this.name = name;
  }

  public void setSize ( Integer size )
  {
    this.size = size;
  }

  public void setMultiple ( boolean multiple )
  {
    this.multiple = multiple;
  }

  public void setDisabled ( boolean disabled )
  {
    this.disabled = disabled;
  }

  public void setOnchange ( String onchange )
  {
    this.onchange = onchange;
  }


  /*
            %attrs;
   //SCS:HACK: %focus; used, but accesskey cleared.
            name        CDATA          #IMPLIED
            size        %Number;       #IMPLIED
            multiple    (multiple)     #IMPLIED
            disabled    (disabled)     #IMPLIED
            tabindex    %Number;       #IMPLIED
            onfocus     %Script;       #IMPLIED
            onblur      %Script;       #IMPLIED
            onchange    %Script;       #IMPLIED
   */

}
