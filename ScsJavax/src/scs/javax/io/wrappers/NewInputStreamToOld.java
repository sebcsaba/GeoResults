package scs.javax.io.wrappers;

import scs.javax.io.IOException;
import scs.javax.io.InputStream;

public class NewInputStreamToOld extends java.io.InputStream
{

  private InputStream stream;

  public NewInputStreamToOld ( InputStream stream )
  {
    this.stream = stream;
  }

  public int read () throws java.io.IOException
  {
    return stream.read();
  }

  public int available() throws java.io.IOException
  {
    return (int) stream.available();
  }

  public long skip(long n) throws java.io.IOException
  {
    stream.seekForward(n);
    return n;
  }

  public void close() throws java.io.IOException
  {
    stream.close();
  }

}
