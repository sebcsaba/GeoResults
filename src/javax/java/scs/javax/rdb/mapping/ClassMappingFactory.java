package scs.javax.rdb.mapping;

import java.util.HashMap;
import java.util.Map;
import scs.javax.collections.List;
import scs.javax.dii.*;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.mapping.fields.RdbField;
import scs.javax.rdb.mapping.tags.*;
import scs.javax.utils.StringUtils;

public class ClassMappingFactory
{

  private ClassMappingFactory ()
  {}

  public static ClassMapping newClassMapping ( MappingTag tag ) throws DIIException, RdbException
  {
    Object[] params = getConstructorParams( tag );
    params[0] = ClassUtils.loadClass( ( String ) params[0] );
    params[1] = ClassUtils.loadClass( ( String ) params[1] );
    return ( ClassMapping ) ConstructorUtils.newInstanceAnyParameter( ClassMapping.class, params );
  }

  public static DeclarativeClassMapping newDeclarativeClassMapping ( MappingTag tag ) throws DIIException, RdbException
  {
    Object[] params = getConstructorParams( tag );
    return ( DeclarativeClassMapping ) ConstructorUtils.newInstanceAnyParameter( DeclarativeClassMapping.class, params );
  }

  private static Object[] getConstructorParams ( MappingTag tag ) throws DIIException, RdbException
  {
    String baseClassName = tag.getClassName();
    String implClassName = baseClassName + "Impl";
    String tableName = tag.getTable();
    List fieldTags = tag.getAllFields();
    List fields = generateRdbFields( fieldTags );
    List compositions = generateRdbCompositions( tag );
    PrimaryKey primaryKey = generatePrimaryKey( tag.getKeyTag(), fields );
    String[] listKeys = generateListKeys( tag.getListKeyTag() );
    SortingCondition[] sortingConditions = generateSortingConditions( tag.getOrderByTag() );
    Map referencedClasses = generateReferencedClasses( fieldTags );
    return new Object[] {baseClassName, implClassName, tableName, fields, compositions, primaryKey, listKeys, sortingConditions, referencedClasses};
  }

  private static List generateRdbFields ( List fieldTags ) throws DIIException
  {
    List fieldsDst = new List( fieldTags.size() );
    for ( int i = 0; i < fieldTags.size(); ++i ) {
      FieldTag fieldTag = ( FieldTag ) fieldTags.get( i );
      fieldsDst.add( getRdbField( fieldTag ) );
    }
    return fieldsDst;
  }

  private static RdbField getRdbField ( FieldTag fieldTag ) throws DIIException
  {
    Class fieldClass = ClassUtils.loadClass( "scs.javax.rdb.mapping.fields.Rdb" + StringUtils.upcaseFirst( fieldTag.getType() ) + "Field" );
    ValueWithStaticType arg1 = new ValueWithStaticType( fieldTag.getName() );
    ValueWithStaticType arg2 = new ValueWithStaticType( fieldTag.isNullEnabled() );
    ConstructorCall init = new ConstructorCall( fieldClass, new ValueWithStaticType[] {arg1, arg2} );
    RdbField field = ( RdbField ) init.create();
    for ( int i = 0; i < fieldTag.getChildrenCount(); ++i ) {
      ParamTag param = fieldTag.getParamChild( i );
      field.setParam( param.getName(), param.getValue() );
    }
    return field;
  }

  private static List generateRdbCompositions ( MappingTag tag )
  {
    List compsSrc = tag.getAllCompositions();
    List compsDst = new List( compsSrc.size() );
    for ( int i = 0; i < compsSrc.size(); ++i ) {
      CompositionTag compTag = ( CompositionTag ) compsSrc.get( i );
      Composition comp = new Composition( compTag.getName(), compTag.getClassName() );
      compsDst.add( comp );
    }
    return compsDst;
  }

  private static PrimaryKey generatePrimaryKey ( KeyTag tag, List fields ) throws DIIException, RdbException
  {
    boolean autoIncrement = tag.isAutoIncrement();
    RdbField[] keys = new RdbField[tag.getChildrenCount()];
    for ( int i = 0; i < tag.getChildrenCount(); ++i ) {
      String fieldName = tag.getAttributeChild( i ).getName();
      keys[i] = ( RdbField ) fields.findItem( "propertyName", fieldName );
      if ( keys[i] == null )throw new NullPointerException( "unknown field in key" );
    }
    return new PrimaryKey( keys, autoIncrement );
  }

  private static String[] generateListKeys ( ListKeyTag listKeyTag )
  {
    String[] result = new String[listKeyTag.getChildrenCount()];
    for ( int i = 0; i < listKeyTag.getChildrenCount(); ++i ) {
      result[i] = listKeyTag.getAttributeChild( i ).getName();
    }
    return result;
  }

  private static SortingCondition[] generateSortingConditions ( OrderByTag orderByTag )
  {
    SortingCondition[] result = new SortingCondition[orderByTag.getChildrenCount()];
    for ( int i = 0; i < orderByTag.getChildrenCount(); ++i ) {
      boolean asc;
      String field = orderByTag.getAttributeChild( i ).getName();
      if ( field.startsWith( "+" ) ) {
        asc = true;
        field = field.substring( 1 );
      } else if ( field.startsWith( "-" ) ) {
        asc = false;
        field = field.substring( 1 );
      } else {
        asc = true;
      }
      result[i] = new SortingCondition( field, asc );
    }
    return result;
  }

  private static Map generateReferencedClasses ( List fieldTags )
  {
    Map result = new HashMap();
    for ( int i = 0; i < fieldTags.size(); ++i ) {
      FieldTag field = ( FieldTag ) fieldTags.get( i );
      String refClass = field.getReferencedClass();
      if ( refClass != null ) result.put( field.getName(), refClass );
    }
    return result;
  }

}
