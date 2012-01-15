package scs.javax.collections;

import java.util.HashMap;
import scs.javax.dii.ClassUtils;
import scs.javax.dii.DIIException;

public class AutoInsertHashMap extends HashMap
{

  private Class baseClass;

  private Object baseObject;

  public AutoInsertHashMap ( Class baseClass )
  {
    super();
    this.baseClass = baseClass;
    this.baseObject = null;
  }

  public AutoInsertHashMap ( Object baseObject )
  {
    super();
    this.baseClass = null;
    this.baseObject = baseObject;
  }

  public Object get ( Object key )
  {
    Object result = super.get( key );
    if ( result == null ) {
      result = getNewValue();
      super.put( key, result );
    }
    return result;
  }

  private Object getNewValue ()
  {
    try {
      if ( baseClass != null ) {
        return ClassUtils.newInstance( baseClass );
      } else if ( baseObject != null ) {
        return baseObject;
      } else return null;
    }
    catch ( DIIException ex ) {
      return null;
    }
  }

}
