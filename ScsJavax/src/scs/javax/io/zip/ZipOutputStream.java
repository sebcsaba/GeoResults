package scs.javax.io.zip;

import java.util.zip.ZipEntry;
import scs.javax.io.IOException;
import scs.javax.io.OutputStream;
import scs.javax.io.OutputStreamBase;
import scs.javax.io.wrappers.NewOutputStreamToOld;

public class ZipOutputStream extends OutputStreamBase
{

  private java.util.zip.ZipOutputStream stream;

  public ZipOutputStream ( OutputStream stream )
  {
    java.io.OutputStream oldOutStream = new NewOutputStreamToOld( stream );
    this.stream = new java.util.zip.ZipOutputStream( oldOutStream );
  }

  public void write ( int b ) throws IOException
  {
    byte[] ba = new byte[1];
    ba[0] = ( byte ) ( b & 0xff );
    write( ba, 0, 1 );
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
  {}

  public void close () throws IOException
  {
    try {
      stream.close();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public void setComment ( String comment )
  {
    stream.setComment( comment );
  }

  public void setMethod ( int method )
  {
    stream.setMethod( method );
  }

  public void setLevel ( int level )
  {
    stream.setLevel( level );
  }

  public void putNextEntry ( ZipEntry e ) throws IOException
  {
    try {
      stream.putNextEntry(e);
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public void closeEntry () throws IOException
  {
    try {
      stream.closeEntry();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public void finish () throws IOException
  {
    try {
      stream.finish();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

}
