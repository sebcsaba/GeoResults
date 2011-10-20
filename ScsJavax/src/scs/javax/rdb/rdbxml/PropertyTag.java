package scs.javax.rdb.rdbxml;

import scs.javax.xml.tags.XmlTag;

public class PropertyTag extends XmlTag
{

  public static final String TAGNAME = "property";

  private String name;

  private String value;

  protected String[] getParameterNames ()
  {
    return new String[]{"name","value"};
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

  public void setValue ( String value )
  {
    this.value = value;
  }

  public String getValue ()
  {
    return value;
  }

}
