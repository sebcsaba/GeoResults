package scs.georesults.om.kozos;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;

public class SzotarBejegyzesImpl extends SzotarBejegyzes
{

  public SzotarBejegyzesImpl ()
  {
    super();
  }

  public SzotarBejegyzesImpl ( String lang, String keystr )
  {
    super( lang, keystr );
  }

  public SzotarBejegyzesImpl ( String lang, String keystr, String valuestr )
  {
    super( lang, keystr, valuestr );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, SzotarBejegyzes.class );
  }

}
