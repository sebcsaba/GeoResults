package scs.georesults.view.fields;

import scs.javax.web.taglibs.TagBase;

public class OptionalIntegerTag extends OptionalTagBase
{

  protected TagBase createBaseTag ( TagBase parent, boolean exists )
  {
    SimpleIntegerTag intTag = new SimpleIntegerTag();
    intTag.init( parent );
    intTag.setName( name );
    intTag.setStyleClass( styleClass );
    intTag.setStyle( style );
    intTag.setValue( value );
    intTag.setDisabled( disabled || !exists );
    intTag.setOnchange( onchange );
    intTag.setOnfocus( onfocus );
    return intTag;
  }

  protected String getJSMode ()
  {
    return FieldTagUtils.OPTIONAL_FIELD_TYPE_SIMPLE;
  }

}
