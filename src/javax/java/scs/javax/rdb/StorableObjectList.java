package scs.javax.rdb;

import java.util.Iterator;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.mapping.ClassMapping;
import scs.javax.rdb.mapping.MappingPool;
import scs.javax.rdb.rdbxml.EntityTag;
import scs.javax.rdb.rdbxml.SublistTag;
import scs.javax.xml.tags.XmlTag;
import scs.javax.rdb.helper.RdbEntityHelper;

public abstract class StorableObjectList extends List implements StorableClass
{

  protected abstract Object getKeyEntity ();

  private ClassMapping getClassMapping () throws DIIException
  {
    return MappingPool.getClassMapping( getKeyEntity().getClass() );
  }

  public void create ( RdbSession session ) throws RdbException
  {
    for ( int i = 0; i < size(); ++i ) {
      StorableEntityBase entity = ( StorableEntityBase ) get( i );
      entity.create( session );
    }
  }

  public void read ( RdbSession session ) throws RdbException
  {
    try {
      ClassMapping cm = MappingPool.getClassMapping( getKeyEntity().getClass() );
      RdbEntityHelper helper = session.getEntityHelper( cm );
      List src = session.queryAll( helper.createReadListStatement( getKeyEntity() ), cm );
      clear();
      for ( int i = 0; i < src.size(); ++i ) {
        StorableEntityBase entity = ( StorableEntityBase ) src.get( i );
        if ( cm.hasAnyComposition() ) {
          for ( Iterator cit = cm.getCompositionNameSet().iterator(); cit.hasNext(); ) {
            String compName = ( String ) cit.next();
            entity.getCompositionValue( compName ).read( session );
          }
        }
        add( entity );
      }
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  public void update ( RdbSession session ) throws RdbException
  {
    delete( session );
    create( session );
  }

  public void delete ( RdbSession session ) throws RdbException
  {
    try {
      for ( int i = 0; i < size(); ++i ) {
        StorableEntityBase entity = ( StorableEntityBase ) get( i );
        entity.delete( session );
      }
      //SCS: a listából már törölt elemek esetleges függő részeit itt törli kaszkádoltan
      ClassMapping cm = getClassMapping();
      RdbEntityHelper helper = session.getEntityHelper( cm );
      List cascade = session.queryAll( helper.createReadListStatement( getKeyEntity() ), cm );
      for ( int i = 0; i < cascade.size(); ++i ) {
        StorableEntityBase entity = ( StorableEntityBase ) cascade.get( i );
        entity.delete( session );
      }
    }
    catch ( DIIException ex ) {
      throw new RdbException( ex );
    }
  }

  public XmlTag createRdbXmlEntityTag () throws RdbException, DIIException
  {
    SublistTag sublistTag = new SublistTag();
    for ( int i = 0; i < size(); ++i ) {
      StorableEntityBase entity = ( StorableEntityBase ) get( i );
      sublistTag.addChild( entity.createRdbXmlEntityTag() );
    }
    return sublistTag;
  }

  public void fillFromRdbXmlSublistTag ( SublistTag sublistTag ) throws DIIException, RdbException
  {
    clear();
    for ( int i = 0; i < sublistTag.getChildrenCount(); ++i ) {
      EntityTag entityTag = sublistTag.getEntityChild( i );
      add( entityTag.createEntity() );
    }
  }

}
