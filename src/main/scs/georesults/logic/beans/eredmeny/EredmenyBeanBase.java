package scs.georesults.logic.beans.eredmeny;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import scs.javax.collections.List;
import scs.javax.web.RequestBeanBase;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebSession;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.Constants;
import scs.georesults.om.verseny.VersenyImpl;

/**
 * <p>Az eremdények megjelenítését segítő osztályok alaposztálya.
 * Ennek példányait általában az eredményeket megjelenítő lapok hozzák létre.
 * Létrehozás után beállítják az objektumnak a környezetet
 * a {@link setPageContext(javax.servlet.jsp.PageContext)} metódussal.</p>
 */
public class EredmenyBeanBase extends RequestBeanBase
{

  /**
   * Az aktuális munkafolyamat
   */
  protected HttpSession session;

  /**
   * Az aktuális verseny
   */
  protected VersenyImpl verseny;

  /**
   * A megjelenítendő eredmények listája. Adatokkal feltölteni a
   * leszármazott osztályok <code>init()</code> művelete fogja.
   */
  protected List eredmenyek;

  /**
   * A megjelenítő lap környezetét állítja be.
   */
  public void setPageContext ( PageContext pageContext ) throws Exception
  {
    this.pageContext = pageContext;
    setRequestWork( ( HttpServletRequest ) pageContext.getRequest() );
    init();
    initPage();
  }

  /**
   * A lapot előállító kérést reprezentáló objektumot állítja be.
   */
  public void setRequest ( HttpServletRequest request ) throws Exception
  {
    setRequestWork( request );
    init();
    initRequest( request );
  }

  /**
   * A HTTP kérés alapján beállítja az adatmezőket.
   */
  private void setRequestWork ( HttpServletRequest request ) throws SessionTimeoutException
  {
    this.session = WebSession.justGetHttpSession( request );
    this.verseny = ( VersenyImpl ) session.getAttribute( Constants.SESSION_KEY_VERSENY );
  }

  /**
   * A leszármazott osztályok által felüldefiniálható metódus. Akkor hajtódik végre,
   * amikor a megjelenítő lap környezete be lett állítva az objektumon.
   */
  public void initPage () throws Exception
  {}

  /**
   * A leszármazott osztályok által felüldefiniálható metódus. Akkor hajtódik végre,
   * amikor a kérést reprezentáló objektum be lett állítva az objektumon.
   */
  public void initRequest ( HttpServletRequest request ) throws Exception
  {}

  /**
   * Visszaadja a program által használt adatbázis-objektumot.
   */
  protected GeoDbSession getDb () throws GeoException
  {
    return GeoDbSession.getCurrentInstance();
  }

  /**
   * Visszaadja az eredmények listáját.
   */
  public List getEredmenyek ()
  {
    return eredmenyek;
  }

}
