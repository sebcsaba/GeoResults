package scs.georesults.view.eredmeny;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.dii.PropertyUtils;
import scs.javax.io.IOException;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.common.LinkTag;
import scs.javax.web.taglibs.common.WriteTag;
import scs.georesults.common.szotar.GlobalSzotar;
import scs.georesults.view.layout.*;

public class TableTag extends TagBase
{

  protected String title;

  protected String frissitesAction;

  protected String displayAction;

  protected List items;

  protected String displayProperty;

  protected String idProperty;

  protected String frissitendoProperty;

  protected String ertekelendoProperty;

  protected String contextPath;

  protected String frissitesCaption;

  public int doEndTag () throws JspException
  {
    try {
      HttpServletRequest request = ( HttpServletRequest ) pageContext.getRequest();
      contextPath = request.getContextPath();
      frissitesCaption = GlobalSzotar.resolve( pageContext, "BS_FRISSITES" );
      printForm();
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  private TagBase createLeftLinkTag ( TagBase parent, Object item, boolean frissitendo, Long id ) throws DIIException
  {
    Object labelObject = PropertyUtils.getProperty( item, displayProperty );
    WriteTag writeTag = new WriteTag();
    writeTag.setLabel( labelObject == null ? "" : labelObject.toString() );
    if ( frissitendo ) {
      writeTag.init( parent );
      return writeTag;
    } else {
      LinkTag linkTag = new LinkTag();
      linkTag.init( parent );
      linkTag.setOnclick( "popupResults('" + getContextPath() + displayAction + "?id=" + id + "')" );
      writeTag.init( linkTag );
      DynamicTag linkDt = new DynamicTag( linkTag );
      linkDt.addChild( writeTag );
      return linkDt;
    }
  }

  private TagBase createLeftCell ( TagBase parent, Object item, boolean frissitendo, Long id ) throws DIIException
  {
    LeftcellTag leftcellTag = new LeftcellTag();
    leftcellTag.init( parent );
    DynamicTag leftcellDt = new DynamicTag( leftcellTag );
    leftcellDt.addChild( createLeftLinkTag( leftcellTag, item, frissitendo, id ) );
    return leftcellDt;
  }

  private TagBase createFrissitesLink ( TagBase parent, boolean frissitendo, Long id )
  {
    String action = frissitesAction;
    if ( id != null ) action = action + "?id=" + id.longValue();
    String styleClass = "frissites";
    if ( frissitendo ) styleClass = styleClass + " most";
    LinkTag linkTag = new LinkTag();
    linkTag.init( parent );
    linkTag.setAction( action );
    linkTag.setStyleClass( styleClass );
    WriteTag writeTag = new WriteTag();
    writeTag.init( linkTag );
    writeTag.setLabel( frissitesCaption );
    DynamicTag linkDt = new DynamicTag( linkTag );
    linkDt.addChild( writeTag );
    return linkDt;
  }

  private TagBase createRightCell ( TagBase parent, boolean frissitendo, Long id )
  {
    RightcellTag rightcellTag = new RightcellTag();
    rightcellTag.init( parent );
    DynamicTag righttcellDt = new DynamicTag( rightcellTag );
    righttcellDt.addChild( createFrissitesLink( rightcellTag, frissitendo, id ) );
    return righttcellDt;
  }

  private TagBase createRow ( TagBase parent, Object item ) throws DIIException
  {
    boolean ertekelendo = true;
    if ( ertekelendoProperty != null ) {
      Boolean er = ( Boolean ) PropertyUtils.getProperty( item, ertekelendoProperty );
      ertekelendo = er.booleanValue();
    }
    if ( ertekelendo ) {
      Long id = ( Long ) PropertyUtils.getProperty( item, idProperty );
      Boolean fr = ( Boolean ) PropertyUtils.getProperty( item, frissitendoProperty );
      RowTag rowTag = new RowTag();
      rowTag.init( parent );
      DynamicTag rowDt = new DynamicTag( rowTag );
      rowDt.addChild( createLeftCell( rowTag, item, fr.booleanValue(), id ) );
      rowDt.addChild( createRightCell( rowTag, fr.booleanValue(), id ) );
      return rowDt;
    } else return new TagBase();
  }

  private TagBase createMfLeftLabel ( TagBase parent )
  {
    WriteTag writeTag = new WriteTag();
    writeTag.setKey( "BS_OSSZES" );
    writeTag.init( parent );
    return writeTag;
  }

  private TagBase createMfLeftcell ( TagBase parent )
  {
    LeftcellTag mfLeft = new LeftcellTag();
    mfLeft.init( parent );
    DynamicTag mfLDt = new DynamicTag( mfLeft );
    mfLDt.addChild( createMfLeftLabel( mfLeft ) );
    return mfLDt;
  }

  private TagBase createMfRightcell ( TagBase parent, boolean frissitendo )
  {
    RightcellTag mfRight = new RightcellTag();
    mfRight.init( parent );
    DynamicTag mfRDt = new DynamicTag( mfRight );
    mfRDt.addChild( createFrissitesLink( mfRight, frissitendo, null ) );
    return mfRDt;
  }

  private TagBase createMindentFrissitRow ( TagBase parent, boolean frissitendo )
  {
    RowTag mfRow = new RowTag();
    mfRow.init( parent );
    DynamicTag mfDt = new DynamicTag( mfRow );
    mfDt.addChild( createMfLeftcell( mfRow ) );
    mfDt.addChild( createMfRightcell( mfRow, frissitendo ) );
    return mfDt;
  }

  private void printForm () throws DIIException, JspException
  {
    SimpleFormTag formTag = new SimpleFormTag();
    formTag.init( this );
    formTag.setTitle( title );
    DynamicTag formDt = new DynamicTag( formTag );
    formDt.addChild( createMindentFrissitRow( formTag, isVanFrissitendo() ) );
    for ( int i = 0; i < items.size(); ++i ) {
      Object item = items.get( i );
      formDt.addChild( createRow( formTag, item ) );
    }
    formDt.eval();
  }

  private boolean isVanFrissitendo () throws DIIException
  {
    boolean ertekelendo = true;
    for ( int i = 0; i < items.size(); ++i ) {
      if ( ertekelendoProperty != null ) {
        Boolean er = ( Boolean ) PropertyUtils.getProperty( items.get( i ), ertekelendoProperty );
        ertekelendo = er.booleanValue();
      }
      Boolean fr = ( Boolean ) PropertyUtils.getProperty( items.get( i ), frissitendoProperty );
      if ( ertekelendo && fr.booleanValue() )return true;
    }
    return false;
  }

  public void printEndForm () throws IOException
  {
    out.endDiv();
  }

  public void setDisplayAction ( String displayAction )
  {
    this.displayAction = displayAction;
  }

  public void setTitle ( String title )
  {
    this.title = title;
  }

  public void setItems ( List items )
  {
    this.items = items;
  }

  public void setIdProperty ( String idProperty )
  {
    this.idProperty = idProperty;
  }

  public void setFrissitesAction ( String frissitesAction )
  {
    this.frissitesAction = frissitesAction;
  }

  public void setFrissitendoProperty ( String frissitendoProperty )
  {
    this.frissitendoProperty = frissitendoProperty;
  }

  public void setDisplayProperty ( String displayProperty )
  {
    this.displayProperty = displayProperty;
  }

  public void setErtekelendoProperty ( String ertekelendoProperty )
  {
    this.ertekelendoProperty = ertekelendoProperty;
  }

}
