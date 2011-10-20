package scs.javax.rdb.mapping.tags;

import scs.javax.xml.tags.XmlTag;

public class AttributeTag extends XmlTag
{

  public static final String TAGNAME = "attribute";

  private String name;

  protected String[] getParameterNames ()
  {
    return new String[]{"name"};
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

}
