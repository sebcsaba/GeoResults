package scs.javax.io;

public class RandomAccessFile extends RandomAccessStreamBase
{

  private java.io.RandomAccessFile stream;

  public RandomAccessFile ( Path filename ) throws FileNotFoundException
  {
    this( filename.toString() );
  }

  public RandomAccessFile ( String filename ) throws FileNotFoundException
  {
    try {
      this.stream = new java.io.RandomAccessFile( filename, "rwd" );
    }
    catch ( java.io.FileNotFoundException ex ) {
      throw new FileNotFoundException( filename );
    }
  }

  public int read () throws IOException
  {
    try {
      int i = stream.read();
      if ( i == -1 )throw new EOFException();
      return i;
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public int read ( byte[] b, int off, int len ) throws IOException
  {
    try {
      return stream.read( b, off, len );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public void write ( int b ) throws IOException
  {
    try {
      stream.write( b );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public void write ( byte[] b, int off, int len ) throws IOException
  {
    try {
      stream.write( b, off, len );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public long length () throws IOException
  {
    try {
      return stream.length();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public void setLength ( long newLength ) throws IOException
  {
    try {
      stream.setLength( newLength );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public void seek ( long pos ) throws IOException
  {
    try {
      stream.seek( pos );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public long getFilePointer () throws IOException
  {
    try {
      return stream.getFilePointer();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public void flush () throws IOException
  {
  }

  public void close () throws IOException
  {
    try {
      stream.close();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

}
