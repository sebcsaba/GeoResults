package scs.javax.xml.tags;

import java.util.HashMap;
import java.util.Map;

public abstract class XmlNamespaceBase implements XmlNamespace
{

  private Map tags;

  protected XmlNamespaceBase ()
  {
    tags = new HashMap();
  }

  protected void add ( String tagName, Class tagClass )
  {
    tags.put( tagName, tagClass );
  }

  public Class forTagName ( String tagName )
  {
    return ( Class ) tags.get( tagName );
  }

}
