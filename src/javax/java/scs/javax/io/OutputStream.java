package scs.javax.io;

public interface OutputStream
{

  public void write ( int b ) throws IOException;

  public void write ( byte[] b ) throws IOException;

  public void write ( byte[] b, int off, int len ) throws IOException;

  public void flush () throws IOException;

  public void close () throws IOException;

  public void seekForward ( long count ) throws IOException;

}
