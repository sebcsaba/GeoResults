package scs.georesults.view.eredmeny;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.io.IOException;
import scs.javax.web.SessionTimeoutException;
import scs.georesults.om.etap.DarabFuggoEtapFeladat;
import scs.georesults.om.etap.DarabFuggoEtapFeladatEtalonBejegyzes;
import scs.georesults.om.menetlevel.DarabFuggoMenetlevelBejegyzes;

public class DarabFuggoReszletesTag extends ReszletesPanelTagBase
{

  protected List bejegyzesek;

  protected DarabFuggoEtapFeladat dfef;

  protected void doPrint () throws DIIException, IOException, SessionTimeoutException
  {
    if ( bejegyzesek == null ) bejegyzesek = new List();
    StringBuffer cimkek = new StringBuffer();
    StringBuffer darabok = new StringBuffer();
    StringBuffer kulonbsegek = new StringBuffer();
    for ( int i = 0; i < dfef.getBejegyzesek().size(); ++i ) {
      DarabFuggoEtapFeladatEtalonBejegyzes etalonBejegyzes = ( DarabFuggoEtapFeladatEtalonBejegyzes ) dfef.getBejegyzesek().get( i );
      DarabFuggoMenetlevelBejegyzes menetlevelBejegyzes = ( DarabFuggoMenetlevelBejegyzes ) bejegyzesek.findItem( "posindex", new Integer( etalonBejegyzes.getPosindex() ) );
      int darab = menetlevelBejegyzes == null ? 0 : menetlevelBejegyzes.getDarab();
      int diff = darab - etalonBejegyzes.getDarab();
      String diffStr = Integer.toString( diff );
      if ( diff > 0 ) diffStr = "+" + diffStr;
      cimkek.append( CELL_TAG_HEADER ).append( etalonBejegyzes.getCimke() ).append( "</td>" );
      darabok.append( CELL_TAG_HEADER ).append( darab ).append( "</td>" );
      kulonbsegek.append( CELL_TAG_HEADER ).append( diffStr ).append( "</td>" );
    }
    writeRow( "BS_CIMKE", cimkek );
    writeRow( "BS_DARAB", darabok );
    writeRow( "BS_KULONBSEG", kulonbsegek );
  }

  public void setBejegyzesek ( List bejegyzesek )
  {
    this.bejegyzesek = bejegyzesek;
  }

  public void setDfef ( DarabFuggoEtapFeladat dfef )
  {
    this.dfef = dfef;
  }

}
