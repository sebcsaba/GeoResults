package scs.georesults.logic.actions.importing;

import scs.javax.dii.DIIException;
import scs.javax.io.IOException;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;

public class CancelAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException, RdbException, IOException, DIIException
  {
    scs.georesults.logic.actions.CancelAction.clearSession( getSession() );
    System.gc();
    return "ok";
  }

}
