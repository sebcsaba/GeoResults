package scs.georesults.view.menetlevel;

import javax.servlet.jsp.JspException;
import scs.javax.dii.DIIException;
import scs.javax.io.IOException;
import scs.javax.rdb.RdbException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebSession;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.GeoException;
import scs.georesults.common.Constants;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaLista;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaResz;
import scs.georesults.om.etap.DarabFuggoEtapFeladat;
import scs.georesults.om.etap.SorrendFuggoEtapFeladat;
import scs.georesults.om.menetlevel.Menetlevel;
import scs.georesults.om.verseny.EtapImpl;
import scs.georesults.om.verseny.VersenyImpl;
import scs.georesults.view.geo.EllenorzopontListakTag;

public class MenetlevelTag extends TagBase
{

  protected Menetlevel menetlevel;

  protected EtapImpl etap;

  protected String firstId;

  public int doEndTag () throws JspException
  {
    try {
      firstId = null;
      updateEtap();
      MenetlevelFormulaLista formula = new MenetlevelFormulaLista( etap.getAbszolutMenetlevelformula() );
      for ( int i = 0; i < formula.size(); ++i ) {
        MenetlevelFormulaResz resz = formula.get( i );
        printTagForResz( resz );
      }
      printEllenorzolistakTag();
      printFocusScript();
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  private void printTagForResz ( MenetlevelFormulaResz resz ) throws Exception
  {
    MenetlevelTagBase tag;
    if ( resz.getMode() == MenetlevelFormulaResz.MODE_TIME ) {
      tag = new IdoTag();
      tag.setValue( menetlevel );
    } else if ( resz.getMode() == MenetlevelFormulaResz.MODE_BUNTETES ) {
      if ( resz.isPopup() ) {
        tag = new BuntetesPopupTag();
      } else {
        tag = new BuntetesInlineTag();
        tag.setValue( menetlevel );
      }
    } else if ( resz.getMode() == MenetlevelFormulaResz.MODE_DARAB ) {
      if ( resz.isPopup() ) {
        tag = new DarabPopupTag();
      } else {
        tag = new DarabInlineTag();
        tag.setValue( menetlevel );
      }
      tag.setEf( getDfef( resz.getEfid() ) );
    } else if ( resz.getMode() == MenetlevelFormulaResz.MODE_SORREND ) {
      if ( resz.isPopup() ) {
        tag = new SorrendPopupTag();
      } else {
        tag = new SorrendInlineTag();
        tag.setValue( menetlevel );
      }
      tag.setEf( getSfef( resz.getEfid() ) );
      tag.setCount( resz.getCount() );
    } else throw new IllegalArgumentException( "unknown mode: " + resz.getMode() );
    tag.init( this );
    new DynamicTag( tag ).eval();
    if ( firstId == null ) firstId = tag.getFirstInputId();
  }

  private SorrendFuggoEtapFeladat getSfef ( long sfftid ) throws GeoException, DIIException, RdbException
  {
    return ( SorrendFuggoEtapFeladat ) etap.getSorrendFuggoEtapFeladatok().findItem( "sfftid", new Long( sfftid ) );
  }

  private DarabFuggoEtapFeladat getDfef ( long dfftid ) throws GeoException, DIIException, RdbException
  {
    return ( DarabFuggoEtapFeladat ) etap.getDarabFuggoEtapFeladatok().findItem( "dfftid", new Long( dfftid ) );
  }

  private void printEllenorzolistakTag () throws JspException
  {
    EllenorzopontListakTag tag = new EllenorzopontListakTag();
    tag.init( this );
    new DynamicTag( tag ).eval();
  }

  private void printFocusScript () throws IOException
  {
    out.startTagWithParam( "script", "type", "text/javascript" );
    out.writeString( "mlstart=document.getElementById('" + firstId + "');" );
    out.writeString( "mlstart.focus();" );
    out.writeString( "mlstart.setSelectionRange(0,mlstart.value.length);" );
    out.endTag( "script" );
  }

  private void updateEtap () throws GeoException, DIIException, RdbException, SessionTimeoutException
  {
    VersenyImpl verseny = ( VersenyImpl ) WebSession.justGetHttpSession( pageContext ).getAttribute( Constants.SESSION_KEY_VERSENY );
    etap = ( EtapImpl ) verseny.getEtapok().findItem( "eid", new Long( menetlevel.getEid() ) );
  }

  public void setMenetlevel ( Menetlevel menetlevel )
  {
    this.menetlevel = menetlevel;
  }

}
