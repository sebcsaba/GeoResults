package scs.georesults.logic.utils;

import javax.servlet.jsp.JspException;
import scs.javax.collections.List;
import scs.javax.rdb.RdbException;
import scs.javax.web.RequestBeanBase;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebSession;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.Constants;
import scs.georesults.common.ValueLabelPair;
import scs.georesults.common.szotar.VersenyResolver;
import scs.georesults.om.verseny.*;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Az importálási varázslóban megjelenő beviteli űrlapok működését segítő osztály.</p>
 */
public class ImportFormUtils extends RequestBeanBase
{

  /**
   * A program adatbázis-objektuma
   */
  private GeoDbSession db;

  /**
   * Az aktuális verseny objektuma
   */
  private Verseny verseny;

  /**
   * Az aktuális verseny szótárát kezelő objektum
   */
  private VersenyResolver versenyResolver;

  /**
   * Az aktuális verseny szakaszainak azonosítóját és nevét (aktuális nyelven),
   * mint név-felirat párokat tartalmazó lista.
   */
  private List szakaszok;

  /**
   * Az aktuális verseny etapjainak azonosítóját és nevét (aktuális nyelven),
   * mint név-felirat párokat tartalmazó lista.
   */
  private List etapok;

  /**
   * Az aktuális verseny szlalomjainak azonosítóját és nevét (aktuális nyelven),
   * mint név-felirat párokat tartalmazó lista.
   */
  private List szlalomok;

  public void init () throws GeoException, SessionTimeoutException
  {
    this.db = GeoDbSession.getCurrentInstance();
    this.verseny = ( Verseny ) WebSession.justGetHttpSession( pageContext ).getAttribute( Constants.SESSION_KEY_VERSENY );
    this.versenyResolver = VersenyResolver.getCurrentInstance( pageContext.getServletContext() );
  }

  public void updateSzakaszok () throws JspException, RdbException
  {
    List src = Szakasz.loadAllForVerseny( db, verseny );
    szakaszok = new List();
    for ( int i = 0; i < src.size(); ++i ) {
      Szakasz szakasz = ( Szakasz ) src.get( i );
      String label = versenyResolver.doResolve( pageContext, szakasz.getNev() );
      szakaszok.add( new ValueLabelPair( szakasz.getSzid(), label ) );
    }
  }

  public List getSzakaszok () throws RdbException, JspException
  {
    if ( szakaszok == null ) updateSzakaszok();
    return szakaszok;
  }

  public void updateEtapok () throws JspException, RdbException
  {
    List src = Etap.loadAllForVerseny( db, verseny );
    etapok = new List();
    for ( int i = 0; i < src.size(); ++i ) {
      Etap etap = ( Etap ) src.get( i );
      String label = versenyResolver.doResolve( pageContext, etap.getNev() );
      etapok.add( new ValueLabelPair( etap.getEid(), label ) );
    }
  }

  public List getEtapok () throws RdbException, JspException
  {
    if ( etapok == null ) updateEtapok();
    return etapok;
  }

  public void updateSzlalomok () throws JspException, RdbException
  {
    List src = Szlalom.loadAllForVerseny( db, verseny );
    szlalomok = new List();
    for ( int i = 0; i < src.size(); ++i ) {
      Szlalom szlalom = ( Szlalom ) src.get( i );
      String label = versenyResolver.doResolve( pageContext, szlalom.getNev() );
      szlalomok.add( new ValueLabelPair( szlalom.getSzlid(), label ) );
    }
  }

  public List getSzlalomok () throws RdbException, JspException
  {
    if ( szlalomok == null ) updateSzlalomok();
    return szlalomok;
  }

}
