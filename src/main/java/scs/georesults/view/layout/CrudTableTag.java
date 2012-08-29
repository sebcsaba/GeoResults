package scs.georesults.view.layout;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import scs.javax.dii.PropertyUtils;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.common.ForEachTag;
import scs.javax.web.taglibs.html.ATag;
import scs.javax.web.taglibs.html.ImgTag;
import scs.georesults.view.WebUtils;

public class CrudTableTag extends LayoutTagBase
{

  protected String title;

  protected String action;

  protected String idfield;

  protected Collection items;

  protected String var;

  protected boolean doHeader;

  protected boolean deleteDisabled;

  protected ForEachTag foreachTag;

  protected String stylePath;

  public int doAfterBody () throws JspException
  {
    try {
      if ( doHeader ) {
        printAfterHeaderRow();
        doHeader = false;
        int result = foreachTag.doStartTag();
        if ( result == EVAL_BODY_INCLUDE ) {
          printBeforeRow();
          return EVAL_BODY_AGAIN;
        } else {
          return EVAL_PAGE;
        }
      } else {
        printAfterRow();
        int result = foreachTag.doAfterBody();
        if ( result == EVAL_BODY_AGAIN ) {
          printBeforeRow();
          return EVAL_BODY_AGAIN;
        } else {
          return result;
        }
      }
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  protected void printHeader () throws Exception
  {
    stylePath = WebUtils.getStylePath( pageContext );
    foreachTag = new ForEachTag();
    foreachTag.init( this );
    foreachTag.setItems( items );
    foreachTag.setVar( var );
    printTableHeader();
    printBeforeHeaderRow();
    doHeader = true;
  }

  protected void printFooter () throws Exception
  {
    printTableFooter();
  }

  public void printTableHeader () throws Exception
  {
    doPrintPanelHeader( globalSzotar.getValue( title ) );
    out.startTagWithParam( "table", "class", "crudTable" );
  }

  public void printTableFooter () throws Exception
  {
    out.endTag( "table" );
    doPrintPanelFooter();
  }

  public void printBeforeHeaderRow () throws Exception
  {
    out.startTag( "tr" );
    out.closeOpenTag();
  }

  public void printAfterHeaderRow () throws Exception
  {
    int plusCols = ( deleteDisabled ? 1 : 2 );
    for ( int i = 0; i < plusCols; ++i ) {
      out.startTagWithParam( "th", "class", "crudIconCol crudHeader" );
      out.closeOpenTag();
      out.endTag( "th" );
    }
    out.endTag( "tr" );
  }

  public void printBeforeRow () throws Exception
  {
    out.startTag( "tr" );
    out.closeOpenTag();
  }

  public void printAfterRow () throws Exception
  {
    String id = getRowId();
    printImageLinkCell( "edit", "BS_SZERKESZTES", id, false );
    if ( !deleteDisabled ) printImageLinkCell( "delete", "BS_TORLES", id, true );
    out.endTag( "tr" );
  }

  private void printImageLinkCell ( String mode, String title, String id, boolean confirm ) throws Exception
  {
    out.startTagWithParam( "td", "class", "crudIconCol" );
    out.closeOpenTag();
    printImageLink( mode, title, id, confirm );
    out.endTag( "td" );
  }

  protected void printIcons () throws Exception
  {
    printImageLink( "new", "BS_UJ", null, false );
    if ( !deleteDisabled ) printImageLink( "delete", "FT_MINDET_TORLI", null, true );
  }

  private void printImageLink ( String mode, String title, String id, boolean confirm ) throws Exception
  {
    HttpServletRequest request = ( HttpServletRequest ) pageContext.getRequest();
    String contextPath = request.getContextPath();
    if (contextPath.equals("/")) {
    	contextPath = "";
    }
	StringBuffer url = new StringBuffer( contextPath );
    url.append( action ).append( "?mode=" ).append( mode );
    if ( id != null ) url.append( "&id=" ).append( id );

    ImgTag img = new ImgTag();
    img.init( this );
    img.setSrc( stylePath + mode + ".gif" );
    img.setStyleClass( "icon" );
    img.setTitle( globalSzotar.getValue( title ) );

    ATag a = new ATag();
    a.init( this );
    a.setHref( url.toString() );
    if ( confirm ) {
      String keyStr = "FT_TORLES_RAKERDEZES";
      if ( id == null ) keyStr = "FT_MINDENT_TORLES_RAKERDEZES";
      String confStr = globalSzotar.getValue( keyStr );
      a.setOnclick( "return confirm('" + confStr + "');" );
    }
    DynamicTag dt = new DynamicTag( a );
    dt.addChild( img );
    dt.eval();
  }

  private String getRowId () throws JspException
  {
    try {
      Object base = pageContext.getAttribute( var, PageContext.REQUEST_SCOPE );
      Object id = PropertyUtils.getProperty( base, idfield );
      return id.toString();
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  public void setAction ( String action )
  {
    this.action = action;
  }

  public void setVar ( String var )
  {
    this.var = var;
  }

  public void setTitle ( String title )
  {
    this.title = title;
  }

  public void setItems ( Collection items )
  {
    this.items = items;
  }

  public void setIdfield ( String idfield )
  {
    this.idfield = idfield;
  }

  public void setForeachTag ( ForEachTag foreachTag )
  {
    this.foreachTag = foreachTag;
  }

  public void setDeleteDisabled ( boolean deleteDisabled )
  {
    this.deleteDisabled = deleteDisabled;
  }

  public boolean isDoHeader ()
  {
    return doHeader;
  }

}
