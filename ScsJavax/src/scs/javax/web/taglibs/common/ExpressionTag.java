package scs.javax.web.taglibs.common;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import scs.javax.collections.ArrayUtils;
import scs.javax.dii.MethodUtils;
import scs.javax.web.taglibs.TagBase;

public class ExpressionTag extends TagBase
{

  private String var;

  private Object bean;

  private String method;

  private Object param1;

  private Object param2;

  private Object param3;

  public int doEndTag () throws JspException
  {
    try {
      Object[] params = createParamArray();
      Object value = MethodUtils.invokeMethodAnyType( bean, method, params );
      if ( var != null ) pageContext.setAttribute( var, value, PageContext.REQUEST_SCOPE );
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  private Object[] createParamArray ()
  {
    Object[] params = new Object[3];
    params[0] = param1;
    params[1] = param2;
    params[2] = param3;
    int i = params.length - 1;
    while ( i >= 0 && params[i] == null )--i;
    if ( i < 0 )return new Object[0];
    if ( i < params.length - 1 ) {
      Object[] result = new Object[i + 1];
      ArrayUtils.arraycopy( params, 0, result, 0, result.length );
      return result;
    } else return params;
  }

  public void setBean ( Object bean )
  {
    this.bean = bean;
  }

  public void setVar ( String var )
  {
    this.var = var;
  }

  public void setMethod ( String method )
  {
    this.method = method;
  }

  public void setParam3 ( Object param3 )
  {
    this.param3 = param3;
  }

  public void setParam2 ( Object param2 )
  {
    this.param2 = param2;
  }

  public void setParam1 ( Object param1 )
  {
    this.param1 = param1;
  }


}
