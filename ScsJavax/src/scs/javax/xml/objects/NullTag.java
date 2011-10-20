package scs.javax.xml.objects;

import java.util.Map;

public class NullTag extends ObjectWrapper
{

  public static final String TAGNAME = "null";

  public NullTag ()
  {}

  protected String[] getParameterNames ()
  {
    return null;
  }

  public String getTagName ()
  {
    return TAGNAME;
  }

  public Object getObjectValue ( Map keysToObjects, String key )
  {
    return null;
  }

}
