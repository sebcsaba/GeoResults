package scs.javax.io;

import java.util.Arrays;

public class DataStreamUtils
{

  private static final int BUFFER_SIZE = 2048;

  private static byte[] skipBuffer;

  private static byte[] writeEmptyBuffer;

  private DataStreamUtils ()
  {}

  public static boolean readBoolean ( InputDataStream stream ) throws IOException
  {
    int ch = stream.read();
    if ( ch < 0 )
      throw new EOFException();
    return ( ch != 0 );
  }

  public static char readChar ( InputDataStream stream ) throws IOException
  {
    int ch1 = stream.read();
    int ch2 = stream.read();
    if ( ( ch1 | ch2 ) < 0 )
      throw new EOFException();
    return ( char ) ( ( ch1 << 8 ) + ( ch2 << 0 ) );
  }

  public static byte readByte ( InputDataStream stream ) throws IOException
  {
    int ch = stream.read();
    if ( ch < 0 )
      throw new EOFException();
    return ( byte ) ( ch );
  }

  public static short readShort ( InputDataStream stream ) throws IOException
  {
    int ch1 = stream.read();
    int ch2 = stream.read();
    if ( ( ch1 | ch2 ) < 0 )
      throw new EOFException();
    return ( short ) ( ( ch1 << 8 ) + ( ch2 << 0 ) );
  }

  public static int readInt ( InputDataStream stream ) throws IOException
  {
    int ch1 = stream.read();
    int ch2 = stream.read();
    int ch3 = stream.read();
    int ch4 = stream.read();
    if ( ( ch1 | ch2 | ch3 | ch4 ) < 0 )
      throw new EOFException();
    return ( ( ch1 << 24 ) + ( ch2 << 16 ) + ( ch3 << 8 ) + ( ch4 << 0 ) );
  }

  public static long readLong ( InputDataStream stream ) throws IOException
  {
    return ( ( long ) ( stream.readInt() ) << 32 ) + ( stream.readInt() & 0xFFFFFFFFL );
  }

  public static float readFloat ( InputDataStream stream ) throws IOException
  {
    return Float.intBitsToFloat( stream.readInt() );
  }

  public static double readDouble ( InputDataStream stream ) throws IOException
  {
    return Double.longBitsToDouble( stream.readLong() );
  }

  public static void writeBoolean ( OutputDataStream stream, boolean v ) throws IOException
  {
    stream.write( v ? 1 : 0 );
  }

  public static void writeChar ( OutputDataStream stream, int v ) throws IOException
  {
    stream.write( ( v >>> 8 ) & 0xFF );
    stream.write( ( v >>> 0 ) & 0xFF );
  }

  public static void writeByte ( OutputDataStream stream, int v ) throws IOException
  {
    stream.write( v );
  }

  public static void writeShort ( OutputDataStream stream, int v ) throws IOException
  {
    stream.write( ( v >>> 8 ) & 0xFF );
    stream.write( ( v >>> 0 ) & 0xFF );
  }

  public static void writeInt ( OutputDataStream stream, int v ) throws IOException
  {
    stream.write( ( v >>> 24 ) & 0xFF );
    stream.write( ( v >>> 16 ) & 0xFF );
    stream.write( ( v >>> 8 ) & 0xFF );
    stream.write( ( v >>> 0 ) & 0xFF );
  }

  public static void writeLong ( OutputDataStream stream, long v ) throws IOException
  {
    stream.write( ( int ) ( v >>> 56 ) & 0xFF );
    stream.write( ( int ) ( v >>> 48 ) & 0xFF );
    stream.write( ( int ) ( v >>> 40 ) & 0xFF );
    stream.write( ( int ) ( v >>> 32 ) & 0xFF );
    stream.write( ( int ) ( v >>> 24 ) & 0xFF );
    stream.write( ( int ) ( v >>> 16 ) & 0xFF );
    stream.write( ( int ) ( v >>> 8 ) & 0xFF );
    stream.write( ( int ) ( v >>> 0 ) & 0xFF );
  }

  public static void writeFloat ( OutputDataStream stream, float v ) throws IOException
  {
    stream.writeInt( Float.floatToIntBits( v ) );
  }

  public static void writeDouble ( OutputDataStream stream, double v ) throws IOException
  {
    stream.writeLong( Double.doubleToLongBits( v ) );
  }

  public static void copyStream ( InputStream inStream, OutputStream outStream ) throws IOException
  {
    byte[] buffer = new byte[BUFFER_SIZE];
    int len;
    while ( ( len = inStream.read( buffer ) ) > 0 ) {
      outStream.write( buffer, 0, len );
    }
  }

  public static void readFully ( InputStream stream, byte[] b, int off, int len ) throws
          IOException
  {
    int n = 0;
    do {
      int count = stream.read( b, off + n, len - n );
      if ( count < 0 )
        throw new EOFException();
      n += count;
    }
    while ( n < len );
  }

  public static void skipBytes ( InputStream stream, long count ) throws IOException
  {
    long remaining = count;
    int nr;
    if ( skipBuffer == null ) skipBuffer = new byte[BUFFER_SIZE];
    while ( remaining > 0 ) {
      nr = stream.read( skipBuffer, 0, ( int ) Math.min( BUFFER_SIZE, remaining ) );
      if ( nr < 0 ) {
        throw new EOFException();
      }
      remaining -= nr;
    }
  }

  public static void writeEmptyBytes ( OutputStream stream, long count ) throws IOException
  {
    long remaining = count;
    int nr;
    if ( writeEmptyBuffer == null ) {
      writeEmptyBuffer = new byte[BUFFER_SIZE];
      Arrays.fill( writeEmptyBuffer, ( byte ) 0 );
    }
    while ( remaining > 0 ) {
      nr = ( int ) Math.min( BUFFER_SIZE, remaining );
      stream.write( writeEmptyBuffer, 0, nr );
      remaining -= nr;
    }
  }

}
