package scs.javax.io.wrappers;

import scs.javax.io.IOException;
import scs.javax.io.InputStreamBase;

public class OldInputStreamToNew extends InputStreamBase
{

  private java.io.InputStream stream;

  public OldInputStreamToNew ( java.io.InputStream stream )
  {
    this.stream = stream;
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
      return stream.read(b,off,len);
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
