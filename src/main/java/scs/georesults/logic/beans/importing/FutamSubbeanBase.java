package scs.georesults.logic.beans.importing;

import javax.servlet.http.HttpServletRequest;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.RdbImportSession;
import scs.javax.rdb.StorableEntityBase;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.Futam;
import scs.georesults.common.SzakaszElem;
import scs.georesults.om.nevezes.Nevezes;
import scs.georesults.om.verseny.Verseny;
import scs.georesults.om.verseny.VersenyImpl;

public abstract class FutamSubbeanBase extends EsemenySubbeanBase
{

  protected long sourceEsemenyId;

  protected SzakaszElem sourceEsemeny;

  protected long hovaEsemenyId;

  protected SzakaszElem hovaEsemeny;

  public abstract boolean isMenetlevel ();

  public void checkForNyelvOrszag ()
  {}

  public abstract List getAllCurrentFutamok () throws RdbException, GeoException;

  public abstract List getAllSourceFutamok () throws RdbException, GeoException;

  public abstract void testVanCurrentEsemeny ( VersenyImpl verseny ) throws RdbException, GeoException;

  public void setSourceEsemenyId ( long sourceEsemenyId )
  {
    this.sourceEsemenyId = sourceEsemenyId;
  }

  public void setHovaEsemenyId ( long hovaEsemenyId )
  {
    this.hovaEsemenyId = hovaEsemenyId;
  }

  public long getSourceEsemenyId ()
  {
    return sourceEsemenyId;
  }

  public long getHovaEsemenyId ()
  {
    return hovaEsemenyId;
  }

  public abstract void updateSourceEsemeny () throws GeoException, RdbException;

  public SzakaszElem getSourceEsemeny () throws GeoException, RdbException
  {
    if ( sourceEsemeny == null ) updateSourceEsemeny();
    return sourceEsemeny;
  }

  public abstract void updateHovaEsemeny () throws GeoException, RdbException;

  public SzakaszElem getHovaEsemeny () throws GeoException, RdbException
  {
    if ( hovaEsemeny == null ) updateHovaEsemeny();
    return hovaEsemeny;
  }

  protected abstract void doDependenciesBeforeImport ( RdbImportSession importSession, Long currentVid ) throws GeoException, DIIException, RdbException;

  public void doImport ( RdbImportSession importSession, HttpServletRequest request ) throws GeoException, RdbException, DIIException
  {
    Long currentVid = new Long( importBean.getCurrentVerseny().getVid() );
    doDependenciesBeforeImport( importSession, currentVid );
    List currentFutamok = getAllCurrentFutamok();
    List sourceFutamok = getAllSourceFutamok();
    for ( int i = 0; i < currentFutamok.size(); ++i ) {
      Futam futam = ( Futam ) currentFutamok.get( i );
      if ( sourceFutamok.findItemIndex( "rajtszam", new Integer( futam.getRajtszam() ) ) >= 0 ) {
        futam.delete( GeoDbSession.getCurrentInstance() );
      }
    }
    List currentNevezesek = Nevezes.loadAllForVerseny( getSourceSession(), getSourceVerseny() );
    importSession.addRemap( Verseny.class, getSourceVerseny().getVid(), currentVid.longValue() );
    importSession.addRemap( getEsemenyClass(), getSourceEsemeny().getSzakaszElemId(), getHovaEsemeny().getSzakaszElemId() );
    for ( int i = 0; i < sourceFutamok.size(); ++i ) {
      Futam futam = ( Futam ) sourceFutamok.get( i );
      if ( currentNevezesek.findItemIndex( "rajtszam", new Integer( futam.getRajtszam() ) ) >= 0 ) {
        importSession.importEntity( ( StorableEntityBase ) futam );
      }
    }
    hovaEsemeny.read( GeoDbSession.getCurrentInstance() );
    hovaEsemeny.setEredmenyFrissitendo( true );
    hovaEsemeny.update( GeoDbSession.getCurrentInstance() );
  }

}
