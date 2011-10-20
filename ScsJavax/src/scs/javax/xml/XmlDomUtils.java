package scs.javax.xml;

import org.w3c.dom.*;

public class XmlDomUtils
{

  private XmlDomUtils ()
  {}

///////////////////////////////////////////////////////////////////////////////////////////////////
// Access to part of DOM
///////////////////////////////////////////////////////////////////////////////////////////////////

  public static Element findFirstChild ( Element elem, String tagName )
  {
    int i = findFirstChildIndex( elem, tagName );
    if ( i >= 0 ) {
      return ( Element ) elem.getChildNodes().item( i );
    } else {
      return null;
    }
  }

  public static int findFirstChildIndex ( Element elem, String tagName )
  {
    NodeList nl = elem.getChildNodes();
    for ( int i = 0; i < nl.getLength(); ++i ) {
      if ( nl.item( i ).getNodeName().equals( tagName ) )return i;
    }
    return -1;
  }

  public static void checkTagName ( Element elem, String tagName ) throws XmlDomException
  {
    if ( !elem.getNodeName().equals( tagName ) ) {
      throw new XmlDomException( "unexpected tag '" + elem.getNodeName() + "' instead of '"
                                 + tagName + "'" );
    }
  }

  public static String getRequiredAttribute ( Element elem, String attrName ) throws
          XmlDomException
  {
    if ( !elem.hasAttribute( attrName ) ) {
      throw new UndefinedAttribute( elem, attrName );
    }
    return elem.getAttribute( attrName );
  }

  public static String getAttribute ( Element elem, String attrName ) throws XmlDomException
  {
    if ( !elem.hasAttribute( attrName ) ) {
      return null;
    }
    return elem.getAttribute( attrName );
  }


  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // transform tree or element
  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public static void reduceTree ( Document doc )
  {
    reduceChildrenListRecursive( doc.getDocumentElement() );
  }

  public static void reduceChildrenListRecursive ( Element elem )
  {
    for ( int i = 0; i < elem.getChildNodes().getLength(); ) {
      Node node = elem.getChildNodes().item( i );
      if ( node instanceof Element ) {
        reduceChildrenListRecursive( ( Element ) node );
        ++i;
      } else {
        elem.removeChild( node );
      }
    }
  }

  public static void reduceChildrenList ( Element elem, Class clazz )
  {
    for ( int i = 0; i < elem.getChildNodes().getLength(); ) {
      if ( clazz.isAssignableFrom( elem.getChildNodes().item( i ).getClass() ) ) {
        ++i;
      } else {
        elem.removeChild( elem.getChildNodes().item( i ) );
      }
    }
  }

///////////////////////////////////////////////////////////////////////////////////////////////////
// Exceptions
///////////////////////////////////////////////////////////////////////////////////////////////////

  public static class UndefinedAttribute extends XmlDomException
  {
    public UndefinedAttribute ( Element elem, String attributeName )
    {
      super( "Undefined attribute '" + attributeName + "' in element '" + elem.getNodeName() + "'" );
    }
  }

}
