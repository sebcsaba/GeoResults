package scs.georesults.logic.beans.etap;

import scs.javax.rdb.StorableEntityBase;
import scs.georesults.Config;
import scs.georesults.logic.beans.ReszAdatBean;

/**
 * <p>Az etapok részegységeinek kezelésénél használt segédosztály.</p>
 */
public class EtapReszAdatBean extends ReszAdatBean
{

  /**
   * A megjelenítendő bejegyzések darabszáma
   */
  private int darab;

  /**
   * A beviteli táblán megjelenő alapértelmezett üres bejegyzés. Ez azért kell, mert a tábla általában nagyobb, mint a benne tárolt bejegyzések (létrehozáskor nincs is bejegyzés), és a megjelenítés
   * ilyenkor ezt az objektumot használja fel.
   */
  private Object defBejegyzes;

  /**
   * Az osztály működését kiegészítő extra objektum. Csak sorrend-függő feladattípus
   * esetén használt, ilyenkor egy {@link SorrendFuggoExtra} típusú objektumot tartalmaz.
   */
  private Object extra;

  /**
   * Létrehoz egy új objektumot a megadott paraméterek alapján. Az extra objektum kezdetben <code>null</code>, a darabszám pedig a konfigurációs osztályban ({@link scs.georesults.common.Constants})
   * megadott értéket veszi fel.
   *
   * @param resz A becsomagolt adatobjektum
   * @param create Igaz, ha létrehozás művelet céljából jön létre
   * @param defBejegyzes Az alapértelemzett bejegyzés
   */
  public EtapReszAdatBean ( StorableEntityBase resz, boolean create, Object defBejegyzes )
  {
    super( resz, create );
    this.darab = Config.ETAP_RESZ_ADAT_LISTA_HOSSZ;
    this.defBejegyzes = defBejegyzes;
  }

  public Object getDefBejegyzes ()
  {
    return defBejegyzes;
  }

  public void setDefBejegyzes ( Object defBejegyzes )
  {
    this.defBejegyzes = defBejegyzes;
  }

  public int getDarab ()
  {
    return darab;
  }

  public void setDarab ( int darab )
  {
    this.darab = darab;
  }

  public Object getExtra ()
  {
    return extra;
  }

  public void setExtra ( Object extra )
  {
    this.extra = extra;
  }

}
