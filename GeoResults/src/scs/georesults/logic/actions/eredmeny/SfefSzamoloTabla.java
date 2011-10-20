package scs.georesults.logic.actions.eredmeny;

import scs.georesults.common.Constants;
import scs.georesults.om.eredmeny.SorrendFuggoFeladatKiertekeles;
import scs.georesults.om.etap.SorrendFuggoEtapFeladatEtalonBejegyzes;
import scs.georesults.om.menetlevel.SorrendFuggoMenetlevelBejegyzes;

/**
 * <p>Ez az osztály a sorrendfüggő feladatok etalonja és a menetlevelek bejegyzései
 * mint két sorozat leghosszabb közös részsorozatát számítja ki.</p>
 * <p>Természetesen nem csak a közös részsorozatot állítja elő, hanem egy olyan sorozatot,
 * amely a két bemeneti sorozat "unióját" tartalmazza abban az értelemben, ahogy a közös
 * részsorozat metszetnek tekinthető.</p>
 */
public class SfefSzamoloTabla
{

  /**
   * Az etalon bejegyzései
   */
  private SorrendFuggoEtapFeladatEtalonBejegyzes[] etalon;

  /**
   * Az etap azonosítója
   */
  private long eid;

  /**
   * A sorrend-függő feladattípus azonosítója
   */
  private long sfftid;

  /**
   * A tábla oszlopainak száma, vagyis az etalon bejegyzéseinek száma.
   */
  private int xsize;

  /**
   * A tábla sorainak száma
   */
  private int ysize;

  /**
   * A számításhoz felhasznált tábla
   */
  private Cell[][] table;

  /**
   * Létrehoz egy új szomlótábla objektumot a paraméterek alapján.
   *
   * @param eid Az etap azonosítója
   * @param sfftid A feladattípus azonosítója
   * @param etalon Az etalon bejegyzéseit tartalmazó lista
   * @param bevitelCount A tábla sorainak várható száma
   */
  public SfefSzamoloTabla ( long eid, long sfftid, SorrendFuggoEtapFeladatEtalonBejegyzes.Lista etalon, int bevitelCount )
  {
    this.eid = eid;
    this.sfftid = sfftid;
    this.etalon = new SorrendFuggoEtapFeladatEtalonBejegyzes[etalon.size()];
    for ( int i = 0; i < etalon.size(); ++i )this.etalon[i] = ( SorrendFuggoEtapFeladatEtalonBejegyzes ) etalon.get( i );
    this.xsize = etalon.size();
    this.ysize = bevitelCount;
    initTable();
  }

  /**
   * Inicializálja a számolótáblát, és üres cellákkal tölti fel.
   */
  private void initTable ()
  {
    this.table = new Cell[xsize + 1][];
    for ( int i = 0; i <= xsize; ++i ) {
      table[i] = new Cell[ysize + 1];
      for ( int j = 0; j <= ysize; ++j ) table[i][j] = new Cell( 0, Cell.A_UNKNOWN );
    }
    for ( int i = 1; i <= xsize; ++i ) table[i][0].arrow = Cell.A_LEFT;
    for ( int j = 1; j <= ysize; ++j ) table[0][j].arrow = Cell.A_UP;
    table[0][0].arrow = Cell.A_OK;
  }

  /**
   * Elvégzi a kiértékelési műveletet.
   *
   * @param bejegyzesLista A menetlevél adott feladathoz tartozó bejegyzéseinek listája
   * @param eredmenyLista Egy olyan lista, amely az eredmény-objektumokat fogja tartalmazni visszatéréskor
   * @param rajtszam A menetlevél rajtszáma
   */
  public synchronized void kiertekel ( SorrendFuggoMenetlevelBejegyzes.Lista bejegyzesLista, SorrendFuggoFeladatKiertekeles.Lista eredmenyLista, int rajtszam )
  {
    SorrendFuggoMenetlevelBejegyzes[] bejegyzesek = new SorrendFuggoMenetlevelBejegyzes[bejegyzesLista.size()];
    for ( int i = 0; i < bejegyzesLista.size(); ++i ) bejegyzesek[i] = ( SorrendFuggoMenetlevelBejegyzes ) bejegyzesLista.get( i );
    int currentYsize = bejegyzesek.length;
    if ( currentYsize > ysize ) enlargeTo( currentYsize, rajtszam );
    eredetiKiertekeles( currentYsize, bejegyzesek );
    SorrendFuggoFeladatKiertekeles[] eredmeny = kiolvasas( currentYsize, bejegyzesek, rajtszam );
    for ( int i = 0; i < eredmeny.length; ++i ) eredmenyLista.add( eredmeny[i] );
  }

  /**
   * Az eredeti algoritmus alapján kitölti a táblázatot.
   *
   * @param currentYsize A használandó sorok száma
   * @param bejegyzesek A menetlevélnek a feladathoz tartozó bejegyzései
   */
  private void eredetiKiertekeles ( int currentYsize, SorrendFuggoMenetlevelBejegyzes[] bejegyzesek )
  {
    for ( int i = 1; i <= xsize; ++i ) {
      for ( int j = 1; j <= currentYsize; ++j ) {
        if ( etalon[i - 1].getFelirat().equals( bejegyzesek[j - 1].getFelirat() ) ) {
          table[i][j].arrow = Cell.A_OK;
          table[i][j].count = table[i - 1][j - 1].count + 1;
        } else {
          if ( table[i - 1][j].count <= table[i][j - 1].count ) {
            table[i][j].arrow = Cell.A_UP;
            table[i][j].count = table[i][j - 1].count;
          } else {
            table[i][j].arrow = Cell.A_LEFT;
            table[i][j].count = table[i - 1][j].count;
          }
        }
      }
    }
  }

