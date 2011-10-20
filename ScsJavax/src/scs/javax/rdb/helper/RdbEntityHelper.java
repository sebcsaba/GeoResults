package scs.javax.rdb.helper;

import scs.javax.rdb.RdbException;
import scs.javax.rdb.mapping.PrimitiveRdbCondition;

public interface RdbEntityHelper
{

  public Object createCreateStatement ( Object entity ) throws RdbException;

  public Object createReadStatement ( Object entity ) throws RdbException;

  public Object createUpdateStatement ( Object entity ) throws RdbException;

  public Object createDeleteStatement ( Object entity ) throws RdbException;

  public Object createReadAllStatement () throws RdbException;

  public Object createReadAllFilteredStatement ( PrimitiveRdbCondition filter ) throws RdbException;

  public Object createReadAllFilteredStatement ( PrimitiveRdbCondition[] filters ) throws RdbException;

  public Object createReadListStatement ( Object keyEntity ) throws RdbException;

  public Object createCreateTableStatement () throws RdbException;

  public Object createCountAllFilteredStatement ( PrimitiveRdbCondition filter ) throws RdbException;

}
