package scs.georesults.om.eredmeny;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.verseny.Szakasz;

public class SzlalomOsszesitettEredmenyImpl extends SzlalomOsszesitettEredmeny
{

  private Szakasz szakasz;

  private SzakaszEredmeny sze;

  private Nevezes nevezes;

  private List reszeredmenyek;

  public SzlalomOsszesitettEredmenyImpl ()
  {
    super();
  }

  public SzlalomOsszesitettEredmenyImpl ( long szid, int rajtszam )
  {
    super( szid, rajtszam );
  }

  public SzlalomOsszesitettEredmenyImpl ( long szid, int rajtszam, long vid, long kategoria, int pontszam, int atvittPontszam )
  {
    super( szid, rajtszam, vid, kategoria, pontszam, atvittPontszam );
  }

  public void updateSzakasz () throws GeoException, RdbException
  {
    szakasz = Szakasz.newInstance( getSzid() );
    szakasz.read( GeoDbSession.getCurrentInstance() );
  }

  public Szakasz getSzakasz () throws RdbException, GeoException
  {
    if ( szakasz == null ) updateSzakasz();
    return szakasz;
  }

  public void updateSzakaszEredmeny () throws GeoException, RdbException
  {
    sze = SzakaszEredmeny.newInstance( getSzid(), getRajtszam() );
    sze.read( GeoDbSession.getCurrentInstance() );
  }

  public SzakaszEredmeny getSzakaszEredmeny () throws RdbException, GeoException
  {
    if ( sze == null ) updateSzakaszEredmeny();
    return sze;
  }

  public void updateNevezes () throws GeoException, RdbException
  {
    nevezes = Nevezes.newInstance( getSzakasz().getVid(), getRajtszam() );
    nevezes.read( GeoDbSession.getCurrentInstance() );
  }

  public Nevezes getNevezes () throws RdbException, GeoException
  {
    if ( nevezes == null ) updateNevezes();
    return nevezes;
  }

  public void updateReszeredmenyek () throws GeoException, RdbException
  {
    reszeredmenyek = SzlalomEredmeny.loadAll( GeoDbSession.getCurrentInstance(), SzlalomEredmeny.class, "rajtszam", getNevezes(), "vid", this );
  }

  public List getReszeredmenyek () throws GeoException, RdbException
  {
    if ( reszeredmenyek == null ) updateReszeredmenyek();
    return reszeredmenyek;
  }

}
