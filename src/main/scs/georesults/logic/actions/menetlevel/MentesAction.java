package scs.georesults.logic.actions.menetlevel;

import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.web.DynamicForm;
import scs.javax.web.WebException;
import scs.georesults.GeoException;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaLista;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaResz;
import scs.georesults.logic.actions.AdatBevitelMentesActionBase;
import scs.georesults.logic.beans.AdatbevitelBean;
import scs.georesults.logic.beans.menetlevel.MenetlevelAdatokBean;
import scs.georesults.om.verseny.Etap;
import scs.georesults.logic.actions.*;

/**
 * <p>A menetlevelek felvitele lapon egy adott menetlevél mentését biztosító szolgáltatás osztálya.</p>
 */
public class MentesAction extends AdatBevitelMentesActionBase
{

  /**
   * A kijelölt etapon jelzi, hogy módosítás történt az adatain, ezért a hozzá tartozó eredményt frissíteni kell.
   *
   * @param id Az etap azonosítója
   */
  protected void invalidate ( long id ) throws WebException, RdbException
  {
    try {
      Etap etap = ( Etap ) getVerseny().getEtapok().findItem( "eid", new Long( id ) );
      if ( !etap.isEredmenyFrissitendo() ) {
        etap.read( getDb() );
        etap.setEredmenyFrissitendo( true );
        etap.update( getDb() );
      }
    }
    catch ( DIIException ex ) {
      throw new GeoException( ex );
    }
  }

  /**
   * Az adatokat tartalmazó bean osztály munkafolyamat-beli kulcsát adja vissza.
   */
  protected String getAdatokSessionKey ()
  {
    return "menetlevelAdatok";
  }

  /**
   * Feltölti a HTTP kérésben érkezett paraméterekkel a betöltött menetlevelet.
   *
   * @param entity A betöltött menetlevél
   * @param adatokBean Az adatbevitelt segítő osztály
   * @param form A kérésben érkezett paramétereket tartalmazó objektum
   */
  protected void fillEntity ( StorableEntityBase entity, AdatbevitelBean adatokBean, DynamicForm form ) throws WebException, RdbException
  {
    MenetlevelFormulaLista formula = new MenetlevelFormulaLista( form.getString( "menetlevelformula" ) );
    MenetlevelAdatokBean adatok = ( MenetlevelAdatokBean ) adatokBean;
    for ( int i = 0; i < formula.size(); ++i ) {
      MenetlevelFormulaResz resz = formula.get( i );
      if ( resz.getMode() == MenetlevelFormulaResz.MODE_TIME ) {
        adatok.fillIdo( form );
      } else if ( resz.getMode() == MenetlevelFormulaResz.MODE_BUNTETES ) {
        if ( !resz.isPopup() ) adatok.fillBuntetesek( form );
      } else if ( resz.getMode() == MenetlevelFormulaResz.MODE_DARAB ) {
        if ( !resz.isPopup() ) adatok.fillDarabFuggoBejegyzesek( form, resz.getEfid() );
      } else if ( resz.getMode() == MenetlevelFormulaResz.MODE_SORREND ) {
        if ( !resz.isPopup() ) adatok.fillSorrendFuggoBejegyzesek( form, resz.getEfid() );
      } else throw new IllegalArgumentException( resz.toString() );
    }
  }

  /**
   * A futamot tartalmazó versenyrész objektum azonosító mezőjének neve.
   */
  protected String getIdFieldName ()
  {
    return "eid";
  }

}
