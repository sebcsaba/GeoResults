package scs.javax.xml.objects;

import java.util.Map;
import scs.javax.dii.DIIException;
import scs.javax.xml.tags.XmlTag;

public class MapEntryTag extends XmlTag
{

  public static final String TAGNAME = "entry";

  public MapEntryTag ()
  {}

  public MapEntryTag ( Map.Entry entry, Map objectsToKeys, String key, int index ) throws
          DIIException
  {
    addChild( ObjectWrapper.createObjectWrapper( entry.getKey(), objectsToKeys,
                                                 key + "/keys[" + index + "]" ) );
    addChild( ObjectWrapper.createObjectWrapper( entry.getValue(), objectsToKeys,
                                                 key + "/values[" + index + "]" ) );
  }

  public Object[] getEntryValue ( Map keysToObjects, String key, int index ) throws DIIException
  {
    ObjectWrapper owk = ( ObjectWrapper ) getChild( 0 );
    Object oKey = owk.getObjectValue( keysToObjects, key + "/keys[" + index + "]" );
    ObjectWrapper owv = ( ObjectWrapper ) getChild( 1 );
    Object oValue = owv.getObjectValue( keysToObjects, key + "/values[" + index + "]" );
    return new Object[] {oKey, oValue};
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
