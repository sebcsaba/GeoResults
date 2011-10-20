package scs.georesults.logic.actions.importing;

import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.beans.importing.FutamSubbeanBase;

public class FutamokHovaAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException
  {
    FutamSubbeanBase subbean = ( FutamSubbeanBase ) getBean().getCategorySubbean();
    if ( !form.has( "hova" ) )throw new GeoMessageException( "IF_NINCS_ADAT_KIJELOLVE" );
    long hovaId = form.getLong( "hova" );
    subbean.setHovaEsemenyId( hovaId );
    return "ok";
  }

  protected boolean isIncSteps ()
  {
    return false;
  }

}
