package scs.georesults.logic.actions.importing;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.common.ValueLabelPair;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.beans.importing.EtapokSubbean;

public class MelyikEtapokAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException, RdbException, DIIException
  {
    EtapokSubbean etapokSubbean = ( EtapokSubbean ) getBean().getCategorySubbean();
    List sourceEtapok = etapokSubbean.getSourceEsemenyek();
    boolean any = false;
    for ( int i = 0; i < sourceEtapok.size(); ) {
      ValueLabelPair etapVlp = ( ValueLabelPair ) sourceEtapok.get( i );
      if ( form.getBoolean( etapVlp.getValue() ) ) {
        ++i;
        any = true;
      } else {
        sourceEtapok.remove( i );
      }
    }
    if ( !any )throw new GeoMessageException( "IF_NINCS_ETAP_KIJELOLVE" );
    if ( getVerseny().getSzakaszok().size() == 0 )throw new ImportException( "IF_NINCS_SZAKASZ" );
    return "ok";
  }

}
