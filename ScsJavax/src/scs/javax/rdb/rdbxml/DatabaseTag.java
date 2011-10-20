package scs.javax.rdb.rdbxml;

import scs.javax.xml.tags.XmlTag;

public class DatabaseTag extends XmlTag
{

  public static final String TAGNAME = "database";

  protected String[] getParameterNames ()
  {
    return new String[0];
  }

  public String getTagName ()
  {
    return TAGNAME;
  }

  public EntityTag getEntityChild ( int i )
  {
    return ( EntityTag ) getChild( i );
  }

}
