package scs.georesults.view.fields;

import scs.javax.lang.Time;
import scs.javax.web.taglibs.TagBase;

public class TimeTag extends CompoundFieldTagBase
{

  protected TagBase createMainTag () throws Exception
  {
    styleClass = "timeTag";
    Time time = ( Time ) value;
    tagsAfter.addChild( createIntegerField( this, "_hour", time.getHour(), 2 ) );
    tagsAfter.addChild( createTextOnlyTag( this, ":" ) );
    tagsAfter.addChild( createIntegerField( this, "_min", time.getMinute(), 2 ) );
    return new TagBase();
  }

}
