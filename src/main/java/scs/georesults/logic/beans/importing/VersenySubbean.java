package scs.georesults.logic.beans.importing;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.*;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.iotools.DatabaseVersenyExportIterator;
import scs.georesults.common.szotar.VersenySzotar;
import scs.georesults.logic.actions.importing.ImportException;
import scs.georesults.logic.utils.ImportUtils;
import scs.georesults.om.alap.VersenySzotarBejegyzes;
import scs.georesults.om.kozos.Nyelv;
import scs.georesults.om.kozos.Orszag;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.verseny.Etap;
import scs.georesults.om.verseny.Verseny;

public class VersenySubbean extends ImportCategorySubbean
{

  public List getRecogniserItems ( VersenySzotar currentVsz ) throws DIIException, RdbException, GeoException
  {
    return null;
  }

  public int getMaximumBranchSteps ()
  {
    return 2;
  }

  public void doImport ( RdbImportSession importSession, HttpServletRequest request ) throws GeoException, RdbException, DIIException
  {
    RdbSession dstSession = GeoDbSession.getCurrentInstance();
    DatabaseVersenyExportIterator srcIter = new DatabaseVersenyExportIterator( getSourceSession(), getSourceVerseny().getVid() );
    Set updateRequired = new HashSet();
    while ( srcIter.hasNext() ) {
      StorableEntityBase entity = ( StorableEntityBase ) srcIter.next();
      importSession.importEntity( entity );
      if ( entity instanceof Verseny || entity instanceof Etap ) updateRequired.add( entity );
    }
    for ( Iterator it = updateRequired.iterator(); it.hasNext(); ) {
      StorableEntityBase entity = ( StorableEntityBase ) it.next();
      if ( entity instanceof Verseny ) {
        Verseny v = ( Verseny ) entity;
        v.setMenetlevelformula( ImportUtils.getUpdatedMenetlevelformula( importSession, v.getMenetlevelformula() ) );
        v.update( dstSession );
      } else if ( entity instanceof Etap ) {
        Etap e = ( Etap ) entity;
        if ( e.getMenetlevelformula() != null ) {
          e.setMenetlevelformula( ImportUtils.getUpdatedMenetlevelformula( importSession, e.getMenetlevelformula() ) );
          e.update( dstSession );
        }
      }
    }
  }

  public void checkForNyelvOrszag () throws GeoException, RdbException, DIIException
  {
    List currentNyelvek = Nyelv.loadAll( GeoDbSession.getCurrentInstance() );
    List currentOrszagok = Orszag.loadAll( GeoDbSession.getCurrentInstance() );
    List src;
    src = Verseny.loadAll( getSourceSession() );
    for ( int i = 0; i < src.size(); ++i ) {
      String lang = ( ( Verseny ) src.get( i ) ).getAlapNyelv();
      if ( currentNyelvek.findItemIndex( "lang", lang ) < 0 )throw new ImportException( "IF_HIBAS_NYELV" );
    }
    src = loadEntitesForVerseny( VersenySzotarBejegyzes.class );
    for ( int i = 0; i < src.size(); ++i ) {
      String lang = ( ( VersenySzotarBejegyzes ) src.get( i ) ).getLang();
      if ( currentNyelvek.findItemIndex( "lang", lang ) < 0 )throw new ImportException( "IF_HIBAS_NYELV" );
    }
    src = loadEntitesForVerseny( Nevezes.class );
    for ( int i = 0; i < src.size(); ++i ) {
      String country = ( ( Nevezes ) src.get( i ) ).getOrszag();
      if ( currentOrszagok.findItemIndex( "country", country ) < 0 )throw new ImportException( "IF_HIBAS_ORSZAG" );
    }
  }

  public void checkForAlapnyelvInvariant () throws DIIException, RdbException, GeoException
  {}

  public String getImportOrAssignTypenamesKey ()
  {
    return null;
  }

}
