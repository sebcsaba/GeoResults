package scs.georesults.logic.beans.importing;

import javax.servlet.http.HttpServletRequest;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbImportSession;
import scs.javax.rdb.RdbSession;
import scs.javax.web.WebException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.ValueLabelPair;
import scs.georesults.common.szotar.GlobalSzotar;
import scs.georesults.common.szotar.VersenySzotar;
import scs.georesults.om.kozos.*;

public class ForditasSubbean extends ImportCategorySubbean
{

  private List lehetsegesNyelvek;

  private GlobalSzotar currentSzotar;

  public String getImportOrAssignTypenamesKey ()
  {
    return null;
  }

  public void checkForAlapnyelvInvariant () throws DIIException, RdbException, GeoException
  {}

  public List getRecogniserItems ( VersenySzotar currentVsz ) throws DIIException, RdbException, GeoException
  {
    return null;
  }

  public int getMaximumBranchSteps ()
  {
    return 2;
  }

  public void checkForNyelvOrszag () throws GeoException, RdbException, DIIException
  {}

  public void updateLehetsegesNyelvek () throws DIIException, GeoException, RdbException
  {
    List sourceNyelvek = Nyelv.loadAll( getSourceSession() );
    List currentNyelvek = Nyelv.loadAll( GeoDbSession.getCurrentInstance() );

    Nyelv aktNyelv = ( Nyelv ) sourceNyelvek.findItem( "lang", importBean.getLang() );
    boolean useSzotar;
    GlobalSzotar sourceSzotar = null;
    List sourceSzotarBejegyzesek = null;
    if ( aktNyelv != null ) {
      sourceSzotar = new GlobalSzotar( getSourceSession(), aktNyelv );
      useSzotar = true;
    } else {
      sourceSzotarBejegyzesek = SzotarBejegyzesImpl.loadAll( getSourceSession() );
      useSzotar = false;
    }
    lehetsegesNyelvek = new List();
    for ( int i = 0; i < sourceNyelvek.size(); ++i ) {
      Nyelv sourceNyelv = ( Nyelv ) sourceNyelvek.get( i );
      if ( currentNyelvek.findItemIndex( "lang", sourceNyelv.getLang() ) == -1 ) {
        String key = NyelvImpl.BUNDLE_PREFIX + sourceNyelv.getLang();
        String label;
        if ( useSzotar ) {
          label = sourceSzotar.getValue( key );
        } else {
          SzotarBejegyzes szb = ( SzotarBejegyzes ) sourceSzotarBejegyzesek.findItem( "keystr", key );
          label = szb.getValuestr();
        }
        ValueLabelPair vlp = new ValueLabelPair( "id_" + sourceNyelv.getLang(), label );
        lehetsegesNyelvek.add( vlp );
      }
    }
  }

  public List getLehetsegesNyelvek () throws DIIException, RdbException, GeoException
  {
    if ( lehetsegesNyelvek == null ) updateLehetsegesNyelvek();
    return lehetsegesNyelvek;
  }

  public void doImport ( RdbImportSession importSession, HttpServletRequest request ) throws WebException, RdbException, DIIException
  {
    currentSzotar = GlobalSzotar.getCurrentInstance( request );
    for ( int i = 0; i < lehetsegesNyelvek.size(); ++i ) {
      ValueLabelPair nyelvVlp = ( ValueLabelPair ) lehetsegesNyelvek.get( i );
      String lang = nyelvVlp.getValue().substring( 3 );
      Nyelv ujNyelv = Nyelv.newInstance( lang );
      importNyelv( importSession, ujNyelv );
    }
  }

  private void importNyelv ( RdbImportSession importSession, Nyelv ujNyelv ) throws RdbException, GeoException, DIIException
  {
    List letezoNyelvek = Nyelv.loadAll( GeoDbSession.getCurrentInstance() );
    List sourceBejegyzesek = getSourceBejegyzesek( ujNyelv );
    GlobalSzotar sourceSzotar = new GlobalSzotar( getSourceSession(), ujNyelv );
    importSession.importEntity( ujNyelv );
    String ujNyelvIgyHivjaMagat = importUjNyelvIgyHivjaMagat( importSession, ujNyelv, sourceSzotar );
    importKorabbiNyelvekNeveiUjNyelven( importSession, ujNyelv, sourceSzotar, letezoNyelvek );
    importUjNyelvNeveiKorabbiNyelveken( importSession, ujNyelv, ujNyelvIgyHivjaMagat, letezoNyelvek );
    importOrszagokNeveiUjNyelven( importSession, ujNyelv, sourceSzotar );
    importSession.importEntities( sourceBejegyzesek );
  }

