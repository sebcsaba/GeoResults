package scs.javax.rdb.mapping;

import scs.javax.rdb.RdbException;
import scs.javax.rdb.mapping.fields.RdbField;

public class PrimaryKey
{

  private RdbField[] keys;

  private boolean autoIncrement;

  public PrimaryKey ( RdbField[] keys )
  {
    this.keys = keys;
    this.autoIncrement = false;
  }

  public PrimaryKey ( RdbField[] keys, boolean autoIncrement ) throws RdbException
  {
    this.keys = keys;
    this.autoIncrement = autoIncrement;
    if ( autoIncrement && keys.length > 1 )throw new RdbException( "invalid key: an auto-incremented key must contain only one field" );
  }

  public PrimaryKey ( RdbField key, boolean autoIncrement )
  {
    this.keys = new RdbField[] {key};
    this.autoIncrement = autoIncrement;
  }

  public boolean isAutoIncrement ()
  {
    return autoIncrement;
  }

  public RdbField[] getFields ()
  {
    return keys;
  }

  public boolean hasField ( RdbField theField )
  {
    for ( int i = 0; i < keys.length; ++i ) {
      if ( keys[i] == theField )return true;
    }
    return false;
  }

}
