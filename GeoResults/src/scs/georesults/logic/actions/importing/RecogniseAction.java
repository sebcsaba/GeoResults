package scs.georesults.logic.actions.importing;

import scs.javax.collections.List;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.beans.importing.RecogniseItem;
import scs.georesults.logic.beans.importing.Recogniser;
import scs.georesults.logic.actions.*;

public class RecogniseAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException
  {
    Recogniser recogniser = getBean().getRecogniser();
    List items = recogniser.getItems();
    for ( int i = 0; i < items.size(); ++i ) {
      RecogniseItem ri = ( RecogniseItem ) items.get( i );
      long newId = form.getLong( ri.getTypeId() );
      recogniser.addRemap( ri.getType(), ri.getId(), newId );
    }
    return "ok";
  }

}
