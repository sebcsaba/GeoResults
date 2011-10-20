package scs.georesults.logic.actions;

import java.util.Enumeration;
import javax.servlet.http.HttpSession;
import scs.javax.rdb.RdbException;
import scs.javax.web.*;
import scs.georesults.Config;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.Constants;
import scs.georesults.logic.GeoMessageException;
import scs.georesults.logic.LezarvaException;
import scs.georesults.logic.PrimitiveMessageException;
import scs.georesults.om.kozos.NyelvImpl;
import scs.georesults.om.verseny.VersenyImpl;
import scs.georesults.BackupThread;

/**
 * <p>A programban használt Action osztályok közös ősosztálya.</p>
 */
public abstract class GeoActionBase extends ActionBase
{

  /**
   * A program adatbázisa
   */
  private GeoDbSession db;

  /**
   * Az aktuálisan használt verseny objektuma
   */
  private VersenyImpl verseny;

  /**
   * Az aktuálisan használ nyelvet reprezentáló objektum
   */
  private NyelvImpl nyelv;

  /**
   * Ez az absztakt metódus végzi a művelet tényleges végrehajtását.
   * Visszatérési értéke egy string, amit a Struts a vezérlési gráfban
   * alkalmazott szabályok címei közötti keresésnél használ fel.
   */
  public abstract String serve ( DynamicForm form ) throws WebException, RdbException;

  /**
   * Ez a metódus kerül meghívásra a Struts által. Továbbítja a kését
   * a {@link serve(DynamicForm)} metódusnak, és bizonyos kivétel-típusok
   * esetén lekezeli azokat. Hibakeresési információt is nyomtat, ha szükséges.
   */
  public String service ( DynamicForm form ) throws Exception
  {
    this.verseny = null;
    if ( Config.DEBUG_SESSION ) debugSession( "before" );
    String result;
    try {
      result = serve( form );
    }
    catch ( LezarvaException ex ) {
      result = "lezarva";
    }
    catch ( GeoMessageException ex ) {
      result = GLOBAL_ERROR_KEY;
      setToRequest( GLOBAL_ERROR_KEY, ex );
    }
    catch ( PrimitiveMessageException ex ) {
      result = GLOBAL_ERROR_KEY;
      setToRequest( GLOBAL_ERROR_KEY, ex );
    }
    BackupThread.getCurrentInstance().notifyVersenyUpdated(getVerseny());
    if ( Config.DEBUG_SESSION ) debugSession( "after" );
    return result;
  }

  /**
   * A munkafolyamat adatairól nyomtat hibakeresési információt a szabványos kiemenetre.
   *
   * @param sign Egy felirat, amely az információs blokk fejlécében szerepel. Azért kell,
   *   mert az információt kétszer, a hívások elején és végén is megjelenítjük.
   */
  private void debugSession ( String sign ) throws SessionTimeoutException
  {
    System.out.println( "--- session log begin --- [" + sign + " " + getClass().getName() + "] ---" );
    HttpSession session = getSession();
    for ( Enumeration en = session.getAttributeNames(); en.hasMoreElements(); ) {
      String name = ( String ) en.nextElement();
      System.out.println( "  " + name + " = " + session.getAttribute( name ) );
    }
    System.out.println( "--- session log  end  --- [" + sign + " " + getClass().getName() + "] ---" );
  }

  /**
   * Az adatbázis objektumot adja vissza.
   */
  protected GeoDbSession getDb () throws GeoException
  {
    if ( db == null ) db = GeoDbSession.getCurrentInstance();
    return db;
  }

  /**
   * Firssíti az aktuális verseny objektumát
   */
  private void updateVerseny () throws SessionTimeoutException
  {
    verseny = ( VersenyImpl ) getFromSession( Constants.SESSION_KEY_VERSENY );
  }

  /**
   * Visszaadja az aktuális verseny objektumát.
   */
  public VersenyImpl getVerseny () throws SessionTimeoutException
  {
    if ( verseny == null ) updateVerseny();
    return verseny;
  }

  /**
   * Firssíti az aktuális nyelv objektumát
   */
  private void updateNyelv () throws SessionTimeoutException
  {
    nyelv = ( NyelvImpl ) getFromSession( Constants.SESSION_KEY_NYELV );
  }

  /**
   * Visszaadja az aktuális nyelv objektumát.
   */
  public NyelvImpl getNyelv () throws SessionTimeoutException
  {
    if ( nyelv == null ) updateNyelv();
    return nyelv;
  }

}
