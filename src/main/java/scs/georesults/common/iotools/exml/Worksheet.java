package scs.georesults.common.iotools.exml;

import scs.javax.collections.List;
import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;

public class Worksheet implements XmlContent
{

  private String name;

  private List columns;

  private List rows;

  private PageSetup pageSetup;

  public Worksheet ()
  {
    columns = new List();
    rows = new List();
  }

  public void print ( XmlWriter out ) throws IOException
  {
    out.startTag( "Worksheet" );
    out.writeParam( "ss:Name", getSheetName() );
    out.startTag( "Table" );
    for ( int i = 0; i < columns.size(); ++i ) getColumn( i ).print( out );
    for ( int i = 0; i < rows.size(); ++i ) getRow( i ).print( out );
    out.endTag( "Table" );
    out.startTag( "WorksheetOptions" );
    out.writeParam( "xmlns", "urn:schemas-microsoft-com:office:excel" );
    if ( pageSetup != null ) pageSetup.print( out );
    out.endTag( "WorksheetOptions" );
    out.endTag( "Worksheet" );
  }

  private String getSheetName ()
  {
    if ( name.length() <= 30 )return name;
    return name.substring( 0, 30 ) + ".";
  }

  public void setName ( String name )
  {
    this.name = name;
  }

  public void setPageSetup ( PageSetup pageSetup )
  {
    this.pageSetup = pageSetup;
  }

  public Column getColumn ( int index )
  {
    return ( Column ) columns.get( index );
  }

  public int getColumnCount ()
  {
    return columns.size();
  }

  public void addColumn ( Column col )
  {
    columns.add( col );
  }

  public Row getRow ( int index )
  {
    return ( Row ) rows.get( index );
  }

  public int getRowCount ()
  {
    return rows.size();
  }

  public void addRow ( Row row )
  {
    rows.add( row );
  }

}
