package scs.georesults.logic.beans.importing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.dii.PropertyUtils;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbImportSession;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.szotar.VersenySzotar;
import scs.georesults.logic.actions.importing.ImportException;
import scs.georesults.logic.utils.ImportUtils;
import scs.georesults.om.alap.BuntetesTipus;
import scs.georesults.om.alap.DarabFuggoFeladatTipus;
import scs.georesults.om.alap.SorrendFuggoFeladatTipus;
import scs.georesults.om.etap.DarabFuggoEtapFeladat;
import scs.georesults.om.etap.SorrendFuggoEtapFeladat;
import scs.georesults.om.verseny.Etap;

public class EtapokSubbean extends EsemenySubbeanBase
{

  private Map importEidSzid = new HashMap(); // Map( Long[eid] -> Long[szid] )

  public String getImportOrAssignTypenamesKey ()
  {
    return "IF_FELADAT_ES_BUNTETES_TIPUSOK";
  }

  public int getMaximumBranchSteps ()
  {
    return 5;
  }

  protected Class getEsemenyClass ()
  {
    return Etap.class;
  }

  public Map getImportEidSzid ()
  {
    return importEidSzid;
  }

  public void doImport ( RdbImportSession importSession, HttpServletRequest request ) throws DIIException, RdbException, GeoException
  {
    Long currentVid = new Long( importBean.getCurrentVerseny().getVid() );
    for ( Iterator it = importEidSzid.entrySet().iterator(); it.hasNext(); ) {
      Map.Entry entry = ( Map.Entry ) it.next();
      Long oldEid = ( Long ) entry.getKey();
      Long newSzid = ( Long ) entry.getValue();
      Etap etap = Etap.newInstance( oldEid.longValue() );
      etap.read( getSourceSession() );
      List dfefk = DarabFuggoEtapFeladat.loadAllForEtap( getSourceSession(), etap );
      List sfefk = SorrendFuggoEtapFeladat.loadAllForEtap( getSourceSession(), etap );
      importEtap( importSession, etap, dfefk, sfefk, currentVid, newSzid.longValue() );
    }
  }

  private void importEtap ( RdbImportSession importSession, Etap etap, List dfefk, List sfefk, Long currentVid, long newSzid ) throws GeoException, RdbException, DIIException
  {
    etap.setVid( currentVid.longValue() );
    etap.setSzid( newSzid );
    importMultilangEntity( importSession, etap, "nev", getSourceVersenySzotar() );
    if ( importBean.isRecognise() ) {
      importBean.getRecogniser().putTypeToSession( DarabFuggoFeladatTipus.class, importSession );
      importBean.getRecogniser().putTypeToSession( SorrendFuggoFeladatTipus.class, importSession );
    } else {
      importMultilangEntities( importSession, loadSzukegesTipusok( DarabFuggoFeladatTipus.class, dfefk, "dfftid" ), "nev", getSourceVersenySzotar(), currentVid );
      importMultilangEntities( importSession, loadSzukegesTipusok( SorrendFuggoFeladatTipus.class, sfefk, "sfftid" ), "nev", getSourceVersenySzotar(), currentVid );
    }
    importSession.importEntities( dfefk );
    importSession.importEntities( sfefk );
    if ( etap.getMenetlevelformula() != null ) {
      etap.setMenetlevelformula( ImportUtils.getUpdatedMenetlevelformula( importSession, etap.getMenetlevelformula() ) );
      etap.update( GeoDbSession.getCurrentInstance() );
    }
  }

  private List loadSzukegesTipusok ( Class clazz, List efk, String idPropertyName ) throws DIIException
  {
    List src = loadEntitesForVerseny( clazz );
    List results = new List( src.size() );
    for ( int i = 0; i < src.size(); ++i ) {
      Object id = PropertyUtils.getProperty( src.get( i ), idPropertyName );
      if ( efk.findItemIndex( idPropertyName, id ) >= 0 ) {
        results.add( src.get( i ) );
      }
    }
    return results;
  }

  public void checkForNyelvOrszag () throws DIIException, RdbException, GeoException
  {}

  public void checkForAlapnyelvInvariant () throws DIIException, RdbException, GeoException
  {
    if ( !importBean.isRecognise() ) {
      VersenySzotar sourceVsz = importBean.getSourceSubbean().getVersenySzotar();
      String currentAlapNyelv = importBean.getCurrentVerseny().getAlapNyelv();
      checkForAlapnyelvInvariantBase( loadEntitesForVerseny( DarabFuggoFeladatTipus.class ), "nev", sourceVsz, currentAlapNyelv );
      checkForAlapnyelvInvariantBase( loadEntitesForVerseny( SorrendFuggoFeladatTipus.class ), "nev", sourceVsz, currentAlapNyelv );
    }
  }

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
