package scs.georesults.view.menetlevel;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.GeoException;
import scs.georesults.om.etap.DarabFuggoEtapFeladatEtalonBejegyzes;
import scs.georesults.om.menetlevel.DarabFuggoFeladatMegoldas;
import scs.georesults.om.menetlevel.DarabFuggoMenetlevelBejegyzes;
import scs.georesults.view.fields.SimpleIntegerTag;
import scs.georesults.view.layout.DataFieldTag;
import scs.georesults.view.layout.SimpleFormTag;

public class DarabInlineTag extends MenetlevelTagBase
{

  private TagBase createIntegerTag ( TagBase parent, DarabFuggoMenetlevelBejegyzes mlb, int index )
  {
    SimpleIntegerTag tag = new SimpleIntegerTag();
    tag.init( parent );
    tag.setName( "dfef_" + mlb.getDfftid() + "_" + index );
    tag.setValue( new Integer( mlb.getDarab() ) );
    return new DynamicTag( tag );
  }

  private TagBase createFieldTag ( TagBase parent, DarabFuggoEtapFeladatEtalonBejegyzes etalonBejegyzes, DarabFuggoMenetlevelBejegyzes mlb, int index )
  {
    DataFieldTag tag = new DataFieldTag();
    tag.init( parent );
    tag.setLabel( etalonBejegyzes.getCimke() );
    DynamicTag dt = new DynamicTag( tag );
    dt.addChild( createIntegerTag( tag, mlb, index ) );
    return dt;
  }

  protected DynamicTag createFormTag ( TagBase parent ) throws GeoException, DIIException, RdbException
  {
    SimpleFormTag tag = new SimpleFormTag();
    tag.init( parent );
    tag.setLabel( getDarabFuggoEtapFeladat().getDarabFuggoFeladatTipus().getNev() );
    DynamicTag dt = new DynamicTag( tag );
    List etalonBejegyzesek = getDarabFuggoEtapFeladat().getBejegyzesek();
    for ( int i = 0; i < etalonBejegyzesek.size(); ++i ) {
      DarabFuggoEtapFeladatEtalonBejegyzes etalonBejegyzes = ( DarabFuggoEtapFeladatEtalonBejegyzes ) etalonBejegyzesek.get( i );
      DarabFuggoMenetlevelBejegyzes mlb = getDarabFuggoMenetlevelBejegyzes( etalonBejegyzes );
      dt.addChild( createFieldTag( tag, etalonBejegyzes, mlb, i ) );
    }
    return dt;
  }

  private DarabFuggoMenetlevelBejegyzes getDarabFuggoMenetlevelBejegyzes ( DarabFuggoEtapFeladatEtalonBejegyzes etalonBejegyzes ) throws DIIException
  {
    DarabFuggoFeladatMegoldas mo = ( DarabFuggoFeladatMegoldas ) value.getDarabFuggoMegoldasok().findItem( "dfftid", new Long( etalonBejegyzes.getDfftid() ) );
    if ( mo == null ) {
      return ( DarabFuggoMenetlevelBejegyzes ) DarabFuggoMenetlevelBejegyzes.newInstance( value.getEid(), value.getRajtszam(), etalonBejegyzes.getDfftid(), etalonBejegyzes.getPosindex() );
    } else {
      return ( DarabFuggoMenetlevelBejegyzes ) mo.getBejegyzesek().get( etalonBejegyzes.getPosindex() );
    }
  }

  public String getFirstInputId () throws GeoException
  {
    List etalonBejegyzesek = getDarabFuggoEtapFeladat().getBejegyzesek();
    if ( etalonBejegyzesek.size() > 0 ) {
      DarabFuggoEtapFeladatEtalonBejegyzes etalonBejegyzes = ( DarabFuggoEtapFeladatEtalonBejegyzes ) etalonBejegyzesek.get( 0 );
      return "dfef_" + etalonBejegyzes.getDfftid() + "_0";
    } else {
      return null;
    }
  }

}
