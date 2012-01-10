package scs.georesults.logic.actions.importing;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.common.Constants;
import scs.georesults.common.szotar.VersenySzotar;
import scs.georesults.logic.beans.importing.Recogniser;

public class BeforeRecogniseAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException, RdbException, DIIException
  {
    VersenySzotar versenySzotar = ( VersenySzotar ) getFromSession( Constants.SESSION_KEY_VERSENY_SZOTAR );
    List items = getBean().getCategorySubbean().getRecogniserItems( versenySzotar );
    Recogniser recogniser = new Recogniser( items );
    getBean().setRecogniser( recogniser );
    return "ok";
  }

  protected boolean isIncSteps ()
  {
    return false;
  }

}
