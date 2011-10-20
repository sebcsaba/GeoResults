package scs.georesults.view.fields;

import javax.servlet.jsp.JspException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.formelements.TextTag;

public class SimpleIntegerTag extends SimpleFieldTag
{

  protected int maxlength = 9;

  protected boolean disabled;

  protected String onchange;

  protected String onfocus;

  protected TagBase createMainTag () throws JspException, SessionTimeoutException
  {
    TextTag textTag = new TextTag();
    textTag.init( this );
    textTag.setId( name );
    textTag.setName( name );
    textTag.setStyleClass( styleClass );
    textTag.setStyle( style );
    textTag.setValue( value == null ? "0" : value.toString() );
    textTag.setMaxlength( new Integer( maxlength ) );
    textTag.setDisabled( disabled );
    if ( onchange != null ) {
      textTag.setOnchange( onchange );
    } else {
      String s = FieldTagUtils.getChangeJS( FieldTagUtils.CHANGEABLE_FIELD_TYPE_INTEGER, pageContext );
      textTag.setOnchange( s );
      textTag.setOnkeypress( s );
    }
    if ( onfocus != null ) {
      textTag.setOnfocus( onfocus );
    } else {
      textTag.setOnfocus( FieldTagUtils.getUnFocusJS() );
    }
    return textTag;
  }

  public void setMaxlength ( int maxlength )
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

}
