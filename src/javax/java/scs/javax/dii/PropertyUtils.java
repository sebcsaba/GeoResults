package scs.javax.dii;

public class PropertyUtils
{

  private PropertyUtils ()
  {}

  public static void setProperty ( Object base, String propName, Object propValue ) throws
          DIIException
  {
    createProperty( base.getClass(), propName ).setValue( base, propValue );
  }

  public static Object getProperty ( Object base, String propName ) throws DIIException
  {
    return createProperty( base.getClass(), propName ).getValue( base );
  }

  public static Class getType ( Class clazz, String propName ) throws DIIException
  {
    return createProperty( clazz, propName ).getType();
  }

  public static Property createProperty ( Class clazz, String propName ) throws DIIException
  {
    try {
      return new FieldProperty( clazz, propName );
    }
    catch ( DIIException ex ) {
      return new BeanProperty( clazz, propName );
    }
  }

  public static Property createTypedProperty ( Class clazz, String name, Class staticType ) throws
          DIIException
  {
    Property prop = createProperty( clazz, name );
    if ( prop.getType() != staticType )
      throw new DIIException( "incompatibile types: " + prop.getType() + " and " + staticType );
    return prop;
  }

  public static boolean equals ( Object value1, Object value2 )
  {
    if ( value1 == null && value2 == null )return true;
    if ( value1 != null && value1.equals( value2 ) )return true;
    return false;
  }

}
