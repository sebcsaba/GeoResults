package scs.javax.rdb.mapping;

import java.util.*;
import scs.javax.dii.DIIException;
import scs.javax.io.IOException;
import scs.javax.io.Path;
import scs.javax.rdb.RdbException;
import scs.javax.rdb.mapping.fields.RdbField;
import scs.javax.rdb.mapping.tags.DbMappingNamespace;
import scs.javax.rdb.mapping.tags.MappingTag;
import scs.javax.xml.XmlDomException;

public class MappingPool
{

  private static Map classMappings = new HashMap();

  private MappingPool ()
  {}

  public static void process ( Path filename ) throws XmlDomException, IOException, DIIException, RdbException
  {
    MappingTag mt = DbMappingNamespace.readMapping( filename );
    ClassMapping cm = ClassMappingFactory.newClassMapping( mt );
    classMappings.put( cm.getBaseClass(), cm );
    classMappings.put( cm.getImplClass(), cm );
  }

  public static ClassMapping getClassMapping ( Class clazz ) throws DIIException
  {
    ClassMapping result = ( ClassMapping ) classMappings.get( clazz );
    if ( result != null )return result;
    throw new NoSuchElementException( "unable to find mapping for " + clazz );
  }

  public static RdbField getField ( Class clazz, String propertyName ) throws DIIException
  {
    return getClassMapping( clazz ).getField( propertyName );
  }

  public static Collection getAllClassMappings ()
  {
    return classMappings.values();
  }

}
