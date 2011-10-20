package scs.javax.rdb.sql;

import scs.javax.rdb.helper.Factory;
import scs.javax.rdb.helper.RdbEntityHelper;
import scs.javax.rdb.helper.RdbHelper;
import scs.javax.rdb.mapping.ClassMappingBase;

public class SqlFactory implements Factory
{

  // All create*Statement() methods return SQL statements as java.lang.String

  private static RdbHelper globalInstance = new SqlHelper();

  public RdbHelper createGlobalHelper ()
  {
    return globalInstance;
  }

  public RdbEntityHelper createEntityHelper ( ClassMappingBase cm )
  {
    return new SqlEntityHelper( cm );
  }

  public boolean isValidHelper ( RdbHelper helper )
  {
    return ( helper instanceof SqlHelper );
  }

  public boolean isValidEntityHelper ( RdbEntityHelper helper )
  {
    return ( helper instanceof SqlEntityHelper );
  }

}
