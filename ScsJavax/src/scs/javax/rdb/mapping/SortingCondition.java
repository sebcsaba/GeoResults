package scs.javax.rdb.mapping;

public class SortingCondition
{

  private String fieldName;

  private boolean ascendent;

  public SortingCondition ( String fieldName, boolean ascendent )
  {
    this.fieldName = fieldName;
    this.ascendent = ascendent;
  }

  public boolean isAscendent ()
  {
    return ascendent;
  }

  public String getFieldName ()
  {
    return fieldName;
  }

}
