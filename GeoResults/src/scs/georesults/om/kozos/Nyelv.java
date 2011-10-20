package scs.georesults.om.kozos;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class Nyelv extends StorableEntityBase
{

  private String lang;

  protected Nyelv ()
  {
  }

  protected Nyelv ( String lang )
  {
    this.lang = lang;
  }

  public String getLang ()
  {
    return lang;
  }

  public void setLang ( String lang )
  {
    this.lang = lang;
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, Nyelv.class );
  }

  public static Nyelv newInstance ()
  {
    return new NyelvImpl();
  }

  public static Nyelv newInstance ( String lang )
  {
    return new NyelvImpl( lang );
  }

}
