package scs.javax.io.wrappers;

import scs.javax.io.IOException;
import scs.javax.io.ReaderBase;

public class OldReaderToNew extends ReaderBase
{

  private java.io.Reader reader;

  public OldReaderToNew ( java.io.Reader reader )
  {
    this.reader = reader;
  }

  public int read ( char[] cbuf, int off, int len ) throws IOException
  {
    try {
      return reader.read( cbuf, off, len );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public void skip ( long n ) throws IOException
  {
    try {
      reader.skip( n );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public boolean ready () throws IOException
  {
    try {
      return reader.ready();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public void close () throws IOException
  {
    try {
      reader.close();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

}
