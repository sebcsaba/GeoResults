package scs.javax.rdb.pseudosession;

import scs.javax.rdb.RdbException;
import scs.javax.rdb.helper.RdbEntityHelper;
import scs.javax.rdb.mapping.ClassMapping;
import scs.javax.rdb.mapping.PrimitiveRdbCondition;
import scs.javax.rdb.mapping.fields.RdbField;

public class RdbEntitiesEntityHelper implements RdbEntityHelper
{

  private ClassMapping cm;

  public RdbEntitiesEntityHelper ( ClassMapping cm )
  {
    this.cm = cm;
  }

  public Object createReadStatement ( Object entity ) throws RdbException
  {
    RdbField[] keyFields = cm.getPrimaryKey().getFields();
    RdbEntityCondition condition = new RdbEntityCondition( entity.getClass() );
    for ( int i = 0; i < keyFields.length; ++i ) {
      condition.addCondition( PrimitiveRdbCondition.newInstanceFromEntity( keyFields[i], entity ) );
    }
    return condition;
  }

  public Object createReadAllStatement () throws RdbException
  {
    RdbField[] keyFields = cm.getPrimaryKey().getFields();
    RdbEntityCondition condition = new RdbEntityCondition( cm.getImplClass() );
    return condition;
  }

  public Object createReadAllFilteredStatement ( PrimitiveRdbCondition filter ) throws RdbException
  {
    RdbField[] keyFields = cm.getPrimaryKey().getFields();
    RdbEntityCondition condition = new RdbEntityCondition( cm.getImplClass() );
    condition.addCondition( filter );
    return condition;
  }

  public Object createReadAllFilteredStatement ( PrimitiveRdbCondition[] filters ) throws RdbException
  {
    RdbField[] keyFields = cm.getPrimaryKey().getFields();
    RdbEntityCondition condition = new RdbEntityCondition( cm.getImplClass() );
    for ( int i = 0; i < filters.length; ++i ) {
      condition.addCondition( filters[i] );
    }
    return condition;
  }

  public Object createReadListStatement ( Object keyEntity ) throws RdbException
  {
    RdbField[] keyFields = cm.getPrimaryKey().getFields();
    RdbEntityCondition condition = new RdbEntityCondition( cm.getImplClass() );
    for ( int i = 0; i < cm.getListKey().length; ++i ) {
      RdbField field = cm.getField( cm.getListKey()[i] );
      condition.addCondition( PrimitiveRdbCondition.newInstanceFromEntity( field, keyEntity ) );
    }
    return condition;
  }

  public Object createCreateStatement ( Object entity ) throws RdbException
  {
    throw new UnsupportedOperationException();
  }

  public Object createUpdateStatement ( Object entity ) throws RdbException
  {
    throw new UnsupportedOperationException();
  }

  public Object createDeleteStatement ( Object entity ) throws RdbException
  {
    throw new UnsupportedOperationException();
  }

  public Object createCreateTableStatement () throws RdbException
  {
    throw new UnsupportedOperationException();
  }

  public Object createCountAllFilteredStatement ( PrimitiveRdbCondition filter ) throws RdbException
  {
    throw new UnsupportedOperationException();
  }

}
