package scs.javax.collections;

import java.util.Iterator;

public class QueueIterator implements Iterator
{

  private List queue;

  private int index;

  public QueueIterator ( List queue )
  {
    this.queue = queue;
    this.index = 0;
  }

  public QueueIterator ()
  {
    this.queue = new List();
    this.index = 0;
  }

  public void remove ()
  {
    throw new UnsupportedOperationException();
  }

  public boolean hasNext ()
  {
    return index < queue.size();
  }

  public Object next ()
  {
    return queue.get( index++ );
  }

  public List getQueue ()
  {
    return queue;
  }

}
