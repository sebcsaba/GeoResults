package scs.javax.io;

public class FileOutputStream extends OutputStreamBase
{

  private java.io.FileOutputStream stream;

  public FileOutputStream ( Path filename ) throws FileNotFoundException
  {
    this( filename.toString() );
  }

  public FileOutputStream ( String filename ) throws FileNotFoundException
  {
    try {
      this.stream = new java.io.FileOutputStream( filename );
    }
    catch ( java.io.FileNotFoundException ex ) {
      throw new FileNotFoundException( filename );
    }
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

  public void flush () throws IOException
  {
    try {
      stream.flush();
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

}
