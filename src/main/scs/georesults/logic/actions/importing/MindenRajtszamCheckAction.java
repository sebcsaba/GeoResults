package scs.georesults.logic.actions.importing;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.common.Futam;
import scs.georesults.logic.beans.importing.FutamSubbeanBase;
import scs.georesults.logic.actions.*;

public class MindenRajtszamCheckAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException, RdbException, DIIException
  {
    FutamSubbeanBase subbean = ( FutamSubbeanBase ) getBean().getCategorySubbean();
    List sourceFutamok = subbean.getAllSourceFutamok();
    List currentNevezesek = getVerseny().getNevezesek();
    for ( int i = 0; i < sourceFutamok.size(); ++i ) {
      Futam newFutam = ( Futam ) sourceFutamok.get( i );
      if ( currentNevezesek.findItemIndex( "rajtszam", new Integer( newFutam.getRajtszam() ) ) < 0 ) {
        return "warning";
      }
    }
    return "ok";
  }

}
