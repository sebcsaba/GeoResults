package scs.javax.rdb.pseudosession;

import scs.javax.rdb.helper.Factory;
import scs.javax.rdb.helper.RdbEntityHelper;
import scs.javax.rdb.helper.RdbHelper;
import scs.javax.rdb.mapping.ClassMapping;
import scs.javax.rdb.mapping.ClassMappingBase;

public class RdbEntitiesFactory implements Factory
{

  // All create*Statement() methods return scs.javax.rdb.pseudosession.RdbEntityCondition objects

  public RdbHelper createGlobalHelper ()
  {
    throw new UnsupportedOperationException();
  }

  public RdbEntityHelper createEntityHelper ( ClassMappingBase cm )
  {
    return new RdbEntitiesEntityHelper( ( ClassMapping ) cm );
  }

  public boolean isValidHelper ( RdbHelper helper )
  {
    return false;
  }

  public boolean isValidEntityHelper ( RdbEntityHelper helper )
  {
    return ( helper instanceof RdbEntitiesEntityHelper );
  }

}
