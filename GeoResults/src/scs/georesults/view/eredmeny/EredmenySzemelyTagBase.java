package scs.georesults.view.eredmeny;

import javax.servlet.jsp.JspException;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.common.WriteTag;
import scs.georesults.om.nevezes.NevezesImpl;
import scs.georesults.view.xmltable.CellTag;
import scs.georesults.view.xmltable.ClipTag;
import scs.georesults.view.xmltable.DataTag;

public abstract class EredmenySzemelyTagBase extends TagBase
{

  protected NevezesImpl nevezes;

  private String clipStyle;

  public int doEndTag () throws JspException
  {
    try {
      doPrint();
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  protected abstract void doPrint () throws Exception;

  public void setNevezes ( NevezesImpl nevezes )
  {
    this.nevezes = nevezes;
  }

  public void setClipStyle ( String clipStyle )
  {
    this.clipStyle = clipStyle;
  }

  protected void printCell ( String borderStyle, int colspan, int rowspan, String content, Integer colIndex ) throws JspException
  {
    // Cell -> Clip -> Data -> Write
    CellTag cell = new CellTag();
    cell.init( this );
    cell.setColspan( new Integer( colspan ) );
    cell.setRowspan( new Integer( rowspan ) );
    cell.setBorderStyle( borderStyle );
    cell.setContentStyle( "text" );
    cell.setColIndex( colIndex );
    DynamicTag dt = new DynamicTag( cell );
    dt.addChild( createClipTag( cell, content ) );
    dt.eval();
  }

  private TagBase createWriteTag ( TagBase parent, String content )
  {
    WriteTag write = new WriteTag();
    write.init( parent );
    write.setCaption( content );
    return write;
  }

  private TagBase createDataTag ( TagBase parent, String content )
  {
    DataTag data = new DataTag();
    data.init( parent );
    data.setType( "String" );
    DynamicTag dt = new DynamicTag( data );
    dt.addChild( createWriteTag( data, content ) );
    return dt;
  }

  private TagBase createClipTag ( TagBase parent, String content )
  {
    ClipTag clip = new ClipTag();
    clip.init( parent );
    clip.setStyleClass( clipStyle );
    DynamicTag dt = new DynamicTag( clip );
    dt.addChild( createDataTag( clip, content ) );
    return dt;
  }

}
