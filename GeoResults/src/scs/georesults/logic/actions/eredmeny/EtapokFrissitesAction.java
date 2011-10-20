package scs.georesults.logic.actions.eredmeny;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.lang.Time;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.WebException;
import scs.georesults.GeoException;
import scs.georesults.common.Constants;
import scs.georesults.common.SzakaszElem;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaLista;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.om.alap.*;
import scs.georesults.om.eredmeny.*;
import scs.georesults.om.etap.*;
import scs.georesults.om.menetlevel.*;
import scs.georesults.om.verseny.EtapImpl;

/**
 * <p>A szlalomok eredménylistáját frissítő szolgáltatás osztálya.</p>
 */
public class EtapokFrissitesAction extends SzakaszAlapElemFrissitesActionBase
{

  protected boolean kellSzakaszFrissites ()
  {
    return true;
  }

  protected List getElemList () throws RdbException, WebException
  {
    return getVerseny().getEtapok();
  }

  protected List loadAllCurrendEredmeny ( SzakaszElem szakaszElem ) throws WebException, RdbException
  {
    return EtapEredmeny.loadAllForEtap( getDb(), ( EtapImpl ) szakaszElem );
  }

  protected String getIdField ()
  {
    return "eid";
  }

  protected List getSzakaszElemhezFutamLista ( SzakaszElem szakaszElem ) throws WebException, RdbException
  {
    return Menetlevel.loadAllForEtap( getDb(), ( EtapImpl ) szakaszElem );
  }

  /**
   * Kiértékeli az adott menetlevél eredményét. Az eredményt tartalmazó,
   * {@link EtapEredmeny} típusú objektumot adja vissza.
   */
  protected StorableEntityBase futamKiertekeles ( SzakaszElem szakaszElem, StorableEntityBase futam ) throws RdbException, DIIException, WebException
  {
    EtapImpl etap = ( EtapImpl ) szakaszElem;
    Menetlevel ml = ( Menetlevel ) futam;
    EtapEredmeny ee = EtapEredmeny.newInstance( ml.getEid(), ml.getRajtszam() );
    ee.setVid( szakaszElem.getVid() );
    int pont = 0;
    ee.setKesesPerc( kesesPercKalkulacio( etap, ml ) );
    ee.setKesesPont( kesesPontKalkulacio( getVerseny().getKesesiZonak(), ee.getKesesPerc() ) );
    ee.setBuntetoPont( buntetoPontKalkulacio( getVerseny().getBuntetesTipusok(), ml.getBuntetesek() ) );
    for ( int i = 0; i < etap.getDarabFuggoEtapFeladatok().size(); ++i ) {
      DarabFuggoEtapFeladatImpl dfef = ( DarabFuggoEtapFeladatImpl ) etap.getDarabFuggoEtapFeladatok().get( i );
      DarabFuggoFeladatMegoldas dffmo = ( DarabFuggoFeladatMegoldas ) ml.getDarabFuggoMegoldasok().findItem( "dfftid", new Long( dfef.getDfftid() ) );
      if ( dffmo == null ) dffmo = DarabFuggoFeladatMegoldas.newInstance( ml.getEid(), ml.getRajtszam(), dfef.getDfftid() );
      pont += kiertekelDfef( dfef, ee, dffmo );
    }
    for ( int i = 0; i < etap.getSorrendFuggoEtapFeladatok().size(); ++i ) {
      SorrendFuggoEtapFeladatImpl sfef = ( SorrendFuggoEtapFeladatImpl ) etap.getSorrendFuggoEtapFeladatok().get( i );
      SorrendFuggoFeladatMegoldas sffmo = ( SorrendFuggoFeladatMegoldas ) ml.getSorrendFuggoMegoldasok().findItem( "sfftid", new Long( sfef.getSfftid() ) );
      if ( sffmo == null ) sffmo = SorrendFuggoFeladatMegoldas.newInstance( ml.getEid(), ml.getRajtszam(), sfef.getSfftid() );
      pont += kiertekelSfef( etap, sfef, ee, sffmo );
    }
    pont += ee.getBuntetoPont() + ee.getKesesPont();
    ee.setPontszam( pont );
    return ee;
  }

