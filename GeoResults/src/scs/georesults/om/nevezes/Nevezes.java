package scs.georesults.om.nevezes;

import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class Nevezes extends StorableEntityBase
{

  private long vid;

  private int rajtszam;

  private String sofor;

  private String navigator;

  private String utas1;

  private String utas2;

  private String utas3;

  private String orszag;

  private long autoTipus;

  protected Nevezes ()
  {
  }

  protected Nevezes ( long vid, int rajtszam )
  {
    this.vid = vid;
    this.rajtszam = rajtszam;
  }

  protected Nevezes ( long vid, int rajtszam, String sofor, String navigator, String utas1, String utas2, String utas3, String orszag, long autoTipus )
  {
    this.vid = vid;
    this.rajtszam = rajtszam;
    this.sofor = sofor;
    this.navigator = navigator;
    this.utas1 = utas1;
    this.utas2 = utas2;
    this.utas3 = utas3;
    this.orszag = orszag;
    this.autoTipus = autoTipus;
  }

  public long getVid ()
  {
    return vid;
  }

  public void setVid ( long vid )
  {
    this.vid = vid;
  }

  public int getRajtszam ()
  {
    return rajtszam;
  }

  public void setRajtszam ( int rajtszam )
  {
    this.rajtszam = rajtszam;
  }

  public String getSofor ()
  {
    return sofor;
  }

  public void setSofor ( String sofor )
  {
    this.sofor = sofor;
  }

  public String getNavigator ()
  {
    return navigator;
  }

  public void setNavigator ( String navigator )
  {
    this.navigator = navigator;
  }

  public String getUtas1 ()
  {
    return utas1;
  }

  public void setUtas1 ( String utas1 )
  {
    this.utas1 = utas1;
  }

  public String getUtas2 ()
  {
    return utas2;
  }

  public void setUtas2 ( String utas2 )
  {
    this.utas2 = utas2;
  }

  public String getUtas3 ()
  {
    return utas3;
  }

  public void setUtas3 ( String utas3 )
  {
    this.utas3 = utas3;
  }

  public String getOrszag ()
  {
    return orszag;
  }

  public void setOrszag ( String orszag )
  {
    this.orszag = orszag;
  }

  public long getAutoTipus ()
  {
    return autoTipus;
  }

  public void setAutoTipus ( long autoTipus )
  {
    this.autoTipus = autoTipus;
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, Nevezes.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, Nevezes.class );
  }

  public static Nevezes newInstance ()
  {
    return new NevezesImpl();
  }

  public static Nevezes newInstance ( long vid, int rajtszam )
  {
    return new NevezesImpl( vid, rajtszam );
  }

  public static Nevezes newInstance ( long vid, int rajtszam, String sofor, String navigator, String utas1, String utas2, String utas3, String orszag, long autoTipus )
  {
    return new NevezesImpl( vid, rajtszam, sofor, navigator, utas1, utas2, utas3, orszag, autoTipus );
  }

}
