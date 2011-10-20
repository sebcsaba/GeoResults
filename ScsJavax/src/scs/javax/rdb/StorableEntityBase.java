package scs.javax.rdb;

import java.util.Iterator;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.dii.PropertyUtils;
import scs.javax.io.Serializable;
import scs.javax.rdb.mapping.*;
import scs.javax.rdb.mapping.fields.RdbField;
import scs.javax.rdb.rdbxml.EntityTag;
import scs.javax.rdb.rdbxml.PropertyTag;
import scs.javax.rdb.rdbxml.SublistTag;
import scs.javax.xml.tags.XmlTag;
import scs.javax.rdb.helper.RdbEntityHelper;

public abstract class StorableEntityBase implements StorableClass, Serializable
{

  public String toString ()
  {
    try {
      ClassMapping cm = MappingPool.getClassMapping( getClass() );
      StringBuffer sb = new StringBuffer( getClass().getName() );
      sb.append( "[" );
      for ( int i = 0; i < cm.getFieldCount(); ++i ) {
        RdbField field = cm.getField( i );
        sb.append( field.getPropertyName() ).append( ":" );
        sb.append( field.getValueFromEntity( this ) );
        sb.append( ";" );
      }
      for ( Iterator it = cm.getCompositionNameSet().iterator(); it.hasNext(); ) {
        String compName = ( String ) it.next();
        sb.append( compName ).append( ":" );
        sb.append( getCompositionValue( compName ).toString() );
      }
      sb.setCharAt( sb.length() - 1, ']' );
      return sb.toString();
    }
    catch ( Exception ex ) {
      return "<<error>>: " + super.toString();
    }
  }

  public StorableObjectList getCompositionValue ( String compName ) throws DIIException
  {
    return ( StorableObjectList ) PropertyUtils.getProperty( this, compName );
  }

  private void setGeneratedKey ( PrimaryKey key, long result ) throws RdbException
  {
    if ( key.isAutoIncrement() ) {
      RdbField field = key.getFields()[0];
      field.setValueForEntity( this, new Long( result ) );
    }
  }

