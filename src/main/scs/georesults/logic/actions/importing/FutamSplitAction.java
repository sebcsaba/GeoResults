package scs.georesults.logic.actions.importing;

import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.beans.importing.FutamSubbeanBase;
import scs.georesults.logic.actions.*;

public class FutamSplitAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException
  {
    FutamSubbeanBase subbean = ( FutamSubbeanBase ) getBean().getCategorySubbean();
    return ( subbean.isMenetlevel() ? "menetlevelek" : "szlalomFutamok" );
  }

}
