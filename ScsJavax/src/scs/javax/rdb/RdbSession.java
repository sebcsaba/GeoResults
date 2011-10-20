package scs.javax.rdb;

import scs.javax.collections.List;
import scs.javax.rdb.helper.Factory;
import scs.javax.rdb.helper.RdbEntityHelper;
import scs.javax.rdb.helper.RdbHelper;
import scs.javax.rdb.mapping.ClassMapping;

public abstract class RdbSession
{

  private boolean log;

  private Factory factory;

  public RdbSession ( Factory factory ) throws RdbException
  {
    this.factory = factory;
    this.log = false;
  }

  public void setLog ( boolean log )
  {
    this.log = log;
  }

  public boolean isLog ()
  {
    return log;
  }

  public Factory getFactory ()
  {
    return factory;
  }

  public RdbHelper getHelper ()
  {
    return factory.createGlobalHelper();
  }

  public RdbEntityHelper getEntityHelper ( ClassMapping cm )
  {
    RdbEntityHelper helper = cm.getHelper();
    if ( !factory.isValidEntityHelper( helper ) ) {
      helper = factory.createEntityHelper( cm );
      cm.setHelper( helper );
    }
    return helper;
  }

  public Object queryFirst ( Object query, ClassMapping cm, Object entity ) throws RdbException
  {
    if ( log ) System.err.println( "RdbSession.queryFirst: " + query );
    return queryFirstWork( query, cm, entity );
  }

  protected abstract Object queryFirstWork ( Object query, ClassMapping cm, Object entity ) throws RdbException;

  public List queryAll ( Object query, ClassMapping cm ) throws RdbException
  {
    if ( log ) System.err.println( "RdbSession.queryAll: " + query );
    return queryAllWork( query, cm );
  }

  protected abstract List queryAllWork ( Object query, ClassMapping cm ) throws RdbException;

  public synchronized long update ( Object update ) throws RdbException
  {
    if ( log ) System.err.println( "RdbSession.update: " + update );
    return updateWork( update );
  }

  protected abstract long updateWork ( Object update ) throws RdbException;

  public static class NoResultException extends RdbException
  {
    public NoResultException ( Object query )
    {
      super( "no result when executing the followig statement: '" + query + "'" );
    }
  }

}
