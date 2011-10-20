package scs.georesults.logic.actions.importing;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.io.IOException;
import scs.javax.io.InputStream;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.mapping.ClassMapping;
import scs.javax.rdb.mapping.MappingPool;
import scs.javax.rdb.mapping.PrimitiveRdbCondition;
import scs.javax.rdb.pseudosession.RdbEntityCondition;
import scs.javax.rdb.pseudosession.RdbXmlEntitySession;
import scs.javax.rdb.pseudosession.RestrictedSqlSession;
import scs.javax.rdb.rdbxml.RdbXmlUtils;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.javax.xml.XmlDomException;
import scs.georesults.logic.beans.importing.ImportSource;
import scs.georesults.om.kozos.NyelvImpl;
import scs.georesults.om.kozos.SzotarBejegyzesImpl;
import scs.georesults.om.verseny.Verseny;
import scs.georesults.om.verseny.VersenyImpl;
import scs.georesults.logic.actions.*;

public class ChooseSourceAction extends ImportActionBase
{

  public String importService ( DynamicForm form ) throws WebException, IOException, DIIException, RdbException
  {
    List entities;
    String srcType = form.getString( "srcType" );
    ImportSource importSource;
    if ( srcType == null ) {
      throw new ImportException( "ER_IMPORTALASI_HIBA" );
    } else if ( srcType.equals( "file" ) ) {
      InputStream in = form.getInputStream( "src_file_filename" );
      entities = getEntitiesFromFile( in );
      importSource = initSessionFromList( entities );
    } else if ( srcType.equals( "db" ) ) {
      long vid = form.getLong( "src_db_verseny" );
      importSource = initSessionFromDb( vid );
    } else throw new ImportException( "ER_IMPORTALASI_HIBA" );
    importSource.init( getBean() );
    getBean().setSourceSubbean( importSource );
    return "ok";
  }

  private List getEntitiesFromFile ( InputStream in ) throws WebException, RdbException, DIIException, IOException
  {
    try {
      return RdbXmlUtils.readEntitiesFromXml( in );
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
    catch ( XmlDomException ex ) {
      throw new ImportException( "IF_HIBAS_FORRASFAJL" );
    }
  }

  private ImportSource initSessionFromList ( List entities ) throws RdbException, WebException, DIIException
  {
    RdbXmlEntitySession entitySession = new RdbXmlEntitySession( entities );
    List versenyek = Verseny.loadAll( entitySession );
    VersenyImpl verseny;
    if ( versenyek.size() == 0 ) {
      verseny = null;
    } else if ( versenyek.size() == 1 ) {
      verseny = ( VersenyImpl ) versenyek.get( 0 );
    } else throw new ImportException( "IF_HIBAS_FORRASFAJL_TOBB_VERSENY" );
    return new ImportSource( entitySession, verseny );
  }

  private ImportSource initSessionFromDb ( long vid ) throws RdbException, WebException, DIIException
  {
    ClassMapping versenyCm = MappingPool.getClassMapping( VersenyImpl.class );
    RdbEntityCondition conditionForVerseny = new RdbEntityCondition( VersenyImpl.class );
    conditionForVerseny.addCondition( new PrimitiveRdbCondition( versenyCm.getField( "vid" ), new Long( vid ) ) );
    RestrictedSqlSession restSession = new RestrictedSqlSession( getDb() );
    restSession.addRestriction( conditionForVerseny );
    restSession.addRestriction( new RestrictedSqlSession.UnsatisfiableCondition( NyelvImpl.class ) );
    restSession.addRestriction( new RestrictedSqlSession.UnsatisfiableCondition( SzotarBejegyzesImpl.class ) );
    VersenyImpl sourceVerseny = ( VersenyImpl ) Verseny.newInstance( vid );
    sourceVerseny.read( restSession );
    return new ImportSource( restSession, sourceVerseny );
  }

}
