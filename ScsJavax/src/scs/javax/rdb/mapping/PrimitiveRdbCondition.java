package scs.javax.rdb.mapping;

import scs.javax.rdb.RdbException;
import scs.javax.rdb.mapping.fields.RdbField;

public class PrimitiveRdbCondition
{

  private RdbField field;

  private Object value;

  public PrimitiveRdbCondition ( RdbField field, Object value )
  {
    this.field = field;
    this.value = value;
  }

  public String toString ()
  {
    String valueStr = ( value == null ? "null" : field.getLiteralFromValue( value ) );
    return field.getPropertyName() + '=' + valueStr;
  }

  public static PrimitiveRdbCondition newInstanceFromValue ( RdbField field, Object value )
  {
    return new PrimitiveRdbCondition( field, value );
  }

  public static PrimitiveRdbCondition newInstanceFromEntity ( RdbField field, Object entity ) throws RdbException
  {
    Object value = field.getValueFromEntity( entity );
    return new PrimitiveRdbCondition( field, value );
  }

  public Object getValue ()
  {
    return value;
  }

  public RdbField getField ()
  {
    return field;
  }

}
