package scs.javax.io;

public abstract class RandomAccessStreamBase implements RandomAccessStream
{

  public void write ( byte[] b ) throws IOException
  {
    write( b, 0, b.length );
  }

  public void seekForward ( long count ) throws IOException
  {
    seek( getFilePointer() + count );
  }

  public int read ( byte[] b ) throws IOException
  {
    return read( b, 0, b.length );
  }

  public void readFully ( byte[] b ) throws IOException
  {
    DataStreamUtils.readFully( this, b, 0, b.length );
  }

  public void readFully ( byte[] b, int off, int len ) throws IOException
  {
    DataStreamUtils.readFully( this, b, off, len );
  }

  public long available () throws IOException
  {
    return length() - getFilePointer();
  }

  public boolean readBoolean () throws IOException
  {
    return DataStreamUtils.readBoolean( this );
  }

  public char readChar () throws IOException
  {
    return DataStreamUtils.readChar( this );
  }

  public byte readByte () throws IOException
  {
    return DataStreamUtils.readByte( this );
  }

  public short readShort () throws IOException
  {
    return DataStreamUtils.readShort( this );
  }

  public int readInt () throws IOException
  {
    return DataStreamUtils.readInt( this );
  }

  public long readLong () throws IOException
  {
    return DataStreamUtils.readLong( this );
  }

  public float readFloat () throws IOException
  {
    return DataStreamUtils.readFloat( this );
  }

  public double readDouble () throws IOException
  {
    return DataStreamUtils.readDouble( this );
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
