package scs.javax.xml.objects;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import scs.javax.dii.DIIException;
import scs.javax.xml.tags.XmlTag;

public class CollectionTag extends XmlTag
{

  public static final String TAGNAME = "collection";

  public CollectionTag ()
  {}

  public CollectionTag ( Collection collection, Map objectsToKeys, String key ) throws DIIException
  {
    int i = 0;
    for ( Iterator it = collection.iterator(); it.hasNext(); ++i ) {
      Object item = it.next();
      addChild( ObjectWrapper.createObjectWrapper( item, objectsToKeys, key + "[i]" ) );
    }
  }

  public void fillObject ( Collection base, Map keysToObjects, String key ) throws DIIException
  {
    for ( int i = 0; i < getChildrenCount(); ++i ) {
      ObjectWrapper ow = ( ObjectWrapper ) getChild( i );
      Object item = ow.getObjectValue( keysToObjects, key + "[" + i + "]" );
      base.add( item );
    }
  }

  public void fillArray ( Object base, Map keysToObjects, String key ) throws DIIException
  {
    for ( int i = 0; i < getChildrenCount(); ++i ) {
      ObjectWrapper ow = ( ObjectWrapper ) getChild( i );
      Object item = ow.getObjectValue( keysToObjects, key + "[" + i + "]" );
      Array.set( base, i, item );
    }
  }

  protected String[] getParameterNames ()
  {
    return null;
  }

  public String getTagName ()
  {
    return TAGNAME;
  }

}
