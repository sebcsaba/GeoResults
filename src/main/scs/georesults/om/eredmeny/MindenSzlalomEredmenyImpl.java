package scs.georesults.om.eredmeny;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.ReszEredmeny;
import scs.georesults.om.nevezes.Nevezes;

public class MindenSzlalomEredmenyImpl extends MindenSzlalomEredmeny implements ReszEredmeny
{

  private Nevezes nevezes;

  private List szlalomEredmenyek;

  public MindenSzlalomEredmenyImpl ()
  {
    super();
  }

  public MindenSzlalomEredmenyImpl ( long vid, int rajtszam )
  {
    super( vid, rajtszam );
  }

  public MindenSzlalomEredmenyImpl ( long vid, int rajtszam, long kategoria, int pontszam )
  {
    super( vid, rajtszam, kategoria, pontszam );
  }

  public void updateNevezes () throws GeoException, RdbException
  {
    nevezes = Nevezes.newInstance( getVid(), getRajtszam() );
    nevezes.read( GeoDbSession.getCurrentInstance() );
  }

  public Nevezes getNevezes () throws GeoException, RdbException
  {
    if ( nevezes == null ) updateNevezes();
    return nevezes;
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
