package scs.javax.io;

public abstract class InputStreamBase implements InputDataStream
{

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

  public void seekForward ( long count ) throws IOException
  {
    DataStreamUtils.skipBytes( this, count );
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

}
