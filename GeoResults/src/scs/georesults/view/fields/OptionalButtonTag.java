package scs.georesults.view.fields;

import scs.javax.web.taglibs.TagBase;

public class OptionalButtonTag extends OptionalTagBase
{

  protected String title;

  protected String onclick;

  protected String accesskey;

  protected String popuptitle;

  protected TagBase createBaseTag ( TagBase parent, boolean exists )
  {
    SimpleButtonTag buttonTag = new SimpleButtonTag();
    buttonTag.init( parent );
    buttonTag.setName( name );
    buttonTag.setStyleClass( styleClass );
    buttonTag.setStyle( style );
    buttonTag.setTitle( title );
    buttonTag.setDisabled( disabled || !exists );
    buttonTag.setOnclick( onclick );
    buttonTag.setOnfocus( onfocus );
    buttonTag.setPopuptitle( popuptitle );
    buttonTag.setAccesskey( accesskey );
    return buttonTag;
  }

  protected String getJSMode ()
  {
    return FieldTagUtils.OPTIONAL_FIELD_TYPE_SIMPLE;
  }

  public void setTitle ( String title )
  {
    this.title = title;
  }

  public void setOnclick ( String onclick )
  {
    this.onclick = onclick;
  }

  public void setPopuptitle ( String popuptitle )
  {
    this.popuptitle = popuptitle;
  }

  public void setAccesskey ( String accesskey )
  {
    this.accesskey = accesskey;
  }

}
