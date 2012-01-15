package scs.javax.io;

public class FileInputStream extends InputStreamBase
{

  private java.io.FileInputStream stream;

  public FileInputStream ( Path filename ) throws FileNotFoundException
  {
    this( filename.toString() );
  }

  public FileInputStream ( String filename ) throws FileNotFoundException
  {
    try {
      this.stream = new java.io.FileInputStream( filename );
    }
    catch ( java.io.FileNotFoundException ex ) {
      throw new FileNotFoundException( filename );
    }
  }

  public int read () throws IOException
  {
    try {
      return stream.read();
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

  public long available () throws IOException
  {
    try {
      return stream.available();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
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

}
