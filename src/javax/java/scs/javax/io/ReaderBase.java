package scs.javax.io;

public abstract class ReaderBase implements Reader
{

  protected Object lock;

  protected ReaderBase ()
  {
    this.lock = this;
  }

  protected ReaderBase ( Object lock )
  {
    if ( lock == null ) {
      throw new NullPointerException();
    }
    this.lock = lock;
  }

  public int read () throws IOException
  {
    char cb[] = new char[1];
    if ( read( cb, 0, 1 ) == 0 ) {
      return -1;
    } else {
      return cb[0];
    }
  }

  public int read ( char[] cbuf ) throws IOException
  {
    return read( cbuf, 0, cbuf.length );
  }

}
