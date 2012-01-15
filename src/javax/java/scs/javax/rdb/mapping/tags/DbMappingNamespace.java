package scs.javax.rdb.mapping.tags;

import scs.javax.dii.DIIException;
import scs.javax.io.IOException;
import scs.javax.io.Path;
import scs.javax.xml.XmlDomException;
import scs.javax.xml.tags.XmlNamespaceBase;
import scs.javax.xml.tags.XmlSerialization;

public class DbMappingNamespace extends XmlNamespaceBase
{

  private static DbMappingNamespace instance = new DbMappingNamespace();

  public DbMappingNamespace ()
  {
    add( AttributeTag.TAGNAME, AttributeTag.class );
    add( CompositionTag.TAGNAME, CompositionTag.class );
    add( FieldTag.TAGNAME, FieldTag.class );
    add( KeyTag.TAGNAME, KeyTag.class );
    add( ListKeyTag.TAGNAME, ListKeyTag.class );
    add( MappingTag.TAGNAME, MappingTag.class );
    add( OrderByTag.TAGNAME, OrderByTag.class );
    add( ParamTag.TAGNAME, ParamTag.class );
  }

  public String getRootElementName ()
  {
    return "mapping";
  }

  public String getPublicID ()
  {
    return "-//sebcsaba//DatabaseClassMapping 1.0 DTD//EN";
  }

  public String getSystemID ()
  {
    return "";
  }

  public static MappingTag readMapping ( String filename ) throws DIIException, IOException, XmlDomException
  {
    return ( MappingTag ) XmlSerialization.read( filename, instance );
  }

  public static MappingTag readMapping ( Path filename ) throws DIIException, IOException, XmlDomException
  {
    return ( MappingTag ) XmlSerialization.read( filename, instance );
  }

}
