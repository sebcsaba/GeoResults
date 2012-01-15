package scs.javax.collections;

import java.lang.reflect.Array;
import java.util.List;

/**
 *
 * <p>Title: FatRepair</p>
 *
 * <p>Kiegészítő függvények, amelyek hiányoznak a java.util.Arrays osztályból</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: Sebastian Co.</p>
 *
 * @author Csaba Sebastian
 * @version 1.0
 */
public class ArrayUtils
{

  private ArrayUtils ()
  {}

  /**
   * A java.lang.System.arraycopy(...) metódust csonagolja be azért, hogy logikusabb helyről
   * lehessen hívni...
   */
  public static void arraycopy ( Object src, int srcPos, Object dest, int destPos, int length )
  {
    java.lang.System.arraycopy( src, srcPos, dest, destPos, length );
  }

  /**
   * Ez pedig a java.util.Arrays.asList(...) wrappere, hogy minden egy helyen legyen.
   */
  public static List asList ( Object[] array )
  {
    return java.util.Arrays.asList( array );
  }

  /**
   * Ez pedig a java.util.Arrays.sort(Object[]) wrappere, hogy minden egy helyen legyen.
   */
  public static void sort ( Object[] array )
  {
    java.util.Arrays.sort( array );
  }

  public static String toString ( Object array )
  {
    if ( array == null ) {
      return "null";
    } else {
      int length = Array.getLength( array );
      if ( length > 0 ) {
        StringBuffer sb = new StringBuffer();
        for ( int i = 0; i < length; ++i ) {
          sb.append( ";" ).append( Array.get( array, i ) );
        }
        return sb.append( "]" ).replace( 0, 1, "[" ).toString();
      } else {
        return "[]";
      }
    }
  }

  /**
   * Egy tömb elemeit hasonlítja össze. Az elemek összehasonlítására a deepEquals() függvényt
   * használja.
   * @param arr A vizsgálandó tömb
   * @return boolean Igaz, ha a tömb összes eleme azonos
   */
  public static boolean equalArray ( Object arr )
  {
    try {
      equalArrayThrows( arr );
      return true;
    }
    catch ( DifferentDataException ex ) {
      return false;
    }
  }

  /**
   * Hasonló a equalArray() függvényhez, de hamis visszatérési érték helyett
   * DifferentDataException kivételt dob.
   * @param arr A vizsgálandó tömb
   * @throws DifferentDataException Ha különbözik a tömb két eleme
   */
  public static void equalArrayThrows ( Object arr ) throws DifferentDataException
  {
    if ( !arr.getClass().isArray() ) {
      throw new IllegalArgumentException( "not array" );
    }
    Object base = Array.get( arr, 0 );
    for ( int i = 1; i < Array.getLength( arr ); ++i ) {
      try {
        deepEqualsThrows( base, Array.get( arr, i ) );
      }
      catch ( DifferentDataException ex ) {
        throw new DifferentDataException( arr );
      }
    }
  }

  /**
   * Két objektum-struktúrát hasonlít össze. Ha a részstruktúrában tömböket talál,
   * akkor azokat a deepArrayEqualsThrows() függvényen keresztül, különben az equals()
   * függvényen keresztül. Rekurzív szerkezeteket nem kezel.
   * @param a Az egyik objektum
   * @param b A másik objektum
   * @return boolean Igaz, ha a két tömb megegyezik.
   */
  public static boolean deepEquals ( Object a, Object b )
  {
    try {
      deepEqualsThrows( a, b );
      return true;
    }
    catch ( DifferentDataException ex ) {
      return false;
    }
  }

  /**
   * Hasonló a deepEquals() függvényhez, de hamis visszatérési érték helyett
   * DifferentDataException kivételt dob.
   * @param a Az egyik objektum
   * @param b A másik objektum
   * @throws DifferentDataException Ha különbözik a két objektum
   */
  public static void deepEqualsThrows ( Object a, Object b ) throws DifferentDataException
  {
    if ( a == null || b == null ) {
      throw new DifferentDataException( a, b );
    }
    if ( a == b ) {
      return;
    }
    boolean arrayA = a.getClass().isArray();
    boolean arrayB = b.getClass().isArray();
    if ( arrayA != arrayB ) {
      throw new DifferentDataException( DifferentDataException.DIFFERENT_CLASS, a, b );
    }
    if ( !arrayA ) {
      if ( a.equals( b ) ) {
        return;
      } else {
        throw new DifferentDataException( a, b );
      }
    }
    deepArrayEqualsThrows( a, b );
  }

  /**
   * Hasonló a java.util.Arrays.equals(Object[],Object[]) metódusához, de itt a tömbök elemeit
   * nem equals() függvényen keresztül hasonlítjuk össze, hanem a deepEquals függvényen keresztül.
   * @param a Az első tömb (bármilyen elemtípussal)
   * @param b Az első tömb (a-val megegyező elemtípussal)
   * @return boolean Igaz, ha a két tömb megegyezik.
   */
  public static boolean deepArrayEquals ( Object a, Object b )
  {
    try {
      deepArrayEqualsThrows( a, b );
      return true;
    }
    catch ( DifferentDataException ex ) {
      return false;
    }
  }

  /**
   * Hasonló a deepArrayEquals() függvényhez, de hamis visszatérési érték helyett
   * DifferentDataException kivételt dob.
   * @param a Az első tömb (bármilyen elemtípussal)
   * @param b Az első tömb (a-val megegyező elemtípussal)
   * @throws DifferentDataException Ha különbözik a két tömb
   */
  public static void deepArrayEqualsThrows ( Object a, Object b ) throws DifferentDataException
  {
    if ( !a.getClass().isArray() ) {
      throw new IllegalArgumentException( "a is not array" );
    }
    if ( !b.getClass().isArray() ) {
      throw new IllegalArgumentException( "b is not array" );
    }
    if ( a.getClass().getComponentType() != b.getClass().getComponentType() ) {
      throw new DifferentDataException( DifferentDataException.DIFFERENT_CLASS,
                                        a.getClass().getComponentType(),
                                        b.getClass().getComponentType() );
    }
    int length = Array.getLength( a );
    if ( length != Array.getLength( b ) ) {
      throw new DifferentDataException( DifferentDataException.DIFFERENT_LENGTH,
                                        Integer.toString( length ),
                                        Integer.toString( Array.getLength( b ) ) );
    }
    for ( int i = 0; i < length; ++i ) {
      deepEqualsThrows( Array.get( a, i ), Array.get( b, i ) );
    }
  }

}
