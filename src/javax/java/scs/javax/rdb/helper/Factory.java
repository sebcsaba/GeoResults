package scs.javax.rdb.helper;

import scs.javax.rdb.mapping.ClassMappingBase;

public interface Factory
{

  public RdbHelper createGlobalHelper ();

  public RdbEntityHelper createEntityHelper ( ClassMappingBase cm );

  public boolean isValidHelper ( RdbHelper helper );

  public boolean isValidEntityHelper ( RdbEntityHelper helper );

}
