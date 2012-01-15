package scs.javax.xml.objects;

import java.util.Map;
import scs.javax.dii.ClassUtils;
import scs.javax.dii.DIIException;
import scs.javax.dii.ValueWithStaticType;
import scs.javax.xml.tags.XmlTag;

public class ParameterTag extends XmlTag
{

  public static final String TAGNAME = "parameter";

  public String type;

  public ParameterTag ()
  {}

  public ParameterTag ( ValueWithStaticType param, Map objectsToKeys, String key ) throws
          DIIException
  {
    this.type = param.getTheClass().getName();
    addChild( ObjectWrapper.createObjectWrapper( param.getTheValue(), objectsToKeys, key ) );
  }

  public ValueWithStaticType getValueWithType ( Map keysToObjects, String key ) throws DIIException
  {
    Class clazz = ClassUtils.loadClass( type );
    ObjectWrapper ow = ( ObjectWrapper ) getChild( 0 );
    Object item = ow.getObjectValue( keysToObjects, key );
    return new ValueWithStaticType( clazz, item );
  }

  protected String[] getParameterNames ()
  {
    return new String[] {"type"};
  }

  public String getTagName ()
  {
    return TAGNAME;
  }

}
