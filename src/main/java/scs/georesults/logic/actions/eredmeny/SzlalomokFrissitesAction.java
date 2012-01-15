package scs.georesults.logic.actions.eredmeny;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.WebException;
import scs.georesults.common.SzakaszElem;
import scs.georesults.om.alap.AutoTipus;
import scs.georesults.om.alap.SzlalomFeladat;
import scs.georesults.om.alap.SzlalomKategoria;
import scs.georesults.om.eredmeny.SzlalomEredmeny;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.szlalom.SzlalomBejegyzes;
import scs.georesults.om.szlalom.SzlalomFutam;
import scs.georesults.om.verseny.SzlalomImpl;

/**
 * <p>A szlalomok eredménylistáját frissítő szolgáltatás osztálya.</p>
 */
public class SzlalomokFrissitesAction extends SzakaszAlapElemFrissitesActionBase
{

  protected boolean kellSzakaszFrissites ()
  {
    return true;
  }

  protected List getElemList () throws RdbException, WebException
  {
    return getVerseny().getSzlalomok();
  }

  protected List loadAllCurrendEredmeny ( SzakaszElem szakaszElem ) throws WebException, RdbException
  {
    return SzlalomEredmeny.loadAllForSzlalom( getDb(), ( SzlalomImpl ) szakaszElem );
  }

  protected String getIdField ()
  {
    return "szlid";
  }

  protected List getSzakaszElemhezFutamLista ( SzakaszElem szakaszElem ) throws WebException, RdbException
  {
    return SzlalomFutam.loadAllForSzlalom( getDb(), ( SzlalomImpl ) szakaszElem );
  }

  protected StorableEntityBase futamKiertekeles ( SzakaszElem szakaszElem, StorableEntityBase futam ) throws RdbException, DIIException, WebException
  {
    SzlalomFutam szf = ( SzlalomFutam ) futam;
    SzlalomEredmeny sze = SzlalomEredmeny.newInstance( szf.getSzlid(), szf.getRajtszam() );
    sze.setVid( szakaszElem.getVid() );
    sze.setPontszam( pontKalkulacio( getVerseny().getSzlalomFeladatok(), szf.getBejegyzesek() ) );
    sze.setKategoria( getKategoria( szf.getRajtszam() ) );
    return sze;
  }

  /**
   * A paraméterben megadott rajtszámú versenyző autójához tartozó kategóriának az azonosítóját adja meg.
   */
  private long getKategoria ( int rajtszam ) throws WebException, DIIException, RdbException
  {
    Nevezes n = ( Nevezes ) getVerseny().getNevezesek().findItem( "rajtszam", new Integer( rajtszam ) );
    AutoTipus autoTipus = ( AutoTipus ) getVerseny().getAutoTipusok().findItem( "atid", new Long( n.getAutoTipus() ) );
    SzlalomKategoria szk = ( SzlalomKategoria ) getVerseny().getSzlalomKategoriak().findItem( "szkid", new Long( autoTipus.getKategoria() ) );
    return szk.getSzkid();
  }

  /**
   * A versenyhez tartozó szlalom-feladatok listája és a szlalom-futamhoz felvitt bejegyzések alapján kiszámítja a futam pontszámát.
   */
  private int pontKalkulacio ( List szlalomFeladatok, SzlalomBejegyzes.Lista bejegyzesek ) throws DIIException
  {
    int pont = 0;
    for ( int i = 0; i < bejegyzesek.size(); ++i ) {
      SzlalomBejegyzes szb = ( SzlalomBejegyzes ) bejegyzesek.get( i );
      if ( szb.getDarab() > 0 ) {
        SzlalomFeladat szf = ( SzlalomFeladat ) szlalomFeladatok.findItem( "szfid", new Long( szb.getSzfid() ) );
        pont += szb.getDarab() * szf.getPont();
      }
    }
    return pont;
  }

}
