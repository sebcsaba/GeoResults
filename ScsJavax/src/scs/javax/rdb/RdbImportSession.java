package scs.javax.rdb;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import scs.javax.collections.List;
import scs.javax.dii.ClassUtils;
import scs.javax.dii.DIIException;
import scs.javax.dii.PropertyUtils;
import scs.javax.rdb.mapping.ClassMapping;
import scs.javax.rdb.mapping.MappingPool;
import scs.javax.rdb.mapping.fields.RdbField;

public class RdbImportSession
{

  private RdbSession session;

  private Map remap; // Map( ClassMapping -> Map( Long[oldId] -> Long[newId] ) )

  public RdbImportSession ( RdbSession session )
  {
    this.session = session;
    this.remap = new HashMap();
  }

  public void importEntities ( List entities ) throws DIIException, RdbException
  {
    for ( int i = 0; i < entities.size(); ++i ) {
      StorableClass entity = ( StorableClass ) entities.get( i );
      importEntity( entity );
    }
  }

  public void importEntity ( StorableClass entity ) throws DIIException, RdbException
  {
    ClassMapping cm = MappingPool.getClassMapping( entity.getClass() );
    prepareEntityForImport( entity, cm );
    if ( cm.getPrimaryKey().isAutoIncrement() ) {
      RdbField keyField = cm.getPrimaryKey().getFields()[0];
      Object oldId = keyField.getValueFromEntity( entity );
      keyField.setValueForEntity( entity, new Long( 0 ) );
      entity.create( session );
      Object newId = keyField.getValueFromEntity( entity );
      addRemap( cm, oldId, newId );
    } else {
      entity.create( session );
    }
  }

  private void prepareEntityForImport ( StorableClass entity, ClassMapping cm ) throws DIIException, RdbException
  {
    for ( Iterator it = cm.getReferencedClassAttributeNames().iterator(); it.hasNext(); ) {
      String propertyName = ( String ) it.next();
      String referencedClassName = cm.getReferencedClassName( propertyName );
      ClassMapping refccm = MappingPool.getClassMapping( ClassUtils.loadClass( referencedClassName ) );
      if ( refccm.getPrimaryKey().isAutoIncrement() ) {
        RdbField refField = cm.getField( propertyName );
        Object refId = refField.getValueFromEntity( entity );
        Object newId = getClassRemaps( refccm ).get( refId );
        if ( newId != null ) {
          refField.setValueForEntity( entity, newId );
        }
      }
    }
    for ( Iterator it = cm.getCompositionNameSet().iterator(); it.hasNext(); ) {
      String compositionName = ( String ) it.next();
      StorableObjectList subList = ( StorableObjectList ) PropertyUtils.getProperty( entity, compositionName );
      ClassMapping subcm = MappingPool.getClassMapping( cm.getComposition( compositionName ).getCompositionClass() );
      for ( int i = 0; i < subList.size(); ++i ) {
        StorableClass subEntity = ( StorableClass ) subList.get( i );
        prepareEntityForImport( subEntity, subcm );
      }
    }
  }

  public void addRemap ( Class clazz, long oldId, long newId ) throws DIIException
  {
    ClassMapping cm = MappingPool.getClassMapping( clazz );
    addRemap( cm, new Long( oldId ), new Long( newId ) );
  }

  private void addRemap ( ClassMapping cm, Object oldId, Object newId )
  {
    getClassRemaps( cm ).put( oldId, newId );
  }

  private Map getClassRemaps ( ClassMapping cm )
  {
    Map classRemaps = ( Map ) remap.get( cm );
    if ( classRemaps == null ) {
      classRemaps = new HashMap();
      remap.put( cm, classRemaps );
    }
    return classRemaps;
  }

  public Object getNewId ( ClassMapping cm, Object oldId )
  {
    return getClassRemaps( cm ).get( oldId );
  }

  public Long getNewId ( Class clazz, Long oldId ) throws DIIException
  {
    ClassMapping cm = MappingPool.getClassMapping( clazz );
    return ( Long ) getNewId( cm, oldId );
  }

  public long getNewId ( Class clazz, long oldId ) throws DIIException
  {
    ClassMapping cm = MappingPool.getClassMapping( clazz );
    Long newId = ( Long ) getClassRemaps( cm ).get( new Long( oldId ) );
    return newId.longValue();
  }

}
