package scs.georesults.om.menetlevel;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.lang.Time;
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
import scs.georesults.om.verseny.Etap;

public class MenetlevelImpl extends Menetlevel implements Futam
{

  public MenetlevelImpl ()
  {
    super();
  }

  public MenetlevelImpl ( long eid, int rajtszam )
  {
    super( eid, rajtszam );
  }

  public MenetlevelImpl ( long eid, int rajtszam, long vid, Time indulasiIdo, Time erkezesiIdo )
  {
    super( eid, rajtszam, vid, indulasiIdo, erkezesiIdo );
  }

  public static List loadAllForNevezes ( RdbSession session, Nevezes nevezes ) throws RdbException
  {
    return loadAll( session, Menetlevel.class, "rajtszam", nevezes, "vid", nevezes );
  }

  public static int countAllForEtap ( GeoDbSession db, Etap etap ) throws DIIException, RdbException
  {
    ClassMapping cm = MappingPool.getClassMapping( Menetlevel.class );
    RdbEntityHelper helper = db.getEntityHelper( cm );
    PrimitiveRdbCondition cond = new PrimitiveRdbCondition( cm.getField( "eid" ), new Long( etap.getEid() ) );
    List src = db.queryAll( helper.createCountAllFilteredStatement( cond ), RdbMetaStringField.getMetaCm() );
    if ( src.size() != 1 )return 0;
    RdbMetaStringField.MetaData md = ( RdbMetaStringField.MetaData ) src.get( 0 );
    return Integer.parseInt( md.getData() );
  }

  public int getNemNullaBuntetesekSzama ()
  {
    int acc = 0;
    for ( int i = 0; i < getBuntetesek().size(); ++i ) {
      if ( ( ( Buntetes ) getBuntetesek().get( i ) ).getDarab() != 0 )++acc;
    }
    return acc;
  }

}
