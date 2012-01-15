package scs.georesults.view.menetlevel;

import scs.javax.rdb.RdbException;
import scs.georesults.GeoException;
import scs.georesults.view.layout.SimpleFormTag;

public class DarabPopupTag extends PopupTagBase
{

  public String getFirstInputId () throws Exception
  {
    return "dfef_" + getDarabFuggoEtapFeladat().getDfftid();
  }

  protected String getOnclickJS ()
  {
    return "popupDfef(" + getDarabFuggoEtapFeladat().getDfftid() + ");";
  }

  protected void setTitleLabel ( SimpleFormTag tag ) throws GeoException, RdbException
  {
    tag.setLabel( getDarabFuggoEtapFeladat().getDarabFuggoFeladatTipus().getNev() );
  }

}
