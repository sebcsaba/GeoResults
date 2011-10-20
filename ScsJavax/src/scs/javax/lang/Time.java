package scs.javax.lang;

import scs.javax.io.Serializable;

public class Time implements Cloneable, Comparable, Serializable
{

  public static final int SECONDS_IN_A_DAY = 24 * 60 * 60;

  public static final int MINUTES_IN_A_DAY = 24 * 60;

  private int hour;

  private int minute;

  private int second;

  public Time ( int hour, int minute, int second )
  {
    this.hour = hour;
    this.minute = minute;
    this.second = second;
    normalize();
  }

  public Time ( int hour, int minute )
  {
    this.hour = hour;
    this.minute = minute;
    this.second = 0;
    normalize();
  }

  public void addSecond ( int second )
  {
    this.second += second;
    normalize();
  }

  public void addMinute ( int minute )
  {
    this.minute += minute;
    normalize();
  }

  public void addHour ( int hour )
  {
    this.hour += hour;
    normalize();
  }

  public Time add ( Time time )
  {
    return new Time( this.hour + time.hour, this.minute + time.minute, this.second + time.second );
  }

  public Time sub ( Time time )
  {
    return fromInteger( this.toInteger() - time.toInteger() );
  }

  private void normalize ()
  {
    minute += second / 60;
    second %= 60;
    hour += minute / 60;
    minute %= 60;
    hour %= 24;
  }

  private static String twoDigitStr ( int value )
  {
    if ( value < 10 ) {
      return "0" + Integer.toString( value );
    } else {
      return Integer.toString( value );
    }
  }

  public String toString ()
  {
    return twoDigitStr( hour ) + ":" + twoDigitStr( minute ) + ":" + twoDigitStr( second );
  }

  public String getHourMinuteString ()
  {
    return twoDigitStr( hour ) + ":" + twoDigitStr( minute );
  }

  public int getHour ()
  {
    return hour;
  }

  public int getMinute ()
  {
    return minute;
  }

  public int getSecond ()
  {
    return second;
  }

  public void setHour ( int hour )
  {
    this.hour = hour;
    normalize();
  }

  public void setMinute ( int minute )
  {
    this.minute = minute;
    normalize();
  }

  public void setSecond ( int second )
  {
    this.second = second;
    normalize();
  }

  public int toInteger ()
  {
    return ( ( hour * 60 + minute ) * 60 ) + second;
  }

  public int toSeconds ()
  {
    return toInteger();
  }

  public int toMinutes ()
  {
    return toInteger() / 60;
  }

  public static Time fromInteger ( int value )
  {
    return new Time( value / 3600, value / 60 % 60, value % 60 );
  }

  public int compareTo ( Object o )
  {
    Time t = ( Time ) o;
    return toInteger() - t.toInteger();
  }

}
