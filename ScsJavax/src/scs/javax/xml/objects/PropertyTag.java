package scs.javax.xml.objects;

import java.util.Map;
import scs.javax.dii.*;
import scs.javax.xml.tags.XmlTag;

public class PropertyTag extends XmlTag
{

  public static final String TAGNAME = "property";

  public String type;

  public String name;

  public PropertyTag ()
  {}

  public PropertyTag ( Object base, String propName, Map objectsToKeys, String key ) throws
          DIIException
  {
    Property prop = PropertyUtils.createProperty( base.getClass(), propName );
    this.type = prop.getType().getName();
    this.name = propName;
    addChild( ObjectWrapper.createObjectWrapper( prop.getValue( base ), objectsToKeys,
                                                 key + "." + propName ) );
  }

  public void setPropertyOnObject ( Object base, Map keysToObjects, String key ) throws
          DIIException
  {
    Class staticType = ClassUtils.loadClass( type );
    Property prop = PropertyUtils.createTypedProperty( base.getClass(), name, staticType );
    ObjectWrapper ow = ( ObjectWrapper ) getChild( 0 );
    Object item = ow.getObjectValue( keysToObjects, key + "." + name );
    prop.setValue( base, item );
  }

  protected String[] getParameterNames ()
  {
    return new String[] {"name", "type"};
  }

  public String getTagName ()
  {
    return TAGNAME;
  }

}
