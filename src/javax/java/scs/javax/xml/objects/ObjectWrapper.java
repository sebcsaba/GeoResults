package scs.javax.xml.objects;

import java.util.Map;
import scs.javax.dii.DIIException;
import scs.javax.xml.tags.XmlTag;

public abstract class ObjectWrapper extends XmlTag
{

  public abstract Object getObjectValue ( Map keysToObjects, String key ) throws DIIException;

  public static ObjectWrapper createObjectWrapper ( Object base, Map objectsToKeys, String key ) throws
          DIIException
  {
    if ( base == null )return new NullTag();
    String oldKey = ( String ) objectsToKeys.get( base );
    if ( oldKey != null )return new ReferenceTag( oldKey );
    objectsToKeys.put( base, key );
    return new ObjectTag( base, objectsToKeys, key );
  }

}
