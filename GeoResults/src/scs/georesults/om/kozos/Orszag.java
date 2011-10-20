package scs.georesults.om.kozos;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class Orszag extends StorableEntityBase
{

  private String country;

  protected Orszag ()
  {
  }

  protected Orszag ( String country )
  {
    this.country = country;
  }

  public String getCountry ()
  {
    return country;
  }

  public void setCountry ( String country )
  {
    this.country = country;
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, Orszag.class );
  }

  public static Orszag newInstance ()
  {
    return new OrszagImpl();
  }

  public static Orszag newInstance ( String country )
  {
    return new OrszagImpl( country );
  }

}
