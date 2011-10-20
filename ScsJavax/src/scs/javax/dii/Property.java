package scs.javax.dii;

public abstract class Property
{

  private String name;

  protected Property ( String name )
  {
    this.name = name;
  }

  public abstract Class getType () throws DIIException;

  public abstract Object getValue ( Object base ) throws DIIException;

  public abstract void setValue ( Object base, Object value ) throws DIIException;

  public String getName ()
  {
    return name;
  }

}
