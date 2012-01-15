package scs.javax.io.wrappers;

import java.io.IOException;
import scs.javax.io.Reader;

public class NewReaderToOld extends java.io.Reader
{

  private Reader reader;

  public NewReaderToOld ( Reader reader )
  {
    this.reader = reader;
  }

  public void close () throws IOException
  {
    reader.close();
  }

  public int read ( char[] cbuf, int off, int len ) throws IOException
  {
    return reader.read( cbuf, off, len );
  }

}
