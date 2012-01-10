package scs.georesults.view.eredmeny;

import scs.javax.web.WebSession;
import scs.javax.web.taglibs.common.WriteTagHelper;
import scs.georesults.common.Constants;
import scs.georesults.om.verseny.VersenyImpl;
import scs.georesults.view.layout.LayoutTagBase;

public class HeaderTag extends LayoutTagBase
{

  protected String title;

  protected String label;

  protected void printHeader () throws Exception
  {
    String pTitle = WriteTagHelper.getTitleOrLabelOrCaption( pageContext, title, null, null );
    String pLabel = WriteTagHelper.getTitleOrLabelOrCaption( pageContext, null, label, null );
    VersenyImpl verseny = ( VersenyImpl ) WebSession.justGetHttpSession( pageContext ).getAttribute( Constants.SESSION_KEY_VERSENY );
    StringBuffer sb = new StringBuffer();
    sb.append( verseny.getNev() ).append( " - " );
    if ( pTitle != null ) sb.append( pTitle ).append( " - " );
    if ( pLabel != null ) sb.append( pLabel ).append( " - " );
    out.writeString( sb.append( verseny.getDatumok() ).toString() );
  }

  protected void printFooter () throws Exception
  {}

  public void setTitle ( String title )
  {
    this.title = title;
  }

  public void setLabel ( String label )
  {
    this.label = label;
  }

}
