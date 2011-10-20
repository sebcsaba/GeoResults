package scs.javax.io.zip;

import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import scs.javax.collections.List;
import scs.javax.io.*;
import scs.javax.io.wrappers.OldInputStreamToNew;

public class ZipUtils
{

  private ZipUtils ()
  {}

  public static void addFileToZip ( Path zipPath, ZipEntry entry, Path filePath ) throws
          IOException, ZipException
  {
    InputStream inStream = new FileInputStream( filePath );
    addStreamToZip( zipPath, entry, inStream );
    inStream.close();
  }

  public static void addStreamToZip ( Path zipPath, ZipEntry entry, InputStream inStream ) throws
          IOException, ZipException, IOException
  {
    try {
      Path tempZipPath = Path.createTempFile( "ziputils", null, zipPath.getParent() );
      ZipOutputStream tempZipOutStream = new ZipOutputStream( new FileOutputStream( tempZipPath ) );
      if ( zipPath.exists() ) {
        ZipFile sourceZipFile = new ZipFile( zipPath.toFile() );
        Collection exceptionEntryStrings = new List( 1 );
        exceptionEntryStrings.add( entry.getName() );
        copyZipEntries( tempZipOutStream, sourceZipFile, exceptionEntryStrings );
        sourceZipFile.close();
        zipPath.delete();
      }
      addStreamToZip( tempZipOutStream, entry, inStream );
      tempZipOutStream.close();
      System.out.println( tempZipPath.getName() );
      if ( !tempZipPath.renameTo( zipPath ) ) {
        throw new IOException( "error while renaming '" + tempZipPath + "' to '" + zipPath + "'" );
      }
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public static void copyZipEntries ( ZipOutputStream outStream, ZipFile sourceZip,
                                      Collection exceptionEntryStrings ) throws IOException
  {
    try {
      for ( Enumeration en = sourceZip.entries(); en.hasMoreElements(); ) {
        ZipEntry ze = ( ZipEntry ) en.nextElement();
        if ( !ze.isDirectory() && !exceptionEntryStrings.contains( ze.getName() ) ) {
          InputStream inStream = new OldInputStreamToNew( sourceZip.getInputStream( ze ) );
          addStreamToZip( outStream, new ZipEntry( ze.getName() ), inStream );
        }
      }
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public static void addFileToZip ( ZipOutputStream outStream, ZipEntry entry, Path filePath ) throws
          IOException
  {
    InputStream inStream = new FileInputStream( filePath );
    addStreamToZip( outStream, entry, inStream );
    inStream.close();
  }

  public static void addStreamToZip ( ZipOutputStream outStream, ZipEntry entry,
                                      InputStream inStream ) throws IOException
  {
    outStream.putNextEntry( entry );
    DataStreamUtils.copyStream( inStream, outStream );
    outStream.closeEntry();
  }

}
