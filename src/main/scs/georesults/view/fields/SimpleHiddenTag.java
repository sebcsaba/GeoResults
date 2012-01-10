package scs.georesults.view.fields;

import javax.servlet.jsp.JspException;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.formelements.HiddenTag;

public class SimpleHiddenTag extends SimpleFieldTag
{

  protected TagBase createMainTag () throws JspException
  {
    HiddenTag hiddenTag = new HiddenTag();
    hiddenTag.init( this );
    hiddenTag.setId( name );
    hiddenTag.setName( name );
    hiddenTag.setValue( value == null ? "" : value.toString() );
    return hiddenTag;
  }

}
