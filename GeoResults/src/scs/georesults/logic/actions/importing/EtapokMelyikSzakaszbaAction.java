package scs.georesults.logic.actions.importing;

import java.util.Map;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.common.ValueLabelPair;
import scs.georesults.logic.beans.importing.EtapokSubbean;
import scs.georesults.logic.actions.*;

public class EtapokMelyikSzakaszbaAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException, RdbException, DIIException
  {
    EtapokSubbean etapokSubbean = ( EtapokSubbean ) getBean().getCategorySubbean();
    List sourceEtapok = etapokSubbean.getSourceEsemenyek();
    Map importEidSzid = etapokSubbean.getImportEidSzid();
    for ( int i = 0; i < sourceEtapok.size(); ++i ) {
      ValueLabelPair etapVlp = ( ValueLabelPair ) sourceEtapok.get( i );
      long szid = form.getLong( "szakasz_for_" + etapVlp.getValue() );
      long eid = Long.parseLong( etapVlp.getValue().substring( 3 ) );
      importEidSzid.put( new Long( eid ), new Long( szid ) );
    }
    return "ok";
  }

}
