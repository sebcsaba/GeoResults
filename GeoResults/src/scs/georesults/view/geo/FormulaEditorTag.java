package scs.georesults.view.geo;

import javax.servlet.jsp.JspException;
import scs.javax.io.IOException;
import scs.javax.rdb.RdbException;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.html.ImgTag;
import scs.georesults.GeoDbSession;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaLista;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaResz;
import scs.georesults.common.szotar.GlobalSzotar;
import scs.georesults.common.szotar.VersenyResolver;
import scs.georesults.om.alap.DarabFuggoFeladatTipus;
import scs.georesults.om.alap.SorrendFuggoFeladatTipus;
import scs.georesults.view.WebUtils;
import scs.georesults.view.fields.SimpleIntegerTag;
import javax.servlet.http.HttpServletRequest;

public class FormulaEditorTag extends TagBase
{

  protected MenetlevelFormulaLista formulaLista;

  protected GlobalSzotar globalSzotar;

  protected VersenyResolver versenyResolver;

  protected GeoDbSession db;

  public int doEndTag () throws JspException
  {
    try {
      globalSzotar = GlobalSzotar.getCurrentInstance( pageContext );
      versenyResolver = VersenyResolver.getCurrentInstance( pageContext.getServletContext() );
      db = GeoDbSession.getCurrentInstance();
      String formula = pageContext.getRequest().getParameter( "formula" );
      formulaLista = new MenetlevelFormulaLista( formula );
      printPanel();
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  private void printPanel () throws IOException, JspException, RdbException
  {
    out.startDivWithClass( "formulaPanel" );
    out.writeParam( "id", "panel" );
    for ( int i = 0; i < formulaLista.size(); ++i ) {
      MenetlevelFormulaResz resz = formulaLista.get( i );
      out.startDivWithClass( "row" );
      out.writeParam( "id", resz.getId() );
      out.startTagWithParam( "table", "class", "formRow" );
      out.startTag( "tr" );
      printLabelCol( resz );
      printPopupCol( resz );
      printCountCol( resz );
      printSpinCol();
      out.endTag( "tr" );
      out.endTag( "table" );
      out.endDiv();
    }
    out.endDiv();
  }

  private void printLabelCol ( MenetlevelFormulaResz resz ) throws IOException, JspException, RdbException
  {
    out.startTagWithParam( "td", "class", "col labelCol" );
    if ( resz.getMode() == MenetlevelFormulaResz.MODE_TIME ) {
      out.writeString( globalSzotar.getValue( "BS_IDO" ) );
    } else if ( resz.getMode() == MenetlevelFormulaResz.MODE_BUNTETES ) {
      out.writeString( globalSzotar.getValue( "RA_BUNTETES" ) );
    } else if ( resz.getMode() == MenetlevelFormulaResz.MODE_DARAB ) {
      DarabFuggoFeladatTipus dff = DarabFuggoFeladatTipus.newInstance( resz.getEfid() );
      dff.read( db );
      out.writeString( versenyResolver.resolve( pageContext.getServletContext(), (HttpServletRequest)pageContext.getRequest(), dff.getNev() ) );
    } else if ( resz.getMode() == MenetlevelFormulaResz.MODE_SORREND ) {
      SorrendFuggoFeladatTipus sff = SorrendFuggoFeladatTipus.newInstance( resz.getEfid() );
      sff.read( db );
      out.writeString( versenyResolver.resolve( pageContext.getServletContext(), (HttpServletRequest)pageContext.getRequest(), sff.getNev() ) );
    }
    out.endTag( "td" );
  }

  private void printPopupCol ( MenetlevelFormulaResz resz ) throws IOException
  {
    out.startTagWithParam( "td", "class", "col popupCol" );
    if ( resz.getMode() != MenetlevelFormulaResz.MODE_TIME ) {
      out.startTagWithParam( "input", "type", "checkbox" );
      out.writeParam( "id", resz.getId() + "_popup" );
      if ( resz.isPopup() ) out.writeParam( "checked", "checked" );
      out.endTag( "input" );
      out.startTagWithParam( "label", "for", resz.getId() + "_popup" );
      out.writeString( globalSzotar.getValue( "BS_POPUP" ) );
      out.endTag( "label" );
    }
    out.endTag( "td" );
  }

  private void printCountCol ( MenetlevelFormulaResz resz ) throws IOException, JspException
  {
    out.startTagWithParam( "td", "class", "col countCol" );
    out.closeOpenTag();
    if ( resz.getMode() == MenetlevelFormulaResz.MODE_SORREND ) {
      SimpleIntegerTag tag = new SimpleIntegerTag();
      tag.setPageContext( pageContext );
      tag.setParent( this );
      tag.setName( resz.getId() + "_count" );
      tag.setValue( new Integer( resz.getCount() ) );
      tag.setStyleClass( "count" );
      new DynamicTag( tag ).eval();
    }
    out.endTag( "td" );
  }

  private void printSpinCol () throws IOException, JspException
  {
    String stylePath = WebUtils.getStylePath( pageContext );
    out.startTagWithParam( "td", "class", "col spinCol" );
    out.startDivWithClass( "spinners" );
    out.startDivWithClass( "spin" );
    out.writeParam( "onclick", "doUp(this);" );

    ImgTag imgUp = new ImgTag();
    imgUp.init( this );
    imgUp.setSrc( stylePath + "uparrow.png" );
    imgUp.setTitle( globalSzotar.getValue( "BS_FEL" ) );
    new DynamicTag( imgUp ).eval();

    out.endDiv();
    out.startDivWithClass( "spin" );
    out.writeParam( "onclick", "doDown(this);" );

    ImgTag imgDown = new ImgTag();
    imgDown.init( this );
    imgDown.setSrc( stylePath + "downarrow.png" );
    imgDown.setTitle( globalSzotar.getValue( "BS_LE" ) );
    new DynamicTag( imgDown ).eval();

    out.endDiv();
    out.endDiv();
    out.endTag( "td" );
  }

}
