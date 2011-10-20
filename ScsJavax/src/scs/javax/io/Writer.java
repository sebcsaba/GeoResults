package scs.javax.io;

public interface Writer
{

  public void write ( int c ) throws IOException;

  public void write ( char[] cbuf ) throws IOException;

  public void write ( char[] cbuf, int off, int len ) throws IOException;

  public void write ( String str ) throws IOException;

  public void write ( String str, int off, int len ) throws IOException;

  public void flush () throws IOException;

  public void close () throws IOException;

}
