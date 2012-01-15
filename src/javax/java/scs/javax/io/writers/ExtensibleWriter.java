package scs.javax.io.writers;

import scs.javax.io.IOException;
import scs.javax.io.Writer;
import scs.javax.io.WriterBase;

public abstract class ExtensibleWriter extends WriterBase
{

  protected Writer out;

  public ExtensibleWriter ( Writer out )
  {
    this.out = out;
  }

  public void write ( char[] cbuf, int off, int len ) throws IOException
  {
    out.write( cbuf, off, len );
  }

  public void flush () throws IOException
  {
    out.flush();
  }

  public void close () throws IOException
  {
    out.close();
  }

}
