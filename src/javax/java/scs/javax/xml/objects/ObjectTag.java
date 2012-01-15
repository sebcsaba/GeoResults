package scs.javax.xml.objects;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import scs.javax.collections.List;
import scs.javax.dii.*;
import scs.javax.xml.tags.XmlTag;

public class ObjectTag extends ObjectWrapper
{

  public static final String TAGNAME = "object";

  public String type;

  public String init;

  private ConstructorTag constructor = null;

  private CollectionTag collection = null;

  private MapTag map = null;

  private List properties = new List();

  public ObjectTag ()
  {}

  public ObjectTag ( Object base, Map objectsToKeys, String key ) throws DIIException
  {
    type = base.getClass().getName();
    if ( base.getClass().isArray() ) {
      int length = Array.getLength( base );
      Collection items = new List( length );
      for ( int i = 0; i < length; ++i ) {
        items.add( Array.get( base, i ) );
      }
      addChild( new CollectionTag( items, objectsToKeys, key ) );
    } else {
      createConstructor( base, objectsToKeys, key );
      if ( base instanceof Map )
        addChild( new MapTag( ( Map ) base, objectsToKeys, key ) );
      if ( base instanceof Collection )
        addChild( new CollectionTag( ( Collection ) base, objectsToKeys, key ) );
      if ( base instanceof Serializable )
        addProperties( ( Serializable ) base, objectsToKeys, key );
    }
  }

  private void createConstructor ( Object base, Map objectsToKeys, String key ) throws DIIException
  {
    ConstructorCall cc = null;
    if ( base instanceof Serializable ) {
      Serializable ser = ( Serializable ) base;
      cc = ser.getConstructorCall();
    }
    if ( cc != null ) {
      addChild( new ConstructorTag( cc, objectsToKeys, key ) );
    } else {
      Class baseClass = base.getClass();
      if ( ConstructorUtils.hasConstructor( baseClass, String.class ) ) {
        init = base.toString();
      } else {
        if ( !ConstructorUtils.hasDefaultConstructor( baseClass ) )
          throw new DIIException( "No constructor defined for class " + baseClass.getName() );
      }
    }
  }

  private void addProperties ( Serializable base, Map objectsToKeys, String key ) throws
          DIIException
  {
    String[] props = base.getProperties();
    if ( props != null ) {
      for ( int i = 0; i < props.length; ++i ) {
        addChild( new PropertyTag( base, props[i], objectsToKeys, key ) );
      }
    }
  }

  public Object getObjectValue ( Map keysToObjects, String key ) throws DIIException
  {
    Class clazz = ClassUtils.loadClass( type );
    if ( clazz.isArray() ) {
      Object value = Array.newInstance( clazz.getComponentType(), collection.getChildrenCount() );
      keysToObjects.put( key, value );
      collection.fillArray( value, keysToObjects, key );
      return value;
    } else {
      Object value = createObject( clazz, keysToObjects, key );
      keysToObjects.put( key, value );
      if ( collection != null ) collection.fillObject( ( Collection ) value, keysToObjects, key );
      if ( map != null ) map.fillObject( ( Map ) value, keysToObjects, key );
      for ( Iterator it = properties.iterator(); it.hasNext(); ) {
        PropertyTag propTag = ( PropertyTag ) it.next();
        propTag.setPropertyOnObject( value, keysToObjects, key );
      }
      return value;
    }
  }

  private Object createObject ( Class clazz, Map keysToObjects, String key ) throws DIIException
  {
    ConstructorCall cc = null;
    if ( constructor != null ) {
      cc = constructor.getConstructorCall( clazz, keysToObjects, key );
    } else if ( init != null ) {
      cc = new ConstructorCall( clazz, new ValueWithStaticType( String.class, init ) );
    } else {
      cc = new ConstructorCall( clazz );
    }
    return cc.create();
  }

  protected String[] getParameterNames ()
  {
    return new String[] {"type", "init"};
  }

  public String getTagName ()
  {
    return TAGNAME;
  }

  public void addChild ( XmlTag item )
  {
    super.addChild( item );
    if ( item instanceof ConstructorTag ) constructor = ( ConstructorTag ) item;
    if ( item instanceof CollectionTag ) collection = ( CollectionTag ) item;
    if ( item instanceof MapTag ) map = ( MapTag ) item;
    if ( item instanceof PropertyTag ) properties.add( item );
  }

}
