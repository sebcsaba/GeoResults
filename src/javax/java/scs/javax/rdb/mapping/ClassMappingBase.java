package scs.javax.rdb.mapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import scs.javax.collections.List;
import scs.javax.rdb.mapping.fields.RdbField;

public class ClassMappingBase
{

  protected String tableName;

  protected PrimaryKey primaryKey;

  protected List fields; // to preserve field order

  protected Map fieldsMap; // String (field name) -> RdbField

  protected Map compositions; // String (composition name) -> Composition

  protected String[] listKey; // String (field name)

  protected Map referencedClasses; // String (field name) -> String (class name)

  protected SortingCondition[] sortingConditions;

  protected ClassMappingBase ( String tableName, List fields, List compositions, PrimaryKey primaryKey, String[] listKey, SortingCondition[] sortingConditions, Map referencedClasses )
  {
    this.tableName = tableName;
    this.fields = fields;
    this.primaryKey = primaryKey;
    this.referencedClasses = referencedClasses;
    initFields( fields );
    initCompositions( compositions );
    initListKey( listKey );
    initSortingConditions( sortingConditions );
  }

  private void initFields ( List fieldList )
  {
    this.fieldsMap = new HashMap();
    for ( int i = 0; i < fieldList.size(); ++i ) {
      RdbField field = ( RdbField ) fieldList.get( i );
      this.fieldsMap.put( field.getPropertyName(), field );
    }
  }

  private void initCompositions ( List compositionList )
  {
    this.compositions = new HashMap();
    if ( compositionList != null ) {
      for ( int i = 0; i < compositionList.size(); ++i ) {
        Composition composition = ( Composition ) compositionList.get( i );
        this.compositions.put( composition.getCompName(), composition );
      }
    }
  }

  private void initListKey ( String[] listKey )
  {
    this.listKey = listKey;
    if ( listKey != null && listKey.length == 0 )this.listKey = null;
  }

  private void initSortingConditions ( SortingCondition[] sortingConditions )
  {
    this.sortingConditions = sortingConditions;
    if ( sortingConditions != null && sortingConditions.length == 0 )this.sortingConditions = null;
  }

  public String getTableName ()
  {
    return tableName;
  }

  public PrimaryKey getPrimaryKey ()
  {
    return primaryKey;
  }

  public SortingCondition[] getSortingConditions ()
  {
    return sortingConditions;
  }

  public String[] getListKey ()
  {
    return listKey;
  }

  public int getFieldCount ()
  {
    return fields.size();
  }

  public RdbField getField ( int i )
  {
    return ( RdbField ) fields.get( i );
  }

  public RdbField getField ( String propertyName )
  {
    return ( RdbField ) fieldsMap.get( propertyName );
  }

  public Set getCompositionNameSet ()
  {
    return compositions.keySet();
  }

  public boolean hasAnyComposition ()
  {
    return compositions.size() > 0;
  }

  public Composition getComposition ( String compName )
  {
    return ( Composition ) compositions.get( compName );
  }

  public Set getReferencedClassAttributeNames ()
  {
    return referencedClasses.keySet();
  }

  public String getReferencedClassName ( String attributeName )
  {
    return ( String ) referencedClasses.get( attributeName );
  }

}
