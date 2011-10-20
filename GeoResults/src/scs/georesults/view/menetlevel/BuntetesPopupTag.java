package scs.georesults.view.menetlevel;

import scs.georesults.view.layout.SimpleFormTag;

public class BuntetesPopupTag extends PopupTagBase
{

  public String getFirstInputId () throws Exception
  {
    return "buntetesek";
  }

  protected String getOnclickJS ()
  {
    return "popupBuntetesek();";
  }

  protected void setTitleLabel ( SimpleFormTag tag )
  {
    tag.setTitle( "RA_BUNTETESEK" );
  }

}
