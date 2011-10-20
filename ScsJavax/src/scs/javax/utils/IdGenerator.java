package scs.javax.utils;

public class IdGenerator
{

  private long id;

  public IdGenerator ()
  {
    this.id = 0;
  }

  public long getLong ()
  {
    return++id;
  }

  public String get ( String prefix )
  {
    return prefix + getLong();
  }

  public String get ()
  {
    return get( "id_" );
  }

}
