package scs.georesults.common.iotools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.dii.PropertyUtils;
import scs.javax.io.IOException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.mapping.ClassMapping;
import scs.javax.rdb.mapping.MappingPool;
import scs.javax.rdb.mapping.fields.RdbField;
import scs.georesults.common.iotools.exml.*;
import scs.javax.io.writers.XmlWriter;

/**
 * <p>Microsoft Excel 2003 XML formátumú kimenetet készítő osztály.</p>
 * <p>A kimenet elkészítéséhez felhasználja a {@link scs.georesults.common.iotools.exml}
 * csomag osztályait.</p>
 */
public class DatabaseExmlWriter extends XmlWriterBase
{

  private static final String HEADER_STYLE_ID = "headerStyle";

  private static final String DATE_FORMAT_STYLE_ID = "dateStyle";

  private static final String TIME_FORMAT_STYLE_ID = "timeStyle";

  public DatabaseExmlWriter ( Iterator dbiter ) throws IOException
  {
    super( dbiter );
  }

  protected void doPrint () throws IOException
  {
    try {
      Workbook wb = new Workbook(HEADER);
      Map sheets = new HashMap();
      wb.addStyle( createHeaderStyle() );
      wb.addStyle( createFormatStyle( DATE_FORMAT_STYLE_ID, NumberFormat.FORMAT_SHORT_DATE ) );
      wb.addStyle( createFormatStyle( TIME_FORMAT_STYLE_ID, NumberFormat.FORMAT_SHORT_TIME ) );
      while ( dbiter.hasNext() ) {
        StorableEntityBase entity = ( StorableEntityBase ) dbiter.next();
        processEntity( wb, sheets, entity );
      }
      wb.print( out );
    }
    catch ( DIIException ex ) {
      throw new IOException( ex );
    }
  }

  private void processEntity ( Workbook wb, Map sheets, Object entity ) throws DIIException
  {
    Class clazz = entity.getClass();
    ClassMapping cm = MappingPool.getClassMapping( clazz );
    Worksheet sheet = ( Worksheet ) sheets.get( clazz );
    if ( sheet == null ) {
      sheet = createWorksheet( cm );
      wb.addWorksheet( sheet );
      sheets.put( clazz, sheet );
    }
    addToSheet( cm, sheet, entity );
    for ( Iterator it = cm.getCompositionNameSet().iterator(); it.hasNext(); ) {
      String compName = ( String ) it.next();
      List compList = ( List ) PropertyUtils.getProperty( entity, compName );
      for ( int i = 0; i < compList.size(); ++i ) {
        Object compItem = compList.get( i );
        processEntity( wb, sheets, compItem );
      }
    }
  }

  private Worksheet createWorksheet ( ClassMapping cm ) throws DIIException
  {
    Worksheet sh = new Worksheet();
    sh.setName( cm.getTableName() );
    Row header = new Row();
    for ( int i = 0; i < cm.getFieldCount(); ++i ) {
      RdbField field = cm.getField( i );
      header.addCell( new Cell( new StringData( field.getPropertyName() ), HEADER_STYLE_ID ) );
    }
    sh.addRow( header );
    return sh;
  }

  private void addToSheet ( ClassMapping cm, Worksheet sheet, Object entity ) throws DIIException
  {
    Row dataRow = new Row();
    for ( int i = 0; i < cm.getFieldCount(); ++i ) {
      RdbField field = cm.getField( i );
      Object prop = PropertyUtils.getProperty( entity, field.getPropertyName() );
      dataRow.addCell( Cell.createCellAndDataFor( prop, DATE_FORMAT_STYLE_ID, TIME_FORMAT_STYLE_ID ) );
    }
    sheet.addRow( dataRow );
  }

  private Style createHeaderStyle ()
  {
    Style headerStyle = new Style();
    headerStyle.setId( HEADER_STYLE_ID );
    headerStyle.setAlign( new Alignment( Alignment.ALIGN_H_CENTER, Alignment.ALIGN_V_CENTER ) );
    headerStyle.setFont( new Font( true ) );
    headerStyle.setInterior( new Interior( "#C0C0C0" ) );
    return headerStyle;
  }

  private Style createFormatStyle ( String styleId, String format )
  {
    Style formatStyle = new Style();
    formatStyle.setId( styleId );
    NumberFormat numForm = new NumberFormat();
    numForm.setFormat( format );
    formatStyle.setNumberFormat( numForm );
    return formatStyle;
  }

}
