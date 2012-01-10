package scs.georesults.logic.actions.szakasz;

import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.WebException;
import scs.georesults.logic.actions.CrudActionBase;
import scs.georesults.om.verseny.Szakasz;
import scs.georesults.om.verseny.SzakaszImpl;

/**
 * <p>A szakaszt felépítő etapok és szlalomok adminisztrációjához
 * szükséges szolgáltatások osztályainak köszös ősosztálya.</p>
 * <p>A két objektumtípus elemeihez két osztály fog kapcsolódni a
 * {@link scs.georesults.logic.actions.szakasz.reszek} csomagban.
 * E két osztály szolgáltatása CRUD műveleteket fog biztosítani,
 * mint jelen osztály ősosztályán ez látható.</p>
 * <p>Ez az osztály csupán az alapműveletek után végrehajtandó frissítést definiálja.</p>
 */
public abstract class SzakaszReszekActionBase extends CrudActionBase
{

  /**
   * Az aktuálisan betöltött szakasz objektumát adja vissza
   */
  protected SzakaszImpl getSzakasz () throws WebException
  {
    return ( SzakaszImpl ) getFromSession( "szakasz" );
  }

  /**
   * Beállítja az aktuális szakaszon, hogy az eredménylistáját frissíteni kell.
   */
  private void setEredmenyFrisitendoParentSzakasz () throws WebException, RdbException
  {
    Szakasz sz = getSzakasz();
    sz.read( getDb() );
    sz.setEredmenyFrissitendo( true );
    sz.update( getDb() );
  }

  /**
   * Objektum létrehozása után frissíti a szakasz adatait.
   */
  protected void doAfterCreate ( StorableEntityBase entity ) throws WebException, RdbException
  {
    setEredmenyFrisitendoParentSzakasz();
  }

  /**
   * Objektum módosítása után frissíti a szakasz adatait.
   */
  protected void doAfterUpdate ( StorableEntityBase entity ) throws WebException, RdbException
  {
    setEredmenyFrisitendoParentSzakasz();
  }

  /**
   * Objektum törlése után frissíti a szakasz adatait.
   */
  protected void doAfterDelete ( StorableEntityBase entity ) throws WebException, RdbException
  {
    setEredmenyFrisitendoParentSzakasz();
  }

  /**
   * Az összes objektum törlése után frissíti a szakasz adatait.
   */
  protected void doAfterDeleteAll () throws WebException, RdbException
  {
    setEredmenyFrisitendoParentSzakasz();
  }

}
