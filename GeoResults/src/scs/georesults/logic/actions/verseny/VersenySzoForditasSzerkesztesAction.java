package scs.georesults.logic.actions.verseny;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.GeoException;
import scs.georesults.common.Constants;
import scs.georesults.common.szotar.VersenySzotar;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.beans.verseny.VersenySzoForditasBean;
import scs.georesults.om.alap.VersenySzotarBejegyzes;

/**
 * <p>A versenyszótár elemeinek adminisztrálását végző szolgáltatás osztálya.</p>
 * <p>A szokásos CRUD műveleteket végzi,a a {@link scs.georesults.logic.actions.CrudActionBase}
 * osztályhoz hasonló, ám nem annyira széttagolt módon.</p>
 */
public class VersenySzoForditasSzerkesztesAction extends GeoActionBase
{

  /**
   * A szolgáltatást segítő {@link VersenySzoForditasBean} objektum kulcsa a munkamenetben.
   */
  protected static final String VSZF_SESSION_KEY = "versenySzoForditas";

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    String mode = form.getString( "mode" );
    if ( form.has( "delete" ) ) {
      VersenySzoForditasBean vszfb = ( VersenySzoForditasBean ) getFromSession( VSZF_SESSION_KEY );
      if ( vszfb.getLang().equals( getVerseny().getAlapNyelv() ) ) {
        throw new GeoMessageException( "ER_ALAPNYELVI_SZO_NEM_TOROLHETO" );
      }
      VersenySzotarBejegyzes vszb = VersenySzotarBejegyzes.newInstance( vszfb.getVid(), vszfb.getVszbid(), vszfb.getLang() );
      vszb.delete( getDb() );
      updateVsz();
      return "back";
    } else if ( mode.equals( "create" ) ) {
      setToSession( VSZF_SESSION_KEY, createBean( form, true ) );
      return "ok";
    } else if ( mode.equals( "edit" ) ) {
      setToSession( VSZF_SESSION_KEY, createBean( form, false ) );
      return "ok";
    } else if ( mode.equals( "save" ) ) {
      VersenySzoForditasBean vszfb = ( VersenySzoForditasBean ) getFromSession( VSZF_SESSION_KEY );
      VersenySzotarBejegyzes vszb = readFormRequest( form, vszfb );
      if ( vszfb.isCreate() ) {
        vszb.create( getDb() );
      } else {
        vszb.update( getDb() );
      }
      updateVsz();
      return "back";
    } else if ( mode.equals( "back" ) ) {
      return "back";
    } else throw new GeoException( "unknown mode parameter: '" + mode + "'" );
  }

  /**
   * Egy új, a működést segítő bean-t hoz létre a paraméterek alapján.
   */
  protected VersenySzoForditasBean createBean ( DynamicForm form, boolean create ) throws WebException
  {
    String lang = form.getString( "lang" );
    long vszbid = form.getLong( "vszbid" );
    return new VersenySzoForditasBean( getNyelv(), getVerseny().getAlapNyelv(), lang, getVerseny().getVid(), vszbid, create );
  }

  /**
   * A HTTP kérés paraméterei alapján egy új {@link VersenySzotarBejegyzes} objektumot hoz létre, és feltölti azt az űrlapról kapott értékek alapján.
   */
  protected VersenySzotarBejegyzes readFormRequest ( DynamicForm form, VersenySzoForditasBean vszfb ) throws WebException
  {
    VersenySzotarBejegyzes vszb = VersenySzotarBejegyzes.newInstance();
    vszb.setVid( vszfb.getVid() );
    vszb.setVszbid( vszfb.getVszbid() );
    vszb.setLang( vszfb.getLang() );
    vszb.setFelirat( form.getString( "felirat" ) );
    vszb.setVid( getVerseny().getVid() );
    return vszb;
  }

  /**
   * Frissíti a verseny szótárát.
   */
  protected void updateVsz () throws WebException, RdbException
  {
    VersenySzotar vsz = ( VersenySzotar ) getFromSession( Constants.SESSION_KEY_VERSENY_SZOTAR );
    vsz.fillSzavak( getDb() );
  }

}
