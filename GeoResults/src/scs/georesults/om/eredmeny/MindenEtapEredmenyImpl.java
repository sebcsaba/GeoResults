package scs.georesults.om.eredmeny;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.ReszEredmeny;
import scs.georesults.om.nevezes.Nevezes;

public class MindenEtapEredmenyImpl extends MindenEtapEredmeny implements ReszEredmeny
{

  private Nevezes nevezes;

  private List etapEredmenyek;

  public MindenEtapEredmenyImpl ()
  {
    super();
  }

  public MindenEtapEredmenyImpl ( long vid, int rajtszam )
  {
    super( vid, rajtszam );
  }

  public MindenEtapEredmenyImpl ( long vid, int rajtszam, int pontszam )
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

  public void updateEtapEredmenyek () throws GeoException, RdbException
  {
    etapEredmenyek = EtapEredmeny.loadAll( GeoDbSession.getCurrentInstance(), EtapEredmeny.class, "rajtszam", getNevezes(), "vid", this );
  }

  public List getEtapEredmenyek () throws GeoException, RdbException
  {
    if ( etapEredmenyek == null ) updateEtapEredmenyek();
    return etapEredmenyek;
  }

}
