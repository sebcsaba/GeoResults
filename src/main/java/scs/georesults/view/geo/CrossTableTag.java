package scs.georesults.view.geo;

import javax.servlet.http.HttpServletRequest;
import scs.javax.collections.List;
import scs.javax.io.IOException;
import scs.javax.rdb.RdbException;
import scs.georesults.GeoDbSession;
import scs.georesults.common.szotar.GlobalSzotar;
import scs.georesults.om.kozos.Nyelv;
import scs.georesults.om.kozos.SzotarBejegyzes;
import scs.georesults.view.layout.LayoutTagBase;

public class CrossTableTag extends LayoutTagBase
{

  private String title;

  private String action;

  private List keys;

  protected void printHeader () throws Exception
  {
    doPrintPanelHeader( GlobalSzotar.resolve( pageContext, title ) );
  }

  protected void printFooter () throws Exception
  {
    GeoDbSession db = GeoDbSession.getCurrentInstance();
    StringBuffer linkBuffer = new StringBuffer();
    List nyelvek = Nyelv.loadAll( db );
    out.startTagWithParam( "table", "class", "crossTable" );
    out.startTag( "tr" );
    printCell( true, "" );
    for ( int i = 0; i < nyelvek.size(); ++i ) {
      Nyelv nyelvOszlop = ( Nyelv ) nyelvek.get( i );
      printCell( true, nyelvOszlop.getLang() );
    }
    out.endTag( "tr" );
    for ( int i = 0; i < keys.size(); ++i ) {
      String rowKey = ( String ) keys.get( i );
      out.startTag( "tr" );
      printCell( true, rowKey.substring( 3 ) );
      for ( int j = 0; j < nyelvek.size(); ++j ) {
        Nyelv nyelvOszlop = ( Nyelv ) nyelvek.get( j );
        printCell( false, createLink( rowKey, nyelvOszlop, linkBuffer, db ) );
      }
      out.endTag( "tr" );
    }
    out.endTag( "table" );
    doPrintPanelFooter();
  }

  private String createLink ( String rowKey, Nyelv nyelvOszlop, StringBuffer linkBuffer, GeoDbSession db ) throws RdbException
  {
    linkBuffer.setLength( 0 );
    linkBuffer.append( "<a href=\"" );
    final String contextPath = ( ( HttpServletRequest ) pageContext.getRequest() ).getContextPath();
    if (!contextPath.equals("/")) {
	  linkBuffer.append( contextPath );
    }
    linkBuffer.append( action );
    linkBuffer.append( "&lang=" ).append( nyelvOszlop.getLang() );
    linkBuffer.append( "&key=" ).append( rowKey );
    SzotarBejegyzes szb = SzotarBejegyzes.newInstance( nyelvOszlop.getLang(), rowKey );
    szb.read( db );
    linkBuffer.append( "\">" );
    String value = szb.getValuestr();
    if ( value.length() == 0 ) value = "&nbsp;";
    linkBuffer.append( value );
    linkBuffer.append( "</a>" );
    return linkBuffer.toString();
  }

  private void printCell ( boolean isHeader, String content ) throws IOException
  {
    out.startTagWithParam( isHeader ? "th" : "td", "class", "ct" );
    out.writeString( content );
    out.endTag( isHeader ? "th" : "td" );
  }

  public void setTitle ( String title )
  {
    this.title = title;
  }

  public void setAction ( String action )
  {
    this.action = action;
  }

  public void setKeys ( List keys )
  {
    this.keys = keys;
  }

}
