package scs.javax.xml.objects;

import java.util.HashMap;
import java.util.IdentityHashMap;
import scs.javax.dii.DIIException;
import scs.javax.io.*;
import scs.javax.xml.XmlDomException;
import scs.javax.xml.tags.XmlNamespaceBase;
import scs.javax.xml.tags.XmlSerialization;
import scs.javax.xml.tags.XmlTag;

public class ObjectSerialization extends XmlNamespaceBase
{

  private static final String ROOT_ELEMENT_REF_KEY = "";

  private static ObjectSerialization instance = new ObjectSerialization();

  private ObjectSerialization ()
  {
    add( MapEntryTag.TAGNAME, MapEntryTag.class );
    add( MapTag.TAGNAME, MapTag.class );
    add( CollectionTag.TAGNAME, CollectionTag.class );
    add( ConstructorTag.TAGNAME, ConstructorTag.class );
    add( ParameterTag.TAGNAME, ParameterTag.class );
    add( PropertyTag.TAGNAME, PropertyTag.class );
    add( ObjectTag.TAGNAME, ObjectTag.class );
    add( NullTag.TAGNAME, NullTag.class );
    add( ReferenceTag.TAGNAME, ReferenceTag.class );
  }

  public String getRootElementName ()
  {
    return "object";
  }

  public String getPublicID ()
  {
    return "-//sebcsaba//XmlSerialization 1.0 DTD//EN";
  }

  public String getSystemID ()
  {
    return "";
  }

  public static Object read ( String filename ) throws DIIException, IOException, XmlDomException
  {
    XmlTag tag = XmlSerialization.read( filename, instance );
    return ( ( ObjectWrapper ) tag ).getObjectValue( new HashMap(), ROOT_ELEMENT_REF_KEY );
  }

  public static Object read ( Path filename ) throws DIIException, IOException, XmlDomException
  {
    XmlTag tag = XmlSerialization.read( filename, instance );
    return ( ( ObjectWrapper ) tag ).getObjectValue( new HashMap(), ROOT_ELEMENT_REF_KEY );
  }

  public static Object read ( InputStream stream ) throws DIIException, IOException,
          XmlDomException
  {
    XmlTag tag = XmlSerialization.read( stream, instance );
    return ( ( ObjectWrapper ) tag ).getObjectValue( new HashMap(), ROOT_ELEMENT_REF_KEY );
  }

  public static void write ( String filename, Object base ) throws IOException, DIIException,
          XmlDomException
  {
    ObjectWrapper ow = ObjectWrapper.createObjectWrapper( base, new IdentityHashMap(),
            ROOT_ELEMENT_REF_KEY );
    XmlSerialization.write( filename, ow, instance );
  }

  public static void write ( Path filename, Object base ) throws IOException, DIIException,
          XmlDomException
  {
    ObjectWrapper ow = ObjectWrapper.createObjectWrapper( base, new IdentityHashMap(),
            ROOT_ELEMENT_REF_KEY );
    XmlSerialization.write( filename, ow, instance );
  }

  public static void write ( OutputStream stream, Object base ) throws IOException, DIIException,
          XmlDomException
  {
    ObjectWrapper ow = ObjectWrapper.createObjectWrapper( base, new IdentityHashMap(),
            ROOT_ELEMENT_REF_KEY );
    XmlSerialization.write( stream, ow, instance );
  }

}