  private String importUjNyelvIgyHivjaMagat ( RdbImportSession importSession, Nyelv ujNyelv, GlobalSzotar sourceSzotar ) throws RdbException, DIIException
  {
    String key = NyelvImpl.BUNDLE_PREFIX + ujNyelv.getLang();
    String label = sourceSzotar.getValue( key );
    SzotarBejegyzes szb = SzotarBejegyzes.newInstance( ujNyelv.getLang(), key, label );
    importSession.importEntity( szb );
    return label;
  }

  private void importKorabbiNyelvekNeveiUjNyelven ( RdbImportSession importSession, Nyelv ujNyelv, GlobalSzotar sourceSzotar, List letezoNyelvek ) throws RdbException, DIIException
  {
    for ( int i = 0; i < letezoNyelvek.size(); ++i ) {
      Nyelv letezoNyelv = ( Nyelv ) letezoNyelvek.get( i );
      String key = NyelvImpl.BUNDLE_PREFIX + letezoNyelv.getLang();
      String label = sourceSzotar.hasKey( key ) ? sourceSzotar.getValue( key ) : currentSzotar.getValue( key );
      SzotarBejegyzes szb = SzotarBejegyzes.newInstance( ujNyelv.getLang(), key, label );
      importSession.importEntity( szb );
    }
  }

  private void importUjNyelvNeveiKorabbiNyelveken ( RdbImportSession importSession, Nyelv ujNyelv, String ujNyelvIgyHivjaMagat, List letezoNyelvek ) throws RdbException, DIIException
  {
    for ( int i = 0; i < letezoNyelvek.size(); ++i ) {
      Nyelv letezoNyelv = ( Nyelv ) letezoNyelvek.get( i );
      String key = NyelvImpl.BUNDLE_PREFIX + ujNyelv.getLang();
      SzotarBejegyzes importSzb = SzotarBejegyzes.newInstance( letezoNyelv.getLang(), key );
      try {
        importSzb.read( getSourceSession() );
      }
      catch ( RdbSession.NoResultException ex ) {
        importSzb.setValuestr( ujNyelvIgyHivjaMagat );
      }
      importSession.importEntity( importSzb );
    }
  }

  private void importOrszagokNeveiUjNyelven ( RdbImportSession importSession, Nyelv ijNyelv, GlobalSzotar sourceSzotar ) throws RdbException, DIIException
  {
    List currentOrszagok = Orszag.loadAll( getSourceSession() );
    for ( int i = 0; i < currentOrszagok.size(); ++i ) {
      Orszag orszag = ( Orszag ) currentOrszagok.get( i );
      String key = OrszagImpl.BUNDLE_PREFIX + orszag.getCountry();
      String label = sourceSzotar.hasKey( key ) ? sourceSzotar.getValue( key ) : currentSzotar.getValue( key );
      SzotarBejegyzes szb = SzotarBejegyzes.newInstance( ijNyelv.getLang(), key, label );
      importSession.importEntity( szb );
    }
  }

  private List getSourceBejegyzesek ( Nyelv ujNyelv ) throws RdbException
  {
    List sourceBejegyzesek = SzotarBejegyzes.loadAllForNyelv( getSourceSession(), ujNyelv );
    List result = new List( sourceBejegyzesek.size() );
    for ( int i = 0; i < sourceBejegyzesek.size(); ++i ) {
      SzotarBejegyzes sourceBejegyzes = ( SzotarBejegyzes ) sourceBejegyzesek.get( i );
      String key = sourceBejegyzes.getKeystr();
      if ( !key.startsWith( NyelvImpl.BUNDLE_PREFIX ) && !key.startsWith( OrszagImpl.BUNDLE_PREFIX ) ) {
        result.add( sourceBejegyzes );
      }
    }
    return result;
  }

  public void setCurrentSzotar ( GlobalSzotar currentSzotar )
  {
    this.currentSzotar = currentSzotar;
  }

}
