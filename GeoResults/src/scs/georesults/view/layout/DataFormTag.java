package scs.georesults.view.layout;

import javax.servlet.jsp.JspException;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.formelements.ImageTag;
import scs.javax.web.taglibs.html.ImgTag;
import scs.georesults.view.WebUtils;
import scs.georesults.view.fields.FormTag;

public class DataFormTag extends LayoutTagBase
{

  protected String name;

  protected String action;

  protected String title;

  protected FormTag formTag;

  protected String stylePath;

  protected void printHeader () throws Exception
  {
    stylePath = WebUtils.getStylePath( pageContext );
    startForm();
    doPrintPanelHeader( globalSzotar.getValue( title ) );
    out.startTagWithParam( "table", "class", "formTable" );
    out.closeOpenTag();
  }

  protected void printFooter () throws Exception
  {
    out.endTag( "table" );
    doPrintPanelFooter();
    formTag.doEndTag();
  }

  protected void printIcons () throws Exception
  {
    printModImage();
    printSaveImage();
    printResetImage();
  }

  private void startForm () throws JspException
  {
    formTag = new FormTag();
    formTag.init( this );
    formTag.setAction( action );
    formTag.setId( name );
    formTag.doStartTag();
  }

  private void printModImage () throws JspException
  {
    ImgTag img = new ImgTag();
    img.init( this );
    img.setId( name + "_mod" );
    img.setSrc( stylePath + "notmodified.gif" );
    img.setTitle( globalSzotar.getValue( "BS_NEM_VOLT_MODOSITVA" ) );
    img.setStyleClass( "icon" );
    new DynamicTag( img ).eval();
  }

  private void printSaveImage () throws JspException
  {
    ImageTag img = new ImageTag();
    img.init( this );
    img.setSrc( stylePath + "save.gif" );
    img.setTitle( globalSzotar.getValue( "BS_MENTES" ) );
    img.setStyleClass( "icon" );
    new DynamicTag( img ).eval();
  }

  private void printResetImage () throws JspException
  {
    ImgTag img = new ImgTag();
    img.init( this );
    img.setSrc( stylePath + "reload.gif" );
    img.setStyleClass( "icon commandIcon" );
    img.setTitle( globalSzotar.getValue( "BS_UJRA" ) );
    img.setOnclick( "doResetForm('" + name + "','" + globalSzotar.getValue( "BS_NEM_VOLT_MODOSITVA" ) + "');" );
    new DynamicTag( img ).eval();
  }

  public void setAction ( String action )
  {
    this.action = action;
  }

  public void setTitle ( String title )
  {
    this.title = title;
  }

  public void setName ( String name )
  {
    this.name = name;
  }

}
