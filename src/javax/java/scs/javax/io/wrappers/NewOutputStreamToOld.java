package scs.javax.io.wrappers;

import scs.javax.io.OutputStream;

public class NewOutputStreamToOld extends java.io.OutputStream
{

  private OutputStream stream;

  public NewOutputStreamToOld ( OutputStream stream )
  {
    this.stream = stream;
  }

  public void write ( int b ) throws java.io.IOException
  {
    stream.write( b );
  }

  public void close () throws java.io.IOException
  {
    stream.close();
  }

  public void flush () throws java.io.IOException
  {
    stream.flush();
  }

}
