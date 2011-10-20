package scs.georesults.logic.actions.importing;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.common.Futam;
import scs.georesults.logic.beans.importing.FutamSubbeanBase;
import scs.georesults.logic.actions.*;

public class LetezoFutamCheckAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException, RdbException
  {
    FutamSubbeanBase subbean = ( FutamSubbeanBase ) getBean().getCategorySubbean();
    List currentFutamok = subbean.getAllCurrentFutamok();
    List sourceFutamok = subbean.getAllSourceFutamok();
    for ( int i = 0; i < currentFutamok.size(); ++i ) {
      Futam currentFutam = ( Futam ) currentFutamok.get( i );
      for ( int j = 0; j < sourceFutamok.size(); ++j ) {
        Futam newFutam = ( Futam ) sourceFutamok.get( j );
        if ( currentFutam.getRajtszam() == newFutam.getRajtszam() ) {
          return "warning";
        }
      }
    }
    return "ok";
  }

}
