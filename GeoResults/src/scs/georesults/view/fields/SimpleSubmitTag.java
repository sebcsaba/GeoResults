package scs.georesults.view.fields;

import javax.servlet.jsp.JspException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.formelements.SubmitTag;
import scs.georesults.common.szotar.GlobalSzotar;

public class SimpleSubmitTag extends SimpleFieldTag
{

  protected String title;

  protected String onfocus;

  protected String accesskey;

  protected String popuptitle;

  protected TagBase createMainTag () throws JspException, SessionTimeoutException
  {
    SubmitTag submitTag = new SubmitTag();
    submitTag.init( this );
    submitTag.setId( name );
    submitTag.setName( name );
    submitTag.setStyleClass( styleClass );
    submitTag.setStyle( style );
    submitTag.setValue( GlobalSzotar.resolve( pageContext, title ) );
    submitTag.setTitle( popuptitle );
    submitTag.setAccesskey( accesskey );
    if ( onfocus != null ) {
      submitTag.setOnfocus( onfocus );
    } else {
      submitTag.setOnfocus( FieldTagUtils.getFocusJS() );
    }
    return submitTag;
  }

  public void setTitle ( String title )
  {
    this.title = title;
  }

  public void setOnfocus ( String onfocus )
  {
    this.onfocus = onfocus;
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
