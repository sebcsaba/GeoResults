package scs.javax.web.taglibs.common;

import javax.servlet.jsp.JspException;
import scs.javax.utils.StringUtils;
import scs.javax.web.taglibs.TagBase;

public class WriteTag extends TagBase
{

  private String key;

  private String label;

  private String caption;

  private boolean escapeXml = true;

  public int doEndTag () throws JspException
  {
    try {
      String str = WriteTagHelper.getTitleOrLabelOrCaption( pageContext, key, label, caption );
      if ( escapeXml ) str = StringUtils.escapeXml( str );
      out.writeString( str );
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  public void setEscapeXml ( boolean escapeXml )
  {
    this.escapeXml = escapeXml;
  }

  public void setLabel ( String label )
  {
    this.label = label;
  }

  public void setKey ( String key )
  {
    this.key = key;
  }

  public void setCaption ( String caption )
  {
    this.caption = caption;
  }

}
