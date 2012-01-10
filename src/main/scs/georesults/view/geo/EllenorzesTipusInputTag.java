package scs.georesults.view.geo;

import javax.servlet.jsp.JspException;
import scs.javax.io.IOException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.formelements.HiddenTag;
import scs.javax.web.taglibs.html.DivTag;
import scs.georesults.common.szotar.GlobalSzotar;
import scs.georesults.logic.utils.EllenorzesTipusUtils;
import scs.georesults.view.fields.FieldTagUtils;
import scs.georesults.view.fields.ItemTag;
import scs.georesults.view.fields.SimpleSelectTag;

public class EllenorzesTipusInputTag extends TagBase
{

  private String name;

  private int value;

  private Integer parentvalue;

  private boolean disabled;

  public int doEndTag () throws JspException
  {
    try {
      out.startTag( "table" );
      GlobalSzotar globalSzotar = GlobalSzotar.getCurrentInstance( pageContext );
      boolean enableSelects = value > 0 && value < 1000;
      if ( parentvalue != null ) {
        int pv = parentvalue.intValue();
        StringBuffer sb = new StringBuffer();
        sb.append( globalSzotar.getValue( "FT_ELLENORZES_TIPUS_MINT_ALAP" ) ).append( ": " );
        if ( EllenorzesTipusUtils.isEllenorzesTipusCombo( pv ) ) {
          sb.append( globalSzotar.getValue( "FT_ELLENORZES_TIPUS_COMBO" ) );
        } else {
          sb.append( globalSzotar.getValue( "FT_ELLENORZES_TIPUS_TEXTBG" ) );
        }
        sb.append( ", " );
        if ( EllenorzesTipusUtils.isEllenorzesTipusEpList( pv ) ) {
          sb.append( globalSzotar.getValue( "FT_ELLENORZES_TIPUS_EPLIST" ) );
        } else {
          sb.append( globalSzotar.getValue( "FT_ELLENORZES_TIPUS_ETALON" ) );
        }
        HiddenTag ht = new HiddenTag();
        ht.init( this );
        ht.setName( name + "_pv" );
        ht.setId( name + "_pv" );
        ht.setValue( Integer.toString( pv ) );
        writeRadio( 1, false, value >= 1000, sb.toString(), ht );
      }
      writeRadio( 2, false, value == 0, globalSzotar.getValue( "FT_ELLENORZES_TIPUS_NINCS" ), null );
      writeRadio( 3, true, enableSelects, globalSzotar.getValue( "FT_ELLENORZES_TIPUS_EGYENI" ), createSelectsTag( enableSelects ) );
      out.endTag( "table" );
      return EVAL_PAGE;
    }
    catch ( IOException ex ) {
      throw new JspException( ex );
    }
    catch ( SessionTimeoutException ex ) {
      throw new JspException( ex );
    }
  }

  private TagBase createSelectsTag ( boolean enableSelects )
  {
    TagBase base = new TagBase();
    base.init( this );

    DivTag div1 = new DivTag();
    div1.init( base );
    DynamicTag ddiv1 = new DynamicTag( div1 );

    int val1 = ( EllenorzesTipusUtils.isEllenorzesTipusTextBg( value ) ) ? 1 : 2;
    ddiv1.addChild( createSelectTag( 1, enableSelects, "FT_ELLENORZES_TIPUS_TEXTBG", "FT_ELLENORZES_TIPUS_COMBO", val1 ) );

    DivTag div2 = new DivTag();
    div2.init( base );
    DynamicTag ddiv2 = new DynamicTag( div2 );
    int val2 = ( EllenorzesTipusUtils.isEllenorzesTipusEpList( value ) ) ? 1 : 2;
    ddiv2.addChild( createSelectTag( 2, enableSelects, "FT_ELLENORZES_TIPUS_EPLIST", "FT_ELLENORZES_TIPUS_ETALON", val2 ) );

    DynamicTag dt = new DynamicTag( base );
    dt.addChild( ddiv1 );
    dt.addChild( ddiv2 );
    return dt;
  }

  private void writeRadio ( int index, boolean enableSelects, boolean selected, String caption, TagBase extraTag ) throws IOException, SessionTimeoutException, JspException
  {
    out.startTag( "tr" );
    out.startTagWithParam( "td", "style", "vertical-align:top;" );

    out.startTagWithParam( "input", "type", "radio" );
    out.writeParam( "name", name + "_r" );
    out.writeParam( "id", name + "_r_" + index );
    out.writeParam( "value", Integer.toString( index ) );
    out.writeParam( "onclick", "document.getElementById('" + name + "_s1').disabled=document.getElementById('" + name + "_s2').disabled=" + !enableSelects );
    out.writeParam( "unfocus", FieldTagUtils.getUnFocusJS() );
    out.writeParam( "onchange", FieldTagUtils.getChangeJS( FieldTagUtils.CHANGEABLE_FIELD_TYPE_SIMPLE, pageContext ) );
    if ( disabled ) out.writeParam( "disabled", "disabled" );
    if ( selected ) out.writeParam( "checked", "checked" );
    out.endTag( "input" );

    out.endTag( "td" );
    out.startTagWithParam( "td", "style", "vertical-align:top;" );

    out.startTagWithParam( "label", "for", name + "_r_" + index );
    out.writeString( caption );
    out.endTag( "label" );

    if ( extraTag != null ) extraTag.eval();

    out.endTag( "td" );
    out.endTag( "tr" );
  }

  private TagBase createSelectTag ( int index, boolean enableSelects, String key1, String key2, int selectValue )
  {
    SimpleSelectTag sst = new SimpleSelectTag();
    sst.init( this );
    sst.setName( name + "_s" + index );
    sst.setDisabled( !enableSelects );
    sst.setValue( new Integer( selectValue ) );
    DynamicTag dt = new DynamicTag( sst );
    dt.addChild( createOption( sst, key1, 1 ) );
    dt.addChild( createOption( sst, key2, 2 ) );
    return dt;
  }

  private ItemTag createOption ( TagBase parent, String key, int value )
  {
    ItemTag it = new ItemTag();
    it.init( parent );
    it.setTitle( key );
    it.setValue( Integer.toString( value ) );
    return it;
  }

  public void setName ( String name )
  {
    this.name = name;
  }

  public void setValue ( int value )
  {
    this.value = value;
  }

  public void setDisabled ( boolean disabled )
  {
    this.disabled = disabled;
  }

  public void setParentvalue ( Integer parentvalue )
  {
    this.parentvalue = parentvalue;
  }

}
