package scs.javax.rdb.rdbxml;

import scs.javax.xml.tags.XmlTag;

public class SublistTag extends XmlTag
{

  public static final String TAGNAME = "sublist";

  private String name;

  protected String[] getParameterNames ()
  {
    return new String[] {"name"};
  }

  public String getTagName ()
  {
    return TAGNAME;
  }

  public void setName ( String name )
  {
    this.name = name;
  }

  public String getName ()
  {
    return name;
  }

  public EntityTag getEntityChild ( int i )
  {
    return ( EntityTag ) getChild( i );
  }

}
