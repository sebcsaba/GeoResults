package scs.javax.rdb.rdbxml;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.io.*;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.xml.XmlDomException;

public class RdbXmlUtils
{

  private RdbXmlUtils ()
  {}

  public static DatabaseTag createDatabaseTag ( List entities ) throws RdbException, DIIException
  {
    DatabaseTag dbtag = new DatabaseTag();
    for ( int i = 0; i < entities.size(); ++i ) {
      StorableEntityBase entity = ( StorableEntityBase ) entities.get( i );
      dbtag.addChild( entity.createRdbXmlEntityTag() );
    }
    return dbtag;
  }

  public static List createEntitiesFromDatabaseTag ( DatabaseTag dbtag ) throws DIIException, RdbException
  {
    List result = new List();
    for ( int i = 0; i < dbtag.getChildrenCount(); ++i ) {
      EntityTag entityTag = dbtag.getEntityChild( i );
      result.add( entityTag.createEntity() );
    }
    return result;
  }

  public static void writeDatabase ( String filename, List entities ) throws IOException, DIIException, XmlDomException, RdbException
  {
    RdbXmlMappingNamespace.writeDatabase( filename, createDatabaseTag( entities ) );
  }

  public static void writeDatabase ( Path filename, List entities ) throws IOException, DIIException, XmlDomException, RdbException
  {
    RdbXmlMappingNamespace.writeDatabase( filename, createDatabaseTag( entities ) );
  }

  public static void writeDatabase ( OutputStream stream, List entities ) throws IOException, DIIException, XmlDomException, RdbException
  {
    RdbXmlMappingNamespace.writeDatabase( stream, createDatabaseTag( entities ) );
  }

  public static List readEntitiesFromXml ( String filename ) throws XmlDomException, IOException, DIIException, RdbException
  {
    return createEntitiesFromDatabaseTag( RdbXmlMappingNamespace.readDatabase( filename ) );
  }

  public static List readEntitiesFromXml ( Path filename ) throws XmlDomException, IOException, DIIException, RdbException
  {
    return createEntitiesFromDatabaseTag( RdbXmlMappingNamespace.readDatabase( filename ) );
  }

  public static List readEntitiesFromXml ( InputStream stream ) throws XmlDomException, IOException, DIIException, RdbException
  {
    return createEntitiesFromDatabaseTag( RdbXmlMappingNamespace.readDatabase( stream ) );
  }

}
