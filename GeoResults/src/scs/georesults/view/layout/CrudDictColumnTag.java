package scs.georesults.view.layout;

import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebSession;
import scs.georesults.common.Constants;
import scs.georesults.common.szotar.GlobalSzotar;

public class CrudDictColumnTag extends LayoutTagBase
{

  protected String title;

  protected Object value;

  protected String prefix;

  protected void printHeader () throws Exception
  {
    CrudTableTag table = ( CrudTableTag ) getParent();
    if ( table.isDoHeader() ) {
      out.startTagWithParam( "th", "class", "crudHeader" );
      out.writeString( globalSzotar.getValue( title ) );
      out.endTag( "th" );
    } else {
      out.startTagWithParam( "td", "class", "crudCell" );
      out.writeString( getCaption() );
      out.endTag( "td" );
    }
  }

  protected String getCaption () throws SessionTimeoutException
  {
    GlobalSzotar globalSzotar = ( GlobalSzotar ) WebSession.justGetHttpSession( pageContext ).getAttribute( Constants.SESSION_KEY_GLOBAL_SZOTAR );
    return globalSzotar.getValue( prefix + value.toString() );
  }

  protected void printFooter () throws Exception
  {}

  public void setTitle ( String title )
  {
    this.title = title;
  }

  public void setValue ( Object value )
  {
    this.value = value;
  }

  public void setPrefix ( String prefix )
  {
    this.prefix = prefix;
  }

}
