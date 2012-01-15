package scs.georesults.common.menetlevelformula;

import scs.javax.collections.List;
import scs.javax.utils.StringUtils;

/**
 * <p>A formula egy részét reprezentálja. Tartalmazza a típusát,
 * és a típusspecifikus adatokat. Minden részt egyértelműen leír
 * egy string. Az adatbázisban ilyen stringek konkatenációjaként
 * tároljuk a formulát.</p>
 */
public class MenetlevelFormulaResz
{

  // "i"
  /**
   * Az időadatokat reprezentáló rész típusának azonosítója.
   */
  public static final int MODE_TIME = 0;

  // "d:{efid}:[p|i]"
  /**
   * Egy darabszám-függő feladattípus adatait reprezentáló rész típusának azonosítója.
   */
  public static final int MODE_DARAB = 1;

  // "s:{efid}:[p|i]:{count}"
  /**
   * Egy sorrend-függő feladattípus adatait reprezentáló rész típusának azonosítója.
   */
  public static final int MODE_SORREND = 2;

  // "b:[p|i]"
  /**
   * A büntetési adatokat reprezentáló rész típusának azonosítója.
   */
  public static final int MODE_BUNTETES = 3;


  /**
   * A rész típusát határozza meg. Lehetséges értékei a <tt>MODE_*</tt> mezők által megadottak.
   */
  private int mode;

  /**
   * Darab- vagy sorrend-függő feladattípust reprezentáló rész esetén a feladattípus azonosítója.
   */
  private long efid;

  /**
   * Azt mutatja meg, hogy az adatbeviteli blokk egy felugró ablakban jelenjen-e meg.
   */
  private boolean popup;

  /**
   * Sorrend-függő feladattípus esetén azt adja meg, hogy mennyi beviteli mezőt biztosítson a részletes adatbeviteli ablakon.
   */
  private int count;


  /**
   * A megadott leíróstring alapján létrehozza részt.
   * A string felépítését lásd a {@link toString()} metódusnál.
   *
   * @param formulaResz A részt leíró formula
   */
  public MenetlevelFormulaResz ( String formulaResz )
  {
    List data = StringUtils.tokenize( formulaResz, ":", false );
    String type = ( String ) data.get( 0 );
    if ( type.equals( "i" ) ) {
      this.mode = MODE_TIME;
      this.popup = false;
    } else {
      String pop;
      if ( type.equals( "b" ) ) {
        this.mode = MODE_BUNTETES;
        pop = ( String ) data.get( 1 );
      } else {
        if ( type.equals( "s" ) ) {
          this.mode = MODE_SORREND;
          this.efid = Long.parseLong( ( String ) data.get( 1 ) );
        } else if ( type.equals( "d" ) ) {
          this.mode = MODE_DARAB;
          this.efid = Long.parseLong( ( String ) data.get( 1 ) );
        } else throw new IllegalArgumentException( formulaResz );
        pop = ( String ) data.get( 2 );
      }
      if ( pop.equals( "p" ) ) {
        this.popup = true;
      } else if ( pop.equals( "i" ) ) {
        this.popup = false;
      } else throw new IllegalArgumentException( formulaResz );
      if ( mode == MODE_SORREND ) {
        this.count = Integer.parseInt( ( String ) data.get( 3 ) );
      }
    }
  }

  /**
   * Létrehoz egy új, időadatokat reprezentáló részt.
   */
  public MenetlevelFormulaResz ()
  {
    this.mode = MODE_TIME;
  }

  /**
   * Létrehoz egy új, büntetési adatokat reprezentáló részt.
   *
   * @param popup Azt mutatja, hogy felugró ablakot definiálunk-e
   */
  public MenetlevelFormulaResz ( boolean popup )
  {
    this.mode = MODE_BUNTETES;
    this.popup = popup;
  }

  /**
   * Létrehoz egy új, darabszám-függő feladattípus adatait reprezentáló részt.
   *
   * @param efid A feladattípus azonosítója
   * @param popup Azt mutatja, hogy felugró ablakot definiálunk-e
   */
  public MenetlevelFormulaResz ( long efid, boolean popup )
  {
    this.mode = MODE_DARAB;
    this.efid = efid;
    this.popup = popup;
  }

