package scs.javax.web;

import javax.servlet.jsp.PageContext;

public class RequestBeanBase
{

  protected PageContext pageContext;

  public void setPageContext ( PageContext pageContext ) throws Exception
  {
    this.pageContext = pageContext;
    init();
  }

  public void init () throws Exception
  {}

  public void setReleasePageContext ( Object o )
  {
    pageContext = null;
  }

  public void releasePageContext ()
  {
    pageContext = null;
  }

}
