package scs.georesults.view.fields;

import javax.servlet.jsp.JspException;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.common.WriteTagHelper;

public class ItemTag extends TagBase
{

  protected String value;

  protected String title;

  protected String label;

  protected String caption;

  protected boolean rendered = true;

  protected boolean disabled;

  public ItemTag ()
  {}

  public ItemTag ( String value, String label )
  {
    this.value = value;
    this.label = label;
  }

  public int doEndTag () throws JspException
  {
    if ( rendered ) {
      if ( getParent() instanceof ItemCollector ) {
        ( ( ItemCollector ) getParent() ).printItem( this );
      }
    }
    return EVAL_PAGE;
  }

  public void setDisabled ( boolean disabled )
  {
    this.disabled = disabled;
  }

  public void setValue ( String value )
  {
    this.value = value;
  }

  public void setRendered ( boolean rendered )
  {
    this.rendered = rendered;
  }

  public void setLabel ( String label )
  {
    this.label = label;
  }

  public void setTitle ( String title )
  {
    this.title = title;
  }

  public void setCaption ( String caption )
  {
    this.caption = caption;
  }

  public String getValue ()
  {
    return value;
  }

  public boolean isDisabled ()
  {
    return disabled;
  }

  public String getPrintedCaption () throws JspException
  {
    return WriteTagHelper.getTitleOrLabelOrCaption( pageContext, title, label, caption );
  }

}
