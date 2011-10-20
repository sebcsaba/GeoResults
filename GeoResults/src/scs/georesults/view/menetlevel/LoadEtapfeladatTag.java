package scs.georesults.view.menetlevel;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import scs.javax.collections.List;
import scs.javax.dii.PropertyUtils;
import scs.javax.web.taglibs.TagBase;

public class LoadEtapfeladatTag extends TagBase
{

  protected List etapok;

  protected int eid;

  protected String listname;

  protected String var;

  protected String idname;

  public int doEndTag () throws JspException
  {
    try {
      long id = Long.parseLong( pageContext.getRequest().getParameter( "id" ) );
      Object etap = etapok.findItem( "eid", new Long( eid ) );
      List efl = ( List ) PropertyUtils.getProperty( etap, listname );
      Object ef = efl.findItem( idname, new Long( id ) );
      pageContext.setAttribute( var, ef, PageContext.REQUEST_SCOPE );
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  public void setEtapok ( List etapok )
  {
    this.etapok = etapok;
  }

  public void setVar ( String var )
  {
    this.var = var;
  }

  public void setListname ( String listname )
  {
    this.listname = listname;
  }

  public void setEid ( int eid )
  {
    this.eid = eid;
  }

  public void setIdname ( String idname )
  {
    this.idname = idname;
  }

}
