package scs.georesults.view.geo;

import javax.servlet.jsp.JspException;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.logic.beans.addons.CharmapBean;

public class CharmapTag extends TagBase
{

  protected CharmapBean bean;

  public int doEndTag () throws JspException
  {
    try {
      out.startTag( "table" );
      CharmapBean.CharRange range = bean.getRange();
      for ( long i = range.getFirst(); i <= range.getLast(); ++i ) {
        if ( i % 16 == 0 ) out.startTag( "tr" );
        out.startTag( "td" );
        out.startDivWithClass( "cell charCell" );
        out.writeParam( "title", "" + i + " / 0x" + Long.toHexString( i ) );
        out.writeParam( "onmouseover", "showPane(this);" );
        out.writeParam( "onclick", "pushback('" + ( ( char ) i ) + "');" );
        out.writeString( Character.toString( ( char ) i ) );
        out.endTag( "div" );
        out.endTag( "td" );
        if ( i % 16 == 15 ) out.endTag( "tr" );
      }
      out.endTag( "table" );
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  public void setBean ( CharmapBean bean )
  {
    this.bean = bean;
  }

}
