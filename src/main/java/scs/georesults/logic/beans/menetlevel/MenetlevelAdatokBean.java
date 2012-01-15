package scs.georesults.logic.beans.menetlevel;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.javax.web.request.InvalidRequestFieldException;
import scs.georesults.GeoException;
import scs.georesults.logic.beans.AdatbevitelBean;
import scs.georesults.om.alap.BuntetesTipus;
import scs.georesults.om.etap.DarabFuggoEtapFeladat;
import scs.georesults.om.etap.DarabFuggoEtapFeladatEtalonBejegyzes;
import scs.georesults.om.etap.SorrendFuggoEtapFeladatImpl;
import scs.georesults.om.menetlevel.*;
import scs.georesults.om.verseny.EtapImpl;
import scs.georesults.om.verseny.VersenyImpl;

/**
 * <p>A menetlevelek adatfelvitelét segítő osztály.</p>
 */
public class MenetlevelAdatokBean extends AdatbevitelBean
{

  /**
   * Létrehoz egy új objektumot az adott versenyhez kapcsolódóan.
   */
  public MenetlevelAdatokBean ( VersenyImpl verseny ) throws GeoException
  {
    super( verseny );
    setBetoltes( Menetlevel.newInstance( 0, 0 ) );
  }

  /**
   * A kapott paraméterek alapján feltölti a menetlevél büntetéseket tartalmazó részét.
   *
   * @param form A paramétereket tartalmazó objektum
   */
  public void fillBuntetesek ( DynamicForm form ) throws GeoException, InvalidRequestFieldException, RdbException
  {
    Menetlevel m = ( Menetlevel ) getFutam();
    m.getBuntetesek().clear();
    List buntetesTipusok = getVerseny().getBuntetesTipusok();
    for ( int i = 0; i < buntetesTipusok.size(); ++i ) {
      BuntetesTipus bt = ( BuntetesTipus ) buntetesTipusok.get( i );
      int darab = form.getInteger( "buntetes_" + bt.getBtid() );
      Buntetes b = Buntetes.newInstance( m.getEid(), m.getRajtszam(), bt.getBtid(), darab );
      m.getBuntetesek().add( b );
    }
  }

