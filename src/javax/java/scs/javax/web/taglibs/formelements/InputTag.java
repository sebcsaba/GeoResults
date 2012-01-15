package scs.javax.web.taglibs.formelements;

import scs.javax.utils.StringUtils;
import scs.javax.web.taglibs.TagBaseWithXhtmlFocusAttributes;

public abstract class InputTag extends TagBaseWithXhtmlFocusAttributes
{

  protected String type;

  protected String name;

  protected String value;

  protected boolean checked;

  protected boolean disabled;

  protected boolean readonly;

  /* deprecated protected String size; */

  protected Integer maxlength;

  protected String onselect;

  protected String onchange;

  protected String src;

  protected String alt;

  /* deprecated protected String usemap; */

  /* deprecated protected String accept; */

  protected void doPrintHeader () throws Exception
  {
    initTypeClearUnexpected();
    out.startTag( "input" );
    writeExistFocusAttributes();
    if ( type != null ) out.writeParam( "type", type );
    if ( name != null ) out.writeParam( "name", name );
    if ( value != null ) out.writeParam( "value", StringUtils.escapeXml( value ) );
    if ( checked ) out.writeParam( "checked", "checked" );
    if ( disabled ) out.writeParam( "disabled", "disabled" );
    if ( readonly ) out.writeParam( "readonly", "readonly" );
    if ( maxlength != null ) out.writeParam( "maxlength", maxlength.toString() );
    if ( onselect != null ) out.writeParam( "onselect", onselect );
    if ( onchange != null ) out.writeParam( "onchange", onchange );
    if ( src != null ) out.writeParam( "src", src );
    if ( alt != null ) out.writeParam( "alt", alt );
  }

  protected abstract void initTypeClearUnexpected ();

  protected void doPrintFooter () throws Exception
  {
    out.endTag( "input" );
  }

  public void setValue ( String value )
  {
    this.value = value;
  }

  public void setType ( String type )
  {
    this.type = type;
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

  public void setName ( String name )
  {
    this.name = name;
  }

  public void setMaxlength ( Integer maxlength )
  {
    this.maxlength = maxlength;
  }

  public void setId ( String id )
  {
    this.id = id;
  }

  public void setDisabled ( boolean disabled )
  {
    this.disabled = disabled;
  }

  public void setChecked ( boolean checked )
  {
    this.checked = checked;
  }

  public void setSrc ( String src )
  {
    this.src = src;
  }

  public void setAlt ( String alt )
  {
    this.alt = alt;
  }

  /*
    (text | password | checkbox |
    radio | submit | reset |
    file | hidden | image | button)

      %attrs;
      %focus;
      type        %InputType;    "text"
      name        CDATA          #IMPLIED
      value       CDATA          #IMPLIED
      checked     (checked)      #IMPLIED
      disabled    (disabled)     #IMPLIED
      readonly    (readonly)     #IMPLIED
      size        CDATA          #IMPLIED
      maxlength   %Number;       #IMPLIED
      onselect    %Script;       #IMPLIED
      onchange    %Script;       #IMPLIED

      src         %URI;          #IMPLIED
      alt         CDATA          #IMPLIED
      usemap      %URI;          #IMPLIED
      accept      %ContentTypes; #IMPLIED

      >

   */

}
