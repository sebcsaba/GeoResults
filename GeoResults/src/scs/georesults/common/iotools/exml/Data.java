package scs.georesults.common.iotools.exml;

import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;
import scs.javax.lang.Date;
import scs.javax.lang.Time;

public abstract class Data implements XmlContent
{

  private String content;

  protected abstract String getType ();

  public void print ( XmlWriter out ) throws IOException
  {
    out.startTag( "Data" );
    out.writeParam( "ss:Type", getType() );
    if ( content != null ) out.writeString( content );
    out.endTag( "Data" );
  }

  protected void setContent ( String content )
  {
    this.content = content;
  }

  public static Data createDataFor ( Object value )
  {
    if ( value == null ) {
      return new StringData();
    } else if ( value instanceof String ) {
      return new StringData( ( String ) value );
    } else if ( value instanceof Boolean ) {
      return new BooleanData( ( ( Boolean ) value ).booleanValue() );
    } else if ( value instanceof Date ) {
      return new DateData( ( Date ) value );
    } else if ( value instanceof Time ) {
      return new TimeData( ( Time ) value );
    } else if ( value instanceof Double || value instanceof Float ) {
      return new NumberData( ( ( Number ) value ).doubleValue() );
    } else if ( value instanceof Number ) {
      return new NumberData( ( ( Number ) value ).longValue() );
    }
    throw new IllegalArgumentException( "unknown object for create cell: '" + value.getClass() + "'" );
  }

}
