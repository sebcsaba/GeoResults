package scs.georesults.logic.utils;

import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbImportSession;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaLista;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaResz;
import scs.georesults.om.alap.DarabFuggoFeladatTipus;
import scs.georesults.om.alap.SorrendFuggoFeladatTipus;

/**
 * <p>Az importálási varázsló működését segítő osztály.</p>
 */
public class ImportUtils
{

  /**
   * Biztosítja, hogy az osztály ne legyen példányosítható.
   */
  private ImportUtils ()
  {}

  /**
   * A verseny menetlevél-formuláját frissíti az importálás után.
   *
   * @param importSession Az importálási folyamat objektuma, a módosított azonosítókat tartalmazza
   * @param menetlevelformula Az eredeti menetlevél-formula
   * @return A módosított menetlevél-formula
   */
  public static String getUpdatedMenetlevelformula ( RdbImportSession importSession, String menetlevelformula ) throws DIIException
  {
    MenetlevelFormulaLista mlfl = new MenetlevelFormulaLista( menetlevelformula );
    for ( int j = 0; j < mlfl.size(); ++j ) {
      MenetlevelFormulaResz mlfr = ( MenetlevelFormulaResz ) mlfl.get( j );
      if ( mlfr.getMode() == MenetlevelFormulaResz.MODE_DARAB ) {
        mlfr.setEfid( importSession.getNewId( DarabFuggoFeladatTipus.class, mlfr.getEfid() ) );
      } else if ( mlfr.getMode() == MenetlevelFormulaResz.MODE_SORREND ) {
        mlfr.setEfid( importSession.getNewId( SorrendFuggoFeladatTipus.class, mlfr.getEfid() ) );
      }
    }
    return mlfl.toString();
  }

}
