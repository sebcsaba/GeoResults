package scs.georesults.view.fields;

import javax.servlet.jsp.JspException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.formelements.CheckboxTag;

public class SimpleCheckboxTag extends SimpleFieldTag
{

  protected boolean disabled;

  protected String onchange;

  protected String onfocus;

  protected TagBase createMainTag () throws JspException, SessionTimeoutException
  {
    CheckboxTag checkboxTag = new CheckboxTag();
    checkboxTag.init( this );
    checkboxTag.setId( name );
    checkboxTag.setName( name );
    boolean checked = false;
    if ( value instanceof Boolean ) checked = ( ( Boolean ) value ).booleanValue();
    checkboxTag.setChecked( checked );
    checkboxTag.setDisabled( disabled );
    if ( onchange != null ) {
      checkboxTag.setOnchange( onchange );
    } else {
      String s = FieldTagUtils.getChangeJS( FieldTagUtils.CHANGEABLE_FIELD_TYPE_SIMPLE, pageContext );
      checkboxTag.setOnchange( s );
    }
    if ( onfocus != null ) {
      checkboxTag.setOnfocus( onfocus );
    } else {
      checkboxTag.setOnfocus( FieldTagUtils.getUnFocusJS() );
    }
    return checkboxTag;
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
