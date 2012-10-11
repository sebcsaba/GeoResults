package scs.javax.rdb.sql;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.helper.RdbEntityHelper;
import scs.javax.rdb.helper.RdbHelper;
import scs.javax.rdb.mapping.*;
import scs.javax.rdb.mapping.fields.RdbField;
import scs.javax.utils.StringUtils;

public class SqlEntityHelper implements RdbEntityHelper
{

  private ClassMappingBase cm;

  private String[] fieldNames;

  private String fieldNameList;
  
  private String fieldNameListWithoutAutoincrement;

  private String orderByClause;

  private final String sql_create_part1;

  private final String sql_read_part1;

  private final String sql_count_part1;

  private final String sql_update_part1;

  private final String sql_delete_part1;

  public SqlEntityHelper ( ClassMappingBase cm )
  {
    this.cm = cm;
    initFieldNameList();
    initFieldNameListWithoutAutoincrement();
    initOrderByClause();
    this.sql_create_part1 = "INSERT INTO " + cm.getTableName() + " (" + fieldNameListWithoutAutoincrement + ") VALUES (";
    this.sql_read_part1 = "SELECT " + fieldNameList + " FROM " + cm.getTableName() + " WHERE ";
    this.sql_count_part1 = "SELECT COUNT(*) FROM " + cm.getTableName() + " WHERE ";
    this.sql_update_part1 = "UPDATE " + cm.getTableName() + " SET ";
    this.sql_delete_part1 = "DELETE FROM " + cm.getTableName() + " WHERE ";
  }

  private void initFieldNameList ()
  {
    fieldNames = new String[cm.getFieldCount()];
    for ( int i = 0; i < cm.getFieldCount(); ++i ) {
      fieldNames[i] = cm.getField( i ).getPropertyName();
    }
    fieldNameList = StringUtils.joinStrings( fieldNames, "," );
  }
  
  private void initFieldNameListWithoutAutoincrement()
  {
    PrimaryKey pk = cm.getPrimaryKey();
	if (pk.isAutoIncrement()) {
    	List fieldNames = new List();
    	for (int i=0; i<cm.getFieldCount(); ++i) {
    		if (!pk.hasField(cm.getField(i))) {
    			fieldNames.add(cm.getField(i).getPropertyName());
    		}
    	}
    	String[] fieldNamesArray = (String[]) fieldNames.toArray(new String[fieldNames.size()]);
		fieldNameListWithoutAutoincrement = StringUtils.joinStrings(fieldNamesArray, ",");
    } else {
    	fieldNameListWithoutAutoincrement = fieldNameList;
    }
  }

  private void initOrderByClause ()
  {
    SortingCondition[] sorts = cm.getSortingConditions();
    if ( sorts == null || sorts.length == 0 ) {
      orderByClause = "";
    } else {
      String[] dirs = new String[sorts.length];
      for ( int i = 0; i < sorts.length; ++i ) {
        dirs[i] = sorts[i].getFieldName() + ( sorts[i].isAscendent() ? " ASC" : " DESC" );
      }
      orderByClause = " ORDER BY " + StringUtils.joinStrings( dirs, ", " );
    }
  }

  private String getFieldValueList ( Object entity ) throws RdbException
  {
    String[] fieldValues = new String[fieldNames.length];
    for ( int i = 0; i < fieldNames.length; ++i ) {
      fieldValues[i] = cm.getField( fieldNames[i] ).getLiteralFromEntity( entity );
    }
    return StringUtils.joinStrings( fieldValues, "," );
  }

  private String getFieldValueListWithoutAutoincrement ( Object entity ) throws RdbException
  {
	PrimaryKey pk = cm.getPrimaryKey();
	if (pk.isAutoIncrement()) {
      List fieldValues = new List();
      for ( int i = 0; i < fieldNames.length; ++i ) {
        RdbField field = cm.getField( fieldNames[i] );
        if (!pk.hasField(field)) {
		  fieldValues.add( field.getLiteralFromEntity( entity ));
        }
      }
      String[] fieldValuesArray = (String[]) fieldValues.toArray(new String[fieldValues.size()]);
	return StringUtils.joinStrings( fieldValuesArray, "," );
	} else {
	  return getFieldValueList(entity);
	}
  }

