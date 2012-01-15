package scs.georesults.logic.actions.importing;

import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.GeoException;
import scs.georesults.logic.beans.importing.ImportBean;

public class StartAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException
  {
    try {
      getVerseny().refresh( getDb() );
      System.gc();
      ImportBean bean = new ImportBean( getNyelv().getLang(), getVerseny() );
      setToSession( SESSION_BEAN_KEY, bean );
      return "ok";
    }
    catch ( RdbException ex ) {
      throw new GeoException( ex );
    }
  }

}
