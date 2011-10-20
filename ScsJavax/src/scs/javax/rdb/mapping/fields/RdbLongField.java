package scs.javax.rdb.mapping.fields;

import java.sql.ResultSet;
import java.sql.SQLException;
import scs.javax.rdb.helper.RdbHelper;

public class RdbLongField extends RdbField
{

  public RdbLongField ( String propertyName, boolean nullEnabled )
  {
    super( propertyName, nullEnabled );
  }

  protected Object getValueFromResultSet0 ( ResultSet rs ) throws SQLException
  {
    long l = rs.getLong( propertyName );
    if ( rs.wasNull() )return null;
    return new Long( l );
  }

  public String getLiteralFromValue ( Object value )
  {
    return ( ( Long ) value ).toString();
  }

  protected String getXmlLiteralFromValue ( Object value )
  {
    return ( ( Long ) value ).toString();
  }

  protected Object getPrimitiveValueFromXmlLiteral ( String value )
  {
    return new Long( Long.parseLong( value ) );
  }

  public String getJavaTypeName ()
  {
    return nullEnabled ? "java.lang.Long" : "long";
  }

  public String getRdbTypeName ( RdbHelper helper )
  {
    return helper.getLongColumnTypeName();
  }

}
