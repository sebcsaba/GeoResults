package scs.georesults.om.nevezes;

import scs.javax.rdb.RdbException;
import scs.javax.utils.StringUtils;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.om.alap.AutoTipus;

public class NevezesImpl extends Nevezes
{

  private AutoTipus auto;

  private Integer utasCount;

  public NevezesImpl ()
  {
    super();
  }

  public NevezesImpl ( long vid, int rajtszam )
  {
    super( vid, rajtszam );
  }

  public NevezesImpl ( long vid, int rajtszam, String sofor, String navigator, String utas1, String utas2, String utas3, String orszag, long autoTipus )
  {
    super( vid, rajtszam, sofor, navigator, utas1, utas2, utas3, orszag, autoTipus );
  }

  public String getUtas ( int i )
  {
    if ( i == 1 )return getUtas1();
    if ( i == 2 )return getUtas2();
    if ( i == 3 )return getUtas3();
    throw new IllegalArgumentException( "maximum 3 utas" );
  }

  public String getRajtszamSorforNavigator ()
  {
    return getRajtszam() + " - " + getSofor() + " - " + getNavigator();
  }

  public String getRajtszamEsMindenki ()
  {
    String[] parts = new String[6];
    parts[0] = Integer.toString( getRajtszam() );
    parts[1] = getSofor();
    parts[2] = getNavigator();
    for ( int i = 1; i <= 3; ++i ) parts[i + 2] = getUtas( i );
    return StringUtils.joinStrings( parts, " - " );
  }

  public void updateUtasCount ()
  {
    String[] utasok = new String[3];
    for ( int i = 1; i <= 3; ++i ) utasok[i - 1] = getUtas( i );
    int i = utasok.length - 1;
    while ( i >= 0 && utasok[i] == null )--i;
    if ( i < 0 ) {
      utasCount = new Integer( 0 );
    } else if ( i < utasok.length - 1 ) {
      utasCount = new Integer( i + 1 );
    } else {
      utasCount = new Integer( utasok.length );
    }
  }

  public int getUtasCount ()
  {
    if ( utasCount == null ) updateUtasCount();
    return utasCount.intValue();
  }

  public void updateAuto () throws GeoException, RdbException
  {
    auto = AutoTipus.newInstance( getAutoTipus() );
    auto.read( GeoDbSession.getCurrentInstance() );
  }

  public AutoTipus getAuto () throws GeoException, RdbException
  {
    if ( auto == null ) updateAuto();
    return auto;
  }

}
