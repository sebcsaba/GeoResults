package scs.georesults.logic.actions.etap;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.logic.LezarvaException;
import scs.georesults.om.verseny.Etap;

/**
 * <p>Egy etap alapadatainak mentését végző szolgáltatás osztálya.</p>
 */
public class AdatokMentesAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    if ( getVerseny().isLeVanZarva() )throw new LezarvaException();
    Etap etap = ( Etap ) getFromSession( "etap" );
    fillEtap( etap, form );
    etap.update( getDb() );
    getVerseny().updateEtapok();
    return "ok";
  }

  /**
   * A HTTP kérésben kapott adatokból feltölti az etap objektumot.
   */
  private void fillEtap ( Etap e, DynamicForm form ) throws WebException
  {
    e.setNev( form.getDictionaryString( "nev" ) );
    e.setIdoLimit( form.getInteger( "idoLimit" ) );
    e.setErtekelendo( form.getBoolean( "ertekelendo" ) );
    if ( form.getBoolean( "formulaPopup_exists" ) ) {
      e.setMenetlevelformula( form.getString( "menetlevelformula" ) );
    } else {
      e.setMenetlevelformula( null );
    }
    e.setEredmenyFrissitendo( true );
  }

}
