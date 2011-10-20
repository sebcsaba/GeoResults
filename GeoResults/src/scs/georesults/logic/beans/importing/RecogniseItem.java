package scs.georesults.logic.beans.importing;

import scs.javax.collections.List;

public class RecogniseItem
{

  private String label;

  private Class type;

  private long id;

  private List options; // List( ValueLabelPair )

  private Long defaultValue;

  public RecogniseItem ( String label, Class type, long id, List options, Long defaultValue )
  {
    this.label = label;
    this.type = type;
    this.id = id;
    this.options = options;
    this.defaultValue = defaultValue;
  }

  public String getTypeId ()
  {
    return type.getName() + "_" + id;
  }

  public Long getDefaultValue ()
  {
    return defaultValue;
  }

  public String getLabel ()
  {
    return label;
  }

  public List getOptions ()
  {
    return options;
  }

  public Class getType ()
  {
    return type;
  }

  public long getId ()
  {
    return id;
  }

}
