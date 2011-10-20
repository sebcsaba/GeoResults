package scs.georesults.common.iotools.exml;

import scs.javax.collections.List;
import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;

public class Workbook implements XmlContent
{

  public static final double SCALE = 20.0 / 3.0;

  private DocumentProperties documentProperties;

  private List styles;

  private List worksheets;

  private String comment;

  public Workbook ( String comment )
  {
    styles = new List();
    worksheets = new List();
    this.comment = comment;
  }

  public void print ( XmlWriter out ) throws IOException
  {
    out.println( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
    if ( comment != null ) out.println( "<!-- " + comment + " -->" );
    out.println( "<?mso-application progid=\"Excel.Sheet\"?>" );
    out.startTag( "Workbook" );
    out.writeParam( "xmlns", "urn:schemas-microsoft-com:office:spreadsheet" );
    out.writeParam( "xmlns:ss", "urn:schemas-microsoft-com:office:spreadsheet" );
    if ( documentProperties != null ) documentProperties.print( out );
    out.startTag( "Styles" );
    for ( int i = 0; i < styles.size(); ++i ) getStyle( i ).print( out );
    out.endTag( "Styles" );
    for ( int i = 0; i < worksheets.size(); ++i ) getWorksheet( i ).print( out );
    out.endTag( "Workbook" );
  }

  public int getStyleCount ()
  {
    return styles.size();
  }

  public Style getStyle ( int index )
  {
    return ( Style ) styles.get( index );
  }

  public void addStyle ( Style st )
  {
    styles.add( st );
  }

  public int getWorksheetsCount ()
  {
    return worksheets.size();
  }

  public Worksheet getWorksheet ( int index )
  {
    return ( Worksheet ) worksheets.get( index );
  }

  public void addWorksheet ( Worksheet ws )
  {
    worksheets.add( ws );
  }

  public void setDocumentProperties ( DocumentProperties documentProperties )
  {
    this.documentProperties = documentProperties;
  }

}
