package scs.georesults.common.iotools.exml;

public class NumberData extends Data
{

  public NumberData ()
  {}

  public NumberData ( long content )
  {
    setContent( content );
  }

  public NumberData ( double content )
  {
    super.setContent( Double.toString( content ) );
  }

  protected String getType ()
  {
    return "Number";
  }

  public void setContent ( long content )
  {
    super.setContent( Long.toString( content ) );
  }

}
