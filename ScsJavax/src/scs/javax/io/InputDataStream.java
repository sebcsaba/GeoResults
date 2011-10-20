package scs.javax.io;

public interface InputDataStream extends InputStream
{

  public boolean readBoolean () throws IOException;

  public char readChar () throws IOException;

  public byte readByte () throws IOException;

  public short readShort () throws IOException;

  public int readInt () throws IOException;

  public long readLong () throws IOException;

  public float readFloat () throws IOException;

  public double readDouble () throws IOException;

}
