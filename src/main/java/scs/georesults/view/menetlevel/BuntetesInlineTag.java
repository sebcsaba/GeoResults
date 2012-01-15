package scs.georesults.view.menetlevel;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebSession;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.GeoException;
import scs.georesults.common.Constants;
import scs.georesults.om.alap.BuntetesTipus;
import scs.georesults.om.menetlevel.Buntetes;
import scs.georesults.om.menetlevel.BuntetesImpl;
import scs.georesults.om.verseny.VersenyImpl;
import scs.georesults.view.fields.SimpleIntegerTag;
import scs.georesults.view.layout.DataFieldTag;
import scs.georesults.view.layout.SimpleFormTag;

public class BuntetesInlineTag extends MenetlevelTagBase
{

  private TagBase createIntegerTag ( TagBase parent, BuntetesImpl buntetes )
  {
    SimpleIntegerTag tag = new SimpleIntegerTag();
    tag.init( parent );
    tag.setName( "buntetes_" + buntetes.getBtid() );
    tag.setValue( new Integer( buntetes.getDarab() ) );
    return new DynamicTag( tag );
  }

  private TagBase createFieldTag ( TagBase parent, BuntetesImpl buntetes ) throws GeoException, RdbException
  {
    DataFieldTag tag = new DataFieldTag();
    tag.init( parent );
    tag.setLabel( buntetes.getBuntetesTipus().getNev() );
    DynamicTag dt = new DynamicTag( tag );
    dt.addChild( createIntegerTag( tag, buntetes ) );
    return dt;
  }

  protected DynamicTag createFormTag ( TagBase parent ) throws GeoException, DIIException, RdbException, SessionTimeoutException
  {
    SimpleFormTag tag = new SimpleFormTag();
    tag.setPageContext( pageContext );
    tag.setParent( parent );
    tag.setTitle( "RA_BUNTETESEK" );
    DynamicTag dt = new DynamicTag( tag );
    List buntetesTipusok = getBuntetesTipusok();
    for ( int i = 0; i < buntetesTipusok.size(); ++i ) {
      BuntetesTipus buntetesTipus = ( BuntetesTipus ) buntetesTipusok.get( i );
      BuntetesImpl buntetes = getBuntetesForTipus( buntetesTipus );
      dt.addChild( createFieldTag( tag, buntetes ) );
    }
    return dt;
  }

  private BuntetesImpl getBuntetesForTipus ( BuntetesTipus bt ) throws DIIException
  {
    int index = value.getBuntetesek().findItemIndex( "btid", new Long( bt.getBtid() ) );
    if ( index >= 0 ) {
      return ( BuntetesImpl ) value.getBuntetesek().get( index );
    } else {
      return ( BuntetesImpl ) Buntetes.newInstance( value.getEid(), value.getRajtszam(), bt.getBtid(), 0 );
    }
  }

  private List getBuntetesTipusok () throws GeoException, RdbException, SessionTimeoutException
  {
    VersenyImpl verseny = ( VersenyImpl ) WebSession.justGetHttpSession( pageContext ).getAttribute( Constants.SESSION_KEY_VERSENY );
    return verseny.getBuntetesTipusok();
  }

  public String getFirstInputId () throws GeoException, RdbException, SessionTimeoutException
  {
    List buntetesTipusok = getBuntetesTipusok();
    if ( buntetesTipusok.size() > 0 ) {
      BuntetesTipus buntetesTipus = ( BuntetesTipus ) buntetesTipusok.get( 0 );
      return "buntetes_" + buntetesTipus.getBtid();
    } else {
      return null;
    }
  }

}
