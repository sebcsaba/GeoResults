package scs.georesults.common.szotar;

import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebSession;
import scs.javax.web.taglibs.common.TextResolver;
import scs.javax.web.taglibs.common.WriteTagHelper;
import scs.georesults.common.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;

/**
 * <p>A versenyhez tartozó szótárat kezelő osztály. A <tt>scs.javax.web.taglibs.common</tt>
 * taglibben definiált WriteTag osztály által használt
 * <i>scs/javax/web/WriteTagHelper/labelRersolver</i> nevű
 * szolgáltatás.</p>
 */
public class VersenyResolver implements TextResolver
{

  /**
   * A szótárban feloldandó stringeket meghatározó reguláris kifejezés.
   */
  private static final Pattern pattern = Pattern.compile( "@\\d+" );

  /**
   * Ez a művelet keresi meg az adott kontextusban a string paraméterben
   * átadott szöveghez megfelelő szöveget: ha az adott stringet fel kell
   * oldani, akkor megteszi, vagyis előkeresi a szótárból a
   * megfelelő értéket, különben a kapott stringet adja vissza.
   *
   * @param context A kontextus objektuma
   * @param string A keresett kulcs vagy egyszerű szöveg
   * @return A kulcshoz tartozó szöveg vagy a szöveg maga
   */
  public String resolve ( HttpServletRequest request, String string ) throws JspException
  {
    try {
      if ( shouldBeResolved( string ) ) {
        HttpSession session = WebSession.justGetHttpSession( request );
        VersenySzotar versenySzotar = ( VersenySzotar ) session.getAttribute( Constants.SESSION_KEY_VERSENY_SZOTAR );
        return versenySzotar.getValue( string );
      } else return string;
    }
    catch ( SessionTimeoutException ex ) {
      throw new JspException( ex );
    }
  }

  public static String doResolve ( PageContext context, String key ) throws JspException
  {
    return doResolve(context.getServletContext(), (HttpServletRequest)context.getRequest(), key);
  }

  /**
   * Az osztály adott kontextushoz tartozó példányát megkeresi,
   * és abban feloldja a kapott stringet.
   *
   * @param context A kontextus objektuma
   * @param key A keresett kulcs vagy egyszerű szöveg
   * @return A kulcshoz tartozó szöveg vagy a szöveg maga
   */
  public static String doResolve ( ServletContext servletContext, HttpServletRequest request, String key ) throws JspException
  {
    return WriteTagHelper.getCurrentInstance( servletContext ).getLabelResolver().resolve( request, key );
  }

  /**
   * Visszaadja az osztály adott kontextushoz tartozó példányát.
   *
   * @param context A kontextus objektum
   * @return A megfelelő példány
   */
  public static VersenyResolver getCurrentInstance ( ServletContext context )
  {
    return ( VersenyResolver ) WriteTagHelper.getCurrentInstance( context ).getLabelResolver();
  }

  /**
   * Megállapítja, hogy az adott stringet fel kell-e oldani a szótárban.
   *
   * @param string A szöveg vagy kulcs
   * @return Igaz, ha a paraméter kulcs
   */
  public static boolean shouldBeResolved ( String string )
  {
    return pattern.matcher( string ).matches();
  }

}
