package scs.javax.rdb.pseudosession;

import java.util.HashMap;
import java.util.Map;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.mapping.ClassMapping;

public class RestrictedSqlSession extends RdbSession
{

  private RdbSession baseSession;

  private Map restrictions; // Map ( Class -> RdbEntityCondition )

  public RestrictedSqlSession ( RdbSession baseSession ) throws RdbException
  {
    super( baseSession.getFactory() );
    this.baseSession = baseSession;
    this.restrictions = new HashMap();
  }

  public void addRestriction ( RdbEntityCondition condition )
  {
    Class clazz = condition.getClazz();
    if ( !clazz.getName().endsWith( "Impl" ) )throw new IllegalArgumentException( "Implementation class required! (*Impl)" );
    restrictions.put( clazz, condition );
  }

  private boolean satisfiesRestriction ( Object entity ) throws RdbException
  {
    try {
      RdbEntityCondition condition = ( RdbEntityCondition ) restrictions.get( entity.getClass() );
      return ( condition == null || condition.testEntity( entity ) );
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  protected Object queryFirstWork ( Object query, ClassMapping cm, Object entity ) throws RdbException
  {
    baseSession.queryFirst( query, cm, entity );
    if ( !satisfiesRestriction( entity ) ) {
      throw new NoResultException( query );
    }
    return entity;
  }

  protected List queryAllWork ( Object query, ClassMapping cm ) throws RdbException
  {
    List result = baseSession.queryAll( query, cm );
    for ( int i = 0; i < result.size(); ) {
      if ( satisfiesRestriction( result.get( i ) ) ) {
        ++i;
      } else {
        result.remove( i );
      }
    }
    return result;
  }

  protected long updateWork ( Object update ) throws RdbException
  {
    throw new UnsupportedOperationException();
  }


  public static class UnsatisfiableCondition extends RdbEntityCondition
  {

    public UnsatisfiableCondition ( Class clazz )
    {
      super( clazz );
    }

    public boolean testEntity ( Object entity ) throws DIIException
    {
      return false;
    }

    public String toString ()
    {
      return "UnsatisfiableCondition(" + clazz.getName() + ")";
    }

  }


}
