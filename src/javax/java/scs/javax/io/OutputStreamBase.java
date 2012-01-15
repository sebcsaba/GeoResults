package scs.javax.io;

public abstract class OutputStreamBase implements OutputDataStream
{

  public void write ( byte[] b ) throws IOException
  {
    write( b, 0, b.length );
  }

  public void seekForward ( long count ) throws IOException
  {
    DataStreamUtils.writeEmptyBytes( this, count );
  }

  public void writeBoolean ( boolean v ) throws IOException
  {
    DataStreamUtils.writeBoolean( this, v );
  }

  public void writeChar ( int v ) throws IOException
  {
    DataStreamUtils.writeChar( this, v );
  }

  public void writeByte ( int v ) throws IOException
  {
    DataStreamUtils.writeByte( this, v );
  }

  public void writeShort ( int v ) throws IOException
  {
    DataStreamUtils.writeShort( this, v );
  }

  public void writeInt ( int v ) throws IOException
  {
    DataStreamUtils.writeInt( this, v );
  }

  public void writeLong ( long v ) throws IOException
  {
    DataStreamUtils.writeLong( this, v );
  }

  public void writeFloat ( float v ) throws IOException
  {
    DataStreamUtils.writeFloat( this, v );
  }

  public void writeDouble ( double v ) throws IOException
  {
    DataStreamUtils.writeDouble( this, v );
  }

}
