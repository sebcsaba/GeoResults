package scs.georesults.logic.actions.szakasz;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.logic.LezarvaException;
import scs.georesults.om.verseny.Szakasz;

/**
 * <p>Egy szakasz alapadatainak mentését végző szolgáltatás osztálya.</p>
 */
public class AdatokMentesAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    if ( getVerseny().isLeVanZarva() )throw new LezarvaException();
    Szakasz szakasz = ( Szakasz ) getFromSession( "szakasz" );
    fillSzakasz( szakasz, form );
    szakasz.update( getDb() );
    getVerseny().updateSzakaszok();
    return "ok";
  }

  /**
   * A HTTP kérésben kapott adatokból feltölti a szakasz objektumot.
   */
  private void fillSzakasz ( Szakasz sz, DynamicForm form ) throws WebException
  {
    sz.setNev( form.getDictionaryString( "nev" ) );
    sz.setMegengedettKesesEtaponkent( form.getInteger( "megengedettKesesEtaponkent" ) );
    sz.setKesesertBuntetoPont( form.getInteger( "kesesertBuntetoPont" ) );
    sz.setSzlalomRedukaltPontokkal( form.getBoolean( "szlalomRedukaltPontokkal" ) );
    sz.setErtekelendo( form.getBoolean( "ertekelendo" ) );
    sz.setEredmenyFrissitendo( true );
  }

}
