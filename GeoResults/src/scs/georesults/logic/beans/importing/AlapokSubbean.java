package scs.georesults.logic.beans.importing;

import javax.servlet.http.HttpServletRequest;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbImportSession;
import scs.georesults.GeoException;
import scs.georesults.common.szotar.VersenySzotar;
import scs.georesults.logic.actions.importing.ImportException;
import scs.georesults.om.alap.*;

public class AlapokSubbean extends ImportCategorySubbean
{

  private boolean sorrendFuggoFeladatTipusok;

  private boolean darabFuggoFeladatTipusok;

  private boolean kesesiZonak;

  private boolean buntetesTipusok;

  private boolean szlalomFeladatok;

  private boolean szlalomKategoriak;

  private boolean autoTipusok;

  public String getImportOrAssignTypenamesKey ()
  {
    return "RB_SZLALOM_KATEGORIAK";
  }

  public int getMaximumBranchSteps ()
  {
    return 3;
  }

  public void doImport ( RdbImportSession importSession, HttpServletRequest request ) throws DIIException, RdbException, GeoException
  {
    Long currentVid = new Long( importBean.getCurrentVerseny().getVid() );
    if ( isBuntetesTipusok() ) importMultilangEntities( importSession, loadEntitesForVerseny( BuntetesTipus.class ), "nev", getSourceVersenySzotar(), currentVid );
    if ( isDarabFuggoFeladatTipusok() ) importMultilangEntities( importSession, loadEntitesForVerseny( DarabFuggoFeladatTipus.class ), "nev", getSourceVersenySzotar(), currentVid );
    if ( isSorrendFuggoFeladatTipusok() ) {
      importMultilangEntities( importSession, loadEntitesForVerseny( SorrendFuggoFeladatTipus.class ), "nev", getSourceVersenySzotar(), currentVid );
      // TODO: itt kell az áthozott feladattípusokhoz tartozó menetlevélformula-részletet átemelni ide is.
      // ugyanezt fentebb is megtenni a DFFT-nél
      // ugyanezt a többi *Subbean-ben nem kellene éppen?
    }
    if ( isKesesiZonak() ) importSimpleEntities( importSession, loadEntitesForVerseny( KesesiZona.class ), currentVid );
    if ( isSzlalomFeladatok() ) importMultilangEntities( importSession, loadEntitesForVerseny( SzlalomFeladat.class ), "nev", getSourceVersenySzotar(), currentVid );
    if ( isSzlalomKategoriak() ) importMultilangEntities( importSession, loadEntitesForVerseny( SzlalomKategoria.class ), "nev", getSourceVersenySzotar(), currentVid );
    if ( isAutoTipusok() && !isSzlalomKategoriak() ) {
      importBean.getRecogniser().putTypeToSession( SzlalomKategoria.class, importSession );
    }
    if ( isAutoTipusok() ) importMultilangEntities( importSession, loadEntitesForVerseny( AutoTipus.class ), "nev", getSourceVersenySzotar(), currentVid );
  }

  public void checkForAlapnyelvInvariant () throws DIIException, RdbException, GeoException
  {
    VersenySzotar sourceVsz = importBean.getSourceSubbean().getVersenySzotar();
    String currentAlapNyelv = importBean.getCurrentVerseny().getAlapNyelv();
    if ( isBuntetesTipusok() ) checkForAlapnyelvInvariantBase( loadEntitesForVerseny( BuntetesTipus.class ), "nev", sourceVsz, currentAlapNyelv );
    if ( isDarabFuggoFeladatTipusok() ) checkForAlapnyelvInvariantBase( loadEntitesForVerseny( DarabFuggoFeladatTipus.class ), "nev", sourceVsz, currentAlapNyelv );
    if ( isSorrendFuggoFeladatTipusok() ) checkForAlapnyelvInvariantBase( loadEntitesForVerseny( SorrendFuggoFeladatTipus.class ), "nev", sourceVsz, currentAlapNyelv );
    if ( isSzlalomFeladatok() ) checkForAlapnyelvInvariantBase( loadEntitesForVerseny( SzlalomFeladat.class ), "nev", sourceVsz, currentAlapNyelv );
    if ( isSzlalomKategoriak() ) checkForAlapnyelvInvariantBase( loadEntitesForVerseny( SzlalomKategoria.class ), "nev", sourceVsz, currentAlapNyelv );
    if ( isAutoTipusok() ) checkForAlapnyelvInvariantBase( loadEntitesForVerseny( AutoTipus.class ), "nev", sourceVsz, currentAlapNyelv );
  }

  public void checkForNyelvOrszag () throws RdbException, GeoException, DIIException
  {}

  public List getRecogniserItems ( VersenySzotar currentVsz ) throws DIIException, RdbException, GeoException
  {
    List result = new List();
    List importEntities = SzlalomKategoria.loadAllForVerseny( getSourceSession(), getSourceVerseny() );
    List currentEntities = importBean.getCurrentVerseny().getSzlalomKategoriak();
    if ( importEntities.size() > 0 && currentEntities.size() == 0 )throw new ImportException( "IF_NINCS_SZLALOMKATEGORIA" );
    addToRecogniserItemsAllEntities( result, importEntities, currentEntities, getSourceVersenySzotar(), currentVsz, SzlalomKategoria.class, "nev", "szkid" );
    return result;
  }

  public boolean isSzlalomKategoriak ()
  {
    return szlalomKategoriak;
  }

  public boolean isSzlalomFeladatok ()
  {
    return szlalomFeladatok;
  }

  public boolean isSorrendFuggoFeladatTipusok ()
  {
    return sorrendFuggoFeladatTipusok;
  }

  public boolean isKesesiZonak ()
  {
    return kesesiZonak;
  }

  public boolean isDarabFuggoFeladatTipusok ()
  {
    return darabFuggoFeladatTipusok;
  }

  public boolean isBuntetesTipusok ()
  {
    return buntetesTipusok;
  }

  public void setAutoTipusok ( boolean autoTipusok )
  {
    this.autoTipusok = autoTipusok;
  }

  public void setSzlalomKategoriak ( boolean szlalomKategoriak )
  {
    this.szlalomKategoriak = szlalomKategoriak;
  }

  public void setSzlalomFeladatok ( boolean szlalomFeladatok )
  {
    this.szlalomFeladatok = szlalomFeladatok;
  }

  public void setSorrendFuggoFeladatTipusok ( boolean sorrendFuggoFeladatTipusok )
  {
    this.sorrendFuggoFeladatTipusok = sorrendFuggoFeladatTipusok;
  }

  public void setKesesiZonak ( boolean kesesiZonak )
  {
    this.kesesiZonak = kesesiZonak;
  }

  public void setDarabFuggoFeladatTipusok ( boolean darabFuggoFeladatTipusok )
  {
    this.darabFuggoFeladatTipusok = darabFuggoFeladatTipusok;
  }

  public void setBuntetesTipusok ( boolean buntetesTipusok )
  {
    this.buntetesTipusok = buntetesTipusok;
  }

  public boolean isAutoTipusok ()
  {
    return autoTipusok;
  }

}
