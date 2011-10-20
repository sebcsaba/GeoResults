package scs.javax.rdb.rdbxml;

import scs.javax.dii.DIIException;
import scs.javax.io.*;
import scs.javax.xml.XmlDomException;
import scs.javax.xml.tags.XmlNamespaceBase;
import scs.javax.xml.tags.XmlSerialization;

public class RdbXmlMappingNamespace extends XmlNamespaceBase
{

  private static RdbXmlMappingNamespace instance = new RdbXmlMappingNamespace();

  public RdbXmlMappingNamespace ()
  {
    add( DatabaseTag.TAGNAME, DatabaseTag.class );
    add( EntityTag.TAGNAME, EntityTag.class );
    add( PropertyTag.TAGNAME, PropertyTag.class );
    add( SublistTag.TAGNAME, SublistTag.class );
  }

  public String getRootElementName ()
  {
    return "database";
  }

  public String getPublicID ()
  {
    return "-//sebcsaba//RdbXmlMapping 1.0 DTD//EN";
  }

  public String getSystemID ()
  {
    return "";
  }

  public static DatabaseTag readDatabase ( String filename ) throws DIIException, IOException, XmlDomException
  {
    return ( DatabaseTag ) XmlSerialization.read( filename, instance );
  }

  public static DatabaseTag readDatabase ( Path filename ) throws DIIException, IOException, XmlDomException
  {
    return ( DatabaseTag ) XmlSerialization.read( filename, instance );
  }

  public static DatabaseTag readDatabase ( InputStream stream ) throws DIIException, IOException, XmlDomException
  {
    return ( DatabaseTag ) XmlSerialization.read( stream, instance );
  }

  public static void writeDatabase ( String filename, DatabaseTag tag ) throws IOException, DIIException, XmlDomException
  {
    XmlSerialization.write( filename, tag, instance );
  }

  public static void writeDatabase ( Path filename, DatabaseTag tag ) throws IOException, DIIException, XmlDomException
  {
    XmlSerialization.write( filename, tag, instance );
  }

  public static void writeDatabase ( OutputStream stream, DatabaseTag tag ) throws IOException, DIIException, XmlDomException
  {
    XmlSerialization.write( stream, tag, instance );
  }

  public static void writeDatabase ( Writer writer, DatabaseTag tag ) throws IOException, DIIException, XmlDomException
  {
    XmlSerialization.write( writer, tag, instance );
  }

}
