package scs.georesults.logic.actions.importing;

import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.actions.*;

public class ImportOrRecogniseAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException
  {
    String ior = form.getString( "ior" );
    if ( ior == null ) {
      throw new ImportException( "ER_IMPORTALASI_HIBA" );
    } else if ( "import".equals( ior ) ) {
      getBean().setRecognise( false );
      getBean().incStepsDone();
      return ior;
    } else if ( "recognise".equals( ior ) ) {
      getBean().setRecognise( true );
      return ior;
    } else throw new ImportException( "ER_IMPORTALASI_HIBA" );
  }

}
