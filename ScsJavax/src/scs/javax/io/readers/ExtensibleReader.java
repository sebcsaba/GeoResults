package scs.javax.io.readers;

import scs.javax.io.IOException;
import scs.javax.io.Reader;
import scs.javax.io.ReaderBase;

public class ExtensibleReader extends ReaderBase
{

  protected Reader in;

  public ExtensibleReader ( Reader in )
  {
    this.in = in;
  }

  public int read ( char[] cbuf, int off, int len ) throws IOException
  {
    return in.read( cbuf, off, len );
  }

  public void skip ( long n ) throws IOException
  {
    in.skip( n );
  }

  public boolean ready () throws IOException
  {
    return in.ready();
  }

  public void close () throws IOException
  {
    in.close();
  }

}
