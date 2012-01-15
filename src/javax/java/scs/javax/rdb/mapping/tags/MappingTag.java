package scs.javax.rdb.mapping.tags;

import scs.javax.xml.tags.XmlTag;
import scs.javax.collections.List;

public class MappingTag extends XmlTag
{

  public static final String TAGNAME = "mapping";

  private String className;

  private String table;

  private Integer fieldCount;

  protected String[] getParameterNames ()
  {
    return new String[] {"className", "table"};
  }

  public String getTagName ()
  {
    return TAGNAME;
  }

  public XmlTag getChild ( int i )
  {
    return super.getChild( i );
  }

  public String getTable ()
  {
    return table;
  }

  public void setClassName ( String className )
  {
    this.className = className;
  }

  public void setTable ( String table )
  {
    this.table = table;
  }

  public String getClassName ()
  {
    return className;
  }

  public int getFieldCount ()
  {
    if ( fieldCount == null ) {
      int i = 0;
      for ( ; i < getChildrenCount(); ++i ) {
        if ( ! ( getChild( i ) instanceof FieldTag ) )break;
      }
      fieldCount = new Integer( i );
    }
    return fieldCount.intValue();
  }

  public List getAllFields ()
  {
    List result = new List( getFieldCount() );
    for ( int i = 0; i < getFieldCount(); ++i ) result.add( ( FieldTag ) getChild( i ) );
    return result;
  }

  public List getAllCompositions ()
  {
    int count = getChildrenCount() - getFieldCount() - 3;
    List result = new List( count );
    for ( int i = 0; i < count; ++i ) result.add( ( CompositionTag ) getChild( getFieldCount() + i ) );
    return result;
  }

  public KeyTag getKeyTag ()
  {
    return ( KeyTag ) getChild( getChildrenCount() - 3 );
  }

  public ListKeyTag getListKeyTag ()
  {
    return ( ListKeyTag ) getChild( getChildrenCount() - 2 );
  }

  public OrderByTag getOrderByTag ()
  {
    return ( OrderByTag ) getChild( getChildrenCount() - 1 );
  }

}
