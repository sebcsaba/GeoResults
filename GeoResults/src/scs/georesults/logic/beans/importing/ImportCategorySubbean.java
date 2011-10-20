package scs.georesults.logic.beans.importing;

import javax.servlet.http.HttpServletRequest;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.dii.MethodUtils;
import scs.javax.dii.PropertyUtils;
import scs.javax.rdb.*;
import scs.javax.web.WebException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.ValueLabelPair;
import scs.georesults.common.szotar.VersenyResolver;
import scs.georesults.common.szotar.VersenySzotar;
import scs.georesults.logic.actions.importing.ImportException;
import scs.georesults.om.alap.VersenySzotarBejegyzes;
import scs.georesults.om.alap.VersenySzotarBejegyzesImpl;
import scs.georesults.om.kozos.Nyelv;
import scs.georesults.om.verseny.VersenyImpl;

public abstract class ImportCategorySubbean
{

  protected ImportBean importBean;

  public void init ( ImportBean importBean )
  {
    this.importBean = importBean;
  }

  public abstract int getMaximumBranchSteps ();

  public abstract String getImportOrAssignTypenamesKey ();

  public abstract void doImport ( RdbImportSession importSession, HttpServletRequest request ) throws WebException, RdbException, DIIException;

  public abstract void checkForAlapnyelvInvariant () throws GeoException, RdbException, DIIException;

  public abstract void checkForNyelvOrszag () throws GeoException, RdbException, DIIException;

  public abstract List getRecogniserItems ( VersenySzotar currentVsz ) throws DIIException, RdbException, GeoException;

  protected RdbSession getSourceSession ()
  {
    return importBean.getSourceSubbean().getSession();
  }

  protected VersenyImpl getSourceVerseny ()
  {
    return importBean.getSourceSubbean().getVerseny();
  }

  protected VersenySzotar getSourceVersenySzotar () throws RdbException, GeoException
  {
    return importBean.getSourceSubbean().getVersenySzotar();
  }

  protected List loadEntitesForVerseny ( Class clazz ) throws DIIException
  {
    Object[] params = new Object[] {getSourceSession(), getSourceVerseny()};
    return ( List ) MethodUtils.invokeStaticMethodAnyType( clazz, "loadAllForVerseny", params );
  }

  protected void importVszbk ( RdbImportSession importSession, VersenySzotar sourceVsz, long oldVszbid, long newVszbid ) throws GeoException, RdbException, DIIException
  {
    importSession.addRemap( VersenySzotarBejegyzes.class, oldVszbid, newVszbid );
    VersenySzotar.SzoForditasokkal szf = sourceVsz.getSzoForditasokkal( VersenySzotar.getKeyFromVszbid( oldVszbid ) );
    long currentVid = importBean.getCurrentVerseny().getVid();
    List nyelvek = Nyelv.loadAll( GeoDbSession.getCurrentInstance() );
    for ( int i = 0; i < nyelvek.size(); ++i ) {
      Nyelv nyelv = ( Nyelv ) nyelvek.get( i );
      if ( szf.isVanIlyenNyelven( nyelv.getLang() ) ) {
        VersenySzotarBejegyzes vszb = VersenySzotarBejegyzes.newInstance( currentVid, newVszbid, nyelv.getLang() );
        vszb.setFelirat( szf.getFeliratAdottNyelven( nyelv.getLang() ) );
        importSession.importEntity( vszb );
      }
    }
  }

  protected void importMultilangEntity ( RdbImportSession importSession, StorableEntityBase entity, String multilangPropertyName, VersenySzotar sourceVsz ) throws DIIException, RdbException, GeoException
  {
    String name = ( String ) PropertyUtils.getProperty( entity, multilangPropertyName );
    if ( VersenyResolver.shouldBeResolved( name ) ) {
      Long oldVszbid = new Long( VersenySzotar.getVszbidFromKey( name ) );
      Long newVszbid = importSession.getNewId( VersenySzotarBejegyzesImpl.class, oldVszbid );
      if ( newVszbid == null ) {
        newVszbid = new Long( VersenySzotarBejegyzesImpl.getNexyKey( GeoDbSession.getCurrentInstance(), importBean.getCurrentVerseny() ) );
        importVszbk( importSession, sourceVsz, oldVszbid.longValue(), newVszbid.longValue() );
      }
      PropertyUtils.setProperty( entity, multilangPropertyName, VersenySzotar.getKeyFromVszbid( newVszbid.longValue() ) );
    }
    importSession.importEntity( entity );
  }

