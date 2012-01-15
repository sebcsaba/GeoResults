package scs.javax.rdb.mapping.fields;

import java.sql.ResultSet;
import java.sql.SQLException;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.mapping.ClassMapping;
import scs.javax.rdb.mapping.PrimaryKey;

public class RdbMetaStringField extends RdbStringField
{

  public static final String STATIC_COLUMN_NAME = "data";

  public static final int STATIC_COLUMN_INDEX = 0;

  private static ClassMapping metaCm;

  public RdbMetaStringField ()
  {
    super( STATIC_COLUMN_NAME, true );
  }

  protected Object getValueFromResultSet0 ( ResultSet rs ) throws SQLException
  {
    String result = rs.getString( STATIC_COLUMN_INDEX + 1 );
    if ( rs.wasNull() )return null;
    return result;
  }

  public static ClassMapping getMetaCm () throws DIIException
  {
    if ( metaCm == null ) {
      RdbField field = new RdbMetaStringField();
      List fields = new List();
      fields.add( field );
      PrimaryKey key = new PrimaryKey( field, false );
      metaCm = new ClassMapping( MetaData.class, MetaDataImpl.class, "", fields, null, key, null, null, null );
    }
    return metaCm;
  }

  public static class MetaData
  {

    private String data;

    public String getData ()
    {
      return data;
    }

    public void setData ( String data )
    {
      this.data = data;
    }

    public String toString ()
    {
      return data;
    }

    public boolean equals ( Object o )
    {
      if ( o instanceof MetaData ) {
        return data.equals( ( ( MetaData ) o ).data );
      } else return false;
    }

    public int hashCode ()
    {
      return data.hashCode();
    }

  }


  public static class MetaDataImpl extends MetaData
  {}

}
