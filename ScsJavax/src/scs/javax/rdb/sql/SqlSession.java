package scs.javax.rdb.sql;

import java.sql.*;
import scs.javax.collections.List;
import scs.javax.dii.ClassUtils;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.mapping.ClassMapping;
import scs.javax.rdb.mapping.fields.RdbField;

public class SqlSession extends RdbSession
{

  private String connectionString;

  private Connection conn;

  public SqlSession ( Class driverClass, String connectionString ) throws RdbException
  {
    super( new SqlFactory() );
    try {
      ClassUtils.newInstance( driverClass );
      this.connectionString = connectionString;
      this.conn = null;
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  private Connection getConnection () throws RdbException
  {
    try {
      if ( conn == null || conn.isClosed() ) {
        conn = DriverManager.getConnection( connectionString );
      }
      return conn;
    }
    catch ( SQLException ex ) {
      throw new RdbException( ex );
    }
  }

  protected Object queryFirstWork ( Object query, ClassMapping cm, Object entity ) throws RdbException
  {
    try {
      Statement stm = getConnection().createStatement();
      ResultSet rs = stm.executeQuery( ( String ) query );
      if ( rs.next() ) {
        copyToEntity( rs, entity, cm );
      } else {
        throw new NoResultException( query );
      }
      rs.close();
      stm.close();
      return entity;
    }
    catch ( SQLException ex ) {
      throw new RdbException( ex );
    }
  }

  private static void copyToEntity ( ResultSet rs, Object entity, ClassMapping cm ) throws RdbException
  {
    for ( int i = 0; i < cm.getFieldCount(); ++i ) {
      RdbField field = cm.getField( i );
      field.setValueFromResultSetToEntity( rs, entity );
    }
  }

  protected List queryAllWork ( Object query, ClassMapping cm ) throws RdbException
  {
    try {
      Statement stm = getConnection().createStatement();
      ResultSet rs = stm.executeQuery( ( String ) query );
      List result = new List();
      while ( rs.next() ) {
        Object entity = ClassUtils.newInstance( cm.getImplClass() );
        copyToEntity( rs, entity, cm );
        result.add( entity );
      }
      rs.close();
      stm.close();
      return result;
    }
    catch ( Exception ex ) {
      throw new RdbException( ex );
    }
  }

  protected long updateWork ( Object update ) throws RdbException
  {
    try {
      Statement stm = getConnection().createStatement();
      stm.executeUpdate( ( String ) update );
      long result = getGeneratedKey( stm );
      stm.close();
      return result;
    }
    catch ( SQLException ex ) {
      throw new RdbException( ex );
    }
  }

  private static long getGeneratedKey ( Statement stm ) throws SQLException
  {
    ResultSet rs = stm.getGeneratedKeys();
    long result;
    if ( rs.next() ) {
      result = rs.getLong( 1 );
    } else {
      result = -1;
    }
    rs.close();
    return result;
  }

}
