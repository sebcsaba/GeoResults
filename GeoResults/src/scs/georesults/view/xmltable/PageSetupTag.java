package scs.georesults.view.xmltable;

import javax.servlet.jsp.JspException;
import scs.javax.io.IOException;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.WebSession;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.common.Constants;
import scs.georesults.om.verseny.VersenyImpl;

public class PageSetupTag extends TagBase
{

  // original metric is inch
  public static final double EXML_PRINT_SIZE_SCALE = 1 / 25.4;

  private static final double leftMargin = 10;

  private static final double rightMargin = 10;

  private static final double topMargin = 12;

  private static final double bottomMargin = 10;

  private static final double headerMargin = 5;

  private static final double footerMargin = 3;

  public int doEndTag () throws JspException
  {
    try {
      out.startTag( "WorksheetOptions" );
      out.writeParam( "xmlns", "urn:schemas-microsoft-com:office:excel" );
      out.startTag( "PageSetup" );
      printLayout();
      VersenyImpl verseny = ( VersenyImpl ) WebSession.justGetHttpSession( pageContext ).getAttribute( Constants.SESSION_KEY_VERSENY );
      printInfobar( "Header", headerMargin, "&amp;B&amp;&quot;Arial,Dőlt&quot;&amp;16" + verseny.getNev() + " - &amp;U - " + verseny.getDatumok() );
      printInfobar( "Footer", footerMargin, "&amp;BGeoResults - © SebCsaba 2006&amp;J&amp;D &amp;I" );
      printMargins();
      out.endTag( "PageSetup" );
      out.startTag( "Print" );
      out.writeEmptyTag( "ValidPrinterInfo" );
      out.writeTextContentTag( "PaperSizeIndex", "9" );
      out.writeTextContentTag( "HorizontalResolution", "-3" );
      out.writeTextContentTag( "VerticalResolution", "-3" );
      out.endTag( "Print" );
      out.endTag( "WorksheetOptions" );
      return EVAL_PAGE;
    }
    catch ( IOException ex ) {
      throw new JspException( ex );
    }
    catch ( SessionTimeoutException ex ) {
      throw new JspException( ex );
    }

  }

  private void printInfobar ( String elem, double margin, String data ) throws IOException
  {
    out.startTag( elem );
    out.writeParam( "x:Margin", Double.toString( margin * EXML_PRINT_SIZE_SCALE ) );
    out.writeParam( "x:Data", data );
    out.endTag( elem );
  }

  private void printLayout () throws IOException
  {
    out.startTagWithParam( "Layout", "x:Orientation", "Landscape" );
    out.endTag( "Layout" );
  }

  private void printMargins () throws IOException
  {
    out.startTag( "PageMargins" );
    out.writeParam( "x:Left", Double.toString( leftMargin * EXML_PRINT_SIZE_SCALE ) );
    out.writeParam( "x:Right", Double.toString( rightMargin * EXML_PRINT_SIZE_SCALE ) );
    out.writeParam( "x:Top", Double.toString( topMargin * EXML_PRINT_SIZE_SCALE ) );
    out.writeParam( "x:Bottom", Double.toString( bottomMargin * EXML_PRINT_SIZE_SCALE ) );
    out.endTag( "PageMargins" );
  }

}
