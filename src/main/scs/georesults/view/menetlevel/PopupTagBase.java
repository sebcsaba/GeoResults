package scs.georesults.view.menetlevel;

import javax.servlet.jsp.tagext.Tag;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.view.fields.SimpleButtonTag;
import scs.georesults.view.layout.DataFieldTag;
import scs.georesults.view.layout.SimpleFormTag;

public abstract class PopupTagBase extends MenetlevelTagBase
{

  protected Tag createButtonTag ( TagBase parent ) throws Exception
  {
    SimpleButtonTag tag = new SimpleButtonTag();
    tag.init( parent );
    tag.setName( getFirstInputId() );
    tag.setTitle( "BS_FELVITEL" );
    tag.setOnclick( getOnclickJS() );
    return new DynamicTag( tag );
  }

  protected Tag createFieldTag ( TagBase parent ) throws Exception
  {
    DataFieldTag tag = new DataFieldTag();
    tag.init( parent );
    tag.setTitle( "BS_BEJEGYZESEK" );
    DynamicTag dt = new DynamicTag( tag );
    dt.addChild( createButtonTag( tag ) );
    return dt;
  }

  protected DynamicTag createFormTag ( TagBase parent ) throws Exception
  {
    SimpleFormTag tag = new SimpleFormTag();
    tag.init( parent );
    setTitleLabel( tag );
    DynamicTag dt = new DynamicTag( tag );
    dt.addChild( createFieldTag( tag ) );
    return dt;
  }

  protected abstract void setTitleLabel ( SimpleFormTag tag ) throws Exception;

  protected abstract String getOnclickJS ();

}
