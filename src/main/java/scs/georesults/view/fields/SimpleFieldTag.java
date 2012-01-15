package scs.georesults.view.fields;

import javax.servlet.jsp.JspException;
import scs.javax.web.taglibs.TagBase;

public abstract class SimpleFieldTag extends TagBase
{

  protected String name;

  protected Object value;

  protected String styleClass;

  protected String style;

  protected TagBase tag;

  public int doStartTag () throws JspException
  {
    try {
      tag = createMainTag();
      return tag.doStartTag();
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  public int doAfterBody () throws JspException
  {
    return tag.doAfterBody();
  }

  public int doEndTag () throws JspException
  {
    return tag.doEndTag();
  }

  protected abstract TagBase createMainTag () throws Exception;

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

}
