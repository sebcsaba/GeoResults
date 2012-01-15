package scs.georesults.om.alap;

import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class KesesiZona extends StorableEntityBase
{

  private long vid;

  private int idoLimit;

  private int pont;

  protected KesesiZona ()
  {
  }

  protected KesesiZona ( long vid, int idoLimit )
  {
    this.vid = vid;
    this.idoLimit = idoLimit;
  }

  protected KesesiZona ( long vid, int idoLimit, int pont )
  {
    this.vid = vid;
    this.idoLimit = idoLimit;
    this.pont = pont;
  }

  public long getVid ()
  {
    return vid;
  }

  public void setVid ( long vid )
  {
    this.vid = vid;
  }

  public int getIdoLimit ()
  {
    return idoLimit;
  }

  public void setIdoLimit ( int idoLimit )
  {
    this.idoLimit = idoLimit;
  }

  public int getPont ()
  {
    return pont;
  }

  public void setPont ( int pont )
  {
    this.pont = pont;
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, KesesiZona.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, KesesiZona.class );
  }

  public static KesesiZona newInstance ()
  {
    return new KesesiZonaImpl();
  }

  public static KesesiZona newInstance ( long vid, int idoLimit )
  {
    return new KesesiZonaImpl( vid, idoLimit );
  }

  public static KesesiZona newInstance ( long vid, int idoLimit, int pont )
  {
    return new KesesiZonaImpl( vid, idoLimit, pont );
  }

}
