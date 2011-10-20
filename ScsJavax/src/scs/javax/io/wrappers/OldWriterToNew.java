package scs.javax.io.wrappers;

import scs.javax.io.IOException;
import scs.javax.io.WriterBase;

public class OldWriterToNew extends WriterBase
{

  private java.io.Writer writer;

  public OldWriterToNew ( java.io.Writer writer )
  {
    this.writer = writer;
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
