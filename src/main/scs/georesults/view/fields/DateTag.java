package scs.georesults.view.fields;

import scs.javax.collections.List;
import scs.javax.lang.Date;
import scs.javax.web.SessionTimeoutException;
import scs.javax.web.taglibs.TagBase;
import scs.georesults.common.ValueLabelPair;
import scs.georesults.common.szotar.GlobalSzotar;

public class DateTag extends CompoundFieldTagBase
{

  protected TagBase createMainTag () throws Exception
  {
    styleClass = "dateTag";
    Date date = ( Date ) value;
    tagsAfter.addChild( createIntegerField( this, "_year", date.getYear(), 4 ) );
    tagsAfter.addChild( createTextOnlyTag( this, "." ) );
    tagsAfter.addChild( createSelectField( this, "_month", Integer.toString( date.getMonth() ), getMonthList() ) );
    tagsAfter.addChild( createTextOnlyTag( this, "." ) );
    tagsAfter.addChild( createIntegerField( this, "_day", date.getDay(), 2 ) );
    return new TagBase();
  }

  private List getMonthList () throws SessionTimeoutException
  {
    GlobalSzotar globalSzotar = GlobalSzotar.getCurrentInstance( pageContext );
    List result = new List();
    for ( int i = 1; i <= 12; ++i ) {
      result.add( new ValueLabelPair( i, globalSzotar.getValue( "BS_MONTH_" + i ) ) );
    }
    return result;
  }

}
