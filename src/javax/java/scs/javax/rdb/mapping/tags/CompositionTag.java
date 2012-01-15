package scs.javax.rdb.mapping.tags;

import scs.javax.xml.tags.XmlTag;

public class CompositionTag extends XmlTag
{

  public static final String TAGNAME = "composition";

  private String name;

  private String className;

  protected String[] getParameterNames ()
  {
    return new String[] {"name", "className"};
  }

  public String getTagName ()
  {
    return TAGNAME;
  }

  public String getName ()
  {
    return name;
  }

  public void setClassName ( String className )
  {
    this.className = className;
  }

  public void setName ( String name )
  {
    this.name = name;
  }

  public String getClassName ()
  {
    return className;
  }
}
