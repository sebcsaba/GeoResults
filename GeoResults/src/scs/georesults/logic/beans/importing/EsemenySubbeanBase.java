package scs.georesults.logic.beans.importing;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoException;
import scs.georesults.common.SzakaszElem;
import scs.georesults.common.ValueLabelPair;
import scs.georesults.common.szotar.VersenyResolver;

public abstract class EsemenySubbeanBase extends ImportCategorySubbean
{

  // Esemény lehet Szakasz, Etap vagy Szlalom

  private List sourceEsemenyek;

  protected abstract Class getEsemenyClass ();

  public void updateSourceEsemenyek () throws DIIException, GeoException, RdbException
  {
    List entities = loadEntitesForVerseny( getEsemenyClass() );
    sourceEsemenyek = new List();
    for ( int i = 0; i < entities.size(); ++i ) {
      SzakaszElem esemeny = ( SzakaszElem ) entities.get( i );
      String label = esemeny.getNev();
      if ( VersenyResolver.shouldBeResolved( esemeny.getNev() ) ) {
        label = getSourceVersenySzotar().getValue( label );
      }
      ValueLabelPair vlp = new ValueLabelPair( "id_" + esemeny.getSzakaszElemId(), label );
      sourceEsemenyek.add( vlp );
    }
  }

  public List getSourceEsemenyek () throws DIIException, RdbException, GeoException
  {
    if ( sourceEsemenyek == null ) updateSourceEsemenyek();
    return sourceEsemenyek;
  }

}
