package scs.georesults;

import scs.javax.collections.List;
import scs.javax.dii.ClassUtils;
import scs.javax.io.Path;
import scs.javax.rdb.helper.Factory;
import scs.javax.rdb.helper.RdbEntityHelper;
import scs.javax.rdb.mapping.ClassMappingFactory;
import scs.javax.rdb.mapping.DeclarativeClassMapping;
import scs.javax.rdb.mapping.tags.DbMappingNamespace;
import scs.javax.rdb.mapping.tags.MappingTag;
import scs.javax.rdb.sql.SqlFactory;
import scs.javax.rdb.writer.ClassWriter;

/**
 * <p>Egy olyan segédprogram főosztálya, amely legenerálja az adatmodell alaposztályait. Az implementációs osztályokat csak akkor generálja, ha az adott fájl még nem létezik.</p>
 */
public class OmGenerate
{

  /**
   * A segédprogramot futtató metódus.
   */
  public static void main ( String[] args )
  {
    try {
      Factory factory = new SqlFactory();
      List dcms = new List( GeoDbSession.mappingXmls.length );
      for ( int i = 0; i < GeoDbSession.mappingXmls.length; ++i ) {
        Path xmlFile = ClassUtils.getSystemResourcePath( GeoDbSession.mappingXmls[i] );
        MappingTag mt = DbMappingNamespace.readMapping( xmlFile );
        DeclarativeClassMapping cm = ClassMappingFactory.newDeclarativeClassMapping( mt );
        ClassWriter.registerClassMapping( cm );
        dcms.add( cm );
      }
      for ( int i = 0; i < dcms.size(); ++i ) {
        DeclarativeClassMapping cm = ( DeclarativeClassMapping ) dcms.get( i );
        ClassWriter classwriter = new ClassWriter( cm );
        classwriter.write( new Path( "src/" ), false );
        RdbEntityHelper helper = factory.createEntityHelper( cm );
        System.out.println( helper.createCreateTableStatement() );
      }
    }
    catch ( Exception ex ) {
      ex.printStackTrace( System.err );
    }
  }

}
