package scs.georesults.view.fields;

import scs.javax.web.taglibs.TagBase;

public abstract class OptionalTagBase extends CompoundFieldTagBase
{

  protected boolean optional;

  protected TagBase createMainTag () throws Exception
  {
    boolean exists = true;
    if ( optional ) {
      exists = ( value != null );
      tagsBefore.addChild( createOptionalCheckboxTag( this, exists ) );
    }
    return createBaseTag( this, exists );
  }

  private TagBase createOptionalCheckboxTag ( TagBase parent, boolean exists )
  {
    SimpleCheckboxTag cbTag = new SimpleCheckboxTag();
    cbTag.init( parent );
    cbTag.setName( name + "_exists" );
    cbTag.setValue( Boolean.valueOf( exists ) );
    cbTag.setOnchange( "doEnable" + getJSMode() + "(this,'" + "');" );
    return cbTag;
  }

  protected abstract String getJSMode ();

  protected abstract TagBase createBaseTag ( TagBase parent, boolean exists );

  public void setOptional ( boolean optional )
  {
    this.optional = optional;
  }

}
