package scs.javax.xml.objects;

import scs.javax.dii.ConstructorCall;
import scs.javax.dii.DIIException;

public interface Serializable
{

  /**
   * minden olyan property neve, amelyet tárolni kell.
   */
  public String[] getProperties ();

  /**
   * A konstruktornak átadandó paraméterek. Ha null, akkor a value attribútum
   * alapján lesz inicializálva (egy String paraméterrel).
   * Ha nulla hosszú tömböt tartalmaz, akkor paraméter nélküli konstruktorral.
   */
  public ConstructorCall getConstructorCall () throws DIIException;

}
