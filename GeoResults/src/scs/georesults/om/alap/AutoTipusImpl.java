package scs.georesults.om.alap;

import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;

public class AutoTipusImpl extends AutoTipus
{

  private SzlalomKategoria szlalomKategoria;

  public AutoTipusImpl ()
  {
    super();
  }

  public AutoTipusImpl ( long atid )
  {
    super( atid );
  }

  public AutoTipusImpl ( long atid, long vid, String nev, long kategoria )
  {
    super( atid, vid, nev, kategoria );
  }

  public void updateSzlalomKategoria () throws GeoException, RdbException
  {
    szlalomKategoria = SzlalomKategoria.newInstance( getKategoria() );
    szlalomKategoria.read( GeoDbSession.getCurrentInstance() );
  }

  public SzlalomKategoria getSzlalomKategoria () throws RdbException, GeoException
  {
    if ( szlalomKategoria == null ) updateSzlalomKategoria();
    return szlalomKategoria;
  }

}
