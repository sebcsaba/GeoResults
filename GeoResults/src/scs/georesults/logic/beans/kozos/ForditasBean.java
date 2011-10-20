package scs.georesults.logic.beans.kozos;

import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.om.kozos.Nyelv;
import scs.georesults.om.kozos.SzotarBejegyzes;

/**
 * <p>A szótár egy részének lefordítását segító osztály.</p>
 */
public class ForditasBean
{

  /**
   * A szótári szavak csoportjának kódja. Ez a szótári kulcsok kétbetűs prefixe.
   */
  private String group;

  /**
   * Azon nyelv kódja, amelyre a fordítást alapozzuk, vagyis amely nyelven a szövegek megjelennek.
   */
  private String alapnyelv;

  /**
   * A fordítás célnyelve
   */
  private Nyelv celnyelv;

  /**
   * A fordítandó szótárbejegyzéseket tartalmazó lista
   */
  private List bejegyzesek; // List< SzotarBejegyzes >

  /**
   * Az aktuális szótárbejegyzés, amelynek részletei éppen megjelenítésre kerülnek.
   */
  private SzotarBejegyzes aktSzb;

  /**
   * A program adatbázisa
   */
  private GeoDbSession db;

  /**
   * Egy új objektumot hoz létre a megadott paraméterek alapján.
   *
   * @param group A fordítandó kifejezések csoportjának azonosítója
   * @param alapnyelv A fordítás alapnyelvének kódja
   * @param celnyelv A fordítás célnyelve
   */
  public ForditasBean ( String group, String alapnyelv, Nyelv celnyelv ) throws RdbException, GeoException
  {
    this.group = group;
    this.alapnyelv = alapnyelv;
    this.celnyelv = celnyelv;
    this.db = GeoDbSession.getCurrentInstance();
    updateBejegyzesek();
  }

  /**
   * A bejegyzések listájának frissítését végzi.
   */
  public void updateBejegyzesek () throws GeoException, RdbException
  {
    bejegyzesek = new List( 50 );
    List src = SzotarBejegyzes.loadAllForNyelv( db, celnyelv );
    for ( int i = 0; i < src.size(); ++i ) {
      SzotarBejegyzes szb = ( SzotarBejegyzes ) src.get( i );
      if ( szb.getKeystr().startsWith( group ) ) bejegyzesek.add( szb );
    }
  }

  public String getGroup ()
  {
    return group;
  }

  public void setAlapnyelv ( String alapnyelv )
  {
    this.alapnyelv = alapnyelv;
  }

  public String getAlapnyelv ()
  {
    return alapnyelv;
  }

  public List getBejegyzesek ()
  {
    return bejegyzesek;
  }

  /**
   * Az aktuális bejegyzés beállítása
   *
   * @param aktSzb Az aktuális bejegyzés új értéke
   */
  public void setAktBejegyzes ( SzotarBejegyzes aktSzb )
  {
    this.aktSzb = aktSzb;
  }

  /**
   * Megadja az aktuális bejegyzés értékét az alapnyelven.
   *
   * @return Az aktuális bejegyzés szövege az alapnyelven.
   */
  public String getAktBejegyzesAlapnyelven () throws RdbException
  {
    SzotarBejegyzes szb = SzotarBejegyzes.newInstance( alapnyelv, aktSzb.getKeystr() );
    szb.read( db );
    return szb.getValuestr();
  }

}
