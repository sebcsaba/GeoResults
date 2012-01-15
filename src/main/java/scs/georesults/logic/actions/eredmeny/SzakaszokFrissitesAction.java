package scs.georesults.logic.actions.eredmeny;

import java.util.HashMap;
import java.util.Map;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.WebException;
import scs.georesults.common.SzakaszElem;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.om.eredmeny.*;
import scs.georesults.om.nevezes.NevezesImpl;
import scs.georesults.om.verseny.Etap;
import scs.georesults.om.verseny.SzakaszImpl;
import scs.georesults.om.verseny.Szlalom;

/**
 * <p>A szakaszok eredménylistáját frissítő szolgáltatás osztálya.</p>
 */
public class SzakaszokFrissitesAction extends SzakaszElemFrissitesActionBase
{

  protected boolean kellSzakaszFrissites ()
  {
    return false;
  }

  protected List getElemList () throws RdbException, WebException
  {
    return getVerseny().getSzakaszok();
  }

  protected String getIdField ()
  {
    return "szid";
  }

  protected void szakaszElemFrissites ( SzakaszElem szakaszElem ) throws DIIException, RdbException, WebException
  {
    SzakaszImpl szakasz = ( SzakaszImpl ) szakaszElem;
    List etapok = szakasz.getEtapok();
    List szlalomok = szakasz.getSzlalomok();
    List nevezesek = getVerseny().getNevezesek();
    checkAllSzakaszElemEredmenyExists( etapok );
    checkAllSzakaszElemEredmenyExists( szlalomok );
    deleteAllCurrentEredmeny( szakasz );
    createSzlalomOsszesitettEredmenyek( szakasz, szlalomok, nevezesek );
    if ( szakasz.isSzlalomRedukaltPontokkal() ) {
      updateSzlalomOsszesitettEredmenyekPontredukalas( szakasz, szlalomok, nevezesek );
    }
    createSzakaszEredmenyek( szakasz, etapok, nevezesek );
    szakasz.read( getDb() );
    szakasz.setEredmenyFrissitendo( false );
    szakasz.update( getDb() );
  }

  /**
   * Előállítja a megadott szakaszhoz tartozó szlalomok összesített
   * eredménylistáját. A lista minden versenyzőhöz tartalamzni fog
   * egy {@link SzlalomOsszesitettEredmeny} típusú objektumot.
   */
  private void createSzlalomOsszesitettEredmenyek ( SzakaszImpl szakasz, List szlalomok, List nevezesek ) throws WebException, RdbException
  {
    for ( int i = 0; i < nevezesek.size(); ++i ) {
      NevezesImpl nevezes = ( NevezesImpl ) nevezesek.get( i );
      SzlalomOsszesitettEredmeny szloe = SzlalomOsszesitettEredmeny.newInstance( szakasz.getSzid(), nevezes.getRajtszam() );
      szloe.setVid( szakasz.getVid() );
      szloe.setKategoria( nevezes.getAuto().getKategoria() );
      int pont = 0;
      for ( int j = 0; j < szlalomok.size(); ++j ) {
        Szlalom szlalom = ( Szlalom ) szlalomok.get( j );
        if ( szlalom.isErtekelendo() ) {
          SzlalomEredmeny szle = SzlalomEredmeny.newInstance( szlalom.getSzlid(), nevezes.getRajtszam() );
          szle.read( getDb() );
          pont += szle.getPontszam();
        }
      }
      szloe.setPontszam( pont );
      szloe.setAtvittPontszam( pont );
      szloe.create( getDb() );
    }
  }

  /**
   * Az adott szakaszhoz tartozó szlalomversenyek összesített eredménylistáján
   * kiszámítja a redukált pontszámokat, vagyis a kategória legjobbjától
   * vett különbséget.
   */
  private void updateSzlalomOsszesitettEredmenyekPontredukalas ( SzakaszImpl szakasz, List szlalomok, List nevezesek ) throws WebException, RdbException
  {
    Map kategoriaMinimumok = new HashMap();
    List szloek = SzlalomOsszesitettEredmeny.loadAllForSzakasz( getDb(), szakasz );
    for ( int i = 0; i < szloek.size(); ++i ) {
      SzlalomOsszesitettEredmeny szloe = ( SzlalomOsszesitettEredmeny ) szloek.get( i );
      Long katId = new Long( szloe.getKategoria() );
      Integer minPont = ( Integer ) kategoriaMinimumok.get( katId );
      if ( minPont == null || ( szloe.getPontszam() < minPont.intValue() ) ) {
        kategoriaMinimumok.put( katId, new Integer( szloe.getPontszam() ) );
      }
    }
    for ( int i = 0; i < szloek.size(); ++i ) {
      SzlalomOsszesitettEredmeny szloe = ( SzlalomOsszesitettEredmeny ) szloek.get( i );
      Long katId = new Long( szloe.getKategoria() );
      Integer minPont = ( Integer ) kategoriaMinimumok.get( katId );
      szloe.setAtvittPontszam( szloe.getPontszam() - minPont.intValue() );
      szloe.update( getDb() );
    }
  }

