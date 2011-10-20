package scs.georesults.om.eredmeny;

import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.ReszEredmeny;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.szlalom.SzlalomFutam;
import scs.georesults.om.verseny.Szlalom;

public class SzlalomEredmenyImpl extends SzlalomEredmeny implements ReszEredmeny
{

  private Szlalom szlalom;

  private Nevezes nevezes;

  private SzlalomFutam szlalomFutam;

  public SzlalomEredmenyImpl ()
  {
    super();
  }

  public SzlalomEredmenyImpl ( long szlid, int rajtszam )
  {
    super( szlid, rajtszam );
  }

  public SzlalomEredmenyImpl ( long szlid, int rajtszam, long vid, int pontszam, long kategoria )
  {
    super( szlid, rajtszam, vid, pontszam, kategoria );
  }

  public void updateSzlalom () throws GeoException, RdbException
  {
    szlalom = Szlalom.newInstance( getSzlid() );
    szlalom.read( GeoDbSession.getCurrentInstance() );
  }

  public Szlalom getSzlalom () throws GeoException, RdbException
  {
    if ( szlalom == null ) updateSzlalom();
    return szlalom;
  }

  public void updateNevezes () throws GeoException, RdbException
  {
    nevezes = Nevezes.newInstance( getSzlalom().getVid(), getRajtszam() );
    nevezes.read( GeoDbSession.getCurrentInstance() );
  }

  public Nevezes getNevezes () throws GeoException, RdbException
  {
    if ( nevezes == null ) updateNevezes();
    return nevezes;
  }

  public void updateSzlalomFutam () throws GeoException, RdbException
  {
    szlalomFutam = SzlalomFutam.newInstance( getSzlid(), getRajtszam() );
    szlalomFutam.read( GeoDbSession.getCurrentInstance() );
  }

  public SzlalomFutam getSzlalomFutam () throws GeoException, RdbException
  {
    if ( szlalomFutam == null ) updateSzlalomFutam();
    return szlalomFutam;
  }

}
