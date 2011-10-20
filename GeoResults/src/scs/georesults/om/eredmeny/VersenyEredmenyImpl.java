package scs.georesults.om.eredmeny;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.ReszEredmeny;
import scs.georesults.om.nevezes.Nevezes;

public class VersenyEredmenyImpl extends VersenyEredmeny implements ReszEredmeny
{

  private Nevezes nevezes;

  private List szakaszEredmenyek;

  public VersenyEredmenyImpl ()
  {
    super();
  }

  public VersenyEredmenyImpl ( long vid, int rajtszam )
  {
    super( vid, rajtszam );
  }

  public VersenyEredmenyImpl ( long vid, int rajtszam, int pontszam )
  {
    super( vid, rajtszam, pontszam );
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

  public void updateSzakaszEredmenyek () throws GeoException, RdbException
  {
    szakaszEredmenyek = SzakaszEredmeny.loadAll( GeoDbSession.getCurrentInstance(), SzakaszEredmeny.class, "rajtszam", getNevezes(), "vid", this );
  }

  public List getSzakaszEredmenyek () throws GeoException, RdbException
  {
    if ( szakaszEredmenyek == null ) updateSzakaszEredmenyek();
    return szakaszEredmenyek;
  }

}
