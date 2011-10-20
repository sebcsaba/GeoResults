package scs.georesults.common.iotools.exml;

import scs.javax.lang.Time;

public class TimeData extends Data
{

  public TimeData ()
  {}

  public TimeData ( Time content )
  {
    setContent( content );
  }

  protected String getType ()
  {
    return "DateTime";
  }

  public void setContent ( Time content )
  {
    super.setContent( "1899-12-31T" + content.toString() + ".000" );
  }

}
