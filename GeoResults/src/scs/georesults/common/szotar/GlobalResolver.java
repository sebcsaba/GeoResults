package scs.georesults.common.szotar;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebSession;
import scs.javax.web.taglibs.common.TextResolver;
import scs.georesults.common.Constants;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>A globális szótárat kezelő osztály. A <tt>scs.javax.web.taglibs.common</tt>
 * taglibben definiált WriteTag osztály által használt
 * <i>scs/javax/web/WriteTagHelper/bundleRersolver</i> nevű
 * szolgáltatás.</p>
 */
public class GlobalResolver implements TextResolver
{

  /**
   * Ez a művelet keresi meg az adott kontextusban a string paraméterben átadott kulcshoz tartozó szótári értéket.
   *
   * @param context A kontextus objektuma
   * @param string A keresett kulcs
   * @return A kulcshoz tartozó szöveg
   */
  public String resolve ( HttpServletRequest request, String string ) throws JspException
  {
    try {
      HttpSession session = WebSession.justGetHttpSession( request );
      GlobalSzotar globalSzotar = ( GlobalSzotar ) session.getAttribute( Constants.SESSION_KEY_GLOBAL_SZOTAR );
      return globalSzotar.getValue( string );
    }
    catch ( SessionTimeoutException ex ) {
      throw new JspException( ex );
    }
  }

}
