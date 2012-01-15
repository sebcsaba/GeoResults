package scs.javax.collections;

import java.util.Collection;
import java.util.Iterator;

public abstract class SelfEnlargerQueueIterator implements Iterator
{

  private QueueIterator iter;

  protected SelfEnlargerQueueIterator ()
  {
    iter = new QueueIterator();
  }

  public boolean hasNext ()
  {
    return iter.hasNext();
  }

  public Object next ()
  {
    Object item = iter.next();
    dequeElement( item );
    return item;
  }

  protected abstract void dequeElement ( Object item );

  protected void add ( Object item )
  {
    iter.getQueue().add( item );
  }

  protected void addAll ( Collection items )
  {
    iter.getQueue().addAll( items );
  }

  public void remove ()
  {
    throw new UnsupportedOperationException();
  }

}
