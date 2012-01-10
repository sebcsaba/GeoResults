package scs.georesults.logic.beans.importing;

import javax.servlet.http.HttpServletRequest;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbImportSession;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.szotar.VersenySzotar;
import scs.georesults.logic.actions.importing.ImportException;
import scs.georesults.om.alap.AutoTipus;
import scs.georesults.om.alap.SzlalomKategoria;
import scs.georesults.om.kozos.Orszag;
import scs.georesults.om.nevezes.CsapatNevezes;
import scs.georesults.om.nevezes.Nevezes;

public class NevezesekSubbean extends ImportCategorySubbean
{

  public String getImportOrAssignTypenamesKey ()
  {
    return "RA_AUTOTIPUSOK";
  }

  public int getMaximumBranchSteps ()
  {
    return 3;
  }

  public void doImport ( RdbImportSession importSession, HttpServletRequest request ) throws DIIException, RdbException, GeoException
  {
    Long currentVid = new Long( importBean.getCurrentVerseny().getVid() );
    if ( importBean.isRecognise() ) {
      importBean.getRecogniser().putTypeToSession( AutoTipus.class, importSession );
    } else {
      importMultilangEntities( importSession, SzlalomKategoria.loadAllForVerseny( getSourceSession(), getSourceVerseny() ), "nev", getSourceVersenySzotar(), currentVid );
      importSimpleEntities( importSession, AutoTipus.loadAllForVerseny( getSourceSession(), getSourceVerseny() ), currentVid );
    }
    importSimpleEntities( importSession, Nevezes.loadAllForVerseny( getSourceSession(), getSourceVerseny() ), currentVid );
    importSimpleEntities( importSession, CsapatNevezes.loadAllForVerseny( getSourceSession(), getSourceVerseny() ), currentVid );
  }

  public void checkForNyelvOrszag () throws DIIException, RdbException, GeoException
  {
    List currentOrszagok = Orszag.loadAll( GeoDbSession.getCurrentInstance() );
    List src = Nevezes.loadAllForVerseny( getSourceSession(), getSourceVerseny() );
    for ( int i = 0; i < src.size(); ++i ) {
      String country = ( ( Nevezes ) src.get( i ) ).getOrszag();
      if ( currentOrszagok.findItemIndex( "country", country ) < 0 )throw new ImportException( "IF_HIBAS_ORSZAG" );
    }
  }

  public void checkForAlapnyelvInvariant () throws DIIException, RdbException, GeoException
  {
    if ( !importBean.isRecognise() ) {
      VersenySzotar sourceVsz = importBean.getSourceSubbean().getVersenySzotar();
      String currentAlapNyelv = importBean.getCurrentVerseny().getAlapNyelv();
      checkForAlapnyelvInvariantBase( loadEntitesForVerseny( SzlalomKategoria.class ), "nev", sourceVsz, currentAlapNyelv );
      checkForAlapnyelvInvariantBase( loadEntitesForVerseny( AutoTipus.class ), "nev", sourceVsz, currentAlapNyelv );
    }
  }

  public List getRecogniserItems ( VersenySzotar currentVsz ) throws DIIException, RdbException, GeoException
  {
    List result = new List();

    List importEntities = AutoTipus.loadAllForVerseny( getSourceSession(), getSourceVerseny() );
    List currentEntities = importBean.getCurrentVerseny().getAutoTipusok();
    if ( importEntities.size() > 0 && currentEntities.size() == 0 )throw new ImportException( "IF_NINCS_AUTOTIPUS" );
    addToRecogniserItemsAllEntities( result, importEntities, currentEntities, getSourceVersenySzotar(), currentVsz, AutoTipus.class, "nev", "atid" );

    return result;
  }

}