  protected void importMultilangEntities ( RdbImportSession importSession, List src, String multilangPropertyName, VersenySzotar sourceVsz, Long currentVid ) throws DIIException, RdbException, GeoException
  {
    if ( currentVid != null ) src.setAll( "vid", currentVid );
    for ( int i = 0; i < src.size(); ++i ) {
      StorableEntityBase entity = ( StorableEntityBase ) src.get( i );
      importMultilangEntity( importSession, entity, multilangPropertyName, sourceVsz );
    }
  }

  protected void importSimpleEntities ( RdbImportSession importSession, List src, Long currentVid ) throws RdbException, DIIException
  {
    if ( currentVid != null ) src.setAll( "vid", currentVid );
    for ( int i = 0; i < src.size(); ++i ) {
      StorableEntityBase entity = ( StorableEntityBase ) src.get( i );
      importSession.importEntity( entity );
    }
  }

  protected List getOptionsList ( List entities, String labelProperty, String idProperty, VersenySzotar currentVsz ) throws DIIException
  {
    List results = new List();
    for ( int i = 0; i < entities.size(); ++i ) {
      StorableEntityBase entity = ( StorableEntityBase ) entities.get( i );
      String label = ( String ) PropertyUtils.getProperty( entity, labelProperty );
      if ( VersenyResolver.shouldBeResolved( label ) ) label = currentVsz.getValue( label );
      Long id = ( Long ) PropertyUtils.getProperty( entity, idProperty );
      results.add( new ValueLabelPair( id.longValue(), label ) );
    }
    return results;
  }

  protected Long matchLabels ( String label, List options )
  {
    for ( int i = 0; i < options.size(); ++i ) {
      ValueLabelPair vlp = ( ValueLabelPair ) options.get( i );
      if ( vlp.getLabel().equals( label ) ) {
        return new Long( vlp.getValue() );
      }
    }
    return null;
  }

  protected void addToRecogniserItemsAllEntities ( List recogniserItems, List importEntities, List currentEntities, VersenySzotar importVsz, VersenySzotar currentVsz, Class entityClass, String labelPropertyName, String idPropertyName ) throws RdbException, GeoException, DIIException
  {
    List currentEntityOptions = getOptionsList( currentEntities, labelPropertyName, idPropertyName, currentVsz );
    for ( int i = 0; i < importEntities.size(); ++i ) {
      StorableEntityBase entity = ( StorableEntityBase ) importEntities.get( i );
      String label = ( String ) PropertyUtils.getProperty( entity, labelPropertyName );
      if ( VersenyResolver.shouldBeResolved( label ) ) label = importVsz.getValue( label );
      Long id = ( Long ) PropertyUtils.getProperty( entity, idPropertyName );
      Long def = matchLabels( label, currentEntityOptions );
      recogniserItems.add( new RecogniseItem( label, entityClass, id.longValue(), currentEntityOptions, def ) );
    }
  }

  protected void checkForAlapnyelvInvariantBase ( Object entity, String labelProperyName, VersenySzotar sourceVsz, String currentAlapNyelv ) throws DIIException, ImportException
  {
    String name = ( String ) PropertyUtils.getProperty( entity, labelProperyName );
    if ( VersenyResolver.shouldBeResolved( name ) ) {
      if ( !sourceVsz.getSzoForditasokkal( name ).isVanIlyenNyelven( currentAlapNyelv ) ) {
        throw new ImportException( "ER_FORDITAS_NEM_LETEZIK_AZ_ALAPNYELVEN" );
      }
    }
  }

  protected void checkForAlapnyelvInvariantBase ( List entities, String labelProperyName, VersenySzotar sourceVsz, String currentAlapNyelv ) throws DIIException, ImportException
  {
    for ( int i = 0; i < entities.size(); ++i ) {
      checkForAlapnyelvInvariantBase( entities.get( i ), labelProperyName, sourceVsz, currentAlapNyelv );
    }
  }

}