  /**
   * A kapott paraméterek alapján feltölti a menetlevél megadott darabszám-függő feladathoz tartozó tartalmazó részét.
   *
   * @param form A paramétereket tartalmazó objektum
   * @param dfftid A feladat-típus azonosítója
   */
  public void fillDarabFuggoBejegyzesek ( DynamicForm form, long dfftid ) throws GeoException, InvalidRequestFieldException, RdbException
  {
    try {
      Menetlevel m = ( Menetlevel ) getFutam();
      DarabFuggoFeladatMegoldas dfmo = ( DarabFuggoFeladatMegoldas ) m.getDarabFuggoMegoldasok().findItem( "dfftid", new Long( dfftid ) );
      if ( dfmo == null ) {
        dfmo = DarabFuggoFeladatMegoldas.newInstance( m.getEid(), m.getRajtszam(), dfftid );
        m.getDarabFuggoMegoldasok().add( dfmo );
      }
      dfmo.getBejegyzesek().clear();
      List etalon = getDarabFuggoEtalon( m.getEid(), dfftid );
      for ( int i = 0; i < etalon.size(); ++i ) {
        DarabFuggoEtapFeladatEtalonBejegyzes dfefeb = ( DarabFuggoEtapFeladatEtalonBejegyzes ) etalon.get( i );
        int darab = form.getInteger( "dfef_" + dfftid + "_" + i );
        DarabFuggoMenetlevelBejegyzes dfmb = DarabFuggoMenetlevelBejegyzes.newInstance( m.getEid(), m.getRajtszam(), dfftid, dfefeb.getPosindex(), darab );
        dfmo.getBejegyzesek().add( dfmb );
      }
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
  }

  /**
   * A kapott paraméterek alapján feltölti a menetlevél megadott sorrend-függő feladathoz tartozó tartalmazó részét.
   *
   * @param form A paramétereket tartalmazó objektum
   * @param sfftid A feladat-típus azonosítója
   */
  public void fillSorrendFuggoBejegyzesek ( DynamicForm form, long sfftid ) throws GeoException, InvalidRequestFieldException, RdbException
  {
    try {
      Menetlevel m = ( Menetlevel ) getFutam();
      SorrendFuggoFeladatMegoldas sfmo = ( SorrendFuggoFeladatMegoldas ) m.getSorrendFuggoMegoldasok().findItem( "sfftid", new Long( sfftid ) );
      if ( sfmo == null ) {
        sfmo = SorrendFuggoFeladatMegoldas.newInstance( m.getEid(), m.getRajtszam(), sfftid );
        m.getSorrendFuggoMegoldasok().add( sfmo );
      }
      sfmo.getBejegyzesek().clear();
      if ( getSorrendFuggoEtapFeladat( m.getEid(), sfftid ).isReszletesBevitel() ) {
        sfmo.setHelyes( null );
        sfmo.setHibas( null );
        for ( int i = 0; ; ++i ) {
          String fieldName = "felirat_" + sfftid + "_" + i;
          if ( !form.has( fieldName ) )break;
          String felirat = form.getOptionalString( fieldName );
          if ( felirat == null )break;
          SorrendFuggoMenetlevelBejegyzes sfmlb = SorrendFuggoMenetlevelBejegyzes.newInstance( m.getEid(), m.getRajtszam(), sfftid, i, felirat );
          sfmo.getBejegyzesek().add( sfmlb );
        }
      } else {
        sfmo.setHelyes( new Integer( form.getInteger( "helyes_" + sfftid ) ) );
        sfmo.setHibas( new Integer( form.getInteger( "hibas_" + sfftid ) ) );
      }
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
  }

  /**
   * Egy adott etap egy bizonyos sorrend-függő feladattípushoz
   * tartozó feladatát reprezentáló objektumot adja vissza.
   *
   * @param eid Az etap azonosítója
   * @param sfftid A feladattípus azonosítója
   * @return A talált objektum
   */
  private SorrendFuggoEtapFeladatImpl getSorrendFuggoEtapFeladat ( long eid, long sfftid ) throws GeoException, DIIException, RdbException
  {
    EtapImpl etap = ( EtapImpl ) getVerseny().getEtapok().findItem( "eid", new Long( eid ) );
    return ( SorrendFuggoEtapFeladatImpl ) etap.getSorrendFuggoEtapFeladatok().findItem( "sfftid", new Long( sfftid ) );
  }

  /**
   * A kapott paraméterek alapján feltölti a menetlevél idő-adatokat tartozó tartalmazó részét.
   *
   * @param form A paramétereket tartalmazó objektum
   */
  public void fillIdo ( DynamicForm form ) throws InvalidRequestFieldException
  {
    Menetlevel m = ( Menetlevel ) getFutam();
    m.setIndulasiIdo( form.getTime( "indulasiIdo" ) );
    m.setErkezesiIdo( form.getTime( "erkezesiIdo" ) );
  }

  /**
   * Egy adott etap egy bizonyos darabszám-függő feladattípushoz
   * tartozó feladatának etalonját, mint etalon-bejegyzések listáját
   * adja vissza.
   *
   * @param eid Az etap azonosítója
   * @param dfftid A feladattípus azonosítója
   * @return A feladat etalon-bejegyzéseinek listája
   */
  private List getDarabFuggoEtalon ( long eid, long dfftid ) throws GeoException, DIIException, RdbException
  {
    EtapImpl etap = ( EtapImpl ) getVerseny().getEtapok().findItem( "eid", new Long( eid ) );
    DarabFuggoEtapFeladat dfef = ( DarabFuggoEtapFeladat ) etap.getDarabFuggoEtapFeladatok().findItem( "dfftid", new Long( dfftid ) );
    return dfef.getBejegyzesek();
  }

  /**
   * A menetlevél formuláját leíró stringet adja vissza. Ha van az aktuális menetlevél által
   * kijelölt etapon definiálva formula akkor azt, különben a versenyen érvényben levő
   * alapformulát adja meg.
   */
  public String getAbsoluteMenetlevelFormula () throws GeoException, DIIException, RdbException
  {
    Menetlevel m = ( Menetlevel ) getFutam();
    EtapImpl etap = ( EtapImpl ) getVerseny().getEtapok().findItem( "eid", new Long( m.getEid() ) );
    return etap.getAbszolutMenetlevelformula();
  }

}
