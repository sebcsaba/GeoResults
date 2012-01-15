package scs.javax.io;

public interface DataWriter extends Writer
{

  public void print ( boolean b ) throws IOException;

  public void print ( char c ) throws IOException;

  public void print ( int i ) throws IOException;

  public void print ( long l ) throws IOException;

  public void print ( float f ) throws IOException;

  public void print ( double d ) throws IOException;

  public void print ( char[] s ) throws IOException;

  public void print ( String s ) throws IOException;

  public void print ( Object obj ) throws IOException;

  public void println () throws IOException;

  public void println ( boolean x ) throws IOException;

  public void println ( char x ) throws IOException;

  public void println ( int x ) throws IOException;

  public void println ( long x ) throws IOException;

  public void println ( float x ) throws IOException;

  public void println ( double x ) throws IOException;

  public void println ( char[] x ) throws IOException;

  public void println ( String x ) throws IOException;

  public void println ( Object x ) throws IOException;

}
