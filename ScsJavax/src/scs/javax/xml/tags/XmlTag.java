package scs.javax.xml.tags;

import java.util.HashMap;
import java.util.Map;
import scs.javax.collections.List;
import scs.javax.dii.DIIException;
import scs.javax.dii.PropertyUtils;
import java.util.Iterator;

/**
 * Minden leszármazottnak az adott XML taghoz tartozó paramétereket mint
 * property-ket elérhetővé kell tenni.
 */

public abstract class XmlTag
{

  private List children;

  protected XmlTag ()
  {
    children = new List();
  }

  public abstract String getTagName ();

  public void addChild ( XmlTag item )
  {
    children.add( item );
  }

  protected XmlTag getChild ( int i )
  {
    return ( XmlTag ) children.get( i );
  }

  public int getChildrenCount ()
  {
    return children.size();
  }

  public void setParameter ( String paramName, String paramValue ) throws DIIException
  {
    PropertyUtils.setProperty( this, paramName, paramValue );
  }

  protected abstract String[] getParameterNames ();

  public Map getParameters () throws DIIException
  {
    Map result = new HashMap();
    String[] params = getParameterNames();
    if ( params != null ) {
      for ( int i = 0; i < params.length; ++i ) {
        Object paramValue = PropertyUtils.getProperty( this, params[i] );
        if ( paramValue != null ) {
          result.put( params[i], paramValue );
        }
      }
    }
    return result;
  }

  public String toString ()
  {
    try {
      StringBuffer sb = new StringBuffer( "<" );
      sb.append( getTagName() ).append( " " );
      for ( Iterator it = getParameters().entrySet().iterator(); it.hasNext(); ) {
        Map.Entry entry = ( Map.Entry ) it.next();
        sb.append( entry.getKey() ).append( "=\"" ).append( entry.getValue() ).append( "\" " );
      }
      if ( getChildrenCount() > 0 ) {
        sb.append( ">" );
        for ( int i = 0; i < getChildrenCount(); ++i ) sb.append( getChild( i ).toString() );
        sb.append( "</" ).append( getTagName() ).append( ">" );
      } else {
        sb.append( "/>" );
      }
      return sb.toString();
    }
    catch ( DIIException ex ) {
      throw new Error( ex );
    }
  }

}
