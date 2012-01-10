package scs.georesults.view.layout;

import scs.georesults.common.szotar.VersenyResolver;

public class CrudColumnTag extends LayoutTagBase
{

  protected String title;

  protected Object value;

  protected void printHeader () throws Exception
  {
    CrudTableTag table = ( CrudTableTag ) getParent();
    if ( table.isDoHeader() ) {
      out.startTagWithParam( "th", "class", "crudHeader" );
      out.writeString( globalSzotar.getValue( title ) );
      out.endTag( "th" );
    } else {
      out.startTagWithParam( "td", "class", "crudCell" );
      out.writeString( VersenyResolver.doResolve( pageContext, value.toString() ) );
      out.endTag( "td" );
    }
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

}
