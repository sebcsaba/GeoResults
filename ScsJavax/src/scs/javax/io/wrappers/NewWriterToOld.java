package scs.javax.io.wrappers;

import java.io.IOException;
import scs.javax.io.Writer;

public class NewWriterToOld extends java.io.Writer
{

  private Writer writer;

  public NewWriterToOld ( Writer writer )
  {
    this.writer = writer;
  }

  public void close () throws IOException
  {
    writer.close();
  }

  public void flush () throws IOException
  {
    writer.flush();
  }

  public void write ( char[] cbuf, int off, int len ) throws IOException
  {
    writer.write( cbuf, off, len );
  }

}
