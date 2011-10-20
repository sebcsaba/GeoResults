package scs.georesults.logic.actions.kozos;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.om.kozos.Orszag;
import scs.georesults.om.kozos.OrszagImpl;

/**
 * <p>Új ország létrehozását végző szolgáltatás osztálya.</p>
 */
public class UjOrszagAction extends UjKozosAdatActionBase
{

  protected String getBundlePrefix ()
  {
    return OrszagImpl.BUNDLE_PREFIX;
  }

  protected List getAllExistEntities () throws WebException, RdbException
  {
    return Orszag.loadAll( getDb() );
  }

  protected StorableEntityBase createNewEntity ( String kod )
  {
    return Orszag.newInstance( kod );
  }

  protected String getEntityKeyName ()
  {
    return "country";
  }

  protected Object createAdditionalObjects ( DynamicForm form ) throws WebException, RdbException
  {
    return null;
  }

  protected void useAdditionalObjects ( Object additionalObject, String kod ) throws WebException, RdbException
  {}

}