  /**
   * Létrehoz egy új, sorrend-függő feladattípus adatait reprezentáló részt.
   *
   * @param efid A feladattípus azonosítója
   * @param popup Azt mutatja, hogy felugró ablakot definiálunk-e
   * @param count A megjelenítendő beviteli mezők száma
   */
  public MenetlevelFormulaResz ( long efid, boolean popup, int count )
  {
    this.mode = MODE_SORREND;
    this.efid = efid;
    this.popup = popup;
    this.count = count;
  }

  /**
   * Előállítja az adott részt leíró stringet. A string formátuma a következő:
   * <ul>
   * <li> időadatokat reprezentáló rész esetén <tt>"i"</tt></li>
   * <li> büntetési adatokat reprezentáló rész esetén <tt>"b:[p|i]"</tt></li>
   * <li> darabszám-függő feladattípus adatait reprezentáló rész esetén <tt>"d:{efid}:[p|i]"</tt></li>
   * <li> sorrend-függő feladattípus adatait reprezentáló rész esetén <tt>"s:{efid}:[p|i]:{count}"</tt></li>
   * </ul>
   * A szögletes zárójelben, <tt>|</tt> jellel elválaszott karakterek közül
   * mindig csak egy szerepelhet, ahol <tt>"p"</tt> a felugró ablakot,
   * míg <tt>"i"</i> az űrlapon közvetlenül megjelenő adatblokkot jelenti.
   * A kapcsos zárójelek helyén egy-egy szám állhat, <tt>efid</tt> esetén a
   * feladattípus azonosítója, <tt>count</tt> esetén a megjelenítendő beviteli
   * mezők számát. Például a 3-as azonosítójú darabszám-függő feladattípushoz
   * felugró ablakot rendelő rész <tt>"d:3:p"</tt> stringgel írható le.
   *
   * @return A formátum-leíró string
   */
  public String toString ()
  {
    StringBuffer sb = new StringBuffer();
    switch ( mode ) {
      case MODE_TIME:
        sb.append( "i" ); break;
      case MODE_DARAB:
        sb.append( "d" ); break;
      case MODE_SORREND:
        sb.append( "s" ); break;
      case MODE_BUNTETES:
        sb.append( "b" ); break;
    }
    if ( mode == MODE_SORREND || mode == MODE_DARAB ) {
      sb.append( ":" ).append( efid );
    }
    if ( mode != MODE_TIME ) {
      sb.append( ":" ).append( popup ? "p" : "i" );
    }
    if ( mode == MODE_SORREND ) {
      sb.append( ":" ).append( count );
    }
    return sb.toString();
  }

  /**
   * A rész típusát adja vissza.
   *
   * @return A típus
   */
  public int getMode ()
  {
    return mode;
  }

  /**
   * A rész feladattípusának azonosítóját adja vissza.
   *
   * @return A feladattípus azonosítója
   */
  public long getEfid ()
  {
    return efid;
  }

  /**
   * Visszaadja, hogy az adott rész felugró ablakot határoz-e meg.
   *
   * @return Igaz, ha felugró ablakot határoz meg
   */
  public boolean isPopup ()
  {
    return popup;
  }

  /**
   * Visszaadja a megjelenítendő beviteli mezők számát.
   *
   * @return A beviteli mezők száma
   */
  public int getCount ()
  {
    return count;
  }

  /**
   * Beállítja a megjelenítendő beviteli mezők számát.
   *
   * @param count A beviteli mezők új darabszáma.
   */
  public void setCount ( int count )
  {
    this.count = count;
  }

  /**
   * Beállítja a rész feladattípusának azonosíóját.
   *
   * @param efid Az új feladattípus azonosítója.
   */
  public void setEfid ( long efid )
  {
    this.efid = efid;
  }

  /**
   * A részt azonosító stringet ad vissza. Ez a rész leíróstringjének
   * első karakterét tartalmazza, továbbá sorrend- vagy darabszám-függő
   * feladattípus eseén a feladattípus azonosítóját.
   *
   * @return A rész azonosító-stringje
   */
  public String getId ()
  {
    switch ( mode ) {
      case MODE_TIME:
        return "i";
      case MODE_BUNTETES:
        return "b";
      case MODE_DARAB:
        return "d:" + efid;
      case MODE_SORREND:
        return "s:" + efid;
    }
    throw new NullPointerException( "mode=" + mode );
  }

}
