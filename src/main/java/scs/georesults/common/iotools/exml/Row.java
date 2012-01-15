package scs.georesults.common.iotools.exml;

import scs.javax.collections.List;
import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;

public class Row implements XmlContent
{

  private Double height;

  private List cells;

  public Row ()
  {
    cells = new List();
  }

  public void print ( XmlWriter out ) throws IOException
  {
    out.startTag( "Row" );
    if ( height != null ) out.writeParam( "ss:Height", height.toString() );
    for ( int i = 0; i < cells.size(); ++i ) getCell( i ).print( out );
    out.endTag( "Row" );
  }

  public void setHeight ( Double height )
  {
    this.height = height;
  }

  public Cell getCell ( int index )
  {
    return ( Cell ) cells.get( index );
  }

  public int getCellCount ()
  {
    return cells.size();
  }

  public void addCell ( Cell cell )
  {
    cells.add( cell );
  }

}
