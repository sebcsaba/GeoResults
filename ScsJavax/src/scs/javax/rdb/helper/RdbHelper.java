package scs.javax.rdb.helper;

import scs.javax.rdb.RdbException;

public interface RdbHelper
{

  public Object createDropTableStatement ( String tablename ) throws RdbException;

  public Object createShowTablesStement () throws RdbException;

  public String getIntegerColumnTypeName ();

  public String getLongColumnTypeName ();

  public String getDateColumnTypeName ();

  public String getStringColumnTypeName ( Integer length );

}
