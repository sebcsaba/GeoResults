package scs.georesults.om.alap;

import scs.javax.collections.List;

public class KesesiZonaImpl extends KesesiZona
{

  public KesesiZonaImpl ()
  {
    super();
  }

  public KesesiZonaImpl ( long vid, int idoLimit )
  {
    super( vid, idoLimit );
  }

  public KesesiZonaImpl ( long vid, int idoLimit, int pont )
  {
    super( vid, idoLimit, pont );
  }

  public static int findFirstPositiveIndex ( List kesesiZonak )
  {
    for ( int i = 0; i < kesesiZonak.size(); ++i ) {
      KesesiZona kz = ( KesesiZona ) kesesiZonak.get( i );
      if ( kz.getIdoLimit() > 0 ) {
        return i;
      }
    }
    return kesesiZonak.size();
  }

}
