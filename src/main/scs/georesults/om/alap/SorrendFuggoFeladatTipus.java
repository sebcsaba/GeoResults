package scs.georesults.om.alap;

import scs.georesults.om.verseny.Verseny;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class SorrendFuggoFeladatTipus extends StorableEntityBase
{

  private long sfftid;

  private long vid;

  private String nev;

  private int hianyHibapont;

  private int tobbletHibapont;

  private boolean reszletesBevitel;

  private int ellenorzesTipus;

  private EllenorzoPont.Lista ellenorzoPontok;

  protected SorrendFuggoFeladatTipus ()
  {
    ellenorzoPontok = new EllenorzoPont.Lista();
  }

  protected SorrendFuggoFeladatTipus ( long sfftid )
  {
    ellenorzoPontok = new EllenorzoPont.Lista();
    setSfftid( sfftid );
  }

  protected SorrendFuggoFeladatTipus ( long sfftid, long vid, String nev, int hianyHibapont, int tobbletHibapont, boolean reszletesBevitel, int ellenorzesTipus )
  {
    ellenorzoPontok = new EllenorzoPont.Lista();
    setSfftid( sfftid );
    setVid( vid );
    setNev( nev );
    setHianyHibapont( hianyHibapont );
    setTobbletHibapont( tobbletHibapont );
    setReszletesBevitel( reszletesBevitel );
    setEllenorzesTipus( ellenorzesTipus );
  }

  public EllenorzoPont.Lista getEllenorzoPontok ()
  {
    return ellenorzoPontok;
  }

  public long getSfftid ()
  {
    return sfftid;
  }

  public void setSfftid ( long sfftid )
  {
    this.sfftid = sfftid;
    ellenorzoPontok.setSfftid( sfftid );
  }

  public long getVid ()
  {
    return vid;
  }

  public void setVid ( long vid )
  {
    this.vid = vid;
  }

  public String getNev ()
  {
    return nev;
  }

  public void setNev ( String nev )
  {
    this.nev = nev;
  }

  public int getHianyHibapont ()
  {
    return hianyHibapont;
  }

  public void setHianyHibapont ( int hianyHibapont )
  {
    this.hianyHibapont = hianyHibapont;
  }

  public int getTobbletHibapont ()
  {
    return tobbletHibapont;
  }

  public void setTobbletHibapont ( int tobbletHibapont )
  {
    this.tobbletHibapont = tobbletHibapont;
  }

  public boolean isReszletesBevitel ()
  {
    return reszletesBevitel;
  }

  public void setReszletesBevitel ( boolean reszletesBevitel )
  {
    this.reszletesBevitel = reszletesBevitel;
  }

  public int getEllenorzesTipus ()
  {
    return ellenorzesTipus;
  }

  public void setEllenorzesTipus ( int ellenorzesTipus )
  {
    this.ellenorzesTipus = ellenorzesTipus;
  }

  public static List loadAllForVerseny ( RdbSession session, Verseny verseny ) throws RdbException
  {
    return loadAll( session, SorrendFuggoFeladatTipus.class, "vid", verseny );
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, SorrendFuggoFeladatTipus.class );
  }

  public static SorrendFuggoFeladatTipus newInstance ()
  {
    return new SorrendFuggoFeladatTipusImpl();
  }

  public static SorrendFuggoFeladatTipus newInstance ( long sfftid )
  {
    return new SorrendFuggoFeladatTipusImpl( sfftid );
  }

  public static SorrendFuggoFeladatTipus newInstance ( long sfftid, long vid, String nev, int hianyHibapont, int tobbletHibapont, boolean reszletesBevitel, int ellenorzesTipus )
  {
    return new SorrendFuggoFeladatTipusImpl( sfftid, vid, nev, hianyHibapont, tobbletHibapont, reszletesBevitel, ellenorzesTipus );
  }

}
