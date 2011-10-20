package scs.javax.collections;

import java.util.Collection;
import java.util.Iterator;
import scs.javax.dii.ConstructorCall;
import scs.javax.dii.DIIException;
import scs.javax.dii.ValueWithStaticType;
import scs.javax.io.Serializable;

public class Interval implements Collection, Serializable
{

  private Comparable lowBound;

  private Comparable highBound;

  public Interval ( Comparable lowBound, Comparable highBound )
  {
    if ( lowBound == null )throw new IllegalArgumentException( "low bound cannot be null" );
    if ( highBound == null )throw new IllegalArgumentException( "high bound cannot be null" );
    this.lowBound = lowBound;
    this.highBound = highBound;
  }

  public boolean isEmpty ()
  {
    return lowBound.compareTo( highBound ) > 0;
  }

  public boolean contains ( Object o )
  {
    return lowBound.compareTo( o ) <= 0 && highBound.compareTo( o ) >= 0;
  }

  public boolean equals ( Object o )
  {
    if ( o instanceof Interval ) {
      Interval intv = ( Interval ) o;
      return lowBound.equals( intv.lowBound ) && highBound.equals( intv.highBound );
    } else {
      return false;
    }
  }

  public int hashCode ()
  {
    return lowBound.hashCode() + highBound.hashCode();
  }

  public boolean containsAll ( Collection c )
  {
    if ( c instanceof Interval ) {
      Interval intv = ( Interval ) c;
      if ( intv.isEmpty() )return true;
      return ( lowBound.compareTo( intv.lowBound ) <= 0
               && highBound.compareTo( intv.highBound ) >= 0 );
    } else {
      for ( Iterator it = c.iterator(); it.hasNext(); ) {
        if ( !contains( it.next() ) )return false;
      }
      return true;
    }
  }

  public int size ()
  {
    throw new UnsupportedOperationException();
  }

  public void clear ()
  {
    throw new UnsupportedOperationException();
  }

  public Object[] toArray ()
  {
    throw new UnsupportedOperationException();
  }

  public boolean add ( Object o )
  {
    throw new UnsupportedOperationException();
  }

  public boolean remove ( Object o )
  {
    throw new UnsupportedOperationException();
  }

  public boolean addAll ( Collection c )
  {
    throw new UnsupportedOperationException();
  }

  public boolean removeAll ( Collection c )
  {
    throw new UnsupportedOperationException();
  }

  public Iterator iterator ()
  {
    throw new UnsupportedOperationException();
  }

  public Object[] toArray ( Object[] a )
  {
    throw new UnsupportedOperationException();
  }

  public boolean retainAll ( Collection c )
  {
    throw new UnsupportedOperationException();
  }

  public String[] getProperties ()
  {
    return null;
  }

  public ConstructorCall getConstructorCall () throws DIIException
  {
    return new ConstructorCall( getClass(), new ValueWithStaticType[] {
                                new ValueWithStaticType( Comparable.class, lowBound ),
                                new ValueWithStaticType( Comparable.class, highBound )} );
  }

}
