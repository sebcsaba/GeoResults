package scs.georesults.common.menetlevelformula;

import scs.javax.collections.List;
import scs.javax.utils.StringUtils;

/**
 * <p>A formula egészét reprezentálja, a részeket reprezentáló
 * {@link scs.georesults.common.menetlevelformula.MenetlevelFormulaResz}
 * típusú objektumokból álló listaként.</p>
 */
public class MenetlevelFormulaLista
{

  /**
   * A részeket tartalmazó lista.
   */
  private List lista;

  /**
   * Létrehoz egy új, üres listát.
   */
  public MenetlevelFormulaLista ()
  {
    lista = new List();
  }

  /**
   * Létrehoz egy új listát a paraméterben megadott string által reprezentált részekkel.
   * A string felépítését lásd a {@link toString()} metódusnál.
   *
   * @param formula A listát leíró formula
   */
  public MenetlevelFormulaLista ( String formula )
  {
    List src = StringUtils.tokenize( formula, ";", false );
    lista = new List( src.size() );
    for ( int i = 0; i < src.size(); ++i ) {
      lista.add( new MenetlevelFormulaResz( ( String ) src.get( i ) ) );
    }
  }

  /**
   * Visszaadja az adott indexhez tartozó rész-objektumot.
   *
   * @param index A lista egy indexe
   * @return A megadott indexű elem
   */
  public MenetlevelFormulaResz get ( int index )
  {
    return ( MenetlevelFormulaResz ) lista.get( index );
  }

  /**
   * Egy új részt ad a lista végéhez.
   *
   * @param item Az új elem
   * @return Igaz, ha sikerült a hozzáadás
   */
  public boolean add ( MenetlevelFormulaResz item )
  {
    return lista.add( item );
  }

  /**
   * Visszaadja a lista elemeinek számát.
   *
   * @return Az elemek száma
   */
  public int size ()
  {
    return lista.size();
  }

  /**
   * Eltávolírja a lista adott indexű elemét.
   *
   * @param index Az elem indexe
   */
  public void remove ( int index )
  {
    lista.remove( index );
  }

  /**
   * Előállítja a listát leíró stringet.
   * A string tartalmazza a részeket leíró stringek
   * konkatenációját, pontosvessző karakterrel elválasztva.
   *
   * @return A formátum-leíró string
   */
  public String toString ()
  {
    String[] parts = new String[lista.size()];
    for ( int i = 0; i < parts.length; ++i ) parts[i] = lista.get( i ).toString();
    return StringUtils.joinStrings( parts, ";" );
  }

  /**
   * Az adott azonosítójú darabszám-függő feladattípust reprezentáló rész
   * listában felvett indexét adja vissza.
   *
   * @param efid A feladattípus azonosítója
   * @return Az elem indexe, vagy -1 ha nem talált megfelelőt
   */
  public int findDarabIndex ( long efid )
  {
    for ( int i = 0; i < lista.size(); ++i ) {
      MenetlevelFormulaResz mfr = ( MenetlevelFormulaResz ) lista.get( i );
      if ( mfr.getMode() == MenetlevelFormulaResz.MODE_DARAB && mfr.getEfid() == efid )return i;
    }
    return -1;
  }

  /**
   * Az adott azonosítójú sorrend-függő feladattípust reprezentáló rész
   * listában felvett indexét adja vissza.
   *
   * @param efid A feladattípus azonosítója
   * @return Az elem indexe, vagy -1 ha nem talált megfelelőt
   */
  public int findSorrendIndex ( long efid )
  {
    for ( int i = 0; i < lista.size(); ++i ) {
      MenetlevelFormulaResz mfr = ( MenetlevelFormulaResz ) lista.get( i );
      if ( mfr.getMode() == MenetlevelFormulaResz.MODE_SORREND && mfr.getEfid() == efid )return i;
    }
    return -1;
  }

  /**
   * A listából eltávolítja az összes darabszám-függő feladattípust reprezentáló elemet.
   */
  public void removeAllDarab ()
  {
    removeAllForMode( MenetlevelFormulaResz.MODE_DARAB );
  }

  /**
   * A listából eltávolítja az összes sorrend-függő feladattípust reprezentáló elemet.
   */
  public void removeAllSorrend ()
  {
    removeAllForMode( MenetlevelFormulaResz.MODE_SORREND );
  }

  /**
   * A listából eltávolítja az összes, a megadott típushoz tartozó elemet.
   *
   * @param mode A típus azonosítója
   */
  private void removeAllForMode ( int mode )
  {
    for ( int i = 0; i < lista.size(); ) {
      MenetlevelFormulaResz mlfr = ( MenetlevelFormulaResz ) lista.get( i );
      if ( mlfr.getMode() == mode ) {
        lista.remove( i );
      } else {
        ++i;
      }
    }
  }

}
