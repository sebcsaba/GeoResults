package scs.javax.io;

import java.nio.charset.Charset;
import scs.javax.io.wrappers.NewInputStreamToOld;
import scs.javax.utils.StringUtils;

public class InputStreamReader extends ReaderBase
{

  private java.io.InputStreamReader reader;

  public InputStreamReader ( InputStream stream )
  {
    this( stream, Charset.forName( StringUtils.DEFAULT_ENCODING ) );
  }

  public InputStreamReader ( InputStream stream, Charset charset )
  {
    java.io.InputStream oldStream = new NewInputStreamToOld( stream );
    reader = new java.io.InputStreamReader( oldStream, charset );
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
