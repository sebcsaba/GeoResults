package scs.georesults.om.eredmeny;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.ReszEredmeny;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.verseny.Szakasz;

public class SzakaszEredmenyImpl extends SzakaszEredmeny implements ReszEredmeny
{

  private Szakasz szakasz;

  private SzlalomOsszesitettEredmeny szloe;

  private Nevezes nevezes;

  private List etapEredmenyek;

  private List szlalomEredmenyek;

  public SzakaszEredmenyImpl ()
  {
    super();
  }

  public SzakaszEredmenyImpl ( long szid, int rajtszam )
  {
    super( szid, rajtszam );
  }

  public SzakaszEredmenyImpl ( long szid, int rajtszam, long vid, boolean keses, int pontszam )
  {
    super( szid, rajtszam, vid, keses, pontszam );
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

  public void updateSzlalomOsszesitettEredmeny () throws GeoException, RdbException
  {
    szloe = SzlalomOsszesitettEredmeny.newInstance( getSzid(), getRajtszam() );
    szloe.read( GeoDbSession.getCurrentInstance() );
  }

  public SzlalomOsszesitettEredmeny getSzlalomOsszesitettEredmeny () throws RdbException, GeoException
  {
    if ( szloe == null ) updateSzlalomOsszesitettEredmeny();
    return szloe;
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

  public void updateEtapEredmenyek () throws GeoException, RdbException
  {
    etapEredmenyek = EtapEredmeny.loadAll( GeoDbSession.getCurrentInstance(), EtapEredmeny.class, "rajtszam", getNevezes(), "vid", this );
  }

  public List getEtapEredmenyek () throws GeoException, RdbException
  {
    if ( etapEredmenyek == null ) updateEtapEredmenyek();
    return etapEredmenyek;
  }

  public void updateSzlalomEredmenyek () throws GeoException, RdbException
  {
    szlalomEredmenyek = SzlalomEredmeny.loadAll( GeoDbSession.getCurrentInstance(), SzlalomEredmeny.class, "rajtszam", getNevezes(), "vid", this );
  }

  public List getSzlalomEredmenyek () throws GeoException, RdbException
  {
    if ( szlalomEredmenyek == null ) updateSzlalomEredmenyek();
    return szlalomEredmenyek;
  }

}
