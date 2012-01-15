package scs.javax.rdb.mapping.tags;

import scs.javax.xml.tags.XmlTag;

public class KeyTag extends XmlTag
{

  public static final String TAGNAME = "key";

  private boolean autoIncrement;

  protected String[] getParameterNames ()
  {
    return new String[] {"autoIncrement"};
  }

  public String getTagName ()
  {
    return TAGNAME;
  }

  public void setAutoIncrement ( String autoIncrement )
  {
    this.autoIncrement = Boolean.valueOf( autoIncrement ).booleanValue();
  }

  public String getAutoIncrement ()
  {
    return Boolean.toString( autoIncrement );
  }

  public boolean isAutoIncrement ()
  {
    return autoIncrement;
  }

  public AttributeTag getAttributeChild ( int i )
  {
    return ( AttributeTag ) getChild( i );
  }

}
