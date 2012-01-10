package scs.georesults.logic.beans.eredmeny;

import scs.javax.collections.List;
import scs.georesults.om.alap.SzlalomKategoria;

/**
 * <p>Ez az osztály az egy adott szlalom-kategóriában egy adot szlalomversenyen elért eredményeket tárolja.</p>
 */
public class SzlalomKategoriaEredmeny
{

  /**
   * A kategória objektuma.
   */
  private SzlalomKategoria kategoria;

  /**
   * A kategória eredményeinek listája.
   */
  private List eredmenyek;

  /**
   * Létrehoz egy új objektumot a megadott kategóriához. Az eredmények listáját üresre állítja.
   */
  public SzlalomKategoriaEredmeny ( SzlalomKategoria kategoria )
  {
    this.kategoria = kategoria;
    this.eredmenyek = new List();
  }

  /**
   * A kategóriát adja vissza.
   */
  public SzlalomKategoria getKategoria ()
  {
    return kategoria;
  }

  /**
   * A kategória eredménylistáját adja vissza.
   */
  public List getEredmenyek ()
  {
    return eredmenyek;
  }

}
