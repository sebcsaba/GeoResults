package scs.javax.io.zip;

import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import scs.javax.io.*;

public class ZipContentOutputStream extends OutputStreamBase
{

  private Path zipfile;

  private ZipEntry zipEntry;

  private OutputStream stream;

  private Path tempfile;

  public ZipContentOutputStream ( Path zipfile, String zipEntryName ) throws IOException
  {
    this.zipfile = zipfile;
    this.zipEntry = new ZipEntry( zipEntryName );
    this.tempfile = Path.createTempFile( "zipcontent", null );
    this.stream = new FileOutputStream( tempfile );
  }

  public void write ( int b ) throws IOException
  {
    stream.write( b );
  }

  public void write ( byte[] b, int off, int len ) throws IOException
  {
    stream.write( b, off, len );
  }

  public void flush () throws IOException
  {
    stream.flush();
  }

  public void close () throws IOException
  {
    try {
      stream.close();
      ZipUtils.addFileToZip( zipfile, zipEntry, tempfile );
      tempfile.delete();
    }
    catch ( ZipException ex ) {
      throw new IOException( ex );
    }
  }

}
