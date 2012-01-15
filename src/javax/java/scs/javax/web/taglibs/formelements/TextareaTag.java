package scs.javax.web.taglibs.formelements;

import scs.javax.io.IOException;
import scs.javax.web.taglibs.TagBaseWithXhtmlFocusAttributes;

public class TextareaTag extends TagBaseWithXhtmlFocusAttributes
{

  protected String name;

  protected int rows;

  protected int cols;

  protected boolean disabled;

  protected boolean readonly;

  protected String onselect;

  protected String onchange;

  protected void doPrintHeader () throws IOException
  {
    out.startTag( "textarea" );
    writeExistFocusAttributes();
    if ( name != null ) out.writeParam( "name", name );
    out.writeParam( "rows", Integer.toString( rows ) );
    out.writeParam( "cols", Integer.toString( cols ) );
    if ( disabled ) out.writeParam( "disabled", "disabled" );
    if ( readonly ) out.writeParam( "readonly", "readonly" );
    if ( onselect != null ) out.writeParam( "onselect", onselect );
    if ( onchange != null ) out.writeParam( "onchange", onchange );
    out.closeOpenTag();
  }

  protected void doPrintFooter () throws IOException
  {
    out.endTag( "textarea" );
  }

  public void setName ( String name )
  {
    this.name = name;
  }

  public void setRows ( int rows )
  {
    this.rows = rows;
  }

  public void setCols ( int cols )
  {
    this.cols = cols;
  }

  public void setDisabled ( boolean disabled )
  {
    this.disabled = disabled;
  }

  public void setReadonly ( boolean readonly )
  {
    this.readonly = readonly;
  }

  public void setOnselect ( String onselect )
  {
    this.onselect = onselect;
  }

  public void setOnchange ( String onchange )
  {
    this.onchange = onchange;
  }

  /*
             %attrs;
            %focus;
            name        CDATA          #IMPLIED
            rows        %Number;       #REQUIRED
            cols        %Number;       #REQUIRED
            disabled    (disabled)     #IMPLIED
            readonly    (readonly)     #IMPLIED
            onselect    %Script;       #IMPLIED
            onchange    %Script;       #IMPLIED
   */

}
