package scs.javax.rdb.pseudosession;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.dii.PropertyUtils;
import scs.javax.rdb.*;
import scs.javax.rdb.mapping.ClassMapping;
import scs.javax.rdb.mapping.MappingPool;
import scs.javax.rdb.mapping.fields.RdbField;

public class RdbXmlEntitySession extends RdbSession
{

  private Map entities; // Map( ClassMapping -> List( StorableEntityBase ) )

  public RdbXmlEntitySession ( List entities ) throws RdbException, DIIException
  {
    super( new RdbEntitiesFactory() );
    this.entities = new HashMap();
    processEntities( entities );
  }

  private List getEntityList ( ClassMapping cm )
  {
    List result = ( List ) entities.get( cm );
    if ( result == null ) {
      result = new List();
      entities.put( cm, result );
    }
    return result;
  }

  private void processEntity ( StorableEntityBase entity ) throws DIIException
  {
    ClassMapping cm = MappingPool.getClassMapping( entity.getClass() );
    getEntityList( cm ).add( entity );
    for ( Iterator it = cm.getCompositionNameSet().iterator(); it.hasNext(); ) {
      String compName = ( String ) it.next();
      StorableObjectList comp = entity.getCompositionValue( compName );
      processEntities( comp );
      comp.clear();
    }
  }

  private void processEntities ( List entities ) throws DIIException
  {
    for ( int i = 0; i < entities.size(); ++i ) {
      StorableEntityBase entity = ( StorableEntityBase ) entities.get( i );
      processEntity( entity );
    }
  }

  protected Object queryFirstWork ( Object query, ClassMapping cm, Object entity ) throws RdbException
  {
    try {
      List fewEntities = getEntityList( cm );
      RdbEntityCondition entityCondition = ( RdbEntityCondition ) query;
      for ( int i = 0; i < fewEntities.size(); ++i ) {
        if ( entityCondition.testEntity( fewEntities.get( i ) ) ) {
          copyEntity( fewEntities.get( i ), entity, cm );
          return entity;
        }
      }
      throw new NoResultException( query );
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  private static void copyEntity ( Object src, Object dst, ClassMapping cm ) throws DIIException
  {
    for ( int i = 0; i < cm.getFieldCount(); ++i ) {
      RdbField field = cm.getField( i );
      Object propValue = PropertyUtils.getProperty( src, field.getPropertyName() );
      PropertyUtils.setProperty( dst, field.getPropertyName(), propValue );
    }
  }

  protected List queryAllWork ( Object query, ClassMapping cm ) throws RdbException
  {
    try {
      List fewEntities = getEntityList( cm );
      RdbEntityCondition entityCondition = ( RdbEntityCondition ) query;
      List results = new List();
      for ( int i = 0; i < fewEntities.size(); ++i ) {
        if ( entityCondition.testEntity( fewEntities.get( i ) ) ) results.add( fewEntities.get( i ) );
      }
      return results;
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  protected long updateWork ( Object update ) throws RdbException
  {
    throw new UnsupportedOperationException();
  }

}
