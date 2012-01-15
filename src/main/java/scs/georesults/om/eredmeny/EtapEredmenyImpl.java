package scs.georesults.om.eredmeny;

import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.ReszEredmeny;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.verseny.Etap;

public class EtapEredmenyImpl extends EtapEredmeny implements ReszEredmeny
{

  private Etap etap;

  private Nevezes nevezes;

  public EtapEredmenyImpl ()
  {
    super();
  }

  public EtapEredmenyImpl ( long eid, int rajtszam )
  {
    super( eid, rajtszam );
  }

  public EtapEredmenyImpl ( long eid, int rajtszam, long vid, int kesesPerc, int kesesPont, int buntetoPont, int pontszam )
  {
    super( eid, rajtszam, vid, kesesPerc, kesesPont, buntetoPont, pontszam );
  }

  public void updateEtap () throws GeoException, RdbException
  {
    etap = Etap.newInstance( getEid() );
    etap.read( GeoDbSession.getCurrentInstance() );
  }

  public Etap getEtap () throws RdbException, GeoException
  {
    if ( etap == null ) updateEtap();
    return etap;
  }

  public void updateNevezes () throws GeoException, RdbException
  {
    nevezes = Nevezes.newInstance( getEtap().getVid(), getRajtszam() );
    nevezes.read( GeoDbSession.getCurrentInstance() );
  }

  public Nevezes getNevezes () throws RdbException, GeoException
  {
    if ( nevezes == null ) updateNevezes();
    return nevezes;
  }

}
