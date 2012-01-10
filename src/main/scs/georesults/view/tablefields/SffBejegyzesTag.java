package scs.georesults.view.tablefields;

import java.util.HashMap;
import java.util.Map;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.GeoException;
import scs.georesults.logic.utils.EllenorzesTipusUtils;
import scs.georesults.om.alap.EllenorzoPont;
import scs.georesults.om.etap.SorrendFuggoEtapFeladatImpl;
import scs.georesults.view.fields.*;
import scs.georesults.view.geo.EllenorzopontListakTag;

public class SffBejegyzesTag extends SimpleFieldTag
{

  protected boolean etalon;

  protected SorrendFuggoEtapFeladatImpl sfef;

  protected int ellenorzesTipus;

  private void initBase ( SimpleFieldTag base )
  {
    base.init( this );
    base.setName( name );
    base.setStyleClass( styleClass );
    base.setStyle( style );
    base.setValue( value );
  }

  private void addEllenorzopontArray ( List checkitems )
  {
    Map epm = ( Map ) pageContext.getRequest().getAttribute( EllenorzopontListakTag.REQUEST_KEY );
    if ( epm == null ) {
      epm = new HashMap();
      pageContext.getRequest().setAttribute( EllenorzopontListakTag.REQUEST_KEY, epm );
    }
    epm.put( new Long( sfef.getSfftid() ), checkitems );
  }

  private boolean isValidItem ( List checkitems )
  {
    if ( value == null || value.equals( "" ) )return true;
    for ( int i = 0; i < checkitems.size(); ++i ) {
      EllenorzoPont ep = ( EllenorzoPont ) checkitems.get( i );
      if ( ep.getFelirat().equals( value ) )return true;
    }
    return false;
  }

  protected TagBase createMainTag () throws RdbException, GeoException
  {
    if ( EllenorzesTipusUtils.isEllenorzesTipusNincs( ellenorzesTipus ) || ( etalon && EllenorzesTipusUtils.isEllenorzesTipusEtalon( ellenorzesTipus ) ) ) {
      return createTagForNincsType();
    } else {
      List checkitems;
      if ( EllenorzesTipusUtils.isEllenorzesTipusEpList( ellenorzesTipus ) ) {
        checkitems = sfef.getSorrendFuggoFeladatTipus().getEllenorzoPontok();
      } else {
        checkitems = sfef.getEtalonEllenorzoPontokListaja();
      }
      if ( EllenorzesTipusUtils.isEllenorzesTipusCombo( ellenorzesTipus ) ) {
        return createTagForComboType( checkitems );
      } else {
        return createTagForTextbgType( checkitems );
      }
    }
  }

  private DynamicTag createTagForNincsType ()
  {
    SimpleStringTag base = new SimpleStringTag();
    initBase( base );
    base.setMaxlength( new Integer( 20 ) );
    return new DynamicTag( base );
  }

  private DynamicTag createTagForComboType ( List checkitems )
  {
    SimpleFieldTag base = new SimpleSelectTag();
    initBase( base );
    DynamicTag dt = new DynamicTag( base );
    ItemTag item0 = new ItemTag( "", "" );
    item0.init( base );
    dt.addChild( item0 );
    ItemsTag items = new ItemsTag();
    items.init( base );
    items.setItems( checkitems );
    items.setLabelPropertyName( "felirat" );
    items.setValuePropertyName( "felirat" );
    dt.addChild( items );
    return dt;
  }

  private DynamicTag createTagForTextbgType ( List checkitems )
  {
    SimpleStringTag base = new SimpleStringTag();
    initBase( base );
    base.setOnchange( "doChangeFelirat(this,event);" );
    base.setOnblur( "checkValueTextBg(this,values_sfftid" + sfef.getSfftid() + ");" );
    base.setMaxlength( new Integer( 20 ) );
    addEllenorzopontArray( checkitems );
    if ( !isValidItem( checkitems ) ) base.setStyle( "background:red;" );
    return new DynamicTag( base );
  }

  public void setEtalon ( boolean etalon )
  {
    this.etalon = etalon;
  }

  public void setSfef ( SorrendFuggoEtapFeladatImpl sfef )
  {
    this.sfef = sfef;
  }

  public void setEllenorzesTipus ( int ellenorzesTipus )
  {
    this.ellenorzesTipus = ellenorzesTipus;
  }

}
