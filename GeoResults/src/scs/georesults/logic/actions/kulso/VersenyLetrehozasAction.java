package scs.georesults.logic.actions.kulso;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.io.IOException;
import scs.javax.lang.Date;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbImportSession;
import scs.javax.rdb.mapping.ClassMapping;
import scs.javax.rdb.mapping.MappingPool;
import scs.javax.rdb.rdbxml.RdbXmlUtils;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.javax.xml.XmlDomException;
import scs.georesults.GeoException;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaLista;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaResz;
import scs.georesults.config.ConfigUtils;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.om.alap.DarabFuggoFeladatTipusImpl;
import scs.georesults.om.alap.SorrendFuggoFeladatTipusImpl;
import scs.georesults.om.verseny.Etap;
import scs.georesults.om.verseny.Verseny;

/**
 * <p>Egy új versenyt létrehozó szolgáltatás osztálya.</p>
 */
public class VersenyLetrehozasAction extends VersenyValasztoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   * A művelethez az importálási funkciókat alkalmazza.
   */
  public String serve ( DynamicForm form ) throws WebException, RdbException
  {
    try {
      String sablonFilename = form.getString( "sablon" );
      if ( sablonFilename == null || sablonFilename.length() == 0 ) {
        throw new GeoMessageException( "ER_HIBAS_SABLON_VALASZTAS" );
      }
      List entities = RdbXmlUtils.readEntitiesFromXml( ConfigUtils.getConfigFilePath( sablonFilename ) );
      Verseny v = findVerseny( entities );
      updateVerseny( v, form.getString( "nev" ) );
      RdbImportSession importSession = new RdbImportSession( getDb() );
      importSession.importEntities( entities );
      updateMenetlevelFormulak( entities, importSession );
      versenySessionbe( v );
      return "ok";
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
    catch ( IOException ex ) {
      throw new GeoException( ex );
    }
    catch ( XmlDomException ex ) {
      throw new GeoException( ex );
    }
  }

  /**
   * Megkeresi az importálandó elemek között az első verseny objektumot.
   */
  private Verseny findVerseny ( List entities )
  {
    for ( int i = 0; i < entities.size(); ++i ) {
      if ( entities.get( i ) instanceof Verseny ) {
        return ( Verseny ) entities.get( i );
      }
    }
    return null;
  }

  /**
   * Frissíti a verseny objektum nevét a paraméterbenmegadottra, valamint a dátumait az aktuális dátumra.
   */
  private void updateVerseny ( Verseny v, String nev ) throws RdbException, WebException
  {
    v.setNev( nev );
    v.setKezdeteDatum( new Date() );
    v.setVegeDatum( new Date() );
  }

  /**
   * A versenyhez, valamint az etapjaihoz tartozó menetlevél-formulákat frissíti a megadott módon.
   *
   * @param entities A versenyt illetve az etapokat tartalmazó lista
   * @param importSession A frissítéshez szükséges adatok, az importálás folyamat objektuma.
   */
  private void updateMenetlevelFormulak ( List entities, RdbImportSession importSession ) throws DIIException, WebException, RdbException
  {
    for ( int i = 0; i < entities.size(); ++i ) {
      if ( entities.get( i ) instanceof Verseny ) {
        Verseny v = ( Verseny ) entities.get( i );
        v.setMenetlevelformula( processMenetlevelFormula( v.getMenetlevelformula(), importSession ) );
        v.update( getDb() );
      } else if ( entities.get( i ) instanceof Etap ) {
        Etap e = ( Etap ) entities.get( i );
        if ( e.getMenetlevelformula() != null ) {
          e.setMenetlevelformula( processMenetlevelFormula( e.getMenetlevelformula(), importSession ) );
          e.update( getDb() );
        }
      }
    }
  }

  /**
   * A verseny menetlevél-formuláját frissíti az importálás után.
   *
   * @param importSession Az importálási folyamat objektuma, a módosított azonosítókat tartalmazza
   * @param menetlevelformula Az eredeti menetlevél-formula
   * @return A módosított menetlevél-formula
   */
  private String processMenetlevelFormula ( String formula, RdbImportSession importSession ) throws DIIException
  {
    ClassMapping darabCM = MappingPool.getClassMapping( DarabFuggoFeladatTipusImpl.class );
    ClassMapping sorrendCM = MappingPool.getClassMapping( SorrendFuggoFeladatTipusImpl.class );
    MenetlevelFormulaLista lista = new MenetlevelFormulaLista( formula );
    for ( int i = 0; i < lista.size(); ++i ) {
      MenetlevelFormulaResz resz = lista.get( i );
      if ( resz.getMode() == MenetlevelFormulaResz.MODE_DARAB ) {
        Long newId = ( Long ) importSession.getNewId( darabCM, new Long( resz.getEfid() ) );
        resz.setEfid( newId.longValue() );
      } else if ( resz.getMode() == MenetlevelFormulaResz.MODE_SORREND ) {
        Long newId = ( Long ) importSession.getNewId( sorrendCM, new Long( resz.getEfid() ) );
        resz.setEfid( newId.longValue() );
      }
    }
    return lista.toString();
  }

}
