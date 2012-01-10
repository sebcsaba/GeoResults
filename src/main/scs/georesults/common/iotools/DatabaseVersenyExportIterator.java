package scs.georesults.common.iotools;

import scs.javax.collections.SelfEnlargerQueueIterator;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.georesults.GeoException;
import scs.georesults.om.alap.*;
import scs.georesults.om.eredmeny.*;
import scs.georesults.om.etap.DarabFuggoEtapFeladat;
import scs.georesults.om.etap.SorrendFuggoEtapFeladat;
import scs.georesults.om.menetlevel.Menetlevel;
import scs.georesults.om.nevezes.CsapatNevezes;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.szlalom.SzlalomFutam;
import scs.georesults.om.verseny.*;

/**
 * <p>Olyan önmagát folyamatosan kiterjesztő sort felhasználó iterátor, amely a megadott
 * adatbázisból a megadott azonosítójú verseny összes adatát tartalmazza.</p>
 */
public class DatabaseVersenyExportIterator extends SelfEnlargerQueueIterator
{

  private RdbSession db;

  public DatabaseVersenyExportIterator ( RdbSession db, long vid ) throws GeoException, RdbException
  {
    this.db = db;
    Verseny v = Verseny.newInstance( vid );
    v.read( db );
    add( v );
    addAll( SzlalomKategoria.loadAllForVerseny( db, v ) );
    addAll( AutoTipus.loadAllForVerseny( db, v ) );
    addAll( BuntetesTipus.loadAllForVerseny( db, v ) );
    addAll( DarabFuggoFeladatTipus.loadAllForVerseny( db, v ) );
    addAll( SorrendFuggoFeladatTipus.loadAllForVerseny( db, v ) );
    addAll( KesesiZona.loadAllForVerseny( db, v ) );
    addAll( SzlalomFeladat.loadAllForVerseny( db, v ) );
    addAll( VersenySzotarBejegyzes.loadAllForVerseny( db, v ) );

    addAll( Nevezes.loadAllForVerseny( db, v ) );
    addAll( CsapatNevezes.loadAllForVerseny( db, v ) );

    addAll( Szakasz.loadAllForVerseny( db, v ) );
    addAll( Etap.loadAllForVerseny( db, v ) );
    addAll( Szlalom.loadAllForVerseny( db, v ) );

    addAll( VersenyEredmeny.loadAllForVerseny( db, v ) );
    addAll( CsapatEredmeny.loadAllForVerseny( db, v ) );
    addAll( MindenEtapEredmeny.loadAllForVerseny( db, v ) );
    addAll( MindenSzlalomEredmeny.loadAllForVerseny( db, v ) );
  }

  protected void dequeElement ( Object item )
  {
    try {
      if ( item instanceof Szakasz ) {
        addAll( SzakaszEredmeny.loadAllForSzakasz( db, ( Szakasz ) item ) );
        addAll( SzlalomOsszesitettEredmeny.loadAllForSzakasz( db, ( Szakasz ) item ) );
      } else if ( item instanceof Etap ) {
        addAll( DarabFuggoEtapFeladat.loadAllForEtap( db, ( Etap ) item ) );
        addAll( SorrendFuggoEtapFeladat.loadAllForEtap( db, ( Etap ) item ) );
        addAll( Menetlevel.loadAllForEtap( db, ( Etap ) item ) );
        addAll( EtapEredmeny.loadAllForEtap( db, ( Etap ) item ) );
      } else if ( item instanceof Szlalom ) {
        addAll( SzlalomFutam.loadAllForSzlalom( db, ( Szlalom ) item ) );
        addAll( SzlalomEredmeny.loadAllForSzlalom( db, ( Szlalom ) item ) );
      }
    }
    catch ( RdbException ex ) {
      throw new Error( ex );
    }
  }

}
