package scs.georesults.om.verseny;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.SzakaszElem;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaLista;
import scs.georesults.common.menetlevelformula.MenetlevelFormulaResz;
import scs.georesults.om.etap.DarabFuggoEtapFeladat;
import scs.georesults.om.etap.SorrendFuggoEtapFeladat;

public class EtapImpl extends Etap implements SzakaszElem
{

  private List darabFuggoEtapFeladatok;

  private List sorrendFuggoEtapFeladatok;

  public EtapImpl ()
  {
    super();
  }

  public EtapImpl ( long eid )
  {
    super( eid );
  }

  public EtapImpl ( long eid, long szid, long vid, String nev, int idoLimit, String menetlevelformula, boolean ertekelendo, boolean eredmenyFrissitendo )
  {
    super( eid, szid, vid, nev, idoLimit, menetlevelformula, ertekelendo, eredmenyFrissitendo );
  }

  private GeoDbSession getDb () throws GeoException
  {
    return GeoDbSession.getCurrentInstance();
  }

  public void updateDarabFuggoEtapFeladatok () throws GeoException, RdbException
  {
    darabFuggoEtapFeladatok = DarabFuggoEtapFeladat.loadAllForEtap( getDb(), this );
  }

  public void updateSorrendFuggoEtapFeladatok () throws GeoException, RdbException
  {
    sorrendFuggoEtapFeladatok = SorrendFuggoEtapFeladat.loadAllForEtap( getDb(), this );
  }

  public List getDarabFuggoEtapFeladatok () throws GeoException, RdbException
  {
    if ( darabFuggoEtapFeladatok == null ) updateDarabFuggoEtapFeladatok();
    return darabFuggoEtapFeladatok;
  }

  public List getSorrendFuggoEtapFeladatok () throws GeoException, RdbException
  {
    if ( sorrendFuggoEtapFeladatok == null ) updateSorrendFuggoEtapFeladatok();
    return sorrendFuggoEtapFeladatok;
  }

  public String getAbszolutMenetlevelformula () throws GeoException, DIIException, RdbException
  {
    if ( getMenetlevelformula() != null ) {
      return getMenetlevelformula();
    } else {
      Verseny verseny = Verseny.newInstance( getVid() );
      verseny.read( getDb() );
      MenetlevelFormulaLista src = new MenetlevelFormulaLista( verseny.getMenetlevelformula() );
      MenetlevelFormulaLista dst = new MenetlevelFormulaLista();
      for ( int i = 0; i < src.size(); ++i ) {
        MenetlevelFormulaResz mlfr = src.get( i );
        if ( mlfr.getMode() == MenetlevelFormulaResz.MODE_DARAB ) {
          int index = getDarabFuggoEtapFeladatok().findItemIndex( "dfftid", new Long( mlfr.getEfid() ) );
          if ( index >= 0 ) dst.add( mlfr );
        } else if ( mlfr.getMode() == MenetlevelFormulaResz.MODE_SORREND ) {
          int index = getSorrendFuggoEtapFeladatok().findItemIndex( "sfftid", new Long( mlfr.getEfid() ) );
          if ( index >= 0 ) dst.add( mlfr );
        } else dst.add( mlfr );
      }
      return dst.toString();
    }
  }

  public long getSzakaszElemId ()
  {
    return getEid();
  }

}
