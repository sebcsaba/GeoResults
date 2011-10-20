package scs.javax.io;

import scs.javax.collections.ArrayUtils;

public class RandomAccessArray extends RandomAccessStreamBase
{

  private byte[] data;

  private int filePos;

  public RandomAccessArray ( byte[] src )
  {
    if ( src == null ) {
      throw new NullPointerException( "src" );
    }
    this.data = src;
    this.filePos = 0;
  }

  public int read () throws IOException
  {
    if ( filePos >= data.length ) {
      return -1;
    }
    int result = data[filePos++];
    if ( result < 0 ) result += 256;
    return result;
  }

  public int read ( byte[] src, int offset, int length ) throws IOException
  {
    int count = length;
    if ( filePos + length > data.length ) {
      count = data.length - filePos;
    }
    ArrayUtils.arraycopy( data, filePos, src, offset, count );
    filePos += count;
    return count;
  }

  public void write ( int b ) throws IOException
  {
    if ( filePos >= data.length ) {
      expand( filePos + 1 );
    }
    data[filePos++] = ( byte ) b;
  }

  public void write ( byte[] b, int off, int len ) throws IOException
  {
    if ( filePos + len > data.length ) {
      expand( filePos + len );
    }
    ArrayUtils.arraycopy( b, off, data, filePos, len );
    filePos += len;
  }

  public long length () throws IOException
  {
    return data.length;
  }

  public void setLength ( long newLength ) throws IOException
  {
    if ( newLength > data.length ) {
      expand( ( int ) newLength );
    } else {
      if ( newLength < data.length ) {
        truncate( ( int ) newLength );
      }
    }
  }

  public void seek ( long pos ) throws IOException
  {
    if ( pos < 0 ) {
      throw new IOException();
    }
    filePos = ( int ) pos;
  }

  public long getFilePointer () throws IOException
  {
    return filePos;
  }

  public void flush () throws IOException
  {
  }

  public void close () throws IOException
  {
    filePos = data.length;
  }

  private void expand ( int newLength )
  {
    byte[] newData = new byte[newLength];
    ArrayUtils.arraycopy( data, 0, newData, 0, data.length );
    data = newData;
  }

  private void truncate ( int newLength )
  {
    byte[] newData = new byte[newLength];
    ArrayUtils.arraycopy( data, 0, newData, 0, newLength );
    data = newData;
    if ( filePos > newLength ) {
      filePos = newLength;
    }
  }

}
