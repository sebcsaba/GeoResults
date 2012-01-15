package scs.georesults.logic.beans.importing;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.georesults.GeoException;
import scs.georesults.common.szotar.VersenySzotar;
import scs.georesults.om.alap.*;
import scs.georesults.om.kozos.Nyelv;
import scs.georesults.om.kozos.SzotarBejegyzes;
import scs.georesults.om.menetlevel.Menetlevel;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.szlalom.SzlalomFutam;
import scs.georesults.om.verseny.Etap;
import scs.georesults.om.verseny.Szlalom;
import scs.georesults.om.verseny.VersenyImpl;

public class ImportSource
{

  protected ImportBean importBean;

  private RdbSession session;

  private VersenyImpl verseny;

  private VersenySzotar versenySzotar;

  public ImportSource ( RdbSession session, VersenyImpl verseny )
  {
    this.session = session;
    this.verseny = verseny;
  }

  public void init ( ImportBean importBean )
  {
    this.importBean = importBean;
  }

  public VersenyImpl getVerseny ()
  {
    return verseny;
  }

  public RdbSession getSession ()
  {
    return session;
  }

  public void updateVersenySzotar () throws RdbException, GeoException
  {
    Nyelv nyelv = Nyelv.newInstance( importBean.getLang() );
    versenySzotar = new VersenySzotar( session, verseny, nyelv );
  }

  public VersenySzotar getVersenySzotar () throws GeoException, RdbException
  {
    if ( versenySzotar == null ) updateVersenySzotar();
    return versenySzotar;
  }

  public boolean isTeljesVersenyImportalhato () throws RdbException
  {
    return verseny != null;
  }

  public boolean isAlapImportalhato () throws RdbException
  {
    boolean any = false;
    if ( !any ) any |= ( DarabFuggoFeladatTipus.loadAll( session ).size() > 0 );
    if ( !any ) any |= ( SorrendFuggoFeladatTipus.loadAll( session ).size() > 0 );
    if ( !any ) any |= ( BuntetesTipus.loadAll( session ).size() > 0 );
    if ( !any ) any |= ( KesesiZona.loadAll( session ).size() > 0 );
    if ( !any ) any |= ( SzlalomFeladat.loadAll( session ).size() > 0 );
    if ( !any ) any |= ( SzlalomKategoria.loadAll( session ).size() > 0 );
    if ( !any ) any |= ( AutoTipus.loadAll( session ).size() > 0 );
    return any;
  }

  public boolean isNevezesImportalhato () throws RdbException
  {
    return verseny != null && Nevezes.loadAllForVerseny( session, verseny ).size() > 0;
  }

  public boolean isEtapImportalhato () throws RdbException
  {
    return verseny != null && Etap.loadAllForVerseny( session, verseny ).size() > 0;
  }

  public boolean isSzlalomFutamImportalhato () throws RdbException
  {
    if ( verseny == null )return false;
    List szlalomok = Szlalom.loadAllForVerseny( session, verseny );
    for ( int i = 0; i < szlalomok.size(); ++i ) {
      if ( SzlalomFutam.loadAllForSzlalom( session, ( Szlalom ) szlalomok.get( i ) ).size() > 0 )return true;
    }
    return false;
  }

  public boolean isMenetlevelImportalhato () throws RdbException
  {
    if ( verseny == null )return false;
    List etapok = Etap.loadAllForVerseny( session, verseny );
    for ( int i = 0; i < etapok.size(); ++i ) {
      if ( Menetlevel.loadAllForEtap( session, ( Etap ) etapok.get( i ) ).size() > 0 )return true;
    }
    return false;
  }

  public boolean isForditasImportalhato () throws RdbException
  {
    List nyelvek = Nyelv.loadAll( session );
    for ( int i = 0; i < nyelvek.size(); ++i ) {
      if ( SzotarBejegyzes.loadAllForNyelv( session, ( Nyelv ) nyelvek.get( i ) ).size() > 0 )return true;
    }
    return false;
  }

}
