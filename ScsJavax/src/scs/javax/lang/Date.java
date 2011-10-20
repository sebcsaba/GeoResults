package scs.javax.lang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import scs.javax.io.Serializable;

public class Date implements Cloneable, Comparable, Serializable
{

  private static final SimpleDateFormat databaseFormat = new SimpleDateFormat( "yyyy-MM-dd" );

  private static final SimpleDateFormat simpleFormat = new SimpleDateFormat( "yyyy.MM.dd" );

  private java.util.Date value;

  public Date ()
  {
    value = new java.util.Date();
  }

  public Date ( int year, int month, int day )
  {
    Calendar c = Calendar.getInstance();
    c.set( year, month - 1, day );
    value = c.getTime();
  }

  public Date ( long date )
  {
    value = new java.util.Date( date );
  }

  public int getYear ()
  {
    Calendar c = Calendar.getInstance();
    c.setTime( value );
    return c.get( Calendar.YEAR );
  }

  public int getMonth ()
  {
    Calendar c = Calendar.getInstance();
    c.setTime( value );
    return c.get( Calendar.MONTH ) + 1;
  }

  public int getDay ()
  {
    Calendar c = Calendar.getInstance();
    c.setTime( value );
    return c.get( Calendar.DATE );
  }

  public long getLongValue ()
  {
    return value.getTime();
  }

  public void setLongValue ( long time )
  {
    value.setTime( time );
  }

  public String getSimpleFace ()
  {
    return simpleFormat.format( value );
  }

  public String getDatabaseFace ()
  {
    return databaseFormat.format( value );
  }

  public void setSimpleFace ( String face ) throws ParseException
  {
    value = simpleFormat.parse( face );
  }

  public void setDatabaseFace ( String face ) throws ParseException
  {
    value = databaseFormat.parse( face );
  }

  public static Date fromSimpleFace ( String face ) throws ParseException
  {
    Date d = new Date();
    d.setSimpleFace( face );
    return d;
  }

  public static Date fromDatabaseFace ( String face ) throws ParseException
  {
    Date d = new Date();
    d.setDatabaseFace( face );
    return d;
  }

  public int compareTo ( Object o )
  {
    Date d = ( Date ) o;
    return value.compareTo( d.value );
  }

  public java.util.Date getOldDate ()
  {
    return value;
  }

}
