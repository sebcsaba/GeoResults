package scs.javax.rdb.pseudosession;

import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.dii.PropertyUtils;
import scs.javax.rdb.mapping.PrimitiveRdbCondition;

public class RdbEntityCondition
{

  protected Class clazz;

  protected List conditions;

  public RdbEntityCondition ( Class clazz )
  {
    this.clazz = clazz;
    this.conditions = new List();
  }

  public RdbEntityCondition ( Class clazz, List conditions )
  {
    this.clazz = clazz;
    this.conditions = conditions;
  }

  public void addCondition ( PrimitiveRdbCondition condition )
  {
    conditions.add( condition );
  }

  public boolean testEntity ( Object entity ) throws DIIException
  {
    if ( !clazz.isInstance( entity ) )return false;
    for ( int i = 0; i < conditions.size(); ++i ) {
      PrimitiveRdbCondition condition = ( PrimitiveRdbCondition ) conditions.get( i );
      Object propValue = PropertyUtils.getProperty( entity, condition.getField().getPropertyName() );
      if ( !PropertyUtils.equals( propValue, condition.getValue() ) )return false;
    }
    return true;
  }

  public String toString ()
  {
    return "RdbEntityQuery(" + clazz.getName() + "," + conditions + ")";
  }

  public Class getClazz ()
  {
    return clazz;
  }

}
