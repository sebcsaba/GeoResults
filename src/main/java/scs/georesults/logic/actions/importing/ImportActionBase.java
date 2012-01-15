package scs.georesults.logic.actions.importing;

import scs.javax.dii.DIIException;
import scs.javax.io.IOException;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebException;
import scs.georesults.GeoException;
import scs.georesults.logic.actions.GeoActionBase;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.beans.importing.ImportBean;

public abstract class ImportActionBase extends GeoActionBase
{

  public static final String SESSION_BEAN_KEY = "importBean";

  private ImportBean bean;

  protected void updateBean () throws SessionTimeoutException
  {
    bean = ( ImportBean ) getFromSession( SESSION_BEAN_KEY );
  }

  protected ImportBean getBean () throws SessionTimeoutException
  {
    if ( bean == null ) updateBean();
    return bean;
  }

  public abstract String importService ( DynamicForm form ) throws WebException, RdbException, IOException, DIIException;

  protected boolean isIncSteps ()
  {
    return true;
  }

  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    String result;
    try {
      updateBean();
      result = importService( form );
      if ( isIncSteps() ) getBean().incStepsDone();
    }
    catch ( ImportException ex ) {
      result = "importError";
      setToRequest( GLOBAL_ERROR_KEY, ex );
    }
    catch ( GeoMessageException ex ) {
      result = "re";
      setToRequest( GLOBAL_ERROR_KEY, ex );
    }
    catch ( IOException ex ) {
      throw new GeoException( ex );
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
    return result;
  }

}
