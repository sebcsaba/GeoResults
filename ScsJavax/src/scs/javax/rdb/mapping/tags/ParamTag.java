package scs.javax.rdb.mapping.tags;

import scs.javax.xml.tags.XmlTag;

public class ParamTag extends XmlTag
{

  public static final String TAGNAME = "param";

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

  public String getValue ()
  {
    return value;
  }

  public void setName ( String name )
  {
    this.name = name;
  }

  public void setValue ( String value )
  {
    this.value = value;
  }

  public String getName ()
  {
    return name;
  }

}
