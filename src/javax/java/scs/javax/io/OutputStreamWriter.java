package scs.javax.io;

import java.nio.charset.Charset;
import scs.javax.io.wrappers.NewOutputStreamToOld;
import scs.javax.utils.StringUtils;

public class OutputStreamWriter extends WriterBase
{

  private java.io.OutputStreamWriter writer;

  public OutputStreamWriter ( OutputStream stream )
  {
    this( stream, Charset.forName( StringUtils.DEFAULT_ENCODING ) );
  }

  public OutputStreamWriter ( OutputStream stream, Charset charset )
  {
    java.io.OutputStream oldStream = new NewOutputStreamToOld( stream );
    writer = new java.io.OutputStreamWriter( oldStream, charset );
  }

  public void write ( char[] cbuf, int off, int len ) throws IOException
  {
    try {
      writer.write( cbuf, off, len );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public void flush () throws IOException
  {
    try {
      writer.flush();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public void close () throws IOException
  {
    try {
      writer.close();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

}
