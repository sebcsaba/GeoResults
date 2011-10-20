package scs.javax.io;

public interface RandomAccessStream extends InputDataStream, OutputDataStream
{

  public long getFilePointer () throws IOException;

  public void seek ( long pos ) throws IOException;

  public long length () throws IOException;

  public void setLength ( long newLength ) throws IOException;

}
