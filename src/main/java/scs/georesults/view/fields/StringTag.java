package scs.georesults.view.fields;

import scs.javax.web.taglibs.TagBase;
import scs.georesults.common.szotar.VersenyResolver;
import scs.georesults.common.szotar.VersenySzotar;

public class StringTag extends CompoundFieldTagBase
{

  protected Integer maxlength;

  protected TagBase createMainTag () throws Exception
  {
    String dictValue = null;
    if ( value != null ) {
      String strValue = value.toString();
      if ( VersenyResolver.shouldBeResolved( strValue ) ) {
        dictValue = strValue;
        value = VersenySzotar.resolve( pageContext, strValue );
      }
    }
    tagsAfter.addChild( createHiddenDictTag( this, dictValue ) );
    return createDisplayStringTag( this, value, dictValue != null );
  }

  private TagBase createDisplayStringTag ( TagBase parent, Object displayValue, boolean fromDictionary )
  {
    String styleStr = style;
    if ( fromDictionary ) {
      if ( styleStr == null ) styleStr = "";
      styleStr = "background:whitesmoke;" + styleStr;
    }
    SimpleStringTag simpleStringTag = new SimpleStringTag();
    simpleStringTag.init( parent );
    simpleStringTag.setName( name );
    simpleStringTag.setStyleClass( styleClass );
    simpleStringTag.setStyle( styleStr );
    simpleStringTag.setMaxlength( maxlength );
    simpleStringTag.setDisabled( disabled );
    simpleStringTag.setOnchange( onchange );
    simpleStringTag.setOnfocus( onfocus );
    simpleStringTag.setValue( displayValue );
    return simpleStringTag;
  }

  private TagBase createHiddenDictTag ( TagBase parent, String dictValue )
  {
    SimpleHiddenTag hiddenTag = new SimpleHiddenTag();
    hiddenTag.init( parent );
    hiddenTag.setName( name + "_dict" );
    hiddenTag.setValue( dictValue );
    return hiddenTag;
  }

  public void setMaxlength ( Integer maxlength )
  {
    this.maxlength = maxlength;
  }

}
