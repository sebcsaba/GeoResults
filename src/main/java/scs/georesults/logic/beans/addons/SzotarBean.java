package scs.georesults.logic.beans.addons;

import java.util.Iterator;
import scs.javax.collections.List;
import scs.javax.web.RequestBeanBase;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebSession;
import scs.georesults.Config;
import scs.georesults.common.Constants;
import scs.georesults.common.szotar.VersenySzotar;

/**
 * <p>A versenyszótár megjelenítését segítő osztály.</p>
 */
public class SzotarBean extends RequestBeanBase
{

  /**
   * A verseny szótára
   */
  private VersenySzotar versenySzotar;

  /**
   * A verseny szótárának elemeit ({@link scs.georesults.common.szotar.VersenySzotar.SzoForditasokkal}
   * típusú objektumokat) tartalmazó lista.
   */
  private List szfLista; // List< SzoForditasokkal >

  /**
   * Inicializálja az objektumot. Betölti a versenyszótárt, és az alapján feltölti az <code>szfLista</code> mezőt.
   */
  public void init () throws SessionTimeoutException
  {
    versenySzotar = ( VersenySzotar ) WebSession.justGetHttpSession( pageContext ).getAttribute( Constants.SESSION_KEY_VERSENY_SZOTAR );
    szfLista = new List();
    for ( Iterator it = versenySzotar.getAllItems().iterator(); it.hasNext(); ) szfLista.add( it.next() );
  }

  /**
   * Az aktuálisan kirajzolt panel indexe.
   */
  private int panelIndex;

  /**
   * A kirajzolandó panelek száma
   */
  public int getPanelCount ()
  {
    return Config.VERSENYSZOTAR_OSZLOPOK_SZAMA;
  }

  /**
   * Beállítja az aktuálisan kirajzolt panel indexét.
   */
  public void setPanelIndex ( int panelIndex )
  {
    this.panelIndex = panelIndex;
  }

  /**
   * Igaz, ha az aktuálisn kirajzolt panel üres.
   */
  public boolean isUresPanel ()
  {
    int panelMaxHeight = ( szfLista.size() - 1 ) / getPanelCount() + 1;
    int startIndex = panelIndex * panelMaxHeight;
    return startIndex >= szfLista.size();
  }

  /**
   * Az aktuálisan kirajzolt panel elemeit adja vissza.
   */
  public List getPanelItems ()
  {
    int panelMaxHeight = ( szfLista.size() - 1 ) / getPanelCount() + 1;
    int startIndex = panelIndex * panelMaxHeight;
    return szfLista.getSubList( startIndex, startIndex + panelMaxHeight );
  }

}
