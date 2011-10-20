package scs.georesults.om.verseny;

import scs.javax.collections.List;
import scs.javax.lang.Date;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.StorableEntityBase;

public abstract class Verseny extends StorableEntityBase
{

  private long vid;

  private String nev;

  private Date kezdeteDatum;

  private Date vegeDatum;

  private String alapNyelv;

  private String menetlevelformula;

  private boolean eredmenyFrissitendoMindenEtap;

  private boolean eredmenyFrissitendoMindenSzlalom;

  private boolean eredmenyFrissitendoVerseny;

  private boolean eredmenyFrissitendoCsapat;

  private Date lezarva;

  protected Verseny ()
  {
  }

  protected Verseny ( long vid )
  {
    this.vid = vid;
  }

  protected Verseny ( long vid, String nev, Date kezdeteDatum, Date vegeDatum, String alapNyelv, String menetlevelformula, boolean eredmenyFrissitendoMindenEtap, boolean eredmenyFrissitendoMindenSzlalom, boolean eredmenyFrissitendoVerseny, boolean eredmenyFrissitendoCsapat, Date lezarva )
  {
    this.vid = vid;
    this.nev = nev;
    this.kezdeteDatum = kezdeteDatum;
    this.vegeDatum = vegeDatum;
    this.alapNyelv = alapNyelv;
    this.menetlevelformula = menetlevelformula;
    this.eredmenyFrissitendoMindenEtap = eredmenyFrissitendoMindenEtap;
    this.eredmenyFrissitendoMindenSzlalom = eredmenyFrissitendoMindenSzlalom;
    this.eredmenyFrissitendoVerseny = eredmenyFrissitendoVerseny;
    this.eredmenyFrissitendoCsapat = eredmenyFrissitendoCsapat;
    this.lezarva = lezarva;
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

  public Date getKezdeteDatum ()
  {
    return kezdeteDatum;
  }

  public void setKezdeteDatum ( Date kezdeteDatum )
  {
    this.kezdeteDatum = kezdeteDatum;
  }

  public Date getVegeDatum ()
  {
    return vegeDatum;
  }

  public void setVegeDatum ( Date vegeDatum )
  {
    this.vegeDatum = vegeDatum;
  }

  public String getAlapNyelv ()
  {
    return alapNyelv;
  }

  public void setAlapNyelv ( String alapNyelv )
  {
    this.alapNyelv = alapNyelv;
  }

  public String getMenetlevelformula ()
  {
    return menetlevelformula;
  }

  public void setMenetlevelformula ( String menetlevelformula )
  {
    this.menetlevelformula = menetlevelformula;
  }

  public boolean isEredmenyFrissitendoMindenEtap ()
  {
    return eredmenyFrissitendoMindenEtap;
  }

  public void setEredmenyFrissitendoMindenEtap ( boolean eredmenyFrissitendoMindenEtap )
  {
    this.eredmenyFrissitendoMindenEtap = eredmenyFrissitendoMindenEtap;
  }

  public boolean isEredmenyFrissitendoMindenSzlalom ()
  {
    return eredmenyFrissitendoMindenSzlalom;
  }

  public void setEredmenyFrissitendoMindenSzlalom ( boolean eredmenyFrissitendoMindenSzlalom )
  {
    this.eredmenyFrissitendoMindenSzlalom = eredmenyFrissitendoMindenSzlalom;
  }

  public boolean isEredmenyFrissitendoVerseny ()
  {
    return eredmenyFrissitendoVerseny;
  }

  public void setEredmenyFrissitendoVerseny ( boolean eredmenyFrissitendoVerseny )
  {
    this.eredmenyFrissitendoVerseny = eredmenyFrissitendoVerseny;
  }

  public boolean isEredmenyFrissitendoCsapat ()
  {
    return eredmenyFrissitendoCsapat;
  }

  public void setEredmenyFrissitendoCsapat ( boolean eredmenyFrissitendoCsapat )
  {
    this.eredmenyFrissitendoCsapat = eredmenyFrissitendoCsapat;
  }

  public Date getLezarva ()
  {
    return lezarva;
  }

  public void setLezarva ( Date lezarva )
  {
    this.lezarva = lezarva;
  }

  public static List loadAll ( RdbSession session ) throws RdbException
  {
    return loadAll( session, Verseny.class );
  }

  public static Verseny newInstance ()
  {
    return new VersenyImpl();
  }

  public static Verseny newInstance ( long vid )
  {
    return new VersenyImpl( vid );
  }

  public static Verseny newInstance ( long vid, String nev, Date kezdeteDatum, Date vegeDatum, String alapNyelv, String menetlevelformula, boolean eredmenyFrissitendoMindenEtap, boolean eredmenyFrissitendoMindenSzlalom, boolean eredmenyFrissitendoVerseny, boolean eredmenyFrissitendoCsapat, Date lezarva )
  {
    return new VersenyImpl( vid, nev, kezdeteDatum, vegeDatum, alapNyelv, menetlevelformula, eredmenyFrissitendoMindenEtap, eredmenyFrissitendoMindenSzlalom, eredmenyFrissitendoVerseny, eredmenyFrissitendoCsapat, lezarva );
  }

}
