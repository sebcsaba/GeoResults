package scs.javax.xml.tags;

import java.util.Iterator;
import java.util.Map;
import scs.javax.dii.ClassUtils;
import scs.javax.dii.DIIException;
import scs.javax.io.*;
import scs.javax.xml.DomSerialization;
import scs.javax.xml.XmlDomException;
import scs.javax.xml.XmlDomUtils;
import org.w3c.dom.*;

public class XmlSerialization
{

  private XmlSerialization ()
  {}

  public static XmlTag read ( String filename, XmlNamespace nsp ) throws XmlDomException, IOException, DIIException
  {
    return read( DomSerialization.read( filename ), nsp );
  }

  public static XmlTag read ( Path filename, XmlNamespace nsp ) throws XmlDomException, IOException, DIIException
  {
    return read( DomSerialization.read( filename ), nsp );
  }

  public static XmlTag read ( InputStream stream, XmlNamespace nsp ) throws XmlDomException, IOException, DIIException
  {
    return read( DomSerialization.read( stream ), nsp );
  }

  public static XmlTag read ( Document dom, XmlNamespace nsp ) throws DIIException
  {
    XmlDomUtils.reduceTree( dom );
    return readTag( dom.getDocumentElement(), nsp );
  }

  private static XmlTag readTag ( Element elem, XmlNamespace nsp ) throws DIIException
  {
    Class tagClass = nsp.forTagName( elem.getNodeName() );
    XmlTag tag = ( XmlTag ) ClassUtils.newInstance( tagClass );
    NamedNodeMap attrs = elem.getAttributes();
    for ( int i = 0; i < attrs.getLength(); ++i ) {
      Node node = attrs.item( i );
      String attrName = node.getNodeName();
      String attrValue = node.getNodeValue();
      tag.setParameter( attrName, attrValue );
    }
    NodeList children = elem.getChildNodes();
    for ( int i = 0; i < children.getLength(); ++i ) {
      Element child = ( Element ) children.item( i );
      tag.addChild( readTag( child, nsp ) );
    }
    return tag;
  }

  public static void write ( String filename, XmlTag root, XmlNamespace nsp ) throws XmlDomException, DIIException, IOException
  {
    DomSerialization.write( filename, write( root, nsp ) );
  }

  public static void write ( Path filename, XmlTag root, XmlNamespace nsp ) throws XmlDomException, DIIException, IOException
  {
    DomSerialization.write( filename, write( root, nsp ) );
  }

  public static void write ( OutputStream stream, XmlTag root, XmlNamespace nsp ) throws XmlDomException, DIIException, IOException
  {
    DomSerialization.write( stream, write( root, nsp ) );
  }

  public static void write ( Writer writer, XmlTag root, XmlNamespace nsp ) throws DIIException, XmlDomException, IOException
  {
    DomSerialization.write( writer, write( root, nsp ) );
  }

  public static Document write ( XmlTag root, XmlNamespace nsp ) throws XmlDomException, DIIException
  {
    Document doc = DomSerialization.newDocument();
    DocumentType dt = doc.getImplementation().createDocumentType( nsp.getRootElementName(), nsp.getPublicID(), nsp.getSystemID() );
    doc.appendChild( dt );
    Element rootElem = writeTag( root, doc );
    doc.appendChild( rootElem );
    return doc;
  }

  private static Element writeTag ( XmlTag tag, Document doc ) throws DIIException
  {
    Element elem = doc.createElement( tag.getTagName() );
    Map params = tag.getParameters();
    for ( Iterator it = params.entrySet().iterator(); it.hasNext(); ) {
      Map.Entry entry = ( Map.Entry ) it.next();
      elem.setAttribute( ( String ) entry.getKey(), ( String ) entry.getValue() );
    }
    for ( int i = 0; i < tag.getChildrenCount(); ++i ) {
      Element child = writeTag( tag.getChild( i ), doc );
      elem.appendChild( child );
    }
    return elem;
  }

}
