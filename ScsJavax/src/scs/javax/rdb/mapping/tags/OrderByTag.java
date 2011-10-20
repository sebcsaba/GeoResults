package scs.javax.rdb.mapping.tags;

import scs.javax.xml.tags.XmlTag;

public class OrderByTag extends XmlTag
{

  public static final String TAGNAME = "orderBy";

  protected String[] getParameterNames ()
  {
    return null;
  }

  public String getTagName ()
  {
    return TAGNAME;
  }

  public AttributeTag getAttributeChild ( int i )
  {
    return ( AttributeTag ) getChild( i );
  }

}
