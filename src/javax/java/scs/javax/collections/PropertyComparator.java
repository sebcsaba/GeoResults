package scs.javax.collections;

import java.util.Comparator;
import scs.javax.dii.DIIException;
import scs.javax.dii.PropertyUtils;

public class PropertyComparator implements Comparator
{

  private String propertyName;

  public PropertyComparator ( String propertyName )
  {
    // force NullPointerException
    this.propertyName = propertyName.toString();
  }

  public int compare ( Object o1, Object o2 )
  {
    try {
      Comparable v1 = ( Comparable ) PropertyUtils.getProperty( o1, propertyName );
      Comparable v2 = ( Comparable ) PropertyUtils.getProperty( o2, propertyName );
      if ( v1 == null && v2 == null ) {
        return 0;
      }
      if ( v1 != null ) {
        return v1.compareTo( v2 );
      } else {
        return -v2.compareTo( v1 );
      }
    }
    catch ( DIIException ex ) {
      throw new Error( ex );
    }
  }

  public boolean equals ( Object obj )
  {
    PropertyComparator c = ( PropertyComparator ) obj;
    return c.propertyName.equals( propertyName );
  }

  public int hashCode ()
  {
    return super.hashCode() + propertyName.hashCode();
  }

}
