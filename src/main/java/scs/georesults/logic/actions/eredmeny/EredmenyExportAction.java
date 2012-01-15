package scs.georesults.logic.actions.eredmeny;

import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.logic.actions.GeoActionBase;

/**
 * <p>Az eredmények exportálása szolgáltatás osztálya.</p>
 *
 * <p>A szolgáltatás komplexsége ellenére ez a legegyszerűbb szolgáltató osztály:
 * attól függően, hogy milyen formátumba történik az exportálás, a megfelelő JSP
 * lapra irányítja át a vezérlést. Ehhez megnézi a HTTP kérés <code>mibe</code>
 * paraméterét, és annak értékét visszaadja a Struts vezérlőrétegének, mint a
 * továbblépési irányt jelző stringet. (Jelenleg csupán egyetlen formátumba,
 * az Ms Excel XML formába képes a program exportálni, de új formátum felvitele
 * esetén sem kell ezt az osztályt módosítani.)</p>
 */
public class EredmenyExportAction extends GeoActionBase
{

  /**
   * Ez a művelet hajtja végre az osztály szolgáltatását.
   */
  public String serve ( DynamicForm form ) throws WebException
  {
    String mibe = form.getString( "mibe" );
    return mibe;
  }

}
