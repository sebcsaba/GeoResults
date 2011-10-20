package scs.javax.rdb.mapping;

import java.util.Map;
import scs.javax.collections.List;
import scs.javax.rdb.mapping.fields.RdbDateField;
import scs.javax.rdb.mapping.fields.RdbTimeField;

public class DeclarativeClassMapping extends ClassMappingBase
{

  private String baseClassName;

  private String implClassName;

  public DeclarativeClassMapping ( String baseClassName, String implClassName, String tableName, List fields, List compositions, PrimaryKey primaryKey, String[] listKey, SortingCondition[] sortingConditions, Map referencedClasses )
  {
    super( tableName, fields, compositions, primaryKey, listKey, sortingConditions, referencedClasses );
    this.baseClassName = baseClassName;
    this.implClassName = implClassName;
  }

  public String getImplClassName ()
  {
    return implClassName;
  }

  public String getBaseClassName ()
  {
    return baseClassName;
  }

  public boolean hasListKey ( String propertyName )
  {
    if ( listKey != null ) {
      for ( int i = 0; i < listKey.length; ++i ) {
        if ( listKey[i].equals( propertyName ) )return true;
      }
    }
    return false;
  }

  public boolean hasDateField ()
  {
    for ( int i = 0; i < getFieldCount(); ++i ) {
      if ( getField( i ) instanceof RdbDateField )return true;
    }
    return false;
  }

  public boolean hasTimeField ()
  {
    for ( int i = 0; i < getFieldCount(); ++i ) {
      if ( getField( i ) instanceof RdbTimeField )return true;
    }
    return false;
  }

  public boolean isListable ()
  {
    return listKey != null && listKey.length > 0;
  }

}
