package scs.javax.rdb.mapping.fields;

import java.sql.ResultSet;
import java.sql.SQLException;
import scs.javax.rdb.helper.RdbHelper;

public class RdbIntegerField extends RdbField
{

  public RdbIntegerField ( String propertyName, boolean nullEnabled )
  {
    super( propertyName, nullEnabled );
  }

  protected Object getValueFromResultSet0 ( ResultSet rs ) throws SQLException
  {
    int i = rs.getInt( propertyName );
    if ( rs.wasNull() )return null;
    return new Integer( i );
  }

  public String getLiteralFromValue ( Object value )
  {
    return ( ( Integer ) value ).toString();
  }

  protected String getXmlLiteralFromValue ( Object value )
  {
    return ( ( Integer ) value ).toString();
  }

  protected Object getPrimitiveValueFromXmlLiteral ( String value )
  {
    return new Integer( Integer.parseInt( value ) );
  }

  public String getJavaTypeName ()
  {
    return nullEnabled ? "java.lang.Integer" : "int";
  }

  public String getRdbTypeName ( RdbHelper helper )
  {
    return helper.getIntegerColumnTypeName();
  }

}
