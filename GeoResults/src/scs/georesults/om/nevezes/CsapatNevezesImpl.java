package scs.georesults.om.nevezes;

public class CsapatNevezesImpl extends CsapatNevezes
{

  public CsapatNevezesImpl ()
  {
    super();
  }

  public CsapatNevezesImpl ( long csnid )
  {
    super( csnid );
  }

  public CsapatNevezesImpl ( long csnid, long vid, String nev, int rajtszam1, int rajtszam2, int rajtszam3, Integer rajtszam4 )
  {
    super( csnid, vid, nev, rajtszam1, rajtszam2, rajtszam3, rajtszam4 );
  }

  public int getRajtszamByIndex ( int index )
  {
    if ( index == 1 )return getRajtszam1();
    if ( index == 2 )return getRajtszam2();
    if ( index == 3 )return getRajtszam3();
    if ( index == 4 )return getRajtszam4().intValue();
    throw new IllegalArgumentException( "maximum 4 auto" );
  }

  public void setRajtszamByIndex ( int index, int rajtszam )
  {
    if ( index == 1 ) setRajtszam1( rajtszam );
    else if ( index == 2 ) setRajtszam2( rajtszam );
    else if ( index == 3 ) setRajtszam3( rajtszam );
    else if ( index == 4 ) setRajtszam4( new Integer( rajtszam ) );
    else throw new IllegalArgumentException( "maximum 4 auto" );
  }

  public boolean isVanBenneRajtszam ( int rajtszam )
  {
    if ( getRajtszam1() == rajtszam )return true;
    if ( getRajtszam2() == rajtszam )return true;
    if ( getRajtszam3() == rajtszam )return true;
    if ( getRajtszam4() != null && getRajtszam4().intValue() == rajtszam )return true;
    return false;
  }

}
