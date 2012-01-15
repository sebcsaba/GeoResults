package scs.georesults.view.fields;

import javax.servlet.jsp.JspException;
import scs.javax.io.IOException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.formelements.OptionTag;
import scs.javax.web.taglibs.formelements.SelectTag;

public class SimpleSelectTag extends SimpleFieldTag implements ItemCollector
{

  protected boolean disabled;

  protected String onchange;

  protected String onfocus;

  protected TagBase createMainTag () throws JspException, SessionTimeoutException
  {
    SelectTag selectTag = new SelectTag();
    selectTag.init( this );
    selectTag.setId( name );
    selectTag.setName( name );
    selectTag.setStyleClass( styleClass );
    selectTag.setStyle( style );
    selectTag.setDisabled( disabled );
    if ( onchange != null ) {
      selectTag.setOnchange( onchange );
    } else {
      String s = FieldTagUtils.getChangeJS( FieldTagUtils.CHANGEABLE_FIELD_TYPE_SIMPLE, pageContext );
      selectTag.setOnchange( s );
    }
    if ( onfocus != null ) {
      selectTag.setOnfocus( onfocus );
    } else {
      selectTag.setOnfocus( FieldTagUtils.getUnFocusJS() );
    }
    return selectTag;
  }

  public void printItem ( ItemTag item ) throws JspException
  {
    try {
      OptionTag optionTag = new OptionTag();
      optionTag.init( this );
      optionTag.setValue( item.getValue() );
      optionTag.setDisabled( item.isDisabled() );
      optionTag.setSelected( value != null && value.toString().equals( item.getValue() ) );
      optionTag.doStartTag();
      out.writeString( item.getPrintedCaption() );
      optionTag.doEndTag();
    }
    catch ( IOException ex ) {
      throw new JspException( ex );
    }
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
