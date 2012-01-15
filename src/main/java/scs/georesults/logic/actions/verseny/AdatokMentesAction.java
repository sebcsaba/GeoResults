package scs.georesults.logic.actions.verseny;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.common.Constants;
import scs.georesults.common.szotar.VersenySzotar;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.LezarvaException;
import scs.georesults.om.verseny.Verseny;

/**
 * <p>A verseny alapadatainak mentését végző szolgáltatás osztálya.</p>
 * <p>A HTTP kérés paraméterei alapján beállítja a verseny objektumot,
 * és elmenti az adatbázisba.</p>
 */
public class AdatokMentesAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    if ( getVerseny().isLeVanZarva() )throw new LezarvaException();
    Verseny verseny = getVerseny();
    fillVerseny( verseny, form );
    checkAlapNyelvValid( verseny.getAlapNyelv() );
    verseny.update( getDb() );
    return "ok";
  }

  /**
   * Ez a művelet tölti fel a HTTP paraméterekalapján a verseny objektumot adatokkal.
   */
  private void fillVerseny ( Verseny verseny, DynamicForm form ) throws WebException
  {
    String alapNyelv = form.getString( "alapNyelv" );
    verseny.setNev( form.getString( "nev" ) );
    verseny.setKezdeteDatum( form.getDate( "kezdeteDatum" ) );
    verseny.setVegeDatum( form.getDate( "vegeDatum" ) );
    verseny.setAlapNyelv( alapNyelv );
    verseny.setMenetlevelformula( form.getString( "menetlevelformula" ) );
  }

  /**
   * Ellenőrzi, hogy a verseny újonnan kiválasztot alapnyelve megfelelő-e, vagyis a verseny szótára tartalmaz-e minden szót ezen a nyelven.
   */
  private void checkAlapNyelvValid ( String alapNyelv ) throws WebException
  {
    VersenySzotar vsz = ( VersenySzotar ) getFromSession( Constants.SESSION_KEY_VERSENY_SZOTAR );
    if ( !vsz.isValidAlapnyelv( alapNyelv ) )throw new GeoMessageException( "ER_HIBAS_ALAPNYELV" );
  }

}
