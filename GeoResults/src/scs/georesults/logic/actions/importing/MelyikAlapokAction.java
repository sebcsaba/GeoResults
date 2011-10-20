package scs.georesults.logic.actions.importing;

import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.beans.importing.AlapokSubbean;
import scs.georesults.logic.beans.importing.ImportBean;
import scs.georesults.logic.actions.*;

public class MelyikAlapokAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException
  {
    AlapokSubbean alapokSubbean = ( AlapokSubbean ) getBean().getCategorySubbean();
    alapokSubbean.setSorrendFuggoFeladatTipusok( form.getBoolean( "sorrendFuggoFeladatTipusok" ) );
    alapokSubbean.setDarabFuggoFeladatTipusok( form.getBoolean( "darabFuggoFeladatTipusok" ) );
    alapokSubbean.setKesesiZonak( form.getBoolean( "kesesiZonak" ) );
    alapokSubbean.setBuntetesTipusok( form.getBoolean( "buntetesTipusok" ) );
    alapokSubbean.setSzlalomFeladatok( form.getBoolean( "szlalomFeladatok" ) );
    alapokSubbean.setSzlalomKategoriak( form.getBoolean( "szlalomKategoriak" ) );
    alapokSubbean.setAutoTipusok( form.getBoolean( "autoTipusok" ) );
    checkAny( alapokSubbean );
    if ( alapokSubbean.isAutoTipusok() && !alapokSubbean.isSzlalomKategoriak() ) {
      return "recognise";
    } else {
      ImportBean bean = getBean();
      bean.incStepsDone();
      return "ok";
    }
  }

  private void checkAny ( AlapokSubbean alapokSubbean ) throws GeoMessageException
  {
    boolean any = false;
    any |= alapokSubbean.isSorrendFuggoFeladatTipusok();
    any |= alapokSubbean.isDarabFuggoFeladatTipusok();
    any |= alapokSubbean.isKesesiZonak();
    any |= alapokSubbean.isBuntetesTipusok();
    any |= alapokSubbean.isSzlalomFeladatok();
    any |= alapokSubbean.isSzlalomKategoriak();
    any |= alapokSubbean.isAutoTipusok();
    if ( !any )throw new GeoMessageException( "IF_NINCS_KATEGORIA_KIJELOLVE" );
  }

}
