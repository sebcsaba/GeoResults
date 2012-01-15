package scs.georesults.view.fields;

import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import scs.javax.dii.DIIException;
import scs.javax.dii.PropertyUtils;
import scs.javax.io.IOException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.formelements.TextareaTag;
import scs.georesults.common.szotar.VersenyResolver;

public class SimpleTextareaTag extends SimpleFieldTag
{

  protected boolean disabled;

  protected String onchange;

  protected String onfocus;

  protected String itemProperty;

  protected int rows;

  protected int cols;

  protected TagBase createMainTag () throws JspException, SessionTimeoutException
  {
    TextareaTag textareaTag = new TextareaTag();
    textareaTag.init( this );
    textareaTag.setId( name );
    textareaTag.setName( name );
    textareaTag.setStyleClass( styleClass );
    textareaTag.setStyle( style );
    textareaTag.setRows( rows );
    textareaTag.setCols( cols );
    textareaTag.setDisabled( disabled );
    if ( onchange != null ) {
      textareaTag.setOnchange( onchange );
    } else {
      String s = FieldTagUtils.getChangeJS( FieldTagUtils.CHANGEABLE_FIELD_TYPE_TEXT, pageContext );
      textareaTag.setOnchange( s );
      textareaTag.setOnkeypress( s );
    }
    if ( onfocus != null ) {
      textareaTag.setOnfocus( onfocus );
    } else {
      textareaTag.setOnfocus( FieldTagUtils.getUnFocusJS() );
    }
    return textareaTag;
  }

  public int doEndTag () throws JspException
  {
    try {
      printValues();
      return super.doEndTag();
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  private void printValues () throws DIIException, IOException, JspException
  {
    if ( value instanceof Collection ) {
      for ( Iterator it = ( ( Collection ) value ).iterator(); it.hasNext(); ) {
        Object item = it.next();
        if ( itemProperty != null ) item = PropertyUtils.getProperty( item, itemProperty );
        if ( item != null ) writeln( item.toString() );
      }
    } else {
      if ( value != null ) writeln( value.toString() );
    }
  }

  private void writeln ( String str ) throws IOException, JspException
  {
    out.writeString( VersenyResolver.doResolve( pageContext, str ) + '\n' );
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

  public void setItemProperty ( String itemProperty )
  {
    this.itemProperty = itemProperty;
  }

  public void setRows ( int rows )
  {
    this.rows = rows;
  }

  public void setCols ( int cols )
  {
    this.cols = cols;
  }

}
