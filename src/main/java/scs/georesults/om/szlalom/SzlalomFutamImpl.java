package scs.georesults.om.szlalom;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbSession;
import scs.javax.rdb.helper.RdbEntityHelper;
import scs.javax.rdb.mapping.ClassMapping;
import scs.javax.rdb.mapping.MappingPool;
import scs.javax.rdb.mapping.PrimitiveRdbCondition;
import scs.javax.rdb.mapping.fields.RdbMetaStringField;
import scs.georesults.GeoDbSession;
import scs.georesults.common.Futam;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.verseny.Szlalom;

public class SzlalomFutamImpl extends SzlalomFutam implements Futam
{

  public SzlalomFutamImpl ()
  {
    super();
  }

  public SzlalomFutamImpl ( long szlid, int rajtszam )
  {
    super( szlid, rajtszam );
  }

  public SzlalomFutamImpl ( long szlid, int rajtszam, long vid )
  {
    super( szlid, rajtszam, vid );
  }

  public static List loadAllForNevezes ( RdbSession session, Nevezes nevezes ) throws RdbException
  {
    return loadAll( session, SzlalomFutam.class, "rajtszam", nevezes, "vid", nevezes );
  }

  public static int countAllForSzlalom ( GeoDbSession db, Szlalom szlalom ) throws DIIException, RdbException
  {
    ClassMapping cm = MappingPool.getClassMapping( SzlalomFutam.class );
    RdbEntityHelper helper = db.getEntityHelper( cm );
    PrimitiveRdbCondition cond = new PrimitiveRdbCondition( cm.getField( "szlid" ), new Long( szlalom.getSzlid() ) );
    List src = db.queryAll( helper.createCountAllFilteredStatement( cond ), RdbMetaStringField.getMetaCm() );
    if ( src.size() != 1 )return 0;
    RdbMetaStringField.MetaData md = ( RdbMetaStringField.MetaData ) src.get( 0 );
    return Integer.parseInt( md.getData() );
  }

}
