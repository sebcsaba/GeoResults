package scs.georesults.common.iotools.exml;

import scs.javax.io.IOException;
import scs.javax.io.writers.XmlWriter;

public class Cell implements XmlContent
{

  private Data data;

  private String styleid;

  private Integer index;

  private Integer mergeacross;

  private Integer mergedown;

  public Cell ()
  {}

  public Cell ( Data data )
  {
    this.data = data;
  }

  public Cell ( Data data, String styleid )
  {
    this.data = data;
    this.styleid = styleid;
  }

  public Cell ( Data data, String styleid, int mergeacross, int mergedown )
  {
    this( data, styleid );
    this.mergeacross = new Integer( mergeacross );
    this.mergedown = new Integer( mergedown );
  }

  public Cell ( Data data, String styleid, int mergeacross, int mergedown, int index )
  {
    this( data, styleid );
    this.mergeacross = new Integer( mergeacross );
    this.mergedown = new Integer( mergedown );
    this.index = new Integer( index );
  }

  public void print ( XmlWriter out ) throws IOException
  {
    out.startTag( "Cell" );
    if ( index != null ) out.writeParam( "ss:Index", index.toString() );
    if ( mergeacross != null ) out.writeParam( "ss:MergeAcross", mergeacross.toString() );
    if ( mergedown != null ) out.writeParam( "ss:MergeDown", mergedown.toString() );
    if ( styleid != null ) out.writeParam( "ss:StyleID", styleid );
    if ( data != null ) data.print( out );
    out.endTag( "Cell" );
  }

  public void setData ( Data data )
  {
    this.data = data;
  }

  public void setStyleid ( String styleid )
  {
    this.styleid = styleid;
  }

  public void setIndex ( Integer index )
  {
    this.index = index;
  }

  public void setMergeacross ( Integer mergeacross )
  {
    this.mergeacross = mergeacross;
  }

  public void setMergedown ( Integer mergedown )
  {
    this.mergedown = mergedown;
  }

  public static Cell createCellAndDataFor ( Object value, String dateStyleid, String timeStyleid )
  {
    Cell cell = new Cell( Data.createDataFor( value ) );
    if ( cell.data instanceof DateData ) {
      cell.setStyleid( dateStyleid );
    } else if ( cell.data instanceof TimeData ) {
      cell.setStyleid( timeStyleid );
    }
    return cell;
  }

}
