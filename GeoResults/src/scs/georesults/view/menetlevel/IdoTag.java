package scs.georesults.view.menetlevel;

import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.view.fields.TimeTag;
import scs.georesults.view.layout.DataFieldTag;
import scs.georesults.view.layout.SimpleFormTag;

public class IdoTag extends MenetlevelTagBase
{

  private TagBase createIndulasiIdoTag ( TagBase parent )
  {
    TimeTag tag = new TimeTag();
    tag.init( parent );
    tag.setName( "indulasiIdo" );
    tag.setValue( value.getIndulasiIdo() );
    return tag;
  }

  private TagBase createErkezesiIdoTag ( TagBase parent )
  {
    TimeTag tag = new TimeTag();
    tag.init( parent );
    tag.setName( "erkezesiIdo" );
    tag.setValue( value.getErkezesiIdo() );
    return tag;
  }

  private TagBase createIndulasiFieldTag ( TagBase parent )
  {
    DataFieldTag tag = new DataFieldTag();
    tag.init( parent );
    tag.setTitle( "RA_INDULASI_IDO" );
    DynamicTag dt = new DynamicTag( tag );
    dt.addChild( createIndulasiIdoTag( tag ) );
    return dt;
  }

  private TagBase createErkezesiFieldTag ( TagBase parent )
  {
    DataFieldTag tag = new DataFieldTag();
    tag.init( parent );
    tag.setTitle( "RA_ERKEZESI_IDO" );
    DynamicTag dt = new DynamicTag( tag );
    dt.addChild( createErkezesiIdoTag( tag ) );
    return dt;
  }

  protected DynamicTag createFormTag ( TagBase parent )
  {
    SimpleFormTag tag = new SimpleFormTag();
    tag.init( parent );
    tag.setTitle( "RA_IDO_ADATOK" );
    DynamicTag dt = new DynamicTag( tag );
    dt.addChild( createIndulasiFieldTag( tag ) );
    dt.addChild( createErkezesiFieldTag( tag ) );
    return dt;
  }

  public String getFirstInputId ()
  {
    return "indulasiIdo_hour";
  }

}
