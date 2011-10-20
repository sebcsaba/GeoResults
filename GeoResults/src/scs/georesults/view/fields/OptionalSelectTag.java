package scs.georesults.view.fields;

import scs.javax.web.taglibs.TagBase;
import javax.servlet.jsp.JspException;
import scs.javax.web.taglibs.DynamicTag;

public class OptionalSelectTag extends OptionalTagBase implements ItemCollector
{

  protected SimpleSelectTag theSelectTag;

  protected TagBase createBaseTag ( TagBase parent, boolean exists )
  {
    theSelectTag = new SimpleSelectTag();
    theSelectTag.init( parent );
    theSelectTag.setName( name );
    theSelectTag.setStyleClass( styleClass );
    theSelectTag.setStyle( style );
    theSelectTag.setValue( value );
    theSelectTag.setDisabled( disabled || !exists );
    theSelectTag.setOnchange( onchange );
    theSelectTag.setOnfocus( onfocus );
    return theSelectTag;
  }

  protected String getJSMode ()
  {
    return FieldTagUtils.OPTIONAL_FIELD_TYPE_SIMPLE;
  }

  public void printItem ( ItemTag item ) throws JspException
  {
    theSelectTag.printItem( item );
  }

}