  /**
   * Az adott darabszám-függő feladat eredményét számolja ki.
   *
   * @param dfef A feladat objektuma
   * @param ee A futam eredményét reprezentáló objektum, amelynek megfelelő listájához az új részeredmény-objektumot hozzáadjuk
   * @param dffmo A menetlevélnek az adott feladatra vonatkozó bejegyzései
   * @return A feladatra kapott pontszám
   */
  private int kiertekelDfef ( DarabFuggoEtapFeladatImpl dfef, EtapEredmeny ee, DarabFuggoFeladatMegoldas dffmo ) throws DIIException, WebException, RdbException
  {
    int hianyAcc = 0;
    int tobbletAcc = 0;
    for ( int i = 0; i < dfef.getBejegyzesek().size(); ++i ) {
      DarabFuggoEtapFeladatEtalonBejegyzes etalonBejegzyes = ( DarabFuggoEtapFeladatEtalonBejegyzes ) dfef.getBejegyzesek().get( i );
      DarabFuggoMenetlevelBejegyzes menetlevelBejegyzes = ( DarabFuggoMenetlevelBejegyzes ) dffmo.getBejegyzesek().findItem( "posindex", new Integer( etalonBejegzyes.getPosindex() ) );
      int diff = ( menetlevelBejegyzes == null ? 0 : menetlevelBejegyzes.getDarab() ) - etalonBejegzyes.getDarab();
      if ( diff > 0 ) {
        tobbletAcc += diff;
      } else {
        hianyAcc -= diff;
      }
    }
    DarabFuggoFeladatEredmeny dffe = DarabFuggoFeladatEredmeny.newInstance( ee.getEid(), ee.getRajtszam(), dfef.getDfftid() );
    dffe.setHiany( hianyAcc );
    dffe.setTobblet( tobbletAcc );
    DarabFuggoFeladatTipus dfft = dfef.getDarabFuggoFeladatTipus();
    dffe.setPontszam( dfft.getHianyHibapont() * hianyAcc + dfft.getTobbletHibapont() * tobbletAcc );
    ee.getDarabFuggoEredmenyek().add( dffe );
    return dffe.getPontszam();
  }

  /**
   * Az adott sorrend-függő feladat eredményét számolja ki.
   * Ha részletes adatfelvitel történt, akkor a {@link SfefSzamoloTabla} osztály
   * egy példányát használja a koplex számítás elvégzésére.
   *
   * @param sfef A feladat objektuma
   * @param ee A futam eredményét reprezentáló objektum, amelynek megfelelő listájához az új részeredmény-objektumot hozzáadjuk
   * @param sffmo A menetlevélnek az adott feladatra vonatkozó bejegyzései
   * @return A feladatra kapott pontszám
   */
  private int kiertekelSfef ( EtapImpl etap, SorrendFuggoEtapFeladatImpl sfef, EtapEredmeny ee, SorrendFuggoFeladatMegoldas sffmo ) throws WebException, DIIException, RdbException
  {
    SorrendFuggoFeladatTipus sfft = sfef.getSorrendFuggoFeladatTipus();
    SorrendFuggoFeladatEredmeny sffe = SorrendFuggoFeladatEredmeny.newInstance( ee.getEid(), ee.getRajtszam(), sfef.getSfftid() );
    int hiany = 0;
    int tobblet = 0;
    if ( sfef.isReszletesBevitel() ) {
      SfefSzamoloTabla tabla = getSzamoloTabla( etap, sfef );
      tabla.kiertekel( sffmo.getBejegyzesek(), sffe.getKiertekeles(), ee.getRajtszam() );
      for ( int i = 0; i < sffe.getKiertekeles().size(); ++i ) {
        SorrendFuggoFeladatKiertekeles x = ( SorrendFuggoFeladatKiertekeles ) sffe.getKiertekeles().get( i );
        if ( x.getTipus() == Constants.FELADATKIERTEKELES_MENETLEVELEN_HIANY )++hiany;
        else if ( x.getTipus() == Constants.FELADATKIERTEKELES_MENETLEVELEN_TOBBLET )++tobblet;
      }
    } else {
      hiany = sfef.getDarab().intValue() - sffmo.getHelyes().intValue();
      tobblet = sffmo.getHibas().intValue();
      if ( hiany < 0 )throw new GeoMessageException( "ER_TUL_SOK_HELYES_BEJEGYZES" );
    }
    sffe.setTobblet( tobblet );
    sffe.setHiany( hiany );
    sffe.setPontszam( hiany * sfft.getHianyHibapont() + tobblet * sfft.getTobbletHibapont() );
    ee.getSorrendFuggoEredmenyek().add( sffe );
    return sffe.getPontszam();
  }

