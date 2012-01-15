package scs.georesults.view.menetlevel;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.GeoException;
import scs.georesults.om.etap.SorrendFuggoEtapFeladatImpl;
import scs.georesults.om.menetlevel.SorrendFuggoFeladatMegoldas;
import scs.georesults.om.menetlevel.SorrendFuggoMenetlevelBejegyzes;
import scs.georesults.view.fields.SimpleIntegerTag;
import scs.georesults.view.layout.DataFieldTag;
import scs.georesults.view.layout.DataTableFieldTag;
import scs.georesults.view.layout.SimpleFormTag;
import scs.georesults.view.tablefields.*;

public class SorrendInlineTag extends MenetlevelTagBase
{

  private TagBase createBejegyzesTag ( TagBase parent ) throws GeoException, RdbException
  {
    CellFieldSffBejegyzesTag tag = new CellFieldSffBejegyzesTag();
    tag.init( parent );
    tag.setProperty( "felirat" );
    tag.setName( "felirat_" + getSorrendFuggoEtapFeladat().getSfftid() );
    tag.setSfef( getSorrendFuggoEtapFeladat() );
    tag.setEllenorzesTipus( getSorrendFuggoEtapFeladat().getValodiEllenorzesTipus() );
    return new DynamicTag( tag );
  }

  private TagBase createInputColumnTag ( TagBase parent ) throws GeoException, RdbException
  {
    InputColumnTag tag = new InputColumnTag();
    tag.init( parent );
    tag.setTitle( "BS_BEJEGYZES" );
    DynamicTag dt = new DynamicTag( tag );
    dt.addChild( createBejegyzesTag( tag ) );
    return dt;
  }

  private TagBase createCounterColumnTag ( TagBase parent )
  {
    CounterColumnTag tag = new CounterColumnTag();
    tag.init( parent );
    tag.setStart( 1 );
    return new DynamicTag( tag );
  }

  private TagBase createInputTableTag ( TagBase parent ) throws GeoException, RdbException, DIIException
  {
    InputTableTag tag = new InputTableTag();
    tag.init( parent );
    tag.setCount( count );
    tag.setItems( getBejegyzesekList() );
    tag.setDefault( SorrendFuggoMenetlevelBejegyzes.newInstance( value.getEid(), value.getRajtszam(), getSorrendFuggoEtapFeladat().getSfftid(), 0, "" ) );
    DynamicTag dt = new DynamicTag( tag );
    dt.addChild( createCounterColumnTag( tag ) );
    dt.addChild( createInputColumnTag( tag ) );
    return dt;
  }

  private TagBase createTableFieldTag ( TagBase parent ) throws GeoException, RdbException, DIIException
  {
    DataTableFieldTag tag = new DataTableFieldTag();
    tag.init( parent );
    tag.setTitle( "BS_BEJEGYZESEK" );
    DynamicTag dt = new DynamicTag( tag );
    dt.addChild( createInputTableTag( tag ) );
    return dt;
  }

  private TagBase createHelyesBejegyzesTag ( TagBase parent, SorrendFuggoFeladatMegoldas sffmo )
  {
    SimpleIntegerTag tag = new SimpleIntegerTag();
    tag.init( parent );
    tag.setName( "helyes_" + sffmo.getSfftid() );
    tag.setValue( sffmo.getHelyes() );
    return tag;
  }

  private TagBase createHibasBejegyzesTag ( TagBase parent, SorrendFuggoFeladatMegoldas sffmo )
  {
    SimpleIntegerTag tag = new SimpleIntegerTag();
    tag.init( parent );
    tag.setName( "hibas_" + sffmo.getSfftid() );
    tag.setValue( sffmo.getHibas() );
    return tag;
  }

  private TagBase createHelyesFieldTag ( TagBase parent, SorrendFuggoFeladatMegoldas sffmo )
  {
    DataFieldTag tag = new DataFieldTag();
    tag.init( parent );
    tag.setTitle( "BS_HELYES" );
    DynamicTag dt = new DynamicTag( tag );
    dt.addChild( createHelyesBejegyzesTag( tag, sffmo ) );
    return dt;
  }

  private TagBase createHibasFieldTag ( TagBase parent, SorrendFuggoFeladatMegoldas sffmo )
  {
    DataFieldTag tag = new DataFieldTag();
    tag.init( parent );
    tag.setTitle( "BS_HIBAS" );
    DynamicTag dt = new DynamicTag( tag );
    dt.addChild( createHibasBejegyzesTag( tag, sffmo ) );
    return dt;
  }

  protected DynamicTag createFormTag ( TagBase parent ) throws GeoException, DIIException, RdbException
  {
    SimpleFormTag tag = new SimpleFormTag();
    tag.init( parent );
    tag.setLabel( getSorrendFuggoEtapFeladat().getSorrendFuggoFeladatTipus().getNev() );
    DynamicTag dt = new DynamicTag( tag );
    if ( getSorrendFuggoEtapFeladat().isReszletesBevitel() ) {
      dt.addChild( createTableFieldTag( tag ) );
    } else {
      SorrendFuggoFeladatMegoldas sffmo = getSffmo();
      dt.addChild( createHelyesFieldTag( tag, sffmo ) );
      dt.addChild( createHibasFieldTag( tag, sffmo ) );
    }
    return dt;
  }

  private SorrendFuggoFeladatMegoldas getSffmo () throws DIIException
  {
    long sfftid = getSorrendFuggoEtapFeladat().getSfftid();
    SorrendFuggoFeladatMegoldas mo = ( SorrendFuggoFeladatMegoldas ) value.getSorrendFuggoMegoldasok().findItem( "sfftid", new Long( sfftid ) );
    if ( mo == null ) {
      return SorrendFuggoFeladatMegoldas.newInstance( value.getEid(), value.getRajtszam(), sfftid, new Integer( 0 ), new Integer( 0 ) );
    } else {
      return mo;
    }
  }

  private List getBejegyzesekList () throws DIIException
  {
    return getSffmo().getBejegyzesek();
  }

  public String getFirstInputId () throws GeoException
  {
    SorrendFuggoEtapFeladatImpl sfef = getSorrendFuggoEtapFeladat();
    if ( sfef.isReszletesBevitel() ) {
      return "felirat_" + sfef.getSfftid() + "_0";
    } else {
      return "helyes_" + sfef.getSfftid();
    }
  }

}
