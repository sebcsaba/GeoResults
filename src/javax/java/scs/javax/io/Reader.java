package scs.javax.io;

public interface Reader
{

  public int read () throws IOException;

  public int read ( char[] cbuf ) throws IOException;

  public int read ( char[] cbuf, int off, int len ) throws IOException;

  public void skip ( long n ) throws IOException;

  public boolean ready () throws IOException;

  public void close () throws IOException;

}
