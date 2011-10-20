package scs.georesults.logic.actions.kozos;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.javax.web.request.InvalidRequestFieldException;
import scs.georesults.GeoException;
import scs.georesults.om.kozos.Nyelv;
import scs.georesults.om.kozos.NyelvImpl;
import scs.georesults.om.kozos.SzotarBejegyzes;

/**
 * <p>Új nyelv létrehozását végző szolgáltatás osztálya.</p>
 */
public class UjNyelvAction extends UjKozosAdatActionBase
{

  protected String getBundlePrefix ()
  {
    return NyelvImpl.BUNDLE_PREFIX;
  }

  protected List getAllExistEntities () throws WebException, RdbException
  {
    return Nyelv.loadAll( getDb() );
  }

  protected StorableEntityBase createNewEntity ( String kod )
  {
    return Nyelv.newInstance( kod );
  }

  protected String getEntityKeyName ()
  {
    return "lang";
  }

  protected Object createAdditionalObjects ( DynamicForm form ) throws InvalidRequestFieldException
  {
    return Nyelv.newInstance( form.getString( "alapnyelv" ) );
  }

  protected void useAdditionalObjects ( Object additionalObject, String kod ) throws GeoException, RdbException
  {
    Nyelv alapnyelv = ( Nyelv ) additionalObject;
    List alapszavak = SzotarBejegyzes.loadAllForNyelv( getDb(), alapnyelv );
    for ( int i = 0; i < alapszavak.size(); ++i ) {
      SzotarBejegyzes szb = ( SzotarBejegyzes ) alapszavak.get( i );
      szb.setLang( kod );
      szb.create( getDb() );
    }
  }

}
