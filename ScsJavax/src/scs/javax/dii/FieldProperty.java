package scs.javax.dii;

import java.lang.reflect.Field;

public class FieldProperty extends Property
{

  private Field field;

  protected FieldProperty ( Class clazz, String name ) throws DIIException
  {
    super( name );
    try {
      field = clazz.getField( name );
    }
    catch ( NoSuchFieldException ex ) {
      throw new DIIException( ex );
    }
  }

  public Class getType () throws DIIException
  {
    return field.getType();
  }

  public Object getValue ( Object base ) throws DIIException
  {
    try {
      return field.get( base );
    }
    catch ( Exception ex ) {
      throw new DIIException( ex );
    }
  }

  public void setValue ( Object base, Object value ) throws DIIException
  {
    try {
      field.set( base, value );
    }
    catch ( Exception ex ) {
      throw new DIIException( ex );
    }
  }

}
