package scs.javax.rdb.mapping.fields;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import scs.javax.dii.DIIException;
import scs.javax.dii.PropertyUtils;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.helper.RdbHelper;

public abstract class RdbField
{

  protected String propertyName;

  protected boolean nullEnabled;

  protected Map params;

  protected RdbField ( String propertyName, boolean nullEnabled )
  {
    this.propertyName = propertyName;
    this.nullEnabled = nullEnabled;
    this.params = new HashMap();
  }

  public String getPropertyName ()
  {
    return propertyName;
  }

  public boolean isNullEnabled ()
  {
    return nullEnabled;
  }

  protected void checkNull ( Object value ) throws RdbException
  {
    if ( value == null && !nullEnabled ) {
      throw new RdbException( "no null value enabled for field '" + propertyName + "'" );
    }
  }

  public Object getValueFromEntity ( Object entity ) throws RdbException
  {
    try {
      Object value = PropertyUtils.getProperty( entity, propertyName );
      checkNull( value );
      return value;
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  public void setValueForEntity ( Object entity, Object value ) throws RdbException
  {
    try {
      checkNull( value );
      PropertyUtils.setProperty( entity, propertyName, value );
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  public Object getValueFromResultSet ( ResultSet rs ) throws RdbException
  {
    try {
      Object value = getValueFromResultSet0( rs );
      checkNull( value );
      return value;
    }
    catch ( SQLException ex ) {
      throw new RdbException( ex );
    }
  }

  protected abstract Object getValueFromResultSet0 ( ResultSet rs ) throws SQLException;

  public void setValueFromResultSetToEntity ( ResultSet rs, Object entity ) throws RdbException
  {
    try {
      setValueForEntity( entity, getValueFromResultSet0( rs ) );
    }
    catch ( SQLException ex ) {
      throw new RdbException( ex );
    }
  }

  public abstract String getLiteralFromValue ( Object value );

  public String getLiteralFromEntity ( Object entity ) throws RdbException
  {
    Object value = getValueFromEntity( entity );
    return ( value == null ? "null" : getLiteralFromValue( value ) );
  }

  public abstract String getJavaTypeName ();

  public abstract String getRdbTypeName ( RdbHelper helper );

  public void setParam ( String paramName, String paramValue )
  {
    params.put( paramName, paramValue );
  }

  public String getParam ( String paramName )
  {
    return ( String ) params.get( paramName );
  }

  protected abstract String getXmlLiteralFromValue ( Object value );

  public String getXmlLiteralFromEntity ( StorableEntityBase entity ) throws RdbException
  {
    Object value = getValueFromEntity( entity );
    return ( value == null ? null : getXmlLiteralFromValue( value ) );
  }

  public void setValueFromXmlLiteralToEntity ( StorableEntityBase entity, String value ) throws RdbException
  {
    Object primitiveValue = null;
    if ( value != null ) primitiveValue = getPrimitiveValueFromXmlLiteral( value );
    setValueForEntity( entity, primitiveValue );
  }

  protected abstract Object getPrimitiveValueFromXmlLiteral ( String value );

}
