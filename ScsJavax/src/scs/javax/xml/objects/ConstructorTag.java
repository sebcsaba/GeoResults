package scs.javax.xml.objects;

import java.util.Map;
import scs.javax.dii.ConstructorCall;
import scs.javax.dii.DIIException;
import scs.javax.dii.ValueWithStaticType;
import scs.javax.xml.tags.XmlTag;

public class ConstructorTag extends XmlTag
{

  public static final String TAGNAME = "constructor";

  public ConstructorTag ()
  {}

  public ConstructorTag ( ConstructorCall cc, Map objectsToKeys, String key ) throws DIIException
  {
    for ( int i = 0; i < cc.getParamCount(); ++i ) {
      addChild( new ParameterTag( cc.getParam( i ), objectsToKeys, key + "/init[" + i + "]" ) );
    }
  }

  public ConstructorCall getConstructorCall ( Class clazz, Map keysToObjects, String key ) throws
          DIIException
  {
    ValueWithStaticType[] params = new ValueWithStaticType[getChildrenCount()];
    for ( int i = 0; i < params.length; ++i ) {
      params[i] = ( ( ParameterTag ) getChild( i ) ).getValueWithType( keysToObjects,
              key + "/init[" + i + "]" );
    }
    return new ConstructorCall( clazz, params );
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
