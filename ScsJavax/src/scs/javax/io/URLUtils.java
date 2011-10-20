package scs.javax.io;

import java.net.MalformedURLException;
import java.net.URL;
import scs.javax.io.wrappers.OldInputStreamToNew;
import scs.javax.io.wrappers.OldOutputStreamToNew;
import scs.javax.io.zip.ZipContentOutputStream;

public class URLUtils
{

  private static final String PROTO_FILE = "file";

  private static final String PROTO_ZIP = "jar";

  private URLUtils ()
  {}

  public static InputStream openInputStream ( URL url ) throws IOException
  {
    try {
      return new OldInputStreamToNew( url.openStream() );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public static OutputStream openOutputStream ( URL url ) throws IOException, MalformedURLException
  {
    String proto = url.getProtocol();
    if ( PROTO_FILE.equals( proto ) ) {
      Path f = new Path( url.getFile() );
      return new FileOutputStream( f );
    }
    if ( PROTO_ZIP.equals( proto ) ) {
      URL zipfile = getZipFileFromURL( url );
      String zipentry = getZipEntryFromURL( url );
      return new ZipContentOutputStream( new Path( zipfile.toString() ), zipentry );
    }
    try {
      return new OldOutputStreamToNew( url.openConnection().getOutputStream() );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  /*  public static RandomAccessStream openRandomAccessStream ( URL url ) throws IOException, MalformedURLException
    {
      String proto = url.getProtocol();
      if ( PROTO_FILE.equals( proto ) ) {
        Path f = new Path( url.getFile() );
        return new RandomAccessFile( f );
      }
      if ( PROTO_ZIP.equals( proto ) ) {
        URL zipfile = getZipFileFromURL(url);
        Path zipentry = getZipEntryFromURL(url);
        return null;//new ZipContentRandomAccessFile(new Path(zipfile.toString()),zipentry);
      }
      return null;
    }*/

  public static URL getZipFileFromURL ( URL url ) throws MalformedURLException
  {
    if ( !PROTO_ZIP.equals( url.getProtocol() ) )
      throw new MalformedURLException( "invalid protocol" );
    String file = url.getFile();
    int exc = file.indexOf( '!' );
    return new URL( file.substring( 0, exc ) );
  }

  public static String getZipEntryFromURL ( URL url ) throws MalformedURLException
  {
    if ( !PROTO_ZIP.equals( url.getProtocol() ) )
      throw new MalformedURLException( "invalid protocol" );
    String file = url.getFile();
    int exc = file.indexOf( '!' );
    return file.substring( exc + 1, file.length() );
  }

  public static URL makeZipURL ( Path zipfile, Path zipentry ) throws IOException
  {
    try {
      String entry = zipentry.toString().replaceAll( "\\\\", "/" );
      return new URL( "jar:" + zipfile.toURL() + "!/" + entry );
    }
    catch ( MalformedURLException ex ) {
      throw new IOException( ex );
    }
  }

}
