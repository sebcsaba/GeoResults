package scs.georesults.view.fields;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import scs.javax.web.SessionTimeoutException;
import scs.georesults.common.szotar.GlobalSzotar;


public class FieldTagUtils
{

  public static final String CHANGEABLE_FIELD_TYPE_TEXT = "Text";

  public static final String CHANGEABLE_FIELD_TYPE_INTEGER = "Integer";

  public static final String CHANGEABLE_FIELD_TYPE_SIMPLE = "Simple";

  public static final String OPTIONAL_FIELD_TYPE_SIMPLE = "Simple";

  public static final String OPTIONAL_FIELD_TYPE_DATE = "Date";

  public static final String OPTIONAL_FIELD_TYPE_TIME = "Time";

  private FieldTagUtils ()
  {}

  public static String getModifiedStr ( PageContext pageContext ) throws JspException, SessionTimeoutException
  {
    return GlobalSzotar.resolve( pageContext, "BS_MODOSITVA" );
  }

  public static String getChangeJS ( String type, PageContext pageContext ) throws JspException, SessionTimeoutException
  {
    return "return doChange" + type + "(this,event,'" + getModifiedStr( pageContext ) + "');";
  }

  public static String getFocusJS ()
  {
    return "doFocus(this);";
  }

  public static String getUnFocusJS ()
  {
    return "doUnFocus();";
  }

}
