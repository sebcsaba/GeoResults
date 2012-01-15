package scs.javax.io.wrappers;

import scs.javax.io.IOException;
import scs.javax.io.OutputStreamBase;

public class OldOutputStreamToNew extends OutputStreamBase
{

  private java.io.OutputStream stream;

  public OldOutputStreamToNew ( java.io.OutputStream stream )
  {
    this.stream = stream;
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

  public void flush () throws IOException
  {
    try {
      stream.flush();
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
