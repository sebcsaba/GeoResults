package scs.georesults.view.xmltable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import scs.javax.web.DynamicForm;
import scs.javax.web.request.InvalidRequestFieldException;
import scs.javax.web.taglibs.TagBase;

public abstract class XmlTagBase extends TagBase
{

  // 1 / (scale of excel metric) * (resize because of the borders)
  public static final double EXML_SIZE_SCALE = 1 / 0.375 * 0.928571429;

  public static final String XML_OUTPUT_TYPE_KEY = "XML_OUTPUT_TYPE_key";

  protected static final String TYPE_KEY = "type";

  protected static final String DEFAULT_TYPE = "xhtml";

  private String initType () throws InvalidRequestFieldException
  {
    String outputType;
    DynamicForm form = new DynamicForm( ( HttpServletRequest ) pageContext.getRequest() );
    if ( form.has( TYPE_KEY ) ) {
      outputType = form.getString( TYPE_KEY );
    } else {
      outputType = DEFAULT_TYPE;
    }
    pageContext.getRequest().setAttribute( XML_OUTPUT_TYPE_KEY, outputType );
    return outputType;
  }

  protected String getType () throws InvalidRequestFieldException
  {
    String outputType = ( String ) pageContext.getRequest().getAttribute( XML_OUTPUT_TYPE_KEY );
    if ( outputType == null ) outputType = initType();
    return outputType;
  }

  protected boolean isXml () throws InvalidRequestFieldException
  {
    return "xml".equals( getType() );
  }

  protected abstract void doPrintXmlStart () throws Exception;

  protected abstract void doPrintXhtmlStart () throws Exception;

  protected abstract void doPrintXmlEnd () throws Exception;

  protected abstract void doPrintXhtmlEnd () throws Exception;

  public int doStartTag () throws JspException
  {
    try {
      initType();
      if ( isXml() ) {
        doPrintXmlStart();
      } else {
        doPrintXhtmlStart();
      }
      out.closeOpenTag();
      return EVAL_BODY_INCLUDE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  public int doEndTag () throws JspException
  {
    try {
      if ( isXml() ) {
        doPrintXmlEnd();
      } else {
        doPrintXhtmlEnd();
      }
      out.closeOpenTag();
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

}
