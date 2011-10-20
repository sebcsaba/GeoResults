package scs.georesults.logic.utils;

import scs.javax.web.DynamicForm;
import scs.javax.web.request.InvalidRequestFieldException;
import scs.georesults.common.Constants;

/**
 * <p>A sorrend-függő feladattípusok ellenőrzési módjait kezelé segédosztály.</p>
 * <p>Ezek az ellenőrzési típusok több beviteli mezőként jelennek meg, de végül
 * egy numerikus értékként tárolódnak. Ezt a leképezést segíti ez az osztály.</p>
 */
public class EllenorzesTipusUtils
{

  /**
   * Biztosítja, hogy az osztály ne legyen példányosítható.
   */
  private EllenorzesTipusUtils ()
  {}

  /**
   * A kapott HTTP kérés adataiból a megadott mezőnévvel szereplő adatok alapján
   * előállítja az ellenőrzési típust ábrázoló numerikus értéket.
   *
   * @param form A HTTP kérés objektuma.
   * @param fieldName A beviteli mező neve.
   * @return A típust meghatározó numerikus érték, vagy <code>null</code>, ha nincs
   *   egyedi típus megadva.
   */
  public static Integer getEllenorzesTipusFromForm ( DynamicForm form, String fieldName ) throws InvalidRequestFieldException
  {
    int base = form.getInteger( fieldName + "_r" );
    if ( base == 1 ) {
      return null;
    } else if ( base == 2 ) {
      return new Integer( Constants.ETAPFELADAT_ELLENORZES_TIPUS_NINCS );
    } else if ( base == 3 ) {
      int type = form.getInteger( fieldName + "_s1" );
      int list = form.getInteger( fieldName + "_s2" );
      return new Integer( getEllenorzesTipus( true, type == 2, list == 2 ) );
    }
    throw new InvalidRequestFieldException( fieldName );
  }

  /**
   * A paraméterekben megadott értékek alapján előállítja az ellenőrzési típust reprezentáló numerikus értéket.
   *
   * @param vanEllenorzes Igaz, ha van ellenőrzés
   * @param tipusCombo Igaz, ha az ellenőrzés lenyíló listát használ, hamis, ha háttérszín-kiemelést
   * @param Igaz, ha az ellenőrzés a feladat etalonját használja forrásnak, hamis, ha a feladat-típusnál megadott elemlistát.
   * @return Az ellenőrzés-típus numerikus értéke.
   */
  public static int getEllenorzesTipus ( boolean vanEllenorzes, boolean tipusCombo, boolean listaEtalon )
  {
    if ( !vanEllenorzes )return Constants.ETAPFELADAT_ELLENORZES_TIPUS_NINCS;
    if ( tipusCombo && listaEtalon )return Constants.ETAPFELADAT_ELLENORZES_TIPUS_COMBO_ETALON;
    if ( tipusCombo && !listaEtalon )return Constants.ETAPFELADAT_ELLENORZES_TIPUS_COMBO_EPLIST;
    if ( !tipusCombo && listaEtalon )return Constants.ETAPFELADAT_ELLENORZES_TIPUS_TEXTBG_ETALON;
    if ( !tipusCombo && !listaEtalon )return Constants.ETAPFELADAT_ELLENORZES_TIPUS_TEXTBG_EPLIST;
    throw new IllegalArgumentException();
  }

  /**
   * Igaz, ha a paraméterben megadott érték olyan ellenőrzés-típust határoz meg, amely lenyíló listát használ.
   */
  public static boolean isEllenorzesTipusCombo ( int ellenorzesTipus )
  {
    return ellenorzesTipus == Constants.ETAPFELADAT_ELLENORZES_TIPUS_COMBO_EPLIST || ellenorzesTipus == Constants.ETAPFELADAT_ELLENORZES_TIPUS_COMBO_ETALON;
  }

  /**
   * Igaz, ha a paraméterben megadott érték olyan ellenőrzés-típust határoz meg, amely háttérszín-kiemelést használ.
   */
  public static boolean isEllenorzesTipusTextBg ( int ellenorzesTipus )
  {
    return ellenorzesTipus == Constants.ETAPFELADAT_ELLENORZES_TIPUS_TEXTBG_EPLIST || ellenorzesTipus == Constants.ETAPFELADAT_ELLENORZES_TIPUS_TEXTBG_ETALON;
  }

  /**
   * Igaz, ha a paraméterben megadott érték olyan ellenőrzés-típust határoz meg, amely a feladat-típusnál megadott listát használja forrásnak.
   */
  public static boolean isEllenorzesTipusEpList ( int ellenorzesTipus )
  {
    return ellenorzesTipus == Constants.ETAPFELADAT_ELLENORZES_TIPUS_TEXTBG_EPLIST || ellenorzesTipus == Constants.ETAPFELADAT_ELLENORZES_TIPUS_COMBO_EPLIST;
  }

  /**
   * Igaz, ha a paraméterben megadott érték olyan ellenőrzés-típust határoz meg, amely a feladat etalonját használja forrásnak.
   */
  public static boolean isEllenorzesTipusEtalon ( int ellenorzesTipus )
  {
    return ellenorzesTipus == Constants.ETAPFELADAT_ELLENORZES_TIPUS_TEXTBG_ETALON || ellenorzesTipus == Constants.ETAPFELADAT_ELLENORZES_TIPUS_COMBO_ETALON;
  }

  /**
   * Igaz, ha a paraméterben megadott érték olyan ellenőrzés-típust határoz meg, hogy nincs ellenőrzés.
   */
  public static boolean isEllenorzesTipusNincs ( int ellenorzesTipus )
  {
    return ellenorzesTipus == Constants.ETAPFELADAT_ELLENORZES_TIPUS_NINCS;
  }

}
