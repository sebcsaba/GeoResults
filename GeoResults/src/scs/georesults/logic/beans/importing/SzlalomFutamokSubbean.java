package scs.georesults.logic.beans.importing;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbImportSession;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.szotar.VersenySzotar;
import scs.georesults.logic.actions.importing.ImportException;
import scs.georesults.om.alap.SzlalomFeladat;
import scs.georesults.om.szlalom.SzlalomFutam;
import scs.georesults.om.verseny.Szlalom;
import scs.georesults.om.verseny.SzlalomImpl;
import scs.georesults.om.verseny.VersenyImpl;

public class SzlalomFutamokSubbean extends FutamSubbeanBase
{

  public String getImportOrAssignTypenamesKey ()
  {
    return "RB_SZLALOM_FELADATOK";
  }

  public int getMaximumBranchSteps ()
  {
    return 7;
  }

  public boolean isMenetlevel ()
  {
    return false;
  }

  protected Class getEsemenyClass ()
  {
    return Szlalom.class;
  }

  public void testVanCurrentEsemeny ( VersenyImpl verseny ) throws RdbException, GeoException
  {
    if ( verseny.getSzlalomok().size() == 0 )throw new ImportException( "IF_NINCS_SZLALOM" );
  }

  public List getAllCurrentFutamok () throws RdbException, GeoException
  {
    return SzlalomFutam.loadAllForSzlalom( GeoDbSession.getCurrentInstance(), ( Szlalom ) getHovaEsemeny() );
  }

  public List getAllSourceFutamok () throws RdbException, GeoException
  {
    return SzlalomFutam.loadAllForSzlalom( getSourceSession(), ( Szlalom ) getSourceEsemeny() );
  }

  public void updateSourceEsemeny () throws RdbException
  {
    sourceEsemeny = ( SzlalomImpl ) Szlalom.newInstance( sourceEsemenyId );
    sourceEsemeny.read( getSourceSession() );
  }

  public void updateHovaEsemeny () throws GeoException, RdbException
  {
    hovaEsemeny = ( SzlalomImpl ) Szlalom.newInstance( hovaEsemenyId );
    hovaEsemeny.read( GeoDbSession.getCurrentInstance() );
  }

  protected void doDependenciesBeforeImport ( RdbImportSession importSession, Long currentVid ) throws GeoException, DIIException, RdbException
  {
    if ( importBean.isRecognise() ) {
      importBean.getRecogniser().putTypeToSession( SzlalomFeladat.class, importSession );
    } else {
      importMultilangEntities( importSession, SzlalomFeladat.loadAllForVerseny( getSourceSession(), getSourceVerseny() ), "nev", getSourceVersenySzotar(), currentVid );
    }
  }

  public void checkForAlapnyelvInvariant () throws DIIException, RdbException, GeoException
  {
    if ( !importBean.isRecognise() ) {
      VersenySzotar sourceVsz = importBean.getSourceSubbean().getVersenySzotar();
      String currentAlapNyelv = importBean.getCurrentVerseny().getAlapNyelv();
      checkForAlapnyelvInvariantBase( loadEntitesForVerseny( SzlalomFeladat.class ), "nev", sourceVsz, currentAlapNyelv );
      checkForAlapnyelvInvariantBase( getSourceEsemeny(), "nev", sourceVsz, currentAlapNyelv );
    }
  }

  public List getRecogniserItems ( VersenySzotar currentVsz ) throws DIIException, RdbException, GeoException
  {
    List result = new List();

    List importEntities = SzlalomFeladat.loadAllForVerseny( getSourceSession(), getSourceVerseny() );
    List currentEntities = importBean.getCurrentVerseny().getSzlalomFeladatok();
    if ( importEntities.size() > 0 && currentEntities.size() == 0 )throw new ImportException( "IF_NINCS_SZLALOMFELADAT" );
    addToRecogniserItemsAllEntities( result, importEntities, currentEntities, getSourceVersenySzotar(), currentVsz, SzlalomFeladat.class, "nev", "szfid" );

    return result;
  }

}