  /**
   * A megadott etaphoz és megadott feladathoz tartozó számolótáblát adja vissza. Ha még nem készült ilyen tábla a szolgáltatás jelenlegi futása során, kkor létrehozza.
   */
  private SfefSzamoloTabla getSzamoloTabla ( EtapImpl etap, SorrendFuggoEtapFeladat sfef ) throws DIIException, WebException, RdbException
  {
    String key = "szamolotabla_" + sfef.getSfftid() + "_" + sfef.getEid();
    SfefSzamoloTabla tabla = ( SfefSzamoloTabla ) getRequest().getAttribute( key );
    if ( tabla == null ) {
      MenetlevelFormulaLista formula = new MenetlevelFormulaLista( etap.getAbszolutMenetlevelformula() );
      int count = formula.get( formula.findSorrendIndex( sfef.getSfftid() ) ).getCount();
      tabla = new SfefSzamoloTabla( etap.getEid(), sfef.getSfftid(), sfef.getBejegyzesek(), count );
      getRequest().setAttribute( key, tabla );
    }
    return tabla;
  }

  /**
   * A versenyhez tartozó büntetés-típusok listája és a menetlevélhez felvitt
   * bejegyzések alapján kiszámítja a menetlevélhez tartozó büntetések pontszámát.
   */
  private int buntetoPontKalkulacio ( List buntetesTipusok, Buntetes.Lista buntetesek ) throws DIIException
  {
    int pont = 0;
    for ( int i = 0; i < buntetesek.size(); ++i ) {
      Buntetes b = ( Buntetes ) buntetesek.get( i );
      if ( b.getDarab() > 0 ) {
        BuntetesTipus bt = ( BuntetesTipus ) buntetesTipusok.findItem( "btid", new Long( b.getBtid() ) );
        pont += b.getDarab() * bt.getPont();
      }
    }
    return pont;
  }

  /**
   * Kiszámolja, hogy az adott etapon hány percet késett a menetlevél szerint a versenyző.
   */
  private int kesesPercKalkulacio ( EtapImpl etap, Menetlevel ml )
  {
    int ind = ml.getIndulasiIdo().toMinutes();
    int erk = ml.getErkezesiIdo().toMinutes();
    if ( erk < ind ) erk += Time.MINUTES_IN_A_DAY;
    return erk - ind - etap.getIdoLimit();
  }

  /**
   * Kiszámolja, hogy a megadott mértékű késésért mennyi hibapont jár a versenyzőnek.
   */
  private int kesesPontKalkulacio ( List kesesiZonak, int kesesPerc ) throws GeoException
  {
    if ( kesesPerc == 0 )return 0;
    int firstPositiveIndex = KesesiZonaImpl.findFirstPositiveIndex( kesesiZonak );
    int pont = 0;
    int prevIdoLimit = 0;
    if ( kesesPerc > 0 ) {
      for ( int i = firstPositiveIndex; i < kesesiZonak.size(); ++i ) {
        KesesiZona kz = ( KesesiZona ) kesesiZonak.get( i );
        if ( kesesPerc <= kz.getIdoLimit() ) {
          pont += ( kesesPerc - prevIdoLimit ) * kz.getPont();
          return pont;
        } else {
          pont += ( kz.getIdoLimit() - prevIdoLimit ) * kz.getPont();
          prevIdoLimit = kz.getIdoLimit();
        }
      }
    } else {
      for ( int i = firstPositiveIndex - 1; i >= 0; --i ) {
        KesesiZona kz = ( KesesiZona ) kesesiZonak.get( i );
        if ( kesesPerc >= kz.getIdoLimit() ) {
          pont += ( kesesPerc - prevIdoLimit ) * kz.getPont();
          return -pont;
        } else {
          pont += ( kz.getIdoLimit() - prevIdoLimit ) * kz.getPont();
          prevIdoLimit = kz.getIdoLimit();
        }
      }
    }
    return 0;
  }

}
