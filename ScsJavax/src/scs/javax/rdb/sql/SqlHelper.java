package scs.javax.rdb.sql;

import scs.javax.rdb.RdbException;
import scs.javax.rdb.helper.RdbHelper;

public class SqlHelper implements RdbHelper
{

  public Object createDropTableStatement ( String tablename ) throws RdbException
  {
    return "DROP TABLE " + tablename;
  }

  public Object createShowTablesStement () throws RdbException
  {
    return "SHOW TABLES";
  }

  public String getIntegerColumnTypeName ()
  {
    return "INT";
  }

  public String getLongColumnTypeName ()
  {
    return "BIGINT";
  }

  public String getDateColumnTypeName ()
  {
    return "DATE";
  }

  public String getStringColumnTypeName ( Integer length )
  {
    int len = ( length == null ) ? 100 : length.intValue();
    return "VARCHAR(" + len + ") CHARACTER SET utf8";
  }

}
