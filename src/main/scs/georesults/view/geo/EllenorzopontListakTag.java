package scs.georesults.view.geo;

import java.util.Iterator;
import java.util.Map;
import javax.servlet.jsp.JspException;
import scs.javax.collections.List;
import scs.javax.io.IOException;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.GeoException;
import scs.georesults.om.alap.EllenorzoPont;

public class EllenorzopontListakTag extends TagBase
{

  public static final String REQUEST_KEY = "ellenorzoPontListak";

  public int doEndTag () throws JspException
  {
    try {
      Map epm = ( Map ) pageContext.getRequest().getAttribute( REQUEST_KEY );
      if ( epm != null ) doPrint( epm );
      return EVAL_PAGE;
    }
    catch ( Exception ex ) {
      throw new JspException( ex );
    }
  }

  private void doPrint ( Map epm ) throws IOException, GeoException
  {
    out.startTagWithParam( "script", "type", "text/javascript" );
    out.closeOpenTag();
    for ( Iterator it = epm.entrySet().iterator(); it.hasNext(); ) {
      Map.Entry me = ( Map.Entry ) it.next();
      Long sfftid = ( Long ) me.getKey();
      out.write( "var values_sfftid" + sfftid.longValue() + " = [" );
      List epl = ( List ) me.getValue();
      for ( int i = 0; i < epl.size(); ++i ) {
        EllenorzoPont ep = ( EllenorzoPont ) epl.get( i );
        if ( i > 0 ) out.write( "," );
        out.write( "\"" + ep.getFelirat() + "\"" );
      }
      out.write( "];" );
      out.println();
    }
    out.endTag( "script" );
  }

}
