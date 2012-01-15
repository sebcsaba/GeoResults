package scs.georesults.view.eredmeny;

import scs.javax.collections.List;
import scs.javax.io.IOException;
import scs.javax.web.SessionTimeoutException;
import scs.georesults.common.Constants;
import scs.georesults.om.eredmeny.SorrendFuggoFeladatKiertekeles;

public class SorrendFuggoReszletesTag extends ReszletesPanelTagBase
{

  private List kiertekeles;

  protected void doPrint () throws IOException, SessionTimeoutException
  {
    StringBuffer menetlevel = new StringBuffer();
    StringBuffer etalon = new StringBuffer();
    StringBuffer kulonbseg = new StringBuffer();
    for ( int i = 0; i < kiertekeles.size(); ++i ) {
      SorrendFuggoFeladatKiertekeles bejegyzes = ( SorrendFuggoFeladatKiertekeles ) kiertekeles.get( i );
      String mlf = bejegyzes.getMenetlevelFelirat();
      if ( mlf == null ) mlf = "";
      String ef = bejegyzes.getEtalonFelirat();
      if ( ef == null ) ef = "";
      String diff = "";
      switch ( bejegyzes.getTipus() ) {
        case Constants.FELADATKIERTEKELES_MEGEGYEZIK:
          diff = "(&middot;)"; break;
        case Constants.FELADATKIERTEKELES_MENETLEVELEN_HIANY:
          diff = "(-)"; break;
        case Constants.FELADATKIERTEKELES_MENETLEVELEN_TOBBLET:
          diff = "(+)"; break;
        case Constants.FELADATKIERTEKELES_TOROLT:
          diff = "(x)"; break;
      }
      menetlevel.append( CELL_TAG_HEADER ).append( mlf ).append( "</td>" );
      etalon.append( CELL_TAG_HEADER ).append( ef ).append( "</td>" );
      kulonbseg.append( CELL_TAG_HEADER ).append( diff ).append( "</td>" );
    }
    writeRow( "FT_SAJAT", menetlevel );
    writeRow( "RA_ETALON", etalon );
    writeRow( "BS_EREDMENY", kulonbseg );
  }

  public void setKiertekeles ( List kiertekeles )
  {
    this.kiertekeles = kiertekeles;
  }

}
