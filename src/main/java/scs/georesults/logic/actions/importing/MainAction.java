package scs.georesults.logic.actions.importing;

import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbImportSession;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.GeoDbSession;
import scs.georesults.common.Constants;
import scs.georesults.common.szotar.GlobalSzotar;
import scs.georesults.common.szotar.VersenySzotar;
import scs.georesults.logic.LezarvaException;
import scs.georesults.logic.beans.importing.ImportCategorySubbean;
import scs.georesults.om.kozos.Nyelv;

public class MainAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException, DIIException, RdbException
  {
    if ( getVerseny().isLeVanZarva() )throw new LezarvaException();
    ImportCategorySubbean subbean = getBean().getCategorySubbean();
    subbean.checkForNyelvOrszag();
    subbean.checkForAlapnyelvInvariant();
    RdbImportSession importSession = new RdbImportSession( GeoDbSession.getCurrentInstance() );
    subbean.doImport( importSession, getRequest() );
    szotarFrissites();
    getVerseny().refresh( getDb() );
    System.gc();
    return "ok";
  }

  private void szotarFrissites () throws RdbException, WebException
  {
    Nyelv nyelv = ( Nyelv ) getFromSession( Constants.SESSION_KEY_NYELV );
    GlobalSzotar globalSzotar = new GlobalSzotar( getDb(), nyelv );
    setToSession( Constants.SESSION_KEY_GLOBAL_SZOTAR, globalSzotar );
    VersenySzotar versenySzotar = new VersenySzotar( getDb(), getVerseny(), nyelv );
    setToSession( Constants.SESSION_KEY_VERSENY_SZOTAR, versenySzotar );
  }

}
