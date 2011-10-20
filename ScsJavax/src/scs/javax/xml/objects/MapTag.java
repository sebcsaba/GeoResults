package scs.javax.xml.objects;

import java.util.Iterator;
import java.util.Map;
import scs.javax.dii.DIIException;
import scs.javax.xml.tags.XmlTag;

public class MapTag extends XmlTag
{

  public static final String TAGNAME = "map";

  public MapTag ()
  {}

  public MapTag ( Map map, Map objectsToKeys, String key ) throws DIIException
  {
    int i = 0;
    for ( Iterator it = map.entrySet().iterator(); it.hasNext(); ++i ) {
      Map.Entry entry = ( Map.Entry ) it.next();
      addChild( new MapEntryTag( entry, objectsToKeys, key, i ) );
    }
  }

  public void fillObject ( Map base, Map keysToObjects, String key ) throws DIIException
  {
    for ( int i = 0; i < getChildrenCount(); ++i ) {
      MapEntryTag met = ( MapEntryTag ) getChild( i );
      Object[] pair = met.getEntryValue( keysToObjects, key, i );
      base.put( pair[0], pair[1] );
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
