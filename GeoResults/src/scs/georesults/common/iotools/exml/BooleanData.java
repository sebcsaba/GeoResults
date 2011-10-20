package scs.georesults.common.iotools.exml;

public class BooleanData extends Data
{

  public BooleanData ()
  {}

  public BooleanData ( boolean content )
  {
    setContent( content );
  }

  protected String getType ()
  {
    return "Boolean";
  }

  public void setContent ( boolean content )
  {
    super.setContent( content ? "1" : "0" );
  }

}
