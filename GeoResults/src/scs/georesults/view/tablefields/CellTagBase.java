package scs.georesults.view.tablefields;

import scs.javax.dii.PropertyUtils;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.view.fields.CompoundFieldTagBase;
import scs.georesults.view.fields.SimpleFieldTag;

public abstract class CellTagBase extends CompoundFieldTagBase
{

  protected String property;

  protected String name;

  protected TagBase createMainTag () throws Exception
  {
    InputTableTag itt = ( InputTableTag ) getParent().getParent();
    SimpleFieldTag base = createBase();
    base.init( this );
    base.setValue( PropertyUtils.getProperty( itt.getCurrentEntity(), property ) );
    base.setName( name + "_" + itt.getRowIndex() );
    return base;
  }

  protected abstract SimpleFieldTag createBase ();

  public void setName ( String name )
  {
    this.name = name;
  }

  public void setProperty ( String property )
  {
    this.property = property;
  }

}
