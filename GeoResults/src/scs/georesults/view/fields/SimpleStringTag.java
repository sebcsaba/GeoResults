package scs.georesults.view.fields;

import javax.servlet.jsp.JspException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.formelements.TextTag;

public class SimpleStringTag extends SimpleFieldTag
{

  protected Integer maxlength;

  protected boolean disabled;

  protected String onchange;

  protected String onfocus;

  protected String onblur;

  protected TagBase createMainTag () throws JspException, SessionTimeoutException
  {
    TextTag textTag = new TextTag();
    textTag.init( this );
    textTag.setId( name );
    textTag.setName( name );
    textTag.setStyleClass( styleClass );
    textTag.setStyle( style );
    textTag.setValue( value == null ? "" : value.toString() );
    textTag.setMaxlength( maxlength );
    textTag.setDisabled( disabled );
    if ( onchange != null ) {
      textTag.setOnchange( onchange );
      textTag.setOnkeypress( onchange );
    } else {
      String s = FieldTagUtils.getChangeJS( FieldTagUtils.CHANGEABLE_FIELD_TYPE_TEXT, pageContext );
      textTag.setOnchange( s );
      textTag.setOnkeypress( s );
    }
    if ( onfocus != null ) {
      textTag.setOnfocus( onfocus );
    } else {
      textTag.setOnfocus( FieldTagUtils.getFocusJS() );
    }
    textTag.setOnblur( onblur );
    return textTag;
  }

  public void setMaxlength ( Integer maxlength )
  {
    this.maxlength = maxlength;
  }

  public void setDisabled ( boolean disabled )
  {
    this.disabled = disabled;
  }

  public void setOnchange ( String onchange )
  {
    this.onchange = onchange;
  }

  public void setOnfocus ( String onfocus )
  {
    this.onfocus = onfocus;
  }

  public void setOnblur ( String onblur )
  {
    this.onblur = onblur;
  }

}
