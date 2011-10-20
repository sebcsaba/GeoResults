package scs.georesults.view.menetlevel;

import scs.georesults.view.layout.SimpleFormTag;

public class SorrendPopupTag extends PopupTagBase
{

  public String getFirstInputId () throws Exception
  {
    return "sfef_" + getSorrendFuggoEtapFeladat().getSfftid();
  }

  protected String getOnclickJS ()
  {
    return "popupSfef(" + getSorrendFuggoEtapFeladat().getSfftid() + "," + count + ");";
  }

  protected void setTitleLabel ( SimpleFormTag tag ) throws Exception
  {
    tag.setLabel( getSorrendFuggoEtapFeladat().getSorrendFuggoFeladatTipus().getNev() );
  }

}
