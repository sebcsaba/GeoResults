package scs.georesults.logic.beans.importing;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbImportSession;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.szotar.VersenySzotar;
import scs.georesults.logic.actions.importing.ImportException;
import scs.georesults.om.alap.BuntetesTipus;
import scs.georesults.om.alap.DarabFuggoFeladatTipus;
import scs.georesults.om.alap.SorrendFuggoFeladatTipus;
import scs.georesults.om.menetlevel.Menetlevel;
import scs.georesults.om.verseny.Etap;
import scs.georesults.om.verseny.EtapImpl;
import scs.georesults.om.verseny.VersenyImpl;

public class MenetlevelekSubbean extends FutamSubbeanBase
{

  public String getImportOrAssignTypenamesKey ()
  {
    return "IF_FELADAT_ES_BUNTETES_TIPUSOK";
  }

  public int getMaximumBranchSteps ()
  {
    return 6;
  }

  public boolean isMenetlevel ()
  {
    return true;
  }

  protected Class getEsemenyClass ()
  {
    return Etap.class;
  }

  public void testVanCurrentEsemeny ( VersenyImpl verseny ) throws RdbException, GeoException
  {
    if ( verseny.getEtapok().size() == 0 )throw new ImportException( "IF_NINCS_ETAP" );
  }

  public List getAllCurrentFutamok () throws RdbException, GeoException
  {
    return Menetlevel.loadAllForEtap( GeoDbSession.getCurrentInstance(), ( Etap ) getHovaEsemeny() );
  }

  public List getAllSourceFutamok () throws RdbException, GeoException
  {
    return Menetlevel.loadAllForEtap( getSourceSession(), ( Etap ) getSourceEsemeny() );
  }

  public void updateSourceEsemeny () throws RdbException
  {
    sourceEsemeny = ( EtapImpl ) Etap.newInstance( sourceEsemenyId );
    sourceEsemeny.read( getSourceSession() );
  }

  public void updateHovaEsemeny () throws GeoException, RdbException
  {
    hovaEsemeny = ( EtapImpl ) Etap.newInstance( hovaEsemenyId );
    hovaEsemeny.read( GeoDbSession.getCurrentInstance() );
  }

  protected void doDependenciesBeforeImport ( RdbImportSession importSession, Long currentVid ) throws GeoException, DIIException, RdbException
  {
    importBean.getRecogniser().putTypeToSession( DarabFuggoFeladatTipus.class, importSession );
    importBean.getRecogniser().putTypeToSession( SorrendFuggoFeladatTipus.class, importSession );
    importBean.getRecogniser().putTypeToSession( BuntetesTipus.class, importSession );
  }

  public void checkForAlapnyelvInvariant () throws DIIException, RdbException, GeoException
  {}

  public List getRecogniserItems ( VersenySzotar currentVsz ) throws DIIException, RdbException, GeoException
  {
    List result = new List();
    VersenySzotar importVsz = getSourceVersenySzotar();

    List importSfftEntities = SorrendFuggoFeladatTipus.loadAllForVerseny( getSourceSession(), getSourceVerseny() );
    List currentSfftEntities = importBean.getCurrentVerseny().getSorrendFuggoFeladatTipusok();
    if ( importSfftEntities.size() > 0 && currentSfftEntities.size() == 0 )throw new ImportException( "IF_NINCS_SFFT" );
    addToRecogniserItemsAllEntities( result, importSfftEntities, currentSfftEntities, importVsz, currentVsz, SorrendFuggoFeladatTipus.class, "nev", "sfftid" );

    List importDfftEntities = DarabFuggoFeladatTipus.loadAllForVerseny( getSourceSession(), getSourceVerseny() );
    List currentDfftEntities = importBean.getCurrentVerseny().getDarabFuggoFeladatTipusok();
    if ( importDfftEntities.size() > 0 && currentDfftEntities.size() == 0 )throw new ImportException( "IF_NINCS_DFFT" );
    addToRecogniserItemsAllEntities( result, importDfftEntities, currentDfftEntities, importVsz, currentVsz, DarabFuggoFeladatTipus.class, "nev", "dfftid" );

    List importBtEntities = BuntetesTipus.loadAllForVerseny( getSourceSession(), getSourceVerseny() );
    List currentBtEntities = importBean.getCurrentVerseny().getBuntetesTipusok();
    if ( importBtEntities.size() > 0 && currentBtEntities.size() == 0 )throw new ImportException( "IF_NINCS_BT" );
    addToRecogniserItemsAllEntities( result, importBtEntities, currentBtEntities, importVsz, currentVsz, BuntetesTipus.class, "nev", "btid" );

    return result;
  }

}
