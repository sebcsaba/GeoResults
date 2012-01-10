package scs.georesults.common.iotools;

import java.util.Iterator;
import javax.servlet.http.HttpServletResponse;
import scs.javax.io.IOException;
import scs.javax.io.Writer;
import scs.javax.io.writers.JspWriter;
import scs.javax.io.writers.XmlWriter;

/**
 * <p>Olyan osztályok közös őse, amelyek az adatbázis (vagy egy része) exportálásakor az objektumokat különböző formában írják ki a kimenetre.</p>
 */
public abstract class DatabaseWriterBase
{

  /**
   * A kimeneten megjelenő fejléc-információ
   */
  protected static final String HEADER = "GeoResults database export (c) SebCsaba 2006";

  /**
   * Az exportálandó objektumokon végighaladó iterátor objektum.
   */
  protected Iterator dbiter;

  /**
   * A kimeneti adatfolyam objektuma, olyan kiíró-osztályba csomagolva,
   * amely HTML/XML elemeket tud könnyen kezelni.
   */
  protected XmlWriter out;

  /**
   * Egy új objektumot hoz létre
   *
   * @param dbiter Az exportálandó objektumok iterátora
   */
  protected DatabaseWriterBase ( Iterator dbiter ) throws IOException
  {
    this.dbiter = dbiter;
  }

  /**
   * A kimeneti adat formátumának típusa
   */
  public abstract String getContentType ();

  /**
   * A kimeneti adat mint fájl ilyen kiterjesztéssel szerepeljen
   */
  public abstract String getExtension ();

  /**
   * A paraméterben kapott szöveget megjegyzésként írja a kimenetre
   */
  protected abstract void writeHeaderCommentLine () throws IOException;

  /**
   * Az adatokat írja ki a kimeneti adatfolyamra a megfelelő formában.
   */
  protected abstract void doPrint () throws IOException;

  /**
   * Végrehajtja az exportálást a megadott HTTP válasz objektumot használva
   */
  public void doExport ( HttpServletResponse response ) throws IOException
  {
    try {
      this.out = new JspWriter( response.getWriter() );
      doWork();
    }
    catch ( java.io.IOException ex ) {
      throw new IOException( ex );
    }
  }

  /**
   * Végrehajtja az exportálást a megadott kimeneti adatfolyamra
   */
  public void doExport ( Writer writer ) throws IOException
  {
    this.out = new JspWriter( writer );
    doWork();
  }

  /**
   * Kiírja az adatokat és a fejléc-információt a megfelelő helyre
   */
  private void doWork () throws IOException
  {
    writeHeaderCommentLine();
    doPrint();
  }

}
