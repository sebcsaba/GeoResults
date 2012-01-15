package scs.georesults.view.fields;

import javax.servlet.jsp.JspException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.formelements.ButtonTag;
import scs.georesults.common.szotar.GlobalSzotar;

public class SimpleButtonTag extends SimpleFieldTag
{

  protected String title;

  protected String onclick;

  protected String onfocus;

  protected boolean disabled;

  protected String accesskey;

  protected String popuptitle;

  protected TagBase createMainTag () throws JspException, SessionTimeoutException
  {
    ButtonTag buttonTag = new ButtonTag();
    buttonTag.init( this );
    buttonTag.setId( name );
    buttonTag.setStyleClass( styleClass );
    buttonTag.setStyle( style );
    buttonTag.setValue( GlobalSzotar.resolve( pageContext, title ) );
    buttonTag.setOnclick( onclick );
    buttonTag.setDisabled( disabled );
    buttonTag.setTitle( popuptitle );
    buttonTag.setAccesskey( accesskey );
    if ( onfocus != null ) {
      buttonTag.setOnfocus( onfocus );
    } else {
      buttonTag.setOnfocus( FieldTagUtils.getFocusJS() );
    }
    return buttonTag;
  }

  public void setDisabled ( boolean disabled )
  {
    this.disabled = disabled;
  }

  public void setTitle ( String title )
  {
    this.title = title;
  }

  public void setOnfocus ( String onfocus )
  {
    this.onfocus = onfocus;
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
