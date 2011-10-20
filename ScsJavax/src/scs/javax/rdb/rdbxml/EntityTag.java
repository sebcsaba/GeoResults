package scs.javax.rdb.rdbxml;

import scs.javax.xml.tags.XmlTag;
import scs.javax.rdb.StorableEntityBase;
import scs.javax.rdb.mapping.ClassMapping;
import scs.javax.rdb.mapping.MappingPool;
import scs.javax.dii.ClassUtils;
import scs.javax.dii.DIIException;
import scs.javax.rdb.mapping.fields.RdbField;
import scs.javax.rdb.StorableObjectList;
import scs.javax.rdb.RdbException;

public class EntityTag extends XmlTag
{

  public static final String TAGNAME = "entity";

  private String type;

  protected String[] getParameterNames ()
  {
    return new String[] {"type"};
  }

  public String getTagName ()
  {
    return TAGNAME;
  }

  public void setType ( String type )
  {
    this.type = type;
  }

  public String getType ()
  {
    return type;
  }

  public StorableEntityBase createEntity () throws DIIException, RdbException
  {
    ClassMapping cm = MappingPool.getClassMapping( ClassUtils.loadClass( getType() ) );
    StorableEntityBase entity = ( StorableEntityBase ) ClassUtils.newInstance( cm.getImplClass() );
    for ( int i = 0; i < getChildrenCount(); ++i ) {
      XmlTag childTag = getChild( i );
      if ( childTag instanceof PropertyTag ) {
        PropertyTag propertyTag = ( PropertyTag ) childTag;
        RdbField field = cm.getField( propertyTag.getName() );
        field.setValueFromXmlLiteralToEntity( entity, propertyTag.getValue() );
      } else if ( childTag instanceof SublistTag ) {
        SublistTag sublistTag = ( SublistTag ) childTag;
        StorableObjectList sublist = entity.getCompositionValue( sublistTag.getName() );
        sublist.fillFromRdbXmlSublistTag( sublistTag );
      }
    }
    return entity;
  }

}