  /**
   * Kiolvassa a táblázatból a végeredményt. Ha az etalon olyan bejegyzést tartalmaz, amely
   * már nem érvényes, akkor az eredménylistán ezt törölt állaptúnak jelzi.
   *
   * @param currentYsize A használandó sorok száma
   * @param bejegyzesek A menetlevélnek a feladathoz tartozó bejegyzései
   * @param rajtszam A menetlevél rajtszáma
   * @return A kiértékelést reprezentáló objektumok tömbje.
   */
  private SorrendFuggoFeladatKiertekeles[] kiolvasas ( int currentYsize, SorrendFuggoMenetlevelBejegyzes[] bejegyzesek, int rajtszam )
  {
    int allCount = xsize + currentYsize - table[xsize][currentYsize].count;
    SorrendFuggoFeladatKiertekeles[] result = new SorrendFuggoFeladatKiertekeles[allCount];
    int i = xsize;
    int j = currentYsize;
    while ( allCount > 0 ) {
      --allCount;
      result[allCount] = SorrendFuggoFeladatKiertekeles.newInstance( eid, rajtszam, sfftid, allCount );
      if ( table[i][j].arrow == Cell.A_OK ) {
        result[allCount].setEtalonFelirat( etalon[--i].getFelirat() );
        result[allCount].setMenetlevelFelirat( bejegyzesek[--j].getFelirat() );
        if ( etalon[i].isErvenyes() ) {
          result[allCount].setTipus( Constants.FELADATKIERTEKELES_MEGEGYEZIK );
        } else {
          result[allCount].setTipus( Constants.FELADATKIERTEKELES_TOROLT );
        }
      } else if ( table[i][j].arrow == Cell.A_LEFT ) {
        result[allCount].setEtalonFelirat( etalon[--i].getFelirat() );
        result[allCount].setMenetlevelFelirat( null );
        if ( etalon[i].isErvenyes() ) {
          result[allCount].setTipus( Constants.FELADATKIERTEKELES_MENETLEVELEN_HIANY );
        } else {
          result[allCount].setTipus( Constants.FELADATKIERTEKELES_TOROLT );
        }
      } else if ( table[i][j].arrow == Cell.A_UP ) {
        result[allCount].setEtalonFelirat( null );
        result[allCount].setMenetlevelFelirat( bejegyzesek[--j].getFelirat() );
        result[allCount].setTipus( Constants.FELADATKIERTEKELES_MENETLEVELEN_TOBBLET );
      }
    }
    return result;
  }

  /**
   * Ha a táblázatnak a menetlevél-formula alapján számított mérete nem lenne elég, akkor ez a művelet megnöveli a tábla méretét.
   *
   * @param currentYsize A sorok szükséges száma
   * @param rajtszam A hibát kiváltó menetlevél rajtszáma.
   */
  private void enlargeTo ( int currentYsize, int rajtszam )
  {
    System.err.println( "Valaki több bejegyzsést vitt fel egy menetlevélre (rajtszam=" + rajtszam + ",sfftid=" + sfftid + "), mint amennyit a formula alapján lehet. Ejnye bejnye." );
    this.ysize = currentYsize;
    initTable();
  }

  /**
   * <p>Az {@link SfefSzamoloTabla} osztály táblájának egy cellája.</p>
   */
  private static class Cell
  {

    /**
     * Azt jelzi, hogy az adott cella nem tartalmaz nyilat,
     * vagyis a számított érték explicit.
     */
    public static final int A_UNKNOWN = 0;

    /**
     * Azt jelzi, hogy a nyíl balra felfelé mutat, vagyis
     * a cellához tartozó indexeken megegyezik a két sorozat eleme.
     */
    public static final int A_OK = 1;

    /**
     * Azt jelzi, hogy a nyíl balra mutat, vagyis
     * a bal oldali szomszéd cella értéke nem kisebb, mint a fenti szomszédé.
     */
    public static final int A_LEFT = 2;

    /**
     * Azt jelzi, hogy a nyíl felfelé mutat, vagyis
     * a fenti szomszéd cella értéke nem kisebb, mint a bal oldali szomszédé.
     */
    public static final int A_UP = 3;

    /**
     * Azt mutatja meg, hogy ha a cella a leghosszabb közös
     * részsorozat eleme lenne, akkor a részsorozaton belül hányadik elem.
     */
    public int count;

    /**
     * Azt mutatja meg, hogy az adott cella melyik irányból
     * származtatja a <code>count</code> értékét. Erre az
     * értékre úgy is hivatkozunk, hogy "merre mutat a cellába
     * rajzolt nyíl".
     */
    public int arrow;

    public Cell ( int count, int arrow )
    {
      this.count = count;
      this.arrow = arrow;
    }

  }

}
