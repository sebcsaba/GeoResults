package scs.georesults.view.fields;

import javax.servlet.jsp.JspException;
import scs.javax.io.IOException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.formelements.RadioTag;

public class SimpleRadiogroupTag extends SimpleFieldTag implements ItemCollector
{

  protected boolean disabled;

  protected String onchange;

  protected String onfocus;

  protected TagBase createMainTag () throws JspException
  {
    TagBase emptyTag = new TagBase();
    emptyTag.init( this );
    return emptyTag;
  }

  public void printItem ( ItemTag item ) throws JspException
  {
    try {
      out.startTag( "p" );
      String id = name + "_" + item.getValue();
      RadioTag radioTag = new RadioTag();
      radioTag.init( this );
      radioTag.setName( name );
      radioTag.setValue( item.getValue() );
      radioTag.setId( id );
      radioTag.setDisabled( disabled || item.isDisabled() );
      radioTag.setChecked( value != null && value.equals( item.getValue() ) );
      if ( onchange != null ) {
        radioTag.setOnchange( onchange );
      } else {
        String s = FieldTagUtils.getChangeJS( FieldTagUtils.CHANGEABLE_FIELD_TYPE_SIMPLE, pageContext );
        radioTag.setOnchange( s );
      }
      if ( onfocus != null ) {
        radioTag.setOnfocus( onfocus );
      } else {
        radioTag.setOnfocus( FieldTagUtils.getUnFocusJS() );
      }
      out.startTagWithParam( "label", "for", id );
      out.closeOpenTag();
      new DynamicTag( radioTag ).eval();
      out.writeString( item.getPrintedCaption() );
      out.endTag( "label" );
      out.endTag( "p" );
    }
    catch ( IOException ex ) {
      throw new JspException( ex );
    }
    catch ( SessionTimeoutException ex ) {
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
