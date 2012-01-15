package scs.georesults.common.iotools.exml;

import scs.javax.lang.Date;

public class DateData extends Data
{

  public DateData ()
  {}

  public DateData ( Date content )
  {
    setContent( content );
  }

  protected String getType ()
  {
    return "DateTime";
  }

  public void setContent ( Date content )
  {
    super.setContent( content.getDatabaseFace() + "T00:00:00.000" );
  }

}
