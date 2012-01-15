package scs.georesults.om.alap;

import scs.georesults.om.kozos.Nyelv;
import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class VersenySzotarBejegyzes extends StorableEntityBase
{

  private long vid;

  private long vszbid;

  private String lang;

  private String felirat;

  protected VersenySzotarBejegyzes ()
  {
  }

  protected VersenySzotarBejegyzes ( long vid, long vszbid, String lang )
  {
    this.vid = vid;
    this.vszbid = vszbid;
    this.lang = lang;
  }

  protected VersenySzotarBejegyzes ( long vid, long vszbid, String lang, String felirat )
  {
    this.vid = vid;
    this.vszbid = vszbid;
    this.lang = lang;
    this.felirat = felirat;
  }

  public long getVid ()
  {
    return vid;
  }

  public void setVid ( long vid )
  {
    this.vid = vid;
  }

  public long getVszbid ()
  {
    return vszbid;
  }

  public void setVszbid ( long vszbid )
  {
    this.vszbid = vszbid;
  }

  public String getLang ()
  {
    return lang;
  }

  public void setLang ( String lang )
  {
    this.lang = lang;
  }

  public String getFelirat ()
  {
    return felirat;
  }

  public void setFelirat ( String felirat )
  {
    this.felirat = felirat;
  }

  public static List loadAllForNyelv ( RdbSession session, Nyelv nyelv ) throws RdbException
  {
    return loadAll( session, VersenySzotarBejegyzes.class, "lang", nyelv );
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, VersenySzotarBejegyzes.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, VersenySzotarBejegyzes.class );
  }

  public static VersenySzotarBejegyzes newInstance ()
  {
    return new VersenySzotarBejegyzesImpl();
  }

  public static VersenySzotarBejegyzes newInstance ( long vid, long vszbid, String lang )
  {
    return new VersenySzotarBejegyzesImpl( vid, vszbid, lang );
  }

  public static VersenySzotarBejegyzes newInstance ( long vid, long vszbid, String lang, String felirat )
  {
    return new VersenySzotarBejegyzesImpl( vid, vszbid, lang, felirat );
  }

}
