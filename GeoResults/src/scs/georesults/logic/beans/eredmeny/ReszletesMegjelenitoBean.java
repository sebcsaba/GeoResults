package scs.georesults.logic.beans.eredmeny;

import java.util.HashMap;
import java.util.Map;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.web.request.InvalidRequestFieldException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.om.eredmeny.EtapEredmeny;
import scs.georesults.om.etap.DarabFuggoEtapFeladat;
import scs.georesults.om.etap.SorrendFuggoEtapFeladat;
import scs.georesults.om.menetlevel.Menetlevel;
import scs.georesults.om.menetlevel.MenetlevelImpl;
import scs.georesults.om.verseny.EtapImpl;

/**
 * <p>A részletes eredménylista megjelenítését segítő osztály.</p>
 */
public class ReszletesMegjelenitoBean extends EredmenyBeanBase
{

  private Map adatok;

  /**
   * Inicializálja az objektumot a munkafolyamtban szereplő 'reszletesMain' bean
   * alapján, amely egy {@link ReszletesMainBean} típusú objektum.
   */
  public void init () throws GeoException, InvalidRequestFieldException, RdbException
  {
    ReszletesMainBean bean = ( ReszletesMainBean ) session.getAttribute( "reszletesMain" );
    processBean( bean );
  }

  /**
   * A kapott bean objektumot feldolgozza: minden etaphoz létrehoz egy megfelelő
   * {@link EtapAdat} objektumot.
   */
  private void processBean ( ReszletesMainBean bean ) throws GeoException, InvalidRequestFieldException, RdbException
  {
    adatok = new HashMap();
    for ( int i = 0; i < verseny.getEtapok().size(); ++i ) {
      EtapImpl etap = ( EtapImpl ) verseny.getEtapok().get( i );
      ReszletesMainBean.EtapFlags flags = ( ReszletesMainBean.EtapFlags ) bean.getEtapFlags().get( new Long( etap.getEid() ) );
      EtapAdat etapAdat = new EtapAdat();
      for ( int j = 0; j < etap.getDarabFuggoEtapFeladatok().size(); ++j ) {
        DarabFuggoEtapFeladat dfef = ( DarabFuggoEtapFeladat ) etap.getDarabFuggoEtapFeladatok().get( j );
        boolean detail = ( ( Boolean ) flags.getDfef().get( new Long( dfef.getDfftid() ) ) ).booleanValue();
        if ( detail ) {
          etapAdat.getReszletesDfefk().add( dfef );
        } else {
          etapAdat.getNemReszletesDfefk().add( dfef );
        }
      }
      for ( int j = 0; j < etap.getSorrendFuggoEtapFeladatok().size(); ++j ) {
        SorrendFuggoEtapFeladat sfef = ( SorrendFuggoEtapFeladat ) etap.getSorrendFuggoEtapFeladatok().get( j );
        boolean detail = ( ( Boolean ) flags.getSfef().get( new Long( sfef.getSfftid() ) ) ).booleanValue();
        if ( detail ) {
          etapAdat.getReszletesSfefk().add( sfef );
        } else {
          etapAdat.getNemReszletesSfefk().add( sfef );
        }
      }
      etapAdat.setEredmenyLista( EtapEredmeny.loadAllForEtap( getDb(), etap ) );
      adatok.put( new Long( etap.getEid() ), etapAdat );
    }
  }

  /**
   * A paraméterben kapott azonosítójá etap adatait adja vissza
   */
  public EtapAdat getForEtap ( long eid )
  {
    return ( EtapAdat ) adatok.get( new Long( eid ) );
  }

  /**
   * A paraméterben megadott etaphoz és adott rajtszámú versenyzőhöz tartozó menetlevelet adja vissza.
   */
  public Menetlevel getMenetlevel ( long eid, int rajtszam ) throws GeoException, RdbException, DIIException
  {
    MenetlevelImpl m = ( MenetlevelImpl ) Menetlevel.newInstance( eid, rajtszam );
    m.read( GeoDbSession.getCurrentInstance() );
    return m;
  }

  /**
   * A paraméterben megadott etaphoz és adott rajtszámú versenyzőhöz tartozó etap-eredményt adja vissza
   */
  public EtapEredmeny getEtapEredmeny ( long eid, int rajtszam ) throws GeoException, RdbException
  {
    EtapEredmeny ee = EtapEredmeny.newInstance( eid, rajtszam );
    ee.read( GeoDbSession.getCurrentInstance() );
    return ee;
  }

  /**
   * Megadja, hogy az adott etapon a megadott rajtszámú versenyző hanyadik helyzést ért el.
   */
  public int getHelyezes ( long eid, int rajtszam ) throws DIIException
  {
    EtapAdat etapAdat = ( EtapAdat ) adatok.get( new Long( eid ) );
    return 1 + etapAdat.getEredmenyLista().findItemIndex( "rajtszam", new Integer( rajtszam ) );
  }

  /**
   * <p>Egy etap megjelenítését szabályozó osztály. Külön listákban
   * tárolja a részletesen illetve nem részletesen megjelenítendő feladatokat.</p>
   */
  public static class EtapAdat
  {

    /**
     * A részletesen megjelenítendő darabszám-függő feladatok listája.
     */
    private List reszletesDfefk;

    /**
     * A nem részletesen megjelenítendő darabszám-függő feladatok listája.
     */
    private List nemReszletesDfefk;

    /**
     * A részletesen megjelenítendő sorrend-függő feladatok listája.
     */
    private List reszletesSfefk;

    /**
     * A nem részletesen megjelenítendő sorrend-függő feladatok listája.
     */
    private List nemReszletesSfefk;

    /**
     * Az etap eredményeinek listáját tartalmazza.
     */
    private List eredmenyLista;

    public EtapAdat ()
    {
      reszletesDfefk = new List();
      nemReszletesDfefk = new List();
      reszletesSfefk = new List();
      nemReszletesSfefk = new List();
    }

    public List getReszletesSfefk ()
    {
      return reszletesSfefk;
    }

    public List getReszletesDfefk ()
    {
      return reszletesDfefk;
    }

    public List getNemReszletesSfefk ()
    {
      return nemReszletesSfefk;
    }

    public List getNemReszletesDfefk ()
    {
      return nemReszletesDfefk;
    }

    public List getEredmenyLista ()
    {
      return eredmenyLista;
    }

    public void setEredmenyLista ( List eredmenyLista )
    {
      this.eredmenyLista = eredmenyLista;
    }

  }

}
