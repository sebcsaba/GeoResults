package scs.javax.rdb.mapping.fields;

import java.sql.ResultSet;
import java.sql.SQLException;
import scs.javax.rdb.helper.RdbHelper;

public class RdbBooleanField extends RdbField
{

  public RdbBooleanField ( String propertyName, boolean nullEnabled )
  {
    super( propertyName, nullEnabled );
  }

  protected Object getValueFromResultSet0 ( ResultSet rs ) throws SQLException
  {
    int i = rs.getInt( propertyName );
    if ( rs.wasNull() )return null;
    return Boolean.valueOf( i != 0 );
  }

  public String getLiteralFromValue ( Object value )
  {
    return ( ( Boolean ) value ).booleanValue() ? "1" : "0";
  }

  protected String getXmlLiteralFromValue ( Object value )
  {
    return ( ( Boolean ) value ).booleanValue() ? "1" : "0";
  }

  protected Object getPrimitiveValueFromXmlLiteral ( String value )
  {
    return Boolean.valueOf( Integer.parseInt( value ) > 0 );
  }

  public String getJavaTypeName ()
  {
    return nullEnabled ? "java.lang.Boolean" : "boolean";
  }

  public String getRdbTypeName ( RdbHelper helper )
  {
    return helper.getIntegerColumnTypeName();
  }

}
