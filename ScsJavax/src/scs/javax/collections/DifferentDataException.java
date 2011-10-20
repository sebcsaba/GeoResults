package scs.javax.collections;

/**
 *
 * <p>Title: FatRepair</p>
 *
 * <p>Olyan esetekben váltódik ki, ha valahol azonos objektumokra volna szükség, de különbözőket
 * talál. Például ArrayUtils.equalArrayThrows()</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: Sebastian Co.</p>
 *
 * @author Csaba Sebastian
 * @version 1.0
 */
public class DifferentDataException extends Exception
{

  public static final String DIFFERENT_DATA = "different data found";

  public static final String DIFFERENT_CLASS = "different array base class found";

  public static final String DIFFERENT_LENGTH = "different array length found";

  public static final String DIFFERENT_ARRAY = "difference in array";

  private Object data1 = null;

  private Object data2 = null;

  private Object array = null;

  public DifferentDataException ( Object data1, Object data2 )
  {
    this( DIFFERENT_DATA, data1, data2 );
  }

  public DifferentDataException ( String baseMsg, Object data1, Object data2 )
  {
    super( baseMsg + ": " + data1 + " != " + data2 );
    this.data1 = data1;
    this.data2 = data2;
  }

  public DifferentDataException ( Object array )
  {
    super( DIFFERENT_ARRAY + ": " + ArrayUtils.toString( array ) );
    this.array = array;
  }

  public Object getData1 ()
  {
    return data1;
  }

  public Object getData2 ()
  {
    return data2;
  }

  public Object getArray ()
  {
    return array;
  }

  public void setData1 ( Object data1 )
  {
    this.data1 = data1;
  }

  public void setData2 ( Object data2 )
  {
    this.data2 = data2;
  }

  public void setArray ( Object array )
  {
    this.array = array;
  }

}
