package scs.georesults.logic.actions.importing;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.io.IOException;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.common.ValueLabelPair;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.beans.importing.ForditasSubbean;

public class MelyikNyelvekAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException, RdbException, IOException, DIIException
  {
    ForditasSubbean forditasSubbean = ( ForditasSubbean ) getBean().getCategorySubbean();
    List lehetsegesNyelvek = forditasSubbean.getLehetsegesNyelvek();
    boolean any = false;
    for ( int i = 0; i < lehetsegesNyelvek.size(); ) {
      ValueLabelPair nyelvVlp = ( ValueLabelPair ) lehetsegesNyelvek.get( i );
      if ( form.getBoolean( nyelvVlp.getValue() ) ) {
        ++i;
        any = true;
      } else {
        lehetsegesNyelvek.remove( i );
      }
    }
    if ( !any ) {
      forditasSubbean.updateLehetsegesNyelvek();
      throw new GeoMessageException( "IF_NINCS_NYELV_KIJELOLVE" );
    }
    return "ok";
  }

}