  private String getUpdateFieldValueList ( Object entity ) throws RdbException
  {
    String[] fieldNamesAndValues = new String[fieldNames.length];
    for ( int i = 0; i < fieldNames.length; ++i ) {
      RdbField field = cm.getField( fieldNames[i] );
      fieldNamesAndValues[i] = field.getPropertyName() + "=" + field.getLiteralFromEntity( entity );
    }
    return StringUtils.joinStrings( fieldNamesAndValues, ", " );
  }

  private static String getCondition ( PrimitiveRdbCondition cond )
  {
    return cond.getField().getPropertyName() + "=" + cond.getField().getLiteralFromValue( cond.getValue() );
  }

  private String getConditionFromPrimaryKeyAndEntity ( Object entity ) throws RdbException
  {
    RdbField[] fields = cm.getPrimaryKey().getFields();
    String[] conds = new String[fields.length];
    for ( int i = 0; i < fields.length; ++i ) {
      conds[i] = PrimitiveRdbCondition.newInstanceFromEntity( fields[i], entity ).toString();
    }
    return StringUtils.joinStrings( conds, " and " );
  }

  public Object createCreateStatement ( Object entity ) throws RdbException
  {
    return sql_create_part1 + getFieldValueListWithoutAutoincrement( entity ) + ")";
  }

  public Object createReadStatement ( Object entity ) throws RdbException
  {
    return sql_read_part1 + getConditionFromPrimaryKeyAndEntity( entity );
  }

  public Object createUpdateStatement ( Object entity ) throws RdbException
  {
    return sql_update_part1 + getUpdateFieldValueList( entity ) + " WHERE " + getConditionFromPrimaryKeyAndEntity( entity );
  }

  public Object createDeleteStatement ( Object entity ) throws RdbException
  {
    return sql_delete_part1 + getConditionFromPrimaryKeyAndEntity( entity );
  }

  public Object createReadAllStatement () throws RdbException
  {
    return sql_read_part1 + "1=1";
  }

  public Object createReadAllFilteredStatement ( PrimitiveRdbCondition filter ) throws RdbException
  {
    return sql_read_part1 + getCondition( filter ) + orderByClause;
  }

  public Object createReadAllFilteredStatement ( PrimitiveRdbCondition[] filters ) throws RdbException
  {
    String[] conds = new String[filters.length];
    for ( int i = 0; i < filters.length; ++i ) conds[i] = getCondition( filters[i] );
    return sql_read_part1 + StringUtils.joinStrings( conds, " AND " ) + orderByClause;
  }

  public Object createReadListStatement ( Object keyEntity ) throws RdbException
  {
    String[] conds = new String[cm.getListKey().length];
    for ( int i = 0; i < cm.getListKey().length; ++i ) {
      RdbField field = cm.getField( cm.getListKey()[i] );
      conds[i] = field.getPropertyName() + "=" + field.getLiteralFromEntity( keyEntity );
    }
    return sql_read_part1 + StringUtils.joinStrings( conds, " AND " ) + orderByClause;
  }

  public Object createCreateTableStatement () throws RdbException
  {
    PrimaryKey key = cm.getPrimaryKey();
    StringBuffer result = new StringBuffer( "CREATE TABLE " );
    result.append( cm.getTableName() ).append( " ( " );
    for ( int i = 0; i < cm.getFieldCount(); ++i ) {
      RdbField field = cm.getField( i );
      result.append( getSqlFieldDefinition( field, key ) ).append( ", " );
    }
    result.append( "PRIMARY KEY (" );
    for ( int i = 0; i < key.getFields().length; ++i ) {
      RdbField field = key.getFields()[i];
      if ( i > 0 ) result.append( ", " );
      result.append( field.getPropertyName() );
    }
    result.append( ") ) DEFAULT CHARSET=utf8;" );
    return result.toString();
  }

  public Object createCountAllFilteredStatement ( PrimitiveRdbCondition filter ) throws RdbException
  {
    return sql_count_part1 + getCondition( filter ) + orderByClause;
  }

  private static String getSqlFieldDefinition ( RdbField field, PrimaryKey key )
  {
    RdbHelper globalHelper = new SqlHelper();
    StringBuffer result = new StringBuffer();
    result.append( field.getPropertyName() ).append( " " ).append( field.getRdbTypeName( globalHelper ) ).append( " " );
    if ( field.isNullEnabled() ) {
      result.append( "NULL" );
    } else {
      result.append( "NOT NULL" );
    }
    if ( key.isAutoIncrement() && key.hasField( field ) ) {
      result.append( " auto_increment" );
    }
    return result.toString();
  }

}
