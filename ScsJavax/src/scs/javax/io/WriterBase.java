package scs.javax.io;

public abstract class WriterBase implements DataWriter
{

  protected Object lock;

  private char[] writeBuffer;

  private final int writeBufferSize = 1024;

  protected WriterBase ()
  {
    this.lock = this;
  }

  protected WriterBase ( Object lock )
  {
    if ( lock == null ) {
      throw new NullPointerException();
    }
    this.lock = lock;
  }

  public void write ( int c ) throws IOException
  {
    synchronized ( lock ) {
      if ( writeBuffer == null ) {
        writeBuffer = new char[writeBufferSize];
      }
      writeBuffer[0] = ( char ) c;
      write( writeBuffer, 0, 1 );
    }
  }

  public void write ( char[] cbuf ) throws IOException
  {
    write( cbuf, 0, cbuf.length );
  }

  public void write ( String str ) throws IOException
  {
    write( str, 0, str.length() );
  }

  public void write ( String str, int off, int len ) throws IOException
  {
    synchronized ( lock ) {
      char cbuf[];
      if ( len <= writeBufferSize ) {
        if ( writeBuffer == null ) {
          writeBuffer = new char[writeBufferSize];
        }
        cbuf = writeBuffer;
      } else {
        cbuf = new char[len];
      }
      str.getChars( off, ( off + len ), cbuf, 0 );
      write( cbuf, 0, len );
    }
  }

  public void print ( boolean b ) throws IOException
  {
    write( String.valueOf( b ) );
  }

  public void print ( char c ) throws IOException
  {
    write( String.valueOf( c ) );
  }

  public void print ( int i ) throws IOException
  {
    write( String.valueOf( i ) );
  }

  public void print ( long l ) throws IOException
  {
    write( String.valueOf( l ) );
  }

  public void print ( float f ) throws IOException
  {
    write( String.valueOf( f ) );
  }

  public void print ( double d ) throws IOException
  {
    write( String.valueOf( d ) );
  }

  public void print ( char[] s ) throws IOException
  {
    write( s );
  }

  public void print ( String s ) throws IOException
  {
    write( String.valueOf( s ) );
  }

  public void print ( Object obj ) throws IOException
  {
    write( String.valueOf( obj ) );
  }

  public void println () throws IOException
  {
    write( System.getProperty( "line.separator" ) );
  }

  public void println ( boolean x ) throws IOException
  {
    print( x );
    println();
  }

  public void println ( char x ) throws IOException
  {
    print( x );
    println();
  }

  public void println ( int x ) throws IOException
  {
    print( x );
    println();
  }

  public void println ( long x ) throws IOException
  {
    print( x );
    println();
  }

  public void println ( float x ) throws IOException
  {
    print( x );
    println();
  }

  public void println ( double x ) throws IOException
  {
    print( x );
    println();
  }

  public void println ( char[] x ) throws IOException
  {
    print( x );
    println();
  }

  public void println ( String x ) throws IOException
  {
    print( x );
    println();
  }

  public void println ( Object x ) throws IOException
  {
    print( x );
    println();
  }

}
