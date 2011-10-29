package scs.javax.io;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Path
{

  public static final String separator = File.separator;

  private File file;

  public Path ( File file )
  {
    this.file = file;
  }

  public Path ( Path parent, String child )
  {
    this.file = new File( parent.file, child );
  }

  public Path ( String pathname )
  {
    this.file = new File( pathname );
  }

  public Path ( String parent, String child )
  {
    this.file = new File( parent, child );
  }

  public Path ( URI uri )
  {
    this.file = new File( uri );
  }


  public boolean canRead ()
  {
    return file.canRead();
  }

  public boolean canWrite ()
  {
    return file.canWrite();
  }

  public int compareTo ( Path pathname )
  {
    return file.compareTo( pathname.file );
  }

  public int compareTo ( Object o )
  {
    if ( o instanceof Path ) {
      return file.compareTo( ( ( Path ) o ).file );
    } else {
      return file.compareTo( ( File ) o );
    }
  }

  public boolean createNewFile () throws IOException
  {
    try {
      return file.createNewFile();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public boolean delete ()
  {
    return file.delete();
  }

  public void deleteOnExit ()
  {
    file.deleteOnExit();
  }

  public boolean equals ( Object obj )
  {
    if ( obj instanceof Path ) {
      return file.equals( ( ( Path ) obj ).file );
    } else {
      return file.equals( obj );
    }
  }

  public boolean exists ()
  {
    return file.exists();
  }

  public Path getAbsolutePath ()
  {
    return new Path( file.getAbsolutePath() );
  }

  public Path getCanonicalPath () throws IOException
  {
    try {
      return new Path( file.getCanonicalPath() );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public String getName ()
  {
    return file.getName();
  }

  public Path getParent ()
  {
    File f = file.getParentFile();
    if ( f == null ) {
      return null;
    } else {
      return new Path( f );
    }
  }

  public String getPath ()
  {
    return file.getPath();
  }

  public int hashCode ()
  {
    return file.hashCode();
  }

  public boolean isAbsolute ()
  {
    return file.isAbsolute();
  }

  public boolean isDirectory ()
  {
    return file.isDirectory();
  }

  public boolean isFile ()
  {
    return file.isFile();
  }

  public boolean isHidden ()
  {
    return file.isHidden();
  }

  public long lastModified ()
  {
    return file.lastModified();
  }

  public long length ()
  {
    return file.length();
  }

  public String[] list ()
  {
    return file.list();
  }

  public String[] list ( FilenameFilter filter )
  {
    return file.list( filter );
  }

  public Path[] listFiles ()
  {
    File[] fs = file.listFiles();
    Path[] ps = new Path[fs.length];
    for ( int i = 0; i < fs.length; ++i ) ps[i] = new Path( fs[i] );
    return ps;
  }

  public Path[] listFiles ( FileFilter filter )
  {
    File[] fs = file.listFiles( filter );
    Path[] ps = new Path[fs.length];
    for ( int i = 0; i < fs.length; ++i ) ps[i] = new Path( fs[i] );
    return ps;
  }

  public Path[] listFiles ( FilenameFilter filter )
  {
    File[] fs = file.listFiles( filter );
    Path[] ps = new Path[fs.length];
    for ( int i = 0; i < fs.length; ++i ) ps[i] = new Path( fs[i] );
    return ps;
  }

  public boolean mkdir ()
  {
    return file.mkdir();
  }

  public boolean mkdirs ()
  {
    return file.mkdirs();
  }

  public boolean renameTo ( Path dest )
  {
    return file.renameTo( dest.file );
  }

  public boolean setLastModified ( long time )
  {
    return file.setLastModified( time );
  }

  public boolean setReadOnly ()
  {
    return file.setReadOnly();
  }

  public String toString ()
  {
    return file.toString();
  }

  public URI toURI ()
  {
    return file.toURI();
  }

  public URL toURL () throws MalformedURLException
  {
    return file.toURL();
  }

  public File toFile ()
  {
    return file;
  }

  public static Path[] listRoots ()
  {
    File[] fs = File.listRoots();
    Path[] ps = new Path[fs.length];
    for ( int i = 0; i < fs.length; ++i ) ps[i] = new Path( fs[i] );
    return ps;
  }

  public static Path createTempFile ( String prefix, String suffix ) throws IOException
  {
    try {
      return new Path( File.createTempFile( prefix, suffix ) );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  public static Path createTempFile ( String prefix, String suffix, Path directory ) throws
          IOException
  {
    try {
      return new Path( File.createTempFile( prefix, suffix, directory.toFile() ) );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

}
