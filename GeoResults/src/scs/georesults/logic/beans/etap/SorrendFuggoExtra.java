package scs.georesults.logic.beans.etap;

import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.om.alap.SorrendFuggoFeladatTipus;
import scs.georesults.om.etap.SorrendFuggoEtapFeladat;
import scs.georesults.om.etap.SorrendFuggoEtapFeladatImpl;
import scs.georesults.om.menetlevel.Menetlevel;
import scs.georesults.om.verseny.EtapImpl;

/**
 * <p>Egy etapnál sorrend-függő feladat adminisztrálásakor felhasznált segédosztály. A
 * CRUD műveletekhez használt {@link scs.georesults.logic.beans.ReszAdatBean}
 * osztály leszármazottja, a {@link EtapReszAdatBean} osztály extra mezőjébe
 * kerül ennek az osztálynak egy példánya.</p>
 */
public class SorrendFuggoExtra
{

  /**
   * A feladatot tartalmazó etap.
   */
  private EtapImpl etap;

  /**
   * A feladat típusa
   */
  private SorrendFuggoFeladatTipus sfft;

  /**
   * A feladat objektuma
   */
  private SorrendFuggoEtapFeladatImpl sfef;

  /**
   * Egy új objektumot hoz létre a megadott paraméterekkel
   *
   * @param etap A feladatot tartalmazó etap.
   * @param sfft A feladat típusa
   * @param sfef A feladat objektuma
   */
  public SorrendFuggoExtra ( EtapImpl etap, SorrendFuggoFeladatTipus sfft, SorrendFuggoEtapFeladat sfef )
  {
    this.etap = etap;
    this.sfft = sfft;
    this.sfef = ( SorrendFuggoEtapFeladatImpl ) sfef;
  }

  /**
   * A feladat nevét (pontosabban a típusának nevét) adja vissza
   */
  public String getNev ()
  {
    return sfft.getNev();
  }

  /**
   * A feladat típusának azonosítóját adja meg.
   */
  public long getSfftid ()
  {
    return sfef.getSfftid();
  }

  /**
   * A feladathoz tartozó adatok bevitele során használt ellenőrzési típust
   * adja meg. Ha az adott feladatnál definiálva van, akkor azt adja vissza.
   * Ha nincs, akkor a feladat típusánál megadott értéket adja, 1000-rel megnövelve.
   */
  public int getEllenorzesTipus ()
  {
    if ( sfef.getEllenorzesTipus() == null ) {
      return sfft.getEllenorzesTipus() + 1000;
    } else {
      return sfef.getEllenorzesTipus().intValue();
    }
  }

  /**
   * A feladat típusánál definiált ellenőrzési típust adja meg.
   */
  public int getEllenorzesTipusParent ()
  {
    return sfft.getEllenorzesTipus();
  }

  /**
   * A feladat típusát adja vissza.
   */
  public SorrendFuggoFeladatTipus getSfft ()
  {
    return sfft;
  }

  /**
   * A feladat objektumát adja vissza
   */
  public SorrendFuggoEtapFeladat getSfef ()
  {
    return sfef;
  }

  /**
   * Megadja, hogy a feladathoz tartozó etaphoz már van-e felvitt menetlevél.
   */
  public boolean isVanMenetlevel () throws GeoException, RdbException
  {
    return Menetlevel.loadAllForEtap( GeoDbSession.getCurrentInstance(), etap ).size() > 0;
  }

}
