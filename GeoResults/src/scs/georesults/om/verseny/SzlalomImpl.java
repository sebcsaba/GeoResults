package scs.georesults.om.verseny;

import scs.georesults.common.SzakaszElem;

public class SzlalomImpl extends Szlalom implements SzakaszElem
{

  public SzlalomImpl ()
  {
    super();
  }

  public SzlalomImpl ( long szlid )
  {
    super( szlid );
  }

  public SzlalomImpl ( long szlid, long szid, long vid, String nev, boolean ertekelendo, boolean eredmenyFrissitendo )
  {
    super( szlid, szid, vid, nev, ertekelendo, eredmenyFrissitendo );
  }

  public long getSzakaszElemId ()
  {
    return getSzlid();
  }

}
