package scs.javax.rdb.mapping;

import scs.javax.dii.ClassUtils;
import scs.javax.dii.DIIException;

public class Composition
{

  private String compName;

  private String className;

  public Composition ( String compName, String className )
  {
    this.compName = compName;
    this.className = className;
  }

  public String getCompName ()
  {
    return compName;
  }

  public String getClassName ()
  {
    return className;
  }

  public Class getCompositionClass () throws DIIException
  {
    return ClassUtils.loadClass( className );
  }

}
