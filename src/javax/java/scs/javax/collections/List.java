package scs.javax.collections;

import java.util.*;
import scs.javax.dii.DIIException;
import scs.javax.dii.PropertyUtils;

/**
 * <p>Title: ScsJavax</p>
 *
 * <p>Description: egy lista ;]</p>
 *
 * <p>Copyright: Copyright (c) Csaba Sebastian 2006</p>
 *
 * <p>Company: Sebastian Co.</p>
 * @author not attributable
 * @version 1.0
 */
public class List extends ArrayList
{

  public List ()
  {
    super();
  }

  public List ( Collection c )
  {
    super( c );
  }

  public List ( int initialCapacity )
  {
    super( initialCapacity );
  }

  public List sort ( String propertyName )
  {
    List temp = ( List ) clone();
    Comparator c = new PropertyComparator( propertyName );
    Collections.sort( temp, c );
    return temp;
  }


  public int findItemIndex ( String propertyName, Object propertyValue ) throws DIIException
  {
    for ( int i = 0; i < size(); ++i ) {
      Object pVal = PropertyUtils.getProperty( get( i ), propertyName );
      if ( PropertyUtils.equals( propertyValue, pVal ) )return i;
    }
    return -1;
  }

  public Object findItem ( String propertyName, Object propertyValue ) throws DIIException
  {
    int index = findItemIndex( propertyName, propertyValue );
    return ( index == -1 ? null : get( index ) );
  }

  public boolean hasAny ( String propertyName, Object propertyValue ) throws DIIException
  {
    return ( findItemIndex( propertyName, propertyValue ) >= 0 );
  }


  public int findItemIndex ( String propertyName1, Object propertyValue1, String propertyName2, Object propertyValue2 ) throws DIIException
  {
    for ( int i = 0; i < size(); ++i ) {
      Object pVal1 = PropertyUtils.getProperty( get( i ), propertyName1 );
      Object pVal2 = PropertyUtils.getProperty( get( i ), propertyName2 );
      if ( PropertyUtils.equals( propertyValue1, pVal1 ) && PropertyUtils.equals( propertyValue2, pVal2 ) )return i;
    }
    return -1;
  }

  public Object findItem ( String propertyName1, Object propertyValue1, String propertyName2, Object propertyValue2 ) throws DIIException
  {
    int index = findItemIndex( propertyName1, propertyValue1, propertyName2, propertyValue2 );
    return ( index == -1 ? null : get( index ) );
  }

  public boolean hasAny ( String propertyName1, Object propertyValue1, String propertyName2, Object propertyValue2 ) throws DIIException
  {
    return ( findItemIndex( propertyName1, propertyValue1, propertyName2, propertyValue2 ) >= 0 );
  }


  public int findItemIndex ( Class clazz )
  {
    for ( int i = 0; i < size(); ++i ) {
      if ( clazz.isInstance( get( i ) ) )return i;
    }
    return -1;
  }

  public Object findItem ( Class clazz )
  {
    int index = findItemIndex( clazz );
    return ( index == -1 ? null : get( index ) );
  }

  public boolean hasAny ( Class clazz )
  {
    return ( findItemIndex( clazz ) >= 0 );
  }


  public void deleteAll ( String propertyName, Object propertyValue ) throws DIIException
  {
    for ( int i = 0; i < size(); ) {
      Object pVal = PropertyUtils.getProperty( get( i ), propertyName );
      if ( PropertyUtils.equals( propertyValue, pVal ) ) {
        remove( i );
      } else {
        ++i;
      }
    }
  }

  public void setAll ( String propertyName, Object propertyValue ) throws DIIException
  {
    for ( int i = 0; i < size(); ++i ) {
      PropertyUtils.setProperty( get( i ), propertyName, propertyValue );
    }
  }

  public List getSubList ( int beginIndex, int endIndex )
  {
    List result = new List( endIndex - beginIndex );
    for ( int i = beginIndex; i < endIndex && i < size(); ++i ) {
      result.add( get( i ) );
    }
    return result;
  }

  public List getSubList ( Class baseClass )
  {
    List result = new List();
    for ( int i = 0; i < size(); ++i ) {
      if ( baseClass.isInstance( get( i ) ) ) result.add( get( i ) );
    }
    return result;
  }

  public List getSubList ( String propertyName, Object propertyValue ) throws DIIException
  {
    List result = new List();
    for ( int i = 0; i < size(); ++i ) {
      Object pVal = PropertyUtils.getProperty( get( i ), propertyName );
      if ( PropertyUtils.equals( propertyValue, pVal ) ) result.add( get( i ) );
    }
    return result;
  }

}
