package scs.javax.rdb.mapping;

import java.util.Map;
import scs.javax.collections.List;
import scs.javax.rdb.helper.RdbEntityHelper;

public class ClassMapping extends ClassMappingBase
{

  private Class baseClass;

  private Class implClass;

  private RdbEntityHelper helper;

  public ClassMapping ( Class baseClass, Class implClass, String tableName, List fields, List compositions, PrimaryKey primaryKey, String[] listKey, SortingCondition[] sortingConditions, Map referencedClasses )
  {
    super( tableName, fields, compositions, primaryKey, listKey, sortingConditions, referencedClasses );
    this.baseClass = baseClass;
    this.implClass = implClass;
  }

  public Class getImplClass ()
  {
    return implClass;
  }

  public Class getBaseClass ()
  {
    return baseClass;
  }

  public RdbEntityHelper getHelper ()
  {
    return helper;
  }

  public void setHelper ( RdbEntityHelper helper )
  {
    this.helper = helper;
  }

}
