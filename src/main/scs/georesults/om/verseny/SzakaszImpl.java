package scs.georesults.om.verseny;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.SzakaszElem;

public class SzakaszImpl extends Szakasz implements SzakaszElem
{

  private List szlalomok;

  private List etapok;

  public SzakaszImpl ()
  {
    super();
  }

  public SzakaszImpl ( long szid )
  {
    super( szid );
  }

  public SzakaszImpl ( long szid, long vid, String nev, int megengedettKesesEtaponkent, int kesesertBuntetoPont, boolean szlalomRedukaltPontokkal, boolean ertekelendo, boolean eredmenyFrissitendo )
  {
    super( szid, vid, nev, megengedettKesesEtaponkent, kesesertBuntetoPont, szlalomRedukaltPontokkal, ertekelendo, eredmenyFrissitendo );
  }

  private GeoDbSession getDb () throws GeoException
  {
    return GeoDbSession.getCurrentInstance();
  }

  public void updateSzlalomok () throws GeoException, RdbException
  {
    szlalomok = Szlalom.loadAllForSzakasz( getDb(), this );
  }

  public void updateEtapok () throws GeoException, RdbException
  {
    etapok = Etap.loadAllForSzakasz( getDb(), this );
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

  public long getSzakaszElemId ()
  {
    return getSzid();
  }

}
