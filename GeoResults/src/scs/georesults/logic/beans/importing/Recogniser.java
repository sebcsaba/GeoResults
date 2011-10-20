package scs.georesults.logic.beans.importing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.rdb.RdbImportSession;

public class Recogniser
{

  private Map remap; // Map( Class -> Map( Long[oldId] -> Long[newId] ) )

  private List items; // List( RecogniseItem )

  public Recogniser ( List items )
  {
    this.items = items;
    this.remap = new HashMap();
  }

  public void addRemap ( Class clazz, long oldId, long newId )
  {
    getClassRemaps( clazz ).put( new Long( oldId ), new Long( newId ) );
  }

  public void putTypeToSession ( Class clazz, RdbImportSession importSession ) throws DIIException
  {
    for ( Iterator it = getClassRemaps( clazz ).entrySet().iterator(); it.hasNext(); ) {
      Map.Entry entry = ( Map.Entry ) it.next();
      long oldId = ( ( Long ) entry.getKey() ).longValue();
      long newId = ( ( Long ) entry.getValue() ).longValue();
      importSession.addRemap( clazz, oldId, newId );
    }
  }

  private Map getClassRemaps ( Class clazz )
  {
    Map classRemaps = ( Map ) remap.get( clazz );
    if ( classRemaps == null ) {
      classRemaps = new HashMap();
      remap.put( clazz, classRemaps );
    }
    return classRemaps;
  }

  public List getItems ()
  {
    return items;
  }

}
