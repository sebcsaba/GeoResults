package scs.javax.rdb.mapping.fields;

import java.sql.ResultSet;
import java.sql.SQLException;
import scs.javax.rdb.helper.RdbHelper;

public class RdbStringField extends RdbField
{

  public static final String PARAM_MAXIMUMLENGTH = "maximum_length";

  public RdbStringField ( String propertyName, boolean nullEnabled )
  {
    super( propertyName, nullEnabled );
  }

  protected Object getValueFromResultSet0 ( ResultSet rs ) throws SQLException
  {
    String result = rs.getString( propertyName );
    if ( rs.wasNull() )return null;
    return result;
  }

  public String getLiteralFromValue ( Object value )
  {
    String str = ( String ) value;
    str = str.replaceAll( "'", "\\\\'" );
    return "'" + str + "'";
  }

  protected String getXmlLiteralFromValue ( Object value )
  {
    String str = ( String ) value;
    return str;
  }

  protected Object getPrimitiveValueFromXmlLiteral ( String value )
  {
    return value;
  }

  public String getJavaTypeName ()
  {
    return "java.lang.String";
  }

  public String getRdbTypeName ( RdbHelper helper )
  {
    Integer length = null;
    String lenstr = getParam( PARAM_MAXIMUMLENGTH );
    if ( lenstr != null ) length = new Integer( Integer.parseInt( lenstr ) );
    return helper.getStringColumnTypeName( length );
  }

}
