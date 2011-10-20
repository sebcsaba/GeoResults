package scs.javax.rdb.mapping.tags;

import scs.javax.xml.tags.XmlTag;

public class FieldTag extends XmlTag
{

  public static final String TAGNAME = "field";

  private String name;

  private String type;

  private boolean nullEnabled;

  private String referencedClass;

  protected String[] getParameterNames ()
  {
    return new String[] {"name", "type", "nullEnabled", "referencedClass"};
  }

  public String getTagName ()
  {
    return TAGNAME;
  }

  public String getName ()
  {
    return name;
  }

  public void setName ( String name )
  {
    this.name = name;
  }

  public String getType ()
  {
    return type;
  }

  public void setType ( String type )
  {
    this.type = type;
  }

  public String getReferencedClass ()
  {
    return referencedClass;
  }

  public void setReferencedClass ( String referencedClass )
  {
    this.referencedClass = referencedClass;
  }

  public void setNullEnabled ( String nullEnabled )
  {
    this.nullEnabled = Boolean.valueOf( nullEnabled ).booleanValue();
  }

  public String getNullEnabled ()
  {
    return Boolean.toString( nullEnabled );
  }

  public boolean isNullEnabled ()
  {
    return nullEnabled;
  }

  public ParamTag getParamChild ( int i )
  {
    return ( ParamTag ) getChild( i );
  }

}
