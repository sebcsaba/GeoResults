package scs.georesults.om.verseny;

import scs.javax.collections.List;
import scs.javax.lang.Date;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.om.alap.*;
import scs.georesults.om.nevezes.CsapatNevezes;
import scs.georesults.om.nevezes.Nevezes;

public class VersenyImpl extends Verseny
{

  private List darabFuggoFeladatTipusok;

  private List sorrendFuggoFeladatTipusok;

  private List kesesiZonak;

  private List buntetesTipusok;

  private List szlalomFeladatok;

  private List szlalomKategoriak;

  private List autoTipusok;

  private List szlalomok;

  private List etapok;

  private List szakaszok;

  private List nevezesek;

  private List csapatNevezesek;

  public VersenyImpl ()
  {
    super();
  }

  public VersenyImpl ( long vid )
  {
    super( vid );
  }

  public VersenyImpl ( long vid, String nev, Date kezdeteDatum, Date vegeDatum, String alapNyelv, String menetlevelformula, boolean eredmenyFrissitendoMindenEtap, boolean eredmenyFrissitendoMindenSzlalom, boolean eredmenyFrissitendoVerseny, boolean eredmenyFrissitendoCsapat, Date lezarva )
  {
    super( vid, nev, kezdeteDatum, vegeDatum, alapNyelv, menetlevelformula, eredmenyFrissitendoMindenEtap, eredmenyFrissitendoMindenSzlalom, eredmenyFrissitendoVerseny, eredmenyFrissitendoCsapat, lezarva );
  }

  public String getDatumok ()
  {
    Date kezd = getKezdeteDatum();
    Date vege = getVegeDatum();
    String kezdStr = kezd.getSimpleFace();
    String vegeStr = vege.getSimpleFace();
    if ( kezd.getYear() != vege.getYear() )
      return kezdStr + "-" + vegeStr;
    else if ( kezd.getMonth() != vege.getMonth() )
      return kezdStr + "-" + vegeStr.substring( 5 );
    else if ( kezd.getDay() != vege.getDay() )
      return kezdStr + "-" + vegeStr.substring( 8 );
    else return kezdStr;
  }

  private GeoDbSession getDb () throws GeoException
  {
    return GeoDbSession.getCurrentInstance();
  }

  public void updateDarabFuggoFeladatTipusok () throws GeoException, RdbException
  {
    darabFuggoFeladatTipusok = DarabFuggoFeladatTipus.loadAllForVerseny( getDb(), this );
  }

  public void updateSorrendFuggoFeladatTipusok () throws GeoException, RdbException
  {
    sorrendFuggoFeladatTipusok = SorrendFuggoFeladatTipus.loadAllForVerseny( getDb(), this );
  }

  public void updateKesesiZonak () throws GeoException, RdbException
  {
    kesesiZonak = KesesiZona.loadAllForVerseny( getDb(), this );
  }

  public void updateBuntetesTipusok () throws GeoException, RdbException
  {
    buntetesTipusok = BuntetesTipus.loadAllForVerseny( getDb(), this );
  }

  public void updateSzlalomFeladatok () throws GeoException, RdbException
  {
    szlalomFeladatok = SzlalomFeladat.loadAllForVerseny( getDb(), this );
  }

  public List getSorrendFuggoFeladatTipusok () throws GeoException, RdbException
  {
    if ( sorrendFuggoFeladatTipusok == null ) updateSorrendFuggoFeladatTipusok();
    return sorrendFuggoFeladatTipusok;
  }

  public List getKesesiZonak () throws GeoException, RdbException
  {
    if ( kesesiZonak == null ) updateKesesiZonak();
    return kesesiZonak;
  }

  public List getDarabFuggoFeladatTipusok () throws GeoException, RdbException
  {
    if ( darabFuggoFeladatTipusok == null ) updateDarabFuggoFeladatTipusok();
    return darabFuggoFeladatTipusok;
  }

  public List getBuntetesTipusok () throws GeoException, RdbException
  {
    if ( buntetesTipusok == null ) updateBuntetesTipusok();
    return buntetesTipusok;
  }

  public List getSzlalomFeladatok () throws GeoException, RdbException
  {
    if ( szlalomFeladatok == null ) updateSzlalomFeladatok();
    return szlalomFeladatok;
  }

  public void updateSzlalomKategoriak () throws GeoException, RdbException
  {
    szlalomKategoriak = SzlalomKategoria.loadAllForVerseny( getDb(), this );
  }

  public List getSzlalomKategoriak () throws GeoException, RdbException
  {
    if ( szlalomKategoriak == null ) updateSzlalomKategoriak();
    return szlalomKategoriak;
  }

  public void updateAutoTipusok () throws GeoException, RdbException
  {
    autoTipusok = AutoTipus.loadAllForVerseny( getDb(), this );
  }

  public List getAutoTipusok () throws GeoException, RdbException
  {
    if ( autoTipusok == null ) updateAutoTipusok();
    return autoTipusok;
  }

  public void updateSzlalomok () throws GeoException, RdbException
  {
    szlalomok = Szlalom.loadAllForVerseny( getDb(), this );
  }

  public void updateEtapok () throws GeoException, RdbException
  {
    etapok = Etap.loadAllForVerseny( getDb(), this );
  }

  public void updateSzakaszok () throws GeoException, RdbException
  {
    szakaszok = Szakasz.loadAllForVerseny( getDb(), this );
  }

  public void updateNevezesek () throws GeoException, RdbException
  {
    nevezesek = Nevezes.loadAllForVerseny( getDb(), this );
  }

  public void updateCsapatNevezesek () throws GeoException, RdbException
  {
    csapatNevezesek = CsapatNevezes.loadAllForVerseny( getDb(), this );
  }

  public List getSzlalomok () throws GeoException, RdbException
  {
    if ( szlalomok == null ) updateSzlalomok();
    return szlalomok;
  }

  public List getEtapok () throws GeoException, RdbException
  {
    if ( etapok == null ) updateEtapok();
    return etapok;
  }

  public List getSzakaszok () throws GeoException, RdbException
  {
    if ( szakaszok == null ) updateSzakaszok();
    return szakaszok;
  }

  public List getNevezesek () throws GeoException, RdbException
  {
    if ( nevezesek == null ) updateNevezesek();
    return nevezesek;
  }

  public List getCsapatNevezesek () throws GeoException, RdbException
  {
    if ( csapatNevezesek == null ) updateCsapatNevezesek();
    return csapatNevezesek;
  }

  public boolean isLeVanZarva ()
  {
    return getLezarva() != null;
  }

  public boolean isBarmiAmiNemFrissitendo ()
  {
    return ( !isEredmenyFrissitendoMindenEtap() || !isEredmenyFrissitendoMindenSzlalom() ||
             !isEredmenyFrissitendoVerseny() || !isEredmenyFrissitendoCsapat() );
  }

  public void setMindenFrissitendo ()
  {
    setEredmenyFrissitendoMindenEtap( true );
    setEredmenyFrissitendoMindenSzlalom( true );
    setEredmenyFrissitendoVerseny( true );
    setEredmenyFrissitendoCsapat( true );
  }

  public void refresh ( RdbSession session ) throws RdbException
  {
    read( session );
    darabFuggoFeladatTipusok = null;
    sorrendFuggoFeladatTipusok = null;
    kesesiZonak = null;
    buntetesTipusok = null;
    szlalomFeladatok = null;
    szlalomKategoriak = null;
    autoTipusok = null;
    szlalomok = null;
    etapok = null;
    szakaszok = null;
    nevezesek = null;
    csapatNevezesek = null;
  }

}
