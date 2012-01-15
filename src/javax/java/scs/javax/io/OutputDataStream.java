package scs.javax.io;

public interface OutputDataStream extends OutputStream
{

  public void writeBoolean ( boolean v ) throws IOException;

  public void writeChar ( int v ) throws IOException;

  public void writeByte ( int v ) throws IOException;

  public void writeShort ( int v ) throws IOException;

  public void writeInt ( int v ) throws IOException;

  public void writeLong ( long v ) throws IOException;

  public void writeFloat ( float v ) throws IOException;

  public void writeDouble ( double v ) throws IOException;

}
