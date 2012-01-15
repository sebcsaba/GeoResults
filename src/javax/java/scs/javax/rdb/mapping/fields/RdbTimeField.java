package scs.javax.rdb.mapping.fields;

import java.sql.ResultSet;
import java.sql.SQLException;
import scs.javax.lang.Time;
import scs.javax.rdb.helper.RdbHelper;

public class RdbTimeField extends RdbField
{

  public RdbTimeField ( String propertyName, boolean nullEnabled )
  {
    super( propertyName, nullEnabled );
  }

  protected Object getValueFromResultSet0 ( ResultSet rs ) throws SQLException
  {
    int i = rs.getInt( propertyName );
    if ( rs.wasNull() )return null;
    return Time.fromInteger( i );
  }

  public String getLiteralFromValue ( Object value )
  {
    return Integer.toString( ( ( Time ) value ).toInteger() );
  }

  protected String getXmlLiteralFromValue ( Object value )
  {
    return Integer.toString( ( ( Time ) value ).toInteger() );
  }

  protected Object getPrimitiveValueFromXmlLiteral ( String value )
  {
    return Time.fromInteger( Integer.parseInt( value ) );
  }

  public String getJavaTypeName ()
  {
    return "scs.javax.lang.Time";
  }

  public String getRdbTypeName ( RdbHelper helper )
  {
    return helper.getIntegerColumnTypeName();
  }

}
