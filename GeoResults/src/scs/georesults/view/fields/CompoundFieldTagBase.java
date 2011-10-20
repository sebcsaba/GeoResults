package scs.georesults.view.fields;

import java.util.Collection;
import javax.servlet.jsp.JspException;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.common.WriteTag;

public abstract class CompoundFieldTagBase extends TagBase
{

  protected String name;

  protected Object value;

  protected String styleClass;

  protected String style;

  protected boolean disabled;

  protected String onchange;

  protected String onfocus;

  protected DynamicTag tagsBefore;

  protected TagBase mainTag;

  protected DynamicTag tagsAfter;

  public int doStartTag () throws JspException
  {
    try {
      tagsBefore = new DynamicTag( pageContext, new TagBase() );
      tagsAfter = new DynamicTag( pageContext, new TagBase() );
      mainTag = createMainTag();
      tagsBefore.eval();
      return mainTag.doStartTag();
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  public int doAfterBody () throws JspException
  {
    return mainTag.doAfterBody();
  }

  public int doEndTag () throws JspException
  {
    int result = mainTag.doEndTag();
    tagsAfter.eval();
    return result;
  }

  protected abstract TagBase createMainTag () throws Exception;

  protected TagBase createTextOnlyTag ( TagBase parent, String str )
  {
    WriteTag textTag = new WriteTag();
    textTag.init( parent );
    textTag.setLabel( str );
    return textTag;
  }

  protected TagBase createIntegerField ( TagBase parent, String postfix, int intValue, int maxLength )
  {
    SimpleIntegerTag intTag = new SimpleIntegerTag();
    intTag.init( parent );
    intTag.setName( name + postfix );
    if ( styleClass != null ) intTag.setStyleClass( styleClass + postfix );
    intTag.setStyle( style );
    intTag.setDisabled( disabled );
    intTag.setMaxlength( maxLength );
    intTag.setValue( Integer.toString( intValue ) );
    intTag.setOnchange( onchange );
    intTag.setOnfocus( onfocus );
    return intTag;
  }

  protected TagBase createSelectField ( TagBase parent, String postfix, String value, Collection items )
  {
    SimpleSelectTag selectTag = new SimpleSelectTag();
    selectTag.init( parent );
    selectTag.setName( name + postfix );
    if ( styleClass != null ) selectTag.setStyleClass( styleClass + postfix );
    selectTag.setStyle( style );
    selectTag.setDisabled( disabled );
    selectTag.setValue( value );
    selectTag.setOnchange( onchange );
    selectTag.setOnfocus( onfocus );
    DynamicTag dynSelectTag = new DynamicTag( selectTag );
    dynSelectTag.addChild( createItemsTag( items, selectTag ) );
    return dynSelectTag;
  }

  protected ItemsTag createItemsTag ( Collection items, TagBase parent )
  {
    ItemsTag itemsTag = new ItemsTag();
    itemsTag.init( parent );
    itemsTag.setItems( items );
    itemsTag.setLabelPropertyName( "label" );
    itemsTag.setValuePropertyName( "value" );
    return itemsTag;
  }

  public void setName ( String name )
  {
    this.name = name;
  }

  public void setValue ( Object value )
  {
    this.value = value;
  }

  public void setStyleClass ( String styleClass )
  {
    this.styleClass = styleClass;
  }

  public void setStyle ( String style )
  {
    this.style = style;
  }

  public void setDisabled ( boolean disabled )
  {
    this.disabled = disabled;
  }

  public void setOnfocus ( String onfocus )
  {
    this.onfocus = onfocus;
  }

  public void setOnchange ( String onchange )
  {
    this.onchange = onchange;
  }

}
