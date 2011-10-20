package scs.georesults.logic.actions.importing;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.GeoException;
import scs.georesults.logic.beans.importing.FutamSubbeanBase;
import scs.georesults.logic.GeoMessageException;

public class FutamokHonnanAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException
  {
    try {
      FutamSubbeanBase subbean = ( FutamSubbeanBase ) getBean().getCategorySubbean();
      if ( !form.has( "honnan" ) )throw new GeoMessageException( "IF_NINCS_ADAT_KIJELOLVE" );
      long esemenyId = Long.parseLong( form.getString( "honnan" ).substring( 3 ) );
      subbean.setSourceEsemenyId( esemenyId );
      subbean.testVanCurrentEsemeny( getVerseny() );
      return "ok";
    }
    catch ( RdbException ex ) {
      throw new GeoException( ex );
    }
  }

}
