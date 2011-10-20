package scs.georesults.logic.actions.importing;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.logic.actions.*;

public class AzonosRajtszamCheckAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException, RdbException, DIIException
  {
    List currentNevezesek = Nevezes.loadAllForVerseny( getDb(), getVerseny() );
    List sourceNevezesek = Nevezes.loadAllForVerseny( getBean().getSourceSubbean().getSession(), getBean().getSourceSubbean().getVerseny() );
    for ( int i = 0; i < sourceNevezesek.size(); ++i ) {
      Nevezes ujNevezes = ( Nevezes ) sourceNevezesek.get( i );
      if ( currentNevezesek.findItemIndex( "rajtszam", new Integer( ujNevezes.getRajtszam() ) ) >= 0 ) {
        throw new ImportException( "IF_AZONOS_RAJTSZAM" );
      }
    }
    return "ok";
  }

  protected boolean isIncSteps ()
  {
    return false;
  }

}
