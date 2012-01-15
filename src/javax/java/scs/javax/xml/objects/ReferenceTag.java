package scs.javax.xml.objects;

import java.util.Map;
import scs.javax.dii.DIIException;

public class ReferenceTag extends ObjectWrapper
{

  public static final String TAGNAME = "reference";

  public String key;

  public ReferenceTag ()
  {}

  public ReferenceTag ( String key )
  {
    this.key = key;
  }

  public Object getObjectValue ( Map keysToObjects, String key ) throws DIIException
  {
    Object result = keysToObjects.get( this.key );
    if ( result == null )throw new DIIException( "undefined reference: " + key );
    return result;
  }

  protected String[] getParameterNames ()
  {
    return new String[] {"key"};
  }

  public String getTagName ()
  {
    return TAGNAME;
  }

}