  public void create ( RdbSession session ) throws RdbException
  {
    try {
      ClassMapping cm = MappingPool.getClassMapping( getClass() );
      RdbEntityHelper helper = session.getEntityHelper( cm );
      long result = session.update( helper.createCreateStatement( this ) );
      setGeneratedKey( cm.getPrimaryKey(), result );
      if ( cm.hasAnyComposition() ) {
        for ( Iterator it = cm.getCompositionNameSet().iterator(); it.hasNext(); ) {
          String compName = ( String ) it.next();
          getCompositionValue( compName ).create( session );
        }
      }
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  public void read ( RdbSession session ) throws RdbException
  {
    try {
      ClassMapping cm = MappingPool.getClassMapping( getClass() );
      RdbEntityHelper helper = session.getEntityHelper( cm );
      session.queryFirst( helper.createReadStatement( this ), cm, this );
      if ( cm.hasAnyComposition() ) {
        for ( Iterator it = cm.getCompositionNameSet().iterator(); it.hasNext(); ) {
          String compName = ( String ) it.next();
          getCompositionValue( compName ).read( session );
        }
      }
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  public void update ( RdbSession session ) throws RdbException
  {
    try {
      ClassMapping cm = MappingPool.getClassMapping( getClass() );
      RdbEntityHelper helper = session.getEntityHelper( cm );
      session.update( helper.createUpdateStatement( this ) );
      if ( cm.hasAnyComposition() ) {
        for ( Iterator it = cm.getCompositionNameSet().iterator(); it.hasNext(); ) {
          String compName = ( String ) it.next();
          getCompositionValue( compName ).update( session );
        }
      }
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  public void delete ( RdbSession session ) throws RdbException
  {
    try {
      ClassMapping cm = MappingPool.getClassMapping( getClass() );
      RdbEntityHelper helper = session.getEntityHelper( cm );
      session.update( helper.createDeleteStatement( this ) );
      if ( cm.hasAnyComposition() ) {
        for ( Iterator it = cm.getCompositionNameSet().iterator(); it.hasNext(); ) {
          String compName = ( String ) it.next();
          getCompositionValue( compName ).delete( session );
        }
      }
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  private static List loadAll0 ( RdbSession session, ClassMapping cm, Object query ) throws RdbException
  {
    try {
      List result = session.queryAll( query, cm );
      checkForCompositions( session, result, cm );
      return result;
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  public static List loadAll ( RdbSession session, Class clazz ) throws RdbException
  {
    try {
      ClassMapping cm = MappingPool.getClassMapping( clazz );
      RdbEntityHelper helper = session.getEntityHelper( cm );
      return loadAll0( session, cm, helper.createReadAllStatement() );
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  public static List loadAll ( RdbSession session, Class clazz, PrimitiveRdbCondition filter ) throws RdbException
  {
    try {
      ClassMapping cm = MappingPool.getClassMapping( clazz );
      RdbEntityHelper helper = session.getEntityHelper( cm );
      return loadAll0( session, cm, helper.createReadAllFilteredStatement( filter ) );
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  public static List loadAll ( RdbSession session, Class clazz, String filterFieldName, Object filterEntity ) throws RdbException
  {
    try {
      ClassMapping cm = MappingPool.getClassMapping( clazz );
      RdbField filterField = cm.getField( filterFieldName );
      PrimitiveRdbCondition filter = new PrimitiveRdbCondition( filterField, filterField.getValueFromEntity( filterEntity ) );
      RdbEntityHelper helper = session.getEntityHelper( cm );
      return loadAll0( session, cm, helper.createReadAllFilteredStatement( filter ) );
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  public static List loadAll ( RdbSession session, Class clazz, String filterFieldName1, Object filterEntity1, String filterFieldName2, Object filterEntity2 ) throws RdbException
  {
    try {
      ClassMapping cm = MappingPool.getClassMapping( clazz );
      RdbField filterField1 = cm.getField( filterFieldName1 );
      PrimitiveRdbCondition filter1 = new PrimitiveRdbCondition( filterField1, filterField1.getValueFromEntity( filterEntity1 ) );
      RdbField filterField2 = cm.getField( filterFieldName2 );
      PrimitiveRdbCondition filter2 = new PrimitiveRdbCondition( filterField2, filterField2.getValueFromEntity( filterEntity2 ) );
      RdbEntityHelper helper = session.getEntityHelper( cm );
      return loadAll0( session, cm, helper.createReadAllFilteredStatement( new PrimitiveRdbCondition[] {filter1, filter2} ) );
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  private static void checkForCompositions ( RdbSession session, List result, ClassMapping cm ) throws RdbException, DIIException
  {
    if ( cm.hasAnyComposition() ) {
      for ( int i = 0; i < result.size(); ++i ) {
        StorableEntityBase entity = ( StorableEntityBase ) result.get( i );
        for ( Iterator cit = cm.getCompositionNameSet().iterator(); cit.hasNext(); ) {
          String compName = ( String ) cit.next();
          StorableObjectList compList = entity.getCompositionValue( compName );
          compList.read( session );
        }
      }
    }
  }

  public XmlTag createRdbXmlEntityTag () throws DIIException, RdbException
  {
    ClassMapping cm = MappingPool.getClassMapping( getClass() );
    EntityTag entityTag = new EntityTag();
    entityTag.setType( getClass().getName() );
    for ( int i = 0; i < cm.getFieldCount(); ++i ) {
      RdbField field = cm.getField( i );
      PropertyTag propTag = new PropertyTag();
      propTag.setName( field.getPropertyName() );
      propTag.setValue( field.getXmlLiteralFromEntity( this ) );
      entityTag.addChild( propTag );
    }
    for ( Iterator cit = cm.getCompositionNameSet().iterator(); cit.hasNext(); ) {
      String compName = ( String ) cit.next();
      SublistTag sublistTag = ( SublistTag ) getCompositionValue( compName ).createRdbXmlEntityTag();
      sublistTag.setName( compName );
      entityTag.addChild( sublistTag );
    }
    return entityTag;
  }

}
