package scs.georesults.common.iotools.exml;

public class StringData extends Data
{

  public StringData ()
  {}

  public StringData ( String content )
  {
    super.setContent( content );
  }

  protected String getType ()
  {
    return "String";
  }

  public void setContent ( String content )
  {
    super.setContent( content );
  }

}
