package scs.javax.io;

public interface InputStream
{

  public int read () throws IOException;

  public int read ( byte[] b ) throws IOException;

  public int read ( byte[] b, int off, int len ) throws IOException;

  public void readFully ( byte[] b ) throws IOException;

  public void readFully ( byte[] b, int off, int len ) throws IOException;

  public long available () throws IOException;

  public void close () throws IOException;

  public void seekForward ( long count ) throws IOException;

}
