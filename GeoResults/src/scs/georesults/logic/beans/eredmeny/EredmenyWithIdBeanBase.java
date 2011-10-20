package scs.georesults.logic.beans.eredmeny;

import javax.servlet.http.HttpServletRequest;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.web.DynamicForm;
import scs.georesults.GeoException;

/**
 * <p>A versenyt felépítő részegységek (etapok, szlalomok, szakaszok)
 * eredményének megjelenítését segíti. Az ezeket tartalmazó lapok közös
 * tulajdonsága, hogy az aktuális elem azonosítóját egy
 * <code>id</code> paraméterben kapja.</p>
 */
public abstract class EredmenyWithIdBeanBase extends EredmenyBeanBase
{

  /**
   * A kapott <code>id</code> paramétert beállítja az objektumnak. A lap környezetének beállításakor hjatódik végre.
   */
  public void initPage () throws Exception
  {
    DynamicForm form = new DynamicForm( ( HttpServletRequest ) pageContext.getRequest() );
    setId( form.getLong( "id" ) );
  }

  /**
   * Az objektum által megjelenített versenyrész azonosítóját állítja be.
   */
  public abstract void setId ( long id ) throws RdbException, GeoException, DIIException;

}
