package scs.georesults.om.eredmeny;

public class CsapatEredmenyImpl extends CsapatEredmeny
{

  public CsapatEredmenyImpl ()
  {
    super();
  }

  public CsapatEredmenyImpl ( long csnid )
  {
    super( csnid );
  }

  public CsapatEredmenyImpl ( long csnid, long vid, String nev, int rajtszam1, int rajtszam2, int rajtszam3, int pontszam )
  {
    super( csnid, vid, nev, rajtszam1, rajtszam2, rajtszam3, pontszam );
  }

  public void setRajtszamByIndex ( int index, int rajtszam )
  {
    if ( index == 1 ) setRajtszam1( rajtszam );
    else if ( index == 2 ) setRajtszam2( rajtszam );
    else if ( index == 3 ) setRajtszam3( rajtszam );
    else throw new IllegalArgumentException( "maximum 3 auto" );
  }

  public int getRajtszamByIndex ( int index )
  {
    if ( index == 1 )return getRajtszam1();
    if ( index == 2 )return getRajtszam2();
    if ( index == 3 )return getRajtszam3();
    throw new IllegalArgumentException( "maximum 3 auto" );
  }

}
