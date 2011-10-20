package scs.javax.rdb.mapping.fields;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import scs.javax.lang.Date;
import scs.javax.rdb.helper.RdbHelper;

public class RdbDateField extends RdbField
{

  public RdbDateField ( String propertyName, boolean nullEnabled )
  {
    super( propertyName, nullEnabled );
  }

  protected Object getValueFromResultSet0 ( ResultSet rs ) throws SQLException
  {
    String face = rs.getString( propertyName );
    if ( rs.wasNull() )return null;
    try {
      return Date.fromDatabaseFace( face );
    }
    catch ( ParseException ex ) {
      throw new SQLException( ex.toString() );
    }
  }

  public String getLiteralFromValue ( Object value )
  {
    return "'" + ( ( Date ) value ).getDatabaseFace() + "'";
  }

  protected String getXmlLiteralFromValue ( Object value )
  {
    return ( ( Date ) value ).getDatabaseFace();
  }

  protected Object getPrimitiveValueFromXmlLiteral ( String value )
  {
    try {
      return Date.fromDatabaseFace( value );
    }
    catch ( ParseException ex ) {
      throw new IllegalArgumentException( ex.getMessage() );
    }
  }

  public String getJavaTypeName ()
  {
    return "scs.javax.lang.Date";
  }

  public String getRdbTypeName ( RdbHelper helper )
  {
    return helper.getDateColumnTypeName();
  }

}
