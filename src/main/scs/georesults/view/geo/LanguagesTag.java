package scs.georesults.view.geo;

import javax.servlet.jsp.JspException;
import scs.javax.collections.List;
import scs.javax.io.IOException;
import scs.javax.rdb.RdbException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebSession;
import scs.javax.web.taglibs.DynamicTag;
import scs.javax.web.taglibs.TagBase;
import scs.javax.web.taglibs.common.LinkTag;
import scs.javax.web.taglibs.common.WriteTag;
import scs.georesults.GeoDbSession;
import scs.georesults.GeoException;
import scs.georesults.common.Constants;
import scs.georesults.om.kozos.Nyelv;
import scs.georesults.om.kozos.NyelvImpl;
import scs.georesults.om.kozos.SzotarBejegyzes;

public class LanguagesTag extends TagBase
{

  private String action;

  private String forward;

  public int doEndTag () throws JspException
  {
    try {
      printNyelvek();
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  private void printNyelvek () throws GeoException, IOException, JspException, RdbException, SessionTimeoutException
  {
    Nyelv aktNyelv = ( Nyelv ) WebSession.justGetHttpSession( pageContext ).getAttribute( Constants.SESSION_KEY_NYELV );
    List nyelvek = Nyelv.loadAll( GeoDbSession.getCurrentInstance() );
    out.startDivWithClass( "languages" );
    for ( int i = 0; i < nyelvek.size(); ++i ) {
      if ( i > 0 ) out.writeString( " | " );
      Nyelv nyelv = ( Nyelv ) nyelvek.get( i );
      if ( nyelv.getLang().equals( aktNyelv.getLang() ) ) {
        createWriteTag( this, nyelv ).eval();
      } else {
        createLinkTag( this, nyelv ).eval();
      }
    }
    out.endDiv();
  }

  private TagBase createWriteTag ( TagBase parent, Nyelv nyelv ) throws GeoException, RdbException
  {
    SzotarBejegyzes szb = SzotarBejegyzes.newInstance( nyelv.getLang(), NyelvImpl.BUNDLE_PREFIX + nyelv.getLang() );
    szb.read( GeoDbSession.getCurrentInstance() );
    String nyelvCaption = szb.getValuestr();
    WriteTag writeTag = new WriteTag();
    writeTag.init( parent );
    writeTag.setLabel( nyelvCaption );
    return writeTag;
  }

  private TagBase createLinkTag ( TagBase parent, Nyelv nyelv ) throws GeoException, RdbException
  {
    String link = action + "?lang=" + nyelv.getLang() + "&from=" + forward;
    LinkTag linkTag = new LinkTag();
    linkTag.init( parent );
    linkTag.setAction( link );
    DynamicTag linkDt = new DynamicTag( linkTag );
    linkDt.addChild( createWriteTag( linkTag, nyelv ) );
    return linkDt;
  }

  public void setAction ( String action )
  {
    this.action = action;
  }

  public void setForward ( String forward )
  {
    this.forward = forward;
  }

}