  /**
   * Előállítja a megadott szakasz összesített
   * eredménylistáját. A lista minden versenyzőhöz tartalamzni fog
   * egy {@link SzakaszEredmeny} típusú objektumot.
   */
  private void createSzakaszEredmenyek ( SzakaszImpl szakasz, List etapok, List nevezesek ) throws WebException, RdbException
  {
    int maxKesesSzakaszon = getMaxKesesSzakaszon( szakasz, etapok );
    for ( int i = 0; i < nevezesek.size(); ++i ) {
      NevezesImpl nevezes = ( NevezesImpl ) nevezesek.get( i );
      SzakaszEredmenyImpl sze = ( SzakaszEredmenyImpl ) SzakaszEredmeny.newInstance( szakasz.getSzid(), nevezes.getRajtszam() );
      sze.setVid( szakasz.getVid() );
      int pont = 0;
      int kesesSzakaszon = 0;
      for ( int j = 0; j < etapok.size(); ++j ) {
        Etap etap = ( Etap ) etapok.get( j );
        if ( etap.isErtekelendo() ) {
          EtapEredmeny ee = EtapEredmeny.newInstance( etap.getEid(), nevezes.getRajtszam() );
          ee.read( getDb() );
          pont += ee.getPontszam();
          kesesSzakaszon += ee.getKesesPerc();
        }
      }
      pont += sze.getSzlalomOsszesitettEredmeny().getAtvittPontszam();
      boolean voltSzakaszKeses = kesesSzakaszon > maxKesesSzakaszon;
      sze.setKeses( voltSzakaszKeses );
      pont += ( voltSzakaszKeses ? szakasz.getKesesertBuntetoPont() : 0 );
      sze.setPontszam( pont );
      sze.create( getDb() );
    }
  }

  /**
   * Megadja a szakaszon engedélyezett maximális késést, percben.
   */
  private int getMaxKesesSzakaszon ( SzakaszImpl szakasz, List etapok )
  {
    int etapCount = 0;
    for ( int i = 0; i < etapok.size(); ++i )if ( ( ( Etap ) etapok.get( i ) ).isErtekelendo() )++etapCount;
    return szakasz.getMegengedettKesesEtaponkent() * etapCount;
  }

  /**
   * Ellenőrzi, hogy az összes forrásadat rendelkezésre áll-e.
   */
  private void checkAllSzakaszElemEredmenyExists ( List szakaszElemek ) throws GeoMessageException
  {
    for ( int i = 0; i < szakaszElemek.size(); ++i ) {
      SzakaszElem szakaszElem = ( SzakaszElem ) szakaszElemek.get( i );
      if ( szakaszElem.isEredmenyFrissitendo() ) {
        throw new GeoMessageException( "ER_ALAPADAT_FFRISSITENDO" );
      }
    }
  }

  /**
   * Törli az adatbázisból az összes, az adott szakasz összesített eredményét tartalmazó objektumot.
   */
  protected void deleteAllCurrentEredmeny ( SzakaszElem szakaszElem ) throws WebException, RdbException
  {
    List szakaszEredmenyek = SzakaszEredmeny.loadAllForSzakasz( getDb(), ( SzakaszImpl ) szakaszElem );
    for ( int i = 0; i < szakaszEredmenyek.size(); ++i ) {
      StorableEntityBase entity = ( StorableEntityBase ) szakaszEredmenyek.get( i );
      entity.delete( getDb() );
    }
    List szlalomOsszesitettEredmenyek = SzlalomOsszesitettEredmeny.loadAllForSzakasz( getDb(), ( SzakaszImpl ) szakaszElem );
    for ( int i = 0; i < szlalomOsszesitettEredmenyek.size(); ++i ) {
      StorableEntityBase entity = ( StorableEntityBase ) szlalomOsszesitettEredmenyek.get( i );
      entity.delete( getDb() );
    }
  }

}
